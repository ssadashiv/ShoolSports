package com.example.jclzh.shoolsports.model.adatapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lzh on 2018/6/5.
 */

public class SportFragmentAdatapter extends FragmentPagerAdapter {

    String [] titles  ;
    List<Fragment> list  ;
    public SportFragmentAdatapter(FragmentManager fm,  List<Fragment> list,String [] titles  ) {
        super(fm);
        this.list= list  ;
        this.titles=titles;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  titles [position] ;
    }
}
