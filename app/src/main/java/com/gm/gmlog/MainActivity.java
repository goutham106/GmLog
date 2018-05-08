package com.gm.gmlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gm.glog.library.GLog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLog.i(TAG, "Hi I am (i)");
        GLog.a(TAG, "Hi I am (a)");
        GLog.d(TAG, "Hi I am (d)");
        GLog.e(TAG, "Hi I am (e)");
        GLog.v(TAG, "Hi I am (v)");
        GLog.w(TAG, "Hi I am (w)");
        GLog.xml(TAG, "\n" +
                "<note>\n" +
                "  <to>GLog</to>\n" +
                "  <from>Goutham</from>\n" +
                "  <heading>Reminder</heading>\n" +
                "  <body>Don't forget me this weekend!</body>\n" +
                "</note>\n");
        GLog.json(TAG, "{ \"name\":\"John\", \"age\":31, \"city\":\"New York\" }");
        GLog.file(TAG, getApplicationContext().getCacheDir(), "TestLogFile.txt", "This is a message from GLog");
    }
}
