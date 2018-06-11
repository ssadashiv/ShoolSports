package com.example.jclzh.shoolsports.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.SportFragmentAdatapter;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_climbFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_runFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_swimFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_walkFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 运动模块
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentlist;

    public SportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_sport, container, false);
        initview(view);
        initdata();
        return  view  ;
    }

    private void initdata() {
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new Sport_walkFragment());
        fragmentlist.add(new Sport_runFragment());
        fragmentlist.add(new Sport_swimFragment());
        fragmentlist.add(new Sport_climbFragment());
        viewPager.setAdapter(new SportFragmentAdatapter(getActivity().getSupportFragmentManager(), fragmentlist,ApplicationDate.TABSPORTS));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initview(View view) {
        tabLayout = view.findViewById(R.id.tablayout_sport);
        viewPager = view.findViewById(R.id.viewpaer_sports);
    }


    @Override
    public void onResume() {
        super.onResume();
//        viewPager.setAdapter(new SportFragmentAdatapter(getActivity().getSupportFragmentManager(),fragmentlist,ApplicationDate.TABSPORTS));

        viewPager.setCurrentItem(0);
    }
}
