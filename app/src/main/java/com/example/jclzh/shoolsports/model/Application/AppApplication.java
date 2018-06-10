package com.example.jclzh.shoolsports.model.Application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.example.jclzh.shoolsports.base.imageloder.GlideImage;
import com.example.jclzh.shoolsports.base.imageloder.ImageHelper;

/**
 * Created by lzh on 2018/5/27.
 */

public class AppApplication extends Application {

    private static Context context;
    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        requestQueue = Volley.newRequestQueue(context);
        ImageHelper.obtain().addImage(new GlideImage());
        //初始化百度地图
        SDKInitializer.initialize(getApplicationContext());

    }

    /**
     * 获取全局上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取Volley请求队列
     *
     * @return
     */
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
