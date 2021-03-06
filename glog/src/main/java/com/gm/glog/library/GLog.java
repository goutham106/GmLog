/*
 * Copyright (c) 2016 Gowtham Parimalazhagan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.gm.glog.library;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.gm.glog.library.format.LogFormat;
import com.gm.glog.library.log.BaseLog;
import com.gm.glog.library.log.FileLog;
import com.gm.glog.library.log.JsonLog;
import com.gm.glog.library.log.XmlLog;

import java.io.File;


/**
 * Name       : Gowtham
 * Created on : 10/12/16.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */

public class GLog {

    private static final String TAG = "GmLog";
    public static final String DEFAULT_MESSAGE = "execute";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String NULL_TIPS = "Log with null object";
    public static final String PARAM = "Param";
    public static final String NULL = "null";
    public static final String TAG_DEFAULT = "GLog";
    public static final String SUFFIX = ".java";
    private static final int EXPIRY_FILE_SIZE = 1;// In MB
    private static LogFormat mLogFormat;
    private static Context context;
    private static int logLevel = Log.WARN;

    public static final int JSON_INDENT = 4;

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;
    public static final int JSON = 0x7;
    public static final int XML = 0x8;

    private static boolean IS_SHOW_LOG = true;
    private static boolean IS_SAVING_DB = true;
    private static final int STACK_TRACE_INDEX = 5;
    private static int FILE_SIZE_TO_EXPIRY;

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void init(Context context, boolean isShowLog, boolean isSaveDb) {
        initialize(context, isShowLog, isSaveDb, EXPIRY_FILE_SIZE, new LogFormat(context));
    }

    public static void init(Context context, boolean isShowLog, boolean isSaveDb, int expiryFileSize) {
        initialize(context, isShowLog, isSaveDb, expiryFileSize, new LogFormat(context));
    }

    public static void init(Context context, boolean isShowLog, boolean isSaveDb, int expiryFileSize, LogFormat logFormat) {
        initialize(context, isShowLog, isSaveDb, expiryFileSize, logFormat);
    }

    public static void initialize(Context context, boolean isShowLog, boolean isSaveDb, int expiryFileSize, LogFormat logFormat) {

        if (context == null) {
            Log.e(TAG, "GLog isn't initialized: Context couldn't be null");
            return;
        }

        GLog.context = context.getApplicationContext();

        synchronized (GLog.class) {

            if (logFormat != null) {
                mLogFormat = logFormat;
                Util.saveLogFormat(context, mLogFormat);
            } else {
                mLogFormat = Util.getLogFormat(context);
            }

            IS_SHOW_LOG = isShowLog;
            IS_SAVING_DB = isSaveDb;
            FILE_SIZE_TO_EXPIRY = expiryFileSize;
        }
    }

    /**
     * Sets the level of logging to display, where each level includes all those below it.
     * The default level is LOG_LEVEL_NONE. Please ensure this is set to Log#ERROR
     * or LOG_LEVEL_NONE before deploying your app to ensure no sensitive information is
     * logged. The levels are:
     * <ul>
     * <li>{@link Log#ASSERT}</li>
     * <li>{@link Log#VERBOSE}</li>
     * <li>{@link Log#DEBUG}</li>
     * <li>{@link Log#INFO}</li>
     * <li>{@link Log#WARN}</li>
     * <li>{@link Log#ERROR}</li>
     * </ul>
     *
     * @param logLevel The level of logcat logging that Parse should do.
     */
    public static void setLogLevel(int logLevel) {
        GLog.logLevel = logLevel;
    }

    /**
     * Call this method to define a custom log message format.
     *
     * @param logFormat LogFormat to set custom log message format.
     */
    public static void setLogFormat(LogFormat logFormat) {
        if (mLogFormat != null) {
            mLogFormat = logFormat;
            Util.saveLogFormat(context, logFormat);
        }
    }

    public static void v() {
        printLog(V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(V, tag, objects);
    }

    public static void d() {
        printLog(D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(D, tag, objects);
    }

    public static void i() {
        printLog(I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(I, tag, objects);
    }

    public static void w() {
        printLog(W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(W, tag, objects);
    }

    public static void e() {
        printLog(E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(E, tag, objects);
    }

    public static void a() {
        printLog(A, null, DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printLog(A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        printLog(A, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(JSON, tag, jsonFormat);
    }

    public static void xml(String xml) {
        printLog(XML, null, xml);
    }

    public static void xml(String tag, String xml) {
        printLog(XML, tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        printFile(tag, targetDirectory, fileName, msg);
    }

    private static void printLog(int type, String tagStr, Object... objects) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                BaseLog.printDefault(type, tag, headString + msg);
                break;
            case JSON:
                JsonLog.printJson(tag, msg, headString);
                break;
            case XML:
                XmlLog.printXml(tag, msg, headString);
                break;
        }
    }


    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {

        if (!IS_SHOW_LOG && !IS_SAVING_DB) {
            return;
        }

        String[] contents = wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

//        FileLog.printFile(tag, targetDirectory, fileName, headString, msg);

        FileLog.printFile(tag, targetDirectory, fileName, headString, getFormattedLog(logLevel, tag, msg), FILE_SIZE_TO_EXPIRY);
    }

    private static String[] wrapperContent(String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        String tag = (tagStr == null ? className : tagStr);
        if (TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT;
        }
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";

        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

    private static boolean isInitialize() {
        if (mLogFormat == null) {
            init(context, true, false, EXPIRY_FILE_SIZE, null);
            return false;
        }
        return true;
    }

    private static String getFormattedLog(int logLevel, String tag, String message) {
        if (isInitialize()) {
            return mLogFormat.formatLogMessage(logLevel, tag, message);
        }
        return null;
    }

}
