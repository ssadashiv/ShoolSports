package com.example.jclzh.shoolsports.Utils.Net;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lzh on 2018/5/27.
 */

public interface NetUtilsInerface {


    /**
     * Json 请求
     * @param url
     * @param map
     * @param netListener
     * @return
     */
    void    jsonpost(String url , Map  map  ,NetListener netListener);

}
