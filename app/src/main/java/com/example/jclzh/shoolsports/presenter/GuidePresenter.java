package com.example.jclzh.shoolsports.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.GuideMode;
import com.example.jclzh.shoolsports.model.GuideModelInterface;
import com.example.jclzh.shoolsports.model.adatapter.GuideViewPagerAdatapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2018/5/27.
 */

public class GuidePresenter implements GuidePresenterInterface {

    GuideModelInterface guideModelInterface;
    private ImageView imageView;

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
    public void setviewpagerimgs(ViewPager viewPager, LinearLayout linearLayout , Context context) {
        Boolean fist  =  true  ;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(35, 35);
        List<View>  list    =  new ArrayList<>();
        int[] guideImgsid = guideModelInterface.getGuideImgsid();

        for (  int imgid:guideImgsid){

            if (fist){
                params.setMargins(0,0,0,0);

            }else {
                params.setMargins(10,0,0,0);

            }
            fist=false;
            imageView = new ImageView(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(imgid);
            list.add(imageView);
            View  view  = new View(context);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.pain_noselect);
            linearLayout.addView(view);
        }
        linearLayout.getChildAt(0).setBackgroundResource(R.drawable.pain_select);


        viewPager.setAdapter(new GuideViewPagerAdatapter(list));

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
        textView.setText(ApplicationDate.VERSION);
    }
}
