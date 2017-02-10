package com.gm.gmlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gm.glog.library.GLog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLog.i("hi","Hi I am (i)");
        GLog.a("hi","Hi I am (a)");
        GLog.d("hi","Hi I am (d)");
        GLog.e("hi","Hi I am (e)");
        GLog.v("hi","Hi I am (v)");
        GLog.w("hi","Hi I am (w)");
        GLog.xml("hi","\n" +
                "<note>\n" +
                "  <to>GLog</to>\n" +
                "  <from>Goutham</from>\n" +
                "  <heading>Reminder</heading>\n" +
                "  <body>Don't forget me this weekend!</body>\n" +
                "</note>\n");
        GLog.json("hi json","{ \"name\":\"John\", \"age\":31, \"city\":\"New York\" }");
        GLog.file("hi",getApplicationContext().getCacheDir(),"TestLogFile.txt","This is a message from GLog");

    }
}
