package com.gm.gmlog;

import android.app.Application;
import android.util.Log;

import com.gm.glog.library.GLog;

/**
 * Created by Gowtham on 10/2/17.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        final int EXPIRY_SIZE = 2;// 2MB
        GLog.init(this, true, true, EXPIRY_SIZE, new CustomLogFormat(this));
        GLog.setLogLevel(Log.VERBOSE);
    }
}
