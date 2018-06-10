package com.example.jclzh.shoolsports.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 2033152950
 * Created by zf on 2018/6/9.
 */

public abstract class GsonUtil<T>  {
    private Type clazz;

    public GsonUtil(String string) {
        this.clazz = clazz;
        Gson gson = new Gson();
        clazz = resolveClass(this);
        if (clazz instanceof List) {
            clazz = new TypeToken<T>() {
            }.getType();
        }
        T o = gson.fromJson(string, clazz);
        Success(o);
    }

    private Type resolveClass(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Log.i("---", "resolveClass: " + type);
        return ((ParameterizedType) (type)).getActualTypeArguments()[0];
    }

    protected abstract void Success(T t);

}
