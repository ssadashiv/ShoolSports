package com.example.jclzh.shoolsports.utils.Net;

import org.json.JSONObject;

/**
 * Created by lzh on 2018/5/27.
 */

public interface NetListener {


    void   yeslistener(JSONObject jsonObject );

    void  errorlistener(String error);
}
