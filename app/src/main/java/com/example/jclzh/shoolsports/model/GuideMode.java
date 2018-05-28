package com.example.jclzh.shoolsports.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.UtilsImp;
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
        MLog.i("设置标识","false");
    }

    /**
     * 判断是否第一次进入APP
     *
     * @return
     */
    @Override
    public boolean IsInit() {
        if ((boolean) UtilsImp.spget("fistapp",true)) {
            MLog.i("判断标识", "用户第一次进入APP");
        }else {
            MLog.i("判断标识","用户不是第一次进入APP");
        }
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
        Bitmap  []  bitmaps  =  {idtobitmap(R.drawable.guide1),idtobitmap(R.drawable.guide2),idtobitmap(R.drawable.guide3),idtobitmap(R.drawable.guide4)};
        return bitmaps;
    }

    /**
     * 获取引导图片的本地资源
     *
     * @return
     */
    @Override
    public int[] getGuideImgsid() {
        int  []  imgs  =  {R.drawable.guide1,R.drawable.guide2,R.drawable.guide3,R.drawable.guide4};
        return imgs;
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



    public   Bitmap  idtobitmap(int id){
        return BitmapFactory.decodeResource(AppApplication.getContext().getResources(),id);

    }

}
