package com.gm.glog.library.format;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.gm.glog.library.BuildConfig;
import com.gm.glog.library.DateTimeUtility;

import java.io.Serializable;

/**
 * Created by Gowtham on 07/05/18.
 * Copyright Indyzen Inc, 2018.
 */
public class LogFormat implements Serializable {

    private String deviceUUID;

    public LogFormat(Context context) {
        Context mContext = context.getApplicationContext();
        deviceUUID = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Implement this method to override the default log message format.
     *
     * @param logLevel The level of logcat logging that Parse should do.
     * @param message  Log message that need to be customized.
     * @return Formatted Log Message that will store in database.
     */
    public String formatLogMessage(int logLevel, String tag, String message) {
        String timeStamp = DateTimeUtility.getCurrentTime();
        String senderName = BuildConfig.VERSION_NAME;
        String osVersion = "Android-" + Build.VERSION.RELEASE;
        String logLevelName = getLogLevelName(logLevel);
        return getFormattedLogMessage(logLevelName, tag, message, timeStamp, senderName, osVersion, deviceUUID);
    }

    public String getFormattedLogMessage(String logLevelName, String tag, String message, String timeStamp,
                                         String senderName, String osVersion, String deviceUUID) {
        if (deviceUUID == null) {
            deviceUUID = "DeviceUUID";
        }

        return timeStamp + " | " + senderName + " : " + osVersion + " | " + deviceUUID + " | " + "[" + logLevelName + "/" + tag + "]: " + message;
    }

    private static String getLogLevelName(int messageLogLevel) {

        String logLevelName;
        switch (messageLogLevel) {
            case Log.VERBOSE:
                logLevelName = "VERBOSE";
                break;
            case Log.DEBUG:
                logLevelName = "DEBUG";
                break;
            case Log.INFO:
                logLevelName = "INFO";
                break;
            case Log.WARN:
                logLevelName = "WARN";
                break;
            case Log.ERROR:
                logLevelName = "ERROR";
                break;
            case Log.ASSERT:
                logLevelName = "ASSERT";
                break;
            default:
                logLevelName = "NONE";
        }

        return logLevelName;
    }

}
