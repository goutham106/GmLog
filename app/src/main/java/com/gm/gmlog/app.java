package com.gm.gmlog;

import android.app.Application;

import com.gm.glog.library.GLog;

/**
 * Created by Gowtham on 10/2/17.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GLog.init(true);
    }
}
