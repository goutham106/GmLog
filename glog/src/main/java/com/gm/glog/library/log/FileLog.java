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

package com.gm.glog.library.log;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Name       : Gowtham
 * Created on : 10/12/16.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */

public class FileLog {

    public static void printFile(String tag, File targetDirectory, String fileName, String headString, String msg, int fileSizeToExpiry) {
        fileName = (fileName == null) ? getFileName() : fileName;
        if (save(targetDirectory, fileName, msg, fileSizeToExpiry)) {
            Log.d(tag, headString + "save log success !");
        } else {
            Log.e(tag, headString + "save log fails !");
        }
    }


    private static boolean save(File dic, String fileName, String msg, int fileSizeToExpiry) {
        File file = new File(dic, fileName);
        PrintWriter out = null;
        try {

            long fileSizeInBytes = file.length();
            // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
            long fileSizeInKB = fileSizeInBytes / 1024;
            // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            long fileSizeInMB = fileSizeInKB / 1024;

            if (fileSizeInMB > fileSizeToExpiry) {
                file.delete();
                file = new File(dic, fileName);
            }

            out = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath(), true)));
            out.println(msg);
            out.println("\n\r");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return true;
    }

    private static String getFileName() {
        Random random = new Random();
        return "GLog_" + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
    }

}
