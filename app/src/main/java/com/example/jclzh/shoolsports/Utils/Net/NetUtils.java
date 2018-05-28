package com.example.jclzh.shoolsports.Utils.Net;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jclzh.shoolsports.model.Application.AppApplication;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lzh on 2018/5/27.
 */

public class NetUtils implements  NetUtilsInerface , Response.Listener<JSONObject>,Response.ErrorListener {

    private RequestQueue requestQueue;
    private JSONObject jsonObject;
    private JsonObjectRequest jsonObjectRequest;
    private          NetListener netListener ;

    /**
     * Json 请求
     *
     * @param url
     * @param map
     * @param netListener
     * @return
     *
     */
    @Override
    public void jsonpost(String url, Map map, NetListener netListener) {

        jsonObject = new JSONObject(map);
        requestQueue = AppApplication.getRequestQueue();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, this,this);
        this.netListener =netListener ;
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 请求失败监听
     * @param volleyError
     */
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (netListener!=null){
            netListener.errorlistener(volleyError.toString());
        }
    }


    /**
     * 请求失败监听
     * @param jsonObject
     */
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (netListener!=null){
            netListener.yeslistener(jsonObject);
        }
    }
}
