package com.gm.gmlog;

import android.content.Context;

import com.gm.glog.library.format.LogFormat;

/**
 * Created by Gowtham on 07/05/18.
 * Copyright Indyzen Inc, 2018.
 */
public class CustomLogFormat extends LogFormat {
    public CustomLogFormat(Context context) {
        super(context);
    }

    @Override
    public String getFormattedLogMessage(String logLevelName, String tag, String message, String timeStamp,
                                         String senderName, String osVersion, String deviceUUID) {
        String formattedMessage = timeStamp + " : " + logLevelName + "/" + tag + " : " + deviceUUID + " : " + message;
        return formattedMessage;
    }

}
