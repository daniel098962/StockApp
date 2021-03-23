package com.etp.stockapp.utils;

import android.util.Log;

/**
 * Created by Daniel on 2021/3/9.
 */
public class LogUtility {

    public static void LongLog(String tag, String message) {

        //region Log 太長 需分段打印
        {
            int maxLogSize = 1000;
            for (int i = 0; i <= message.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > message.length() ? message.length() : end;
                Log.d(tag, message.substring(start, end));
            }
        }
        //endregion
    }
}
