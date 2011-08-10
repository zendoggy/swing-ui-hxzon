package org.hxzon.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidationList {
    private static Map<String, Class<? extends Checker>> checkersClass = new HashMap<String, Class<? extends Checker>>();
    private static Map<String,Checker> checkers=new HashMap<String,Checker>();
    private static Map<String,List<Checker>> lists=new HashMap<String,List<Checker>>();
    
    private List<Checker> checkerList = new ArrayList<Checker>();
    private List<String> managers=new ArrayList<String>();
    
    static{
        registerDefaultChecker();
    }
    public static TextValidationList newList(String name){
        lists.put(name, new ArrayList<Checker>());
        return new TextValidationList(lists.get(name));
    }
    
    public static TextValidationList getList(String name){
        return new TextValidationList(lists.get(name));
    }
    //
    public TextValidationList(){
    }
    
    private TextValidationList(List<Checker> checkerList){
        this.checkerList=checkerList;
    }

    public void check(String value, String fieldName) {
        try {
            for (int i = 0; i < checkerList.size(); i++) {
                checkerList.get(i).valid(value);
            }
        } catch (CheckerException e) {
            for (String name : managers) {
                ValidationManager.getManager(name).set(fieldName, false, e.getMessage());
            }
            return;
        }
        for (String name : managers) {
            ValidationManager.getManager(name).set(fieldName, true, "");
        }
    }
    
    public String trimValue(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    public TextValidationList addChecker(Checker... checkers) {
        for (Checker checker : checkers) {
            addChecker(checker);
        }
        return this;
    }

    public TextValidationList addChecker(Checker checker) {
        if (checker != null) {
            checkerList.remove(checker);
            checkerList.add(checker);
        }
        return this;
    }

    public TextValidationList addChecker(String name) {
        addChecker(name, null);
        return this;
    }

    public TextValidationList addChecker(String name, String constraints) {
        Checker checker = null;
        if(constraints ==null || constraints.isEmpty()){
            checker=checkers.get(name);
        }
        else {
            try {
                Class clazz = checkersClass.get(name);
                checker = (Checker) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return this;
            }
        }
        if (checker != null) {
            if (constraints != null && !constraints.isEmpty()) {
                String[] split = constraints.split(";");
                checker.setConstraints(split);
            }
            checkerList.remove(checker);
            checkerList.add(checker);
        }
        return this;
    }

    public TextValidationList removeChecker(Checker... checkers) {
        for (Checker checker : checkers) {
            if (checker != null) {
                checkerList.remove(checker);
            }
        }
        return this;
    }

    public TextValidationList removeChecker(String checkerName) {
        Class clazz = checkersClass.get(checkerName);
        for (int i = 0; i < checkerList.size(); i++) {
            if (checkerList.get(i).getClass().equals(clazz)) {
                checkerList.remove(i);
            }
        }
        return this;
    }

    public TextValidationList clearChecker() {
        checkerList.clear();
        return this;
    }
    
    public TextValidationList addManager(String name){
        managers.add(name);
        return this;
    }
    
    public TextValidationList removeManager(String name){
        managers.remove(name);
        return this;
    }
    
    public TextValidationList clearManager(String name){
        managers.clear();
        return this;
    }
    
    public static void registerChecker(String name,Checker checker){
        checkers.put(name, checker);
        checkersClass.put(name, checker.getClass());
    }
    
    public static void unregisterChecker(String name){
        checkers.remove(name);
        checkersClass.remove(name);
    }

    public static void registerDefaultChecker(){
        registerChecker("notNull", new NotNullChecker());
        registerChecker("length", new LengthChecker());
        registerChecker("int", new IntegerChecker());
        registerChecker("double", new DecimalChecker());
        registerChecker("pattern", new PatternChecker());
        registerChecker("email", new EMailChecker());
        registerChecker("date", new DateChecker());
        registerChecker("time", new TimeChecker());
        registerChecker("datetime", new DateTimeChecker());
        
    }
    
       

    public Checker getChecker(String name) {
        return null;//checkerMap.get(name);
    }

    //CheckerException
    public static class CheckerException extends RuntimeException{
        public CheckerException(String message){
            super(message);
        }
    }
    //Checker
    public static abstract class Checker {
        private Map<String, String> properties = new HashMap<String, String>();

        public abstract void valid(String value);

        public void setConstraints(String[] constraints) {
            properties.clear();
            if (constraints == null) {
                return;
            }
            for (String constraint : constraints) {
                String[] split = constraint.split("=");
                if (split.length < 2) {
                    // 
                } else if (split[1].isEmpty() || "null".equals(split[1])) {
                    //
                } else {
                    if ("int.min".equals(split[1])) {
                        split[1] = String.valueOf(Integer.MIN_VALUE);
                    } else if ("int.max".equals(split[1])) {
                        split[1] = String.valueOf(Integer.MAX_VALUE);
                    } else if ("double.min".equals(split[1])) {
                        split[1] = String.valueOf(Double.MIN_VALUE);
                    } else if ("double.max".equals(split[1])) {
                        split[1] = String.valueOf(Double.MAX_VALUE);
                    }
                    properties.put(split[0], split[1]);
                }
            }
            updateProperties();
        }

        protected void updateProperties() {
        };

        protected String getNullBeEmpty(String property) {
            String value = properties.get(property);
            if (value == null) {
                value = "";
            }
            return value;
        }

        protected String fireError(String error) {
            return getNullBeEmpty(error);
        }

        public void setConstraint(String property, String value) {
            if (property != null) {
                properties.put(property, value);
            }
            updateProperties();
        }

    }

    public static class NotNullChecker extends LengthChecker {

        public void valid(String value) {
            setConstraint("min", "1");
            super.valid(value);
        }

    }

    public static class LengthChecker extends Checker {
        private int min = -1;
        private int max = Integer.MAX_VALUE;

        public void valid(String value) {
            if (value == null) {
                value = "";
            }
            if (value.length() < min || value.length() > max) {
                throw new CheckerException(fireError("typeError"));
            }
        }

        protected void updateProperties() {
            try {
                min = Integer.parseInt(getNullBeEmpty("min"));
            } catch (NumberFormatException e) {
                min = -1;
            }
            try {
                max = Integer.parseInt(getNullBeEmpty("max"));
            } catch (NumberFormatException e) {
                max = Integer.MAX_VALUE;
            }
        }
    }

    public static class IntegerChecker extends Checker {
        private BigInteger min = null;
        private BigInteger max = null;

        public void valid(String value) {
            if (value == null || value.isEmpty()) {
                return ;
            }
            BigInteger intValue = null;
            try {
                intValue = new BigInteger(value);
            } catch (NumberFormatException e) {
                throw new CheckerException(fireError("typeError"));
            }
            if (min != null && intValue.compareTo(min) == -1) {
                throw new CheckerException(fireError("rangeError"));
            }
            if (max != null && intValue.compareTo(max) == 1) {
                throw new CheckerException(fireError("rangeError"));
            }
        }

        protected void updateProperties() {
            try {
                min = new BigInteger(getNullBeEmpty("min"));
            } catch (NumberFormatException e) {
                min = null;
            }
            try {
                max = new BigInteger(getNullBeEmpty("max"));
            } catch (NumberFormatException e) {
                max = null;
            }
        }
    }

    public static class DecimalChecker extends Checker {
        private BigDecimal min = null;
        private BigDecimal max = null;

        public void valid(String value) {
            if (value == null || value.isEmpty()) {
                return ;
            }
            BigDecimal intValue = null;
            try {
                intValue = new BigDecimal(value);
            } catch (NumberFormatException e) {
                throw new CheckerException(fireError("typeError"));
            }
            if (min != null && intValue.compareTo(min) == -1) {
                throw new CheckerException(fireError("rangeError"));
            }
            if (max != null && intValue.compareTo(max) == 1) {
                throw new CheckerException(fireError("rangeError"));
            }
        }

        protected void updateProperties() {
            try {
                min = new BigDecimal(getNullBeEmpty("min"));
            } catch (NumberFormatException e) {
                min = null;
            }
            try {
                max = new BigDecimal(getNullBeEmpty("max"));
            } catch (NumberFormatException e) {
                max = null;
            }
        }
    }

    public static class DateTimeChecker extends Checker {
        protected SimpleDateFormat format = new SimpleDateFormat();
        protected Date min = null;
        protected Date max = null;

        public void valid(String value) {
            if (value == null || value.isEmpty()) {
                return ;
            }
            Date date = null;
            try {
                date = format.parse(value);
                // System.out.println(date);
            } catch (ParseException e) {
                throw new CheckerException(fireError("typeError"));
            }
            if (min != null && date.before(min)) {
                throw new CheckerException(fireError("rangeError"));
            }
            if (max != null && date.after(max)) {
                throw new CheckerException(fireError("rangeError"));
            }
        }

        protected void updateProperties() {
            String pattern = getNullBeEmpty("pattern");
            if (pattern.isEmpty()) {
                pattern = "yyyy-MM-dd,hh:mm:ss";
            }
            format.applyPattern(pattern);
            try {
                min = format.parse(getNullBeEmpty("min"));
                // System.out.println(min);
            } catch (ParseException e) {
                min = null;
            }
            try {
                max = format.parse(getNullBeEmpty("max"));
                // System.out.println(max);
            } catch (ParseException e) {
                max = null;
            }
        }

    }

    public static class DateChecker extends DateTimeChecker {

        protected void updateProperties() {
            if (getNullBeEmpty("pattern").isEmpty()) {
                setConstraint("pattern", "yyyy-MM-dd");
            }
        }

    }

    public static class TimeChecker extends DateTimeChecker {

        protected void updateProperties() {
            if (getNullBeEmpty("pattern").isEmpty()) {
                setConstraint("pattern", "hh:mm:ss");
            }
        }

    }

    public static class PatternChecker extends Checker {

        public void valid(String value) {
            if (value == null || value.isEmpty()) {
                return ;
            }
            Pattern pattern = null;
            if (getNullBeEmpty("caseInsensitive").isEmpty()) {
                pattern = Pattern.compile(getNullBeEmpty("pattern"));
            } else {
                pattern = Pattern.compile(getNullBeEmpty("pattern"), Pattern.CASE_INSENSITIVE);
            }
            Matcher m = pattern.matcher(value);
            if (!m.matches()) {
                throw new CheckerException(fireError("typeError"));
            }
        }

    }

    public static class EMailChecker extends PatternChecker {
        // TODO: Implement this
        // http://www.ex-parrot.com/~pdw/Mail-RFC822-Address.html regex in java
        private static String ATOM = "[^\\x00-\\x1F^\\(^\\)^\\<^\\>^\\@^\\,^\\;^\\:^\\\\^\\\"^\\.^\\[^\\]^\\s]";
        private static String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
        private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

        public void valid(String value) {
            String mailPattern = "^" + ATOM + "+(\\." + ATOM + "+)*@" + DOMAIN + "|" + IP_DOMAIN + ")$";
            setConstraint("pattern", mailPattern);
            setConstraint("caseInsensitive", "caseInsensitive");
            super.valid(value);
        }

    }

}
