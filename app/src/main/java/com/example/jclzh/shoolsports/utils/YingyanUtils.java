package com.example.jclzh.shoolsports.utils;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.example.jclzh.shoolsports.model.Application.AppApplication;

/**
 * Created by lzh on 2018/6/8.
 */

public class YingyanUtils {


    private LBSTraceClient mTraceClient;
    private Trace mTrace;
    private OnTraceListener mTraceListener;
    private int tag = 1 ;
    // 轨迹服务ID
    long serviceId = 201201;
    // 设备标识
        String entityName = "myTrace";
    // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
    boolean isNeedObjectStorage = false;

    public void   inityingyan(){


    // 初始化轨迹服务
        mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);


    // 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(AppApplication.getContext());

    // 定位周期(单位:秒)
    int gatherInterval = 5;
    // 打包回传周期(单位:秒)
    int packInterval = 10;
    // 设置定位和打包周期
    mTraceClient.setInterval(gatherInterval, packInterval);



        // 初始化轨迹服务监听器
        // 开启服务回调
        // 停止服务回调
        // 开启采集回调
        // 停止采集回调地图
        // 推送回调
        mTraceListener = new OnTraceListener() {
            @Override
            public void onBindServiceCallback(int i, String s) {
                MLog.i("地图","鹰眼监听开启");
            }

            // 开启服务回调
            @Override
            public void onStartTraceCallback(int status, String message) {}
            // 停止服务回调
            @Override
            public void onStopTraceCallback(int status, String message) {}
            // 开启采集回调
            @Override
            public void onStartGatherCallback(int status, String message) {}
            // 停止采集回调地图
            @Override
            public void onStopGatherCallback(int status, String message) {}
            // 推送回调
            @Override
            public void onPushCallback(byte messageNo, PushMessage message) {}

            @Override
            public void onInitBOSCallback(int i, String s) {

            }
        };








    }

    /**
     * 开启轨迹跟踪
     */
    public  void  starttoke(){
        // 开启服务
        mTraceClient.startTrace(mTrace, mTraceListener);
        // 开启采集
        mTraceClient.startGather(mTraceListener);
    }


    /**
     * 停止鹰眼跟踪
     */
    public  void   stoptoke(){

        // 停止服务
        mTraceClient.stopTrace(mTrace, mTraceListener);
        // 停止采集
        mTraceClient.stopGather(mTraceListener);

    }


    /**
     * 查询运动轨迹
     */
    public  void   gettoke(){

        // 创建历史轨迹请求实例
        HistoryTrackRequest historyTrackRequest = new HistoryTrackRequest(tag, serviceId, entityName);
       //设置轨迹查询起止时间

        // 开始时间(单位：秒)
        long startTime = System.currentTimeMillis() / 1000 - 12 * 60 * 60;
        // 结束时间(单位：秒)
        long endTime = System.currentTimeMillis() / 1000;
        // 设置开始时间
        historyTrackRequest.setStartTime(startTime);
        // 设置结束时间
        historyTrackRequest.setEndTime(endTime);


        // 初始化轨迹监听器
        OnTrackListener mTrackListener = new OnTrackListener() {
            // 历史轨迹回调
            @Override
            public void onHistoryTrackCallback(HistoryTrackResponse response) {


            }
        };


        // 查询历史轨迹
        mTraceClient.queryHistoryTrack(historyTrackRequest, mTrackListener);


    }



}

