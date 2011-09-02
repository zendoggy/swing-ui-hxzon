package org.hxzon.util;

import java.util.ArrayList;
import java.util.List;

public class DebugUtil {
    public static boolean open = false;
    public static final int error = 1;
    public static final int info = 2;
    public static final int debug = 3;
    public static final int trace = 4;
    private static final List<String> classNames = new ArrayList<String>();
    static {

    }

    private static void output(int type, String className, String methodName, int lineNumber, String message) {
        if (!classNames.contains(className)) {
            return;
        }
        String level = "";
        switch (type) {
        case 1:
            level = "ERROR";
            break;
        case 2:
            level = "INFO";
            break;
        case 3:
            level = "DEBUG";
            break;
        case 4:
            level = "TRACE";
            break;
        default:
        }
        className = className.substring(className.lastIndexOf('.') + 1);
        String output = (level + ":" + className + "." + methodName + "(" + lineNumber + ")" + ":" + message);
        if (type == error) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

    private static void output(int type, String message) {
        if (open) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getClassName();
            String methodName = ste.getMethodName();
            int lineNumber = ste.getLineNumber();
            output(type, className, methodName, lineNumber, message);
        }
    }

    public static void error(String message) {
        output(error, message);
    }

    public static void error(String message, Throwable e) {

    }

    public static void debug(String message) {
        output(debug, message);
    }

    public static void trace(String message) {
        output(trace, message);
    }

    public static void info(String message) {
        output(info, message);
    }

}