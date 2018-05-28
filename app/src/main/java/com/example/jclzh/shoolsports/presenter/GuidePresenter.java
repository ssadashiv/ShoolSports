package com.example.jclzh.shoolsports.presenter;

import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jclzh.shoolsports.model.GuideMode;
import com.example.jclzh.shoolsports.model.GuideModelInterface;

/**
 * Created by lzh on 2018/5/27.
 */

public class GuidePresenter implements GuidePresenterInterface {

    GuideModelInterface guideModelInterface;

    public GuidePresenter() {
        this.guideModelInterface = new GuideMode();
    }


    /**
     * 判断用户是否第一次进入APP并且设置标记
     *
     * @return
     */
    @Override
    public boolean Isfist() {

        if (guideModelInterface.IsInit()) {
            guideModelInterface.setinit();
            return  true ;
        } else {
            return false;
        }
    }

    /**
     * @param viewPager    滑动引导
     * @param linearLayout 加载图片的点布局
     */
    @Override
    public void setviewpagerimgs(ViewPager viewPager, LinearLayout linearLayout) {

    }

    /**
     * 设置用户不是首次进入APP的引导
     *
     * @param viewPager
     */
    @Override
    public void setviewpagerimg(ViewPager viewPager) {

    }

    /**
     * 设置当前版本文字信息
     */
    @Override
    public void setversion(TextView textView) {

    }
}
