package com.example.jclzh.shoolsports.model.adatapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lzh on 2018/5/28.
 */

public class GuideViewPagerAdatapter extends PagerAdapter {


  List<View> list ;

    public GuideViewPagerAdatapter(List<View> list) {
        this.list = list;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        if (list!=null) {
            return list.size();
        }else {
            return  0 ;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       container.addView(list.get(position));
       return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
