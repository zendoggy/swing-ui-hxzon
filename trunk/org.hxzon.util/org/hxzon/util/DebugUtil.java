package org.hxzon.util;

public class DebugUtil {
	private static final int error = 1;
	private static final int info = 2;
	private static final int debug = 3;
	private static final int trace = 4;

	private static void output(int type, String className, String methodName, int lineNumber, String message) {
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

	public static void error(String message) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int lineNumber = ste.getLineNumber();
		output(error, className, methodName, lineNumber, message);
	}

	public static void error(String message, Throwable e) {
		error(message);
		e.printStackTrace();
	}

	public static void debug(String message) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int lineNumber = ste.getLineNumber();
		output(debug, className, methodName, lineNumber, message);
	}

	public static void trace(String message) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int lineNumber = ste.getLineNumber();
		output(trace, className, methodName, lineNumber, message);
	}

	public static void info(String message) {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int lineNumber = ste.getLineNumber();
		output(info, className, methodName, lineNumber, message);
	}

}
