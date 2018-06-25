package com.example.jclzh.shoolsports.utils.Net;

import com.android.volley.VolleyError;

public interface UploadInterface {
    void onSuccess(String response);
    void onError(VolleyError error);
}
