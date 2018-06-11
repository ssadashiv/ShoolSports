package com.example.jclzh.shoolsports.utils.Net;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.UtilsImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzh on 2018/5/27.
 */

public class NetUtils {

    private static RequestQueue requestQueue;
    private static JSONObject jsonObject;
    private static JsonObjectRequest jsonObjectRequest;

    /**
     * Json 请求
     *
     * @param url
     * @param map
     * @param netListener
     * @return
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
     *
     * @param url
     * @param netListener
     */
    public static void jsonget(String url, Map<String, String> map, final NetListener netListener) {

        MLog.i("URLurl拼装前:", url);
        url = url + "?";
        if (map != null) {

            for (Map.Entry<String, String> entry : map.entrySet()) {

                url = url + entry.getKey() + "=" + entry.getValue() + "&";

            }

        }
        MLog.i("URL拼装后:", url);
        requestQueue = AppApplication.getRequestQueue();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                netListener.yeslistener(jsonObject);
                MLog.i("网络请求结果", jsonObject.toString());
                try {
                    MLog.i("网络请求结果状态码:", "" + jsonObject.getInt("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                netListener.errorlistener(volleyError.toString());
                MLog.i("网络请求失败结果", volleyError.toString());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    /**
     * 多文件上传
     * @param picPath
     */
    public static void doMultiUploadTest(String picPath) {

        requestQueue = AppApplication.getRequestQueue();
        String path = picPath;
        Log.e("zb", "picPath=" + picPath);
        Map<String, String> params = new HashMap<String, String>();
        params.put("tag", "users");
        params.put("type", "file");
        params.put("my_filed", "file");
        String study_code = (String) UtilsImp.spget("ck_user", "");
        if (study_code != null && !study_code.equals("")) {
            params.put("study_code", study_code);
        }
        Log.e("zb", "params=" + params);
        File f1 = new File(path);
        Log.e("zb", "f1=" + f1.toString());
//    File f2 = new File(path);
//    Log.e("zb","f2="+f2.toString());

        if (!f1.exists()) {
            Toast.makeText(AppApplication.getContext(), "图片不存在，测试无效", Toast.LENGTH_SHORT).show();
            return;
        }
        List<File> f = new ArrayList<File>();
        f.add(f1);
//    f.add(f2);
        MultipartRequest request = new MultipartRequest(ApplicationDate.API_UPLOAD_IMAGE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(AppApplication.getContext(), "上传成功,response = " + response, Toast.LENGTH_SHORT).show();
                Log.e("zb", "success,response = " + response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AppApplication.getContext(), "上传失败,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("zb", "error,response = " + error.getMessage());
            }
        }, "f_file[]", f, params); //注意这个key必须是f_file[],后面的[]不能少
        requestQueue.add(request);
    }


    /**
     * 单文件上传
     * @param picPath
     */
    public static void doUploadTest(String picPath) {

        requestQueue = AppApplication.getRequestQueue();
        String path = picPath;
        Log.e("zb", "picPath=" + picPath);
        Map<String, String> params = new HashMap<String, String>();
        String study_code = (String) UtilsImp.spget("ck_user", "");
        MLog.i("ss","path:"+path);
        if (study_code != null && !study_code.equals("")) {
            params.put("study_code", study_code);
        }
        Log.e("zb", "params=" + params);
        File f1 = new File(path);
        Log.e("zb", "f1=" + f1.toString());

        if (!f1.exists()) {
            Toast.makeText(AppApplication.getContext(), "图片不存在，测试无效", Toast.LENGTH_SHORT).show();
            return;
        }
        MultipartRequest request = new MultipartRequest(ApplicationDate.API_UPLOAD_IMAGE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(AppApplication.getContext(), "上传成功,response = " + response, Toast.LENGTH_SHORT).show();
                Log.e("zb", "success,response = " + response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AppApplication.getContext(), "上传失败,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("zb", "error,response = " + error.getMessage());
            }
        }, study_code,f1, params); //注意这个key必须是f_file[],后面的[]不能少
        requestQueue.add(request);
    }
}