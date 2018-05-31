package com.example.jclzh.shoolsports.model.adatapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 主界面中的VIEWPAER适配器
 * Created by lzh on 2018/5/28.
 */

public class HomepagerAdater extends FragmentPagerAdapter {

    List<Fragment> fragmentList ;

    public HomepagerAdater(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }



    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
            return fragmentList.size();
    }
}
