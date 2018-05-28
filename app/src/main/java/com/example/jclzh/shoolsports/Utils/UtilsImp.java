package com.example.jclzh.shoolsports.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzh on 2018/5/27.
 */

public class UtilsImp {

    private static SharedPreferences.Editor edit;
    private static SharedPreferences sharedPreferences;

    /**
     * Sp存储数据
     *
     * @param k
     * @param v
     */
    public static void spput(String k, Object v) {

        sharedPreferences = AppApplication.getContext().getSharedPreferences(ApplicationDate.APPSPNAME, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();

        if (v instanceof String) {
            edit.putString(k, (String) v);
        } else if (v instanceof Integer) {
            edit.putInt(k, (Integer) v);
        } else if (v instanceof Boolean) {
            edit.putBoolean(k, (Boolean) v);
        } else if (v instanceof Float) {
            edit.putFloat(k, (Float) v);
        } else if (v instanceof Long) {
            edit.putLong(k, (Long) v);
        } else {
            Log.e("工具类", "spput: " + "您的储存格式value无法识别格式");
        }
    }

    /**
     * SP获取数据
     *
     * @param k
     * @param v
     * @return
     */
    public static Object spget(String k, Object v) {
        sharedPreferences = AppApplication.getContext().getSharedPreferences(ApplicationDate.APPSPNAME, Context.MODE_PRIVATE);

        if (v instanceof String) {
            return sharedPreferences.getString(k, (String) v);
        } else if (v instanceof Integer) {
            return sharedPreferences.getInt(k, (Integer) v);
        } else if (v instanceof Boolean) {
            return sharedPreferences.getBoolean(k, (Boolean) v);
        } else if (v instanceof Float) {
            return sharedPreferences.getFloat(k, (Float) v);
        } else if (v instanceof Long) {
            return sharedPreferences.getLong(k, (Long) v);
        } else {
            Log.e("工具类", "spget: " + "您的获取SP格式value无法识别格式");
            return null;
        }

    }


    /**
     * 获取当前的时间  （Long类型）
     *
     * @return
     */
    public static Long getnowtimelong() {
        return System.currentTimeMillis();
    }


    /**
     * 获取当前的时间（String 类型）
     */
    public static String getnowtimestring() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }


    /**
     * 获取当前的时间自定义格式(String 类型)
     */
    public static String gettimeofstring(String style) {

        SimpleDateFormat formatter = new SimpleDateFormat(style);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);

    }


    /**
     * 获取IP
     */
    public static String getip() {
        return ApplicationDate.IP;
    }


    /**
     * 获取端口
     */
    public static int getport() {
        return ApplicationDate.port;
    }

    ;

}
