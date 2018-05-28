package com.example.jclzh.shoolsports.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lzh on 2018/5/27.
 */

public interface GuidePresenterInterface {


    /**
     *
     * 判断用户是否第一次进入APP并且设置标记
     * @return
     */
    boolean  Isfist();
    /**
     *
     * @param viewPager    滑动引导
     * @param linearLayout 加载图片的点布局
     */
    void  setviewpagerimgs(ViewPager viewPager, LinearLayout linearLayout , Context context);


    /**
     * 设置用户不是首次进入APP的引导
     * @param viewPager
     */
    void  setviewpagerimg(ViewPager viewPager);


    /**
     * 设置当前版本文字信息
     */
    void  setversion(TextView textViewversion);




}
