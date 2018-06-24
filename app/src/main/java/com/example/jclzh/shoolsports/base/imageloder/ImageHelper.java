package com.example.jclzh.shoolsports.base.imageloder;

import com.example.jclzh.shoolsports.base.imageloder.config.ImageConfig;
import com.example.jclzh.shoolsports.base.imageloder.interfaces.ImageBase;

/**
 * 2033152950
 * Created by zf on 2018/1/2.
 */

public class ImageHelper implements ImageBase {

    private static ImageBase ImageBase = null;

    private static ImageHelper instance;

    public static ImageHelper obtain() {
        if (instance == null) {
            synchronized (ImageHelper.class) {
                if (instance == null) {
                    instance = new ImageHelper();
                }
            }
        }
        return instance;
    }

    private ImageHelper() {
    }

    public void addImage(ImageBase imageBase) {
        if (ImageBase == null)
            ImageBase = imageBase;
    }

    @Override
    public void load(ImageConfig config) {
        ImageBase.load(config);
    }

}
