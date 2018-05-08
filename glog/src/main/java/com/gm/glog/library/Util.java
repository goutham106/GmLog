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
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.gm.glog.library.format.LogFormat;
import com.google.gson.GsonBuilder;

/**
 * Name       : Gowtham
 * Created on : 10/12/16.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */

public class Util {

    private static final String HL_SHARED_PREFS_KEY = "com.gm.glog:SharedPreference";
    private static final String HL_LOG_FORMAT_KEY = "com.gm.glog:LogFormat";

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void saveLogFormat(Context context, LogFormat logFormat) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor(context);
        editor.putString(HL_LOG_FORMAT_KEY, new GsonBuilder().create().toJson(logFormat));
        editor.apply();
    }

    public static LogFormat getLogFormat(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String json = sharedPreferences.getString(HL_LOG_FORMAT_KEY, null);

        if (json == null)
            return new LogFormat(context);

        LogFormat logFormat = new GsonBuilder().create().fromJson(json, LogFormat.class);

        if (logFormat == null)
            return new LogFormat(context);
        else
            return logFormat;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(HL_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getSharedPreferencesEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

}
