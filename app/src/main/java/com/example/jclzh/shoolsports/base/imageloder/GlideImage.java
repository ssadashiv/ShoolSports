package com.example.jclzh.shoolsports.base.imageloder;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.jclzh.shoolsports.base.imageloder.config.ImageConfig;
import com.example.jclzh.shoolsports.base.imageloder.interfaces.ImageBase;

public class GlideImage implements ImageBase {

    @Override
    public void load(ImageConfig config) {
        DiskCacheStrategy diskCacheStrategy;
        if (config.isDiskCacheStrategy()) {
            diskCacheStrategy = DiskCacheStrategy.NONE;
        } else {
            diskCacheStrategy = DiskCacheStrategy.RESOURCE;
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(!config.isSkipMemoryCache())
                .error(config.getErrorImage())
                .priority(Priority.HIGH)
                .diskCacheStrategy(diskCacheStrategy);
        Glide.with(config.getT()).load(config.getUrl()).apply(options).into(config.getImageView());
    }

}
