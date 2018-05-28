package com.example.jclzh.shoolsports.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.Utils.UtilsImp;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.Application.AppApplication;

/**
 * Created by lzh on 2018/5/27.
 */

public class GuideMode implements  GuideModelInterface {
    /**
     * 第一个进入APP设置FLAGE
     */
    @Override
    public void setinit() {
        UtilsImp.spput("fistapp",false);
    }

    /**
     * 判断是否第一次进入APP
     *
     * @return
     */
    @Override
    public boolean IsInit() {
        return (boolean) UtilsImp.spget("fistapp",true);
    }

    /**
     * 获取引导界面的图片组
     *
     * @return
     */
    @Override
    public Bitmap[] getGuideImgs() {
        //todo  从网络获取引导图片
        return new Bitmap[0];
    }

    /**
     * 获取引导界面的图片
     */
    @Override
    public Bitmap getGuiImg() {
        return BitmapFactory.decodeResource(AppApplication.getContext().getResources(),R.drawable.guide);
    }

    /**
     * 获取当前版本
     */
    @Override
    public String getversion() {
        return ApplicationDate.VERSION;
    }
}
