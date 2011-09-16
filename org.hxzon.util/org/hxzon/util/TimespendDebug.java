package org.hxzon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimespendDebug {
    public static boolean open = true;
    public static int timeout = 5;
    private static final List<String> classNames = new ArrayList<String>();
    static {
    }

    public static void clearCalss() {
        classNames.clear();
    }

    public static void addClass(Class clazz) {
        classNames.add(clazz.getName());
    }

    private static final Map<String, Long> times = new HashMap<String, Long>();

    public static void start(String name) {
        times.put(name, System.currentTimeMillis());
    }

    public static void end(String name) {
        long end = System.currentTimeMillis();
        long spend = end - times.get(name);
        if (spend < timeout) {
            output(false, name + " use time " + spend + " ms");
        } else {
            output(true, name + " use time " + spend + " ms");
        }
        times.remove(name);
    }

    private static void output(boolean slow, String className, String methodName, int lineNumber, String message) {
        if (!classNames.contains(className)) {
            return;
        }
        className = className.substring(className.lastIndexOf('.') + 1);
        String output = (className + "." + methodName + "(" + lineNumber + ")" + ":" + message);
        if (slow) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

    private static void output(boolean slow, String message) {
        if (open) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
            String className = ste.getClassName();
            String methodName = ste.getMethodName();
            int lineNumber = ste.getLineNumber();
            output(slow, className, methodName, lineNumber, message);
        }
    }

}
