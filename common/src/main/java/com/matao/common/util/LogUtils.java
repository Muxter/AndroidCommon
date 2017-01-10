package com.matao.common.util;

import android.util.Log;

import com.matao.common.BuildConfig;

/**
 * Created by matao on 2016-12-11 16:42
 */

public class LogUtils {
    private static String className;    // 所在的类名
    private static String methodName;   // 所在的方法名
    private static int lineNumber;      // 所在行号

    public static final int VERBOSE = 1;        //显示Verbose及以上的Log
    public static final int DEBUG = 2;          //显示Debug及以上的Log
    public static final int INFO = 3;           //显示Info及以上的Log
    public static final int WARN = 4;           //显示Warn及以上的Log
    public static final int ERROR = 5;          //显示Error及以上的Log
    public static final int NOTHING = 6;        //全部不显示

    private static final int LEVEL = VERBOSE;

    private LogUtils() {
        throw new UnsupportedOperationException("LogUtil cannot be instantiated!");
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(String log) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(methodName);
        sb.append(":");
        sb.append(lineNumber);
        sb.append("] ");
        sb.append(log);
        return sb.toString();
    }


    private static void getMethodName(StackTraceElement[] stackTraceElements) {
        // stackTraceElements[0]代表的是当前LogUtil类别
        className = stackTraceElements[1].getClassName();
        methodName = stackTraceElements[1].getMethodName();
        lineNumber = stackTraceElements[1].getLineNumber();
    }

    public static void v(String message) {
        if (isDebuggable()) {
            if (LEVEL <= VERBOSE) {
                getMethodName(new Throwable().getStackTrace());
                Log.v(className, createLog(message));
            }
        }
    }

    public static void d(String message) {
        if (isDebuggable()) {
            if (LEVEL <= DEBUG) {
                getMethodName(new Throwable().getStackTrace());
                Log.d(className, createLog(message));
            }
        }
    }

    public static void i(String message) {
        if (isDebuggable()) {
            if (LEVEL <= INFO) {
                getMethodName(new Throwable().getStackTrace());
                Log.i(className, createLog(message));
            }
        }
    }


    public static void w(String message) {
        if (isDebuggable()) {
            if (LEVEL <= WARN) {
                getMethodName(new Throwable().getStackTrace());
                Log.w(className, createLog(message));
            }
        }
    }

    public static void e(String message) {
        if (isDebuggable()) {
            if (LEVEL <= ERROR) {
                getMethodName(new Throwable().getStackTrace());
                Log.e(className, createLog(message));
            }
        }
    }
}
