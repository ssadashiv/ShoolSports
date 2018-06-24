package com.example.jclzh.shoolsports.base.imageloder.config;

import android.content.Context;
import android.widget.ImageView;

import com.example.jclzh.shoolsports.R;


/**
 * 2033152950
 * Created by zf on 2018/1/2.
 */

public class ImageConfig<T extends Context> {

    private String Url = null;

    private int errorImage = R.mipmap.message;

    private T t;

    private ImageView imageView = null;

    //内存缓存
    private boolean skipMemoryCache = true;
    //磁盘缓存
    private boolean DiskCacheStrategy = true;

    public ImageConfig<T> setUrl(String url) {
        Url = url;
        return this;
    }

    public ImageConfig setErrorImage(int errorImage) {
        this.errorImage = errorImage;
        return this;
    }

    public ImageConfig setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public ImageConfig setSkipMemoryCache(boolean skipMemoryCache) {
        this.skipMemoryCache = skipMemoryCache;
        return this;
    }

    public ImageConfig setDiskCacheStrategy(boolean diskCacheStrategy) {
        DiskCacheStrategy = diskCacheStrategy;
        return this;
    }

    private ImageConfig() {
    }

    public ImageConfig(T t) {
        this.t = t;
    }

    public String getUrl() {
        return Url;
    }

    public int getErrorImage() {
        return errorImage;
    }

    public T getT() {
        return t;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isDiskCacheStrategy() {
        return DiskCacheStrategy;
    }

}
