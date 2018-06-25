package com.example.jclzh.shoolsports.model.Application;

import com.example.jclzh.shoolsports.model.bean.User;

/**
 * 用于全局资源控制的类
 * Created by lzh on 2018/5/27.
 */

public class ApplicationDate {

    public static User USER = null ;
    //LOG  等级 ：D 开关
    public static boolean ISLOG_D = true;

    //LOG  等级 ：I 开关
    public static boolean ISLOG_I = true;

    //LOG  等级 ：W 开关
    public static boolean ISLOG_W = true;

    //LOG  等级 ：E 开关
    public static boolean ISLOG_E = true;

    //LOG  等级 ：M 开关
    public static boolean ISLOG_M = true;

    //LOG  等级 ：R开关
    public static boolean ISLOG_R = true;

    //Tost  是否显示开关
    public static boolean ISTOST = true;

    //当前软件的版本
    public static final String VERSION = "1.0.0";

    //默认的时间日期格式
    public static final String TIMESTYLE = "yyyy年MM月dd日  HH:mm:ss";

    //SharedPreferences 储存标识
    public static final String APPSPNAME = "SHOOLSPORTS";

    //服务器IP地址
    public static final String IP = "39.106.132.165";
    //服务器端口
    public static final int PORT = 7001;

    //服务器基础URL
    public static final String chidurl = "http://" + IP + ":" + PORT;

    //登录API

    public static final String API_LOGIN_SAVERENWU = "http://" + IP + ":" + PORT + "/api/saveautomation";

    public static final String API_LOGIN_URL = "http://" + IP + ":" + PORT + "/api/login";

    public static final String API_getchallenge_URL = "http://" + IP + ":" + PORT + "/api/getchallenge";

    public static final String API_acceptchallenge_URL = "http://" + IP + ":" + PORT + "/api/acceptchallenge";

    public static final String API_LOGIN_RENWU = "http://" + IP + ":" + PORT + "/api/getmyselftask";

    public static final String API_IMG_URL = "http://" + IP + ":"     + PORT;

    //运动项目
    public static final String[] TABSPORTS = {"步行", "跑步", "爬山", "游泳"};

    //上传图片的url
    public   static   final  String   API_UPLOAD_IMAGE_URL= "http://"+IP+":"+PORT+"/api/uploadimage";

    //获取自身所有任务
    public static final String API_MY_TASK = "http://"+IP+":"+PORT+"/api/getmyselftask";

}
