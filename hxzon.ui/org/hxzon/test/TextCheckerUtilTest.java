package org.hxzon.test;

import org.hxzon.util.TextValidationList;
import org.hxzon.util.ValidationManager;
import org.hxzon.util.ValidationManager.ValidationListener;

public class TextCheckerUtilTest {
    public static void main(String[] args) {
        ValidationListener listener=new ValidationListener(){
            
            @Override
            public void stateChanged(ValidationManager vm) {
                System.out.println(vm.getErrorMessage());
            }
            
        };
        ValidationManager.getManager("hello").addValidationListener(listener);
        TextValidationList c = new TextValidationList();
        c.addManager("hello");
        c.addChecker("notNull", "typeError=[:fn]不能为空");

        c.check("", "用户名");
         c.addChecker("email", "typeError=[:fn]不是邮件格式");
         c.check("a@163.com", "邮件地址");
         c.check("@163.com", "备用邮件地址");
        
         c.removeChecker("email");
         c.check(null, "生日");
         c.removeChecker("notNull");
         c.check(null, "身份证");
        
         c.addChecker("notNull", "typeError=不能为空(不共享)");
         c.addChecker("length", "min=8;max=8;typeError=[:fn]长度不对(:min,:max)");
         c.check("","不共享notNull");
         c.check("12345678", "密码");
         c.check("1234567", "备用密码");
        
         c.removeChecker("length");
         c.addChecker("int", "typeError=不是整数");
         c.check("11", "鹅蛋价格");
         c.check("12.5", "鸡蛋价格");
         c.removeChecker("int");
         c.addChecker("double",
         "min=12.5;typeError=不是浮点数;rangeError=需大于:max或小于:min");
         c.check("11.5", "价格");
        
//         c.clearErrorMessage();
         c.clearChecker();
         c.addChecker("date",
         "pattern=yyyyMMdd;min=19851230;typeError=时间格式错误;rangeError=时间范围不对");
         c.check("19851229", "出生日期");
         c.removeChecker("date");
         c.addChecker("time",
         ";min=12:30:45;typeError=时间格式错误;rangeError=时间范围不对");
         c.check("12:30:44", "出生时间");
//        for (String s : c.getErrorMessage()) {
//            System.out.println(s);
//        }

    }

}
