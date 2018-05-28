package com.example.jclzh.shoolsports.Utils;

import android.util.Log;

import com.example.jclzh.shoolsports.model.Application.ApplicationDate;

/**
 * 打印LOG  请全局使用此类  方便后期维护
 * 自定义的LOG  后期可控制LOG 是否打印的开关
 * Created by lzh on 2018/5/27.
 */

public class MLog {

    public static void d(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_D) {
            Log.d(TAG, "d: " + logvalue);
        }
    }

    public static void i(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_I) {
            Log.d(TAG, "i: " + logvalue);
        }
    }

    public static void w(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_W) {
            Log.d(TAG, "w: " + logvalue);
        }
    }

    public static void e(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_E) {
            Log.d(TAG, "e: " + logvalue);
        }
    }

    public static void m(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_M) {
            Log.d(TAG, "m: " + logvalue);
        }
    }

    public static void r(String TAG, String logvalue) {
        if (ApplicationDate.ISLOG_R) {
            Log.d(TAG, "r: " + logvalue);
        }
    }
}
