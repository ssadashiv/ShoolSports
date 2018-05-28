package com.example.jclzh.shoolsports.model;

import android.graphics.Bitmap;

/**
 * Created by lzh on 2018/5/27.
 */

public interface GuideModelInterface {


    /**
     * 第一个进入APP设置FLAGE
     */
    void    setinit();


    /**
     * 判断是否第一次进入APP
     * @return
     */
     boolean   IsInit();


    /**
     * 获取引导界面的图片组
     * @return
     */
    Bitmap[]   getGuideImgs();

    /**
     * 获取引导图片的本地资源
     * @return
     */
    int []  getGuideImgsid();


    /**
     * 获取引导界面的图片
     */
    Bitmap  getGuiImg();



    /**
     * 获取当前版本
     */

    String getversion();


}
