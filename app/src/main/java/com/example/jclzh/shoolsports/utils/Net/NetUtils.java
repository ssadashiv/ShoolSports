package com.example.jclzh.shoolsports.utils.Net;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import com.example.jclzh.shoolsports.model.bean.User;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzh on 2018/5/27.
 */

public class NetUtils {

    private static RequestQueue requestQueue;
    private  static JSONObject jsonObject;
    private static JsonObjectRequest jsonObjectRequest;

    private  static Gson  gson = new Gson();
    /**
     * Json 请求
     *
     * @param url
     * @param map
     * @param netListener
     * @return
     *
     */
    public static void jsonpost(String url, Map map, final NetListener netListener) {

        jsonObject = new JSONObject(map);
        requestQueue = AppApplication.getRequestQueue();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                netListener.yeslistener(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                netListener.errorlistener(volleyError.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * JSON的get请求
     * @param url
     * @param netListener
     */
    public static void  jsonget(String url  ,Map<String ,String>   map,final NetListener netListener){
        String   jsonuser = (String) UtilsImp.spget("user", "");
        User user = gson.fromJson(jsonuser, User.class);
        map.put("token",String.valueOf(user.getToken()));

        MLog.i("URLurl拼装前:",url);
        url=url+"?";
        if (map!=null){


            for (Map.Entry<String,String> entry : map.entrySet()){
                url=url+entry.getKey()+"="+entry.getValue()+"&";
            }

        }
        MLog.i("URL拼装后:",url);
        requestQueue = AppApplication.getRequestQueue();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                netListener.yeslistener(jsonObject);
                MLog.i("网络请求结果",jsonObject.toString());
                try {
                    MLog.i("网络请求结果状态码:",""+jsonObject.getInt("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                netListener.errorlistener(volleyError.toString());
                MLog.i("网络请求失败结果",volleyError.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}
