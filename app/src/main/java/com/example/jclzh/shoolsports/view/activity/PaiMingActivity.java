package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.SportFragmentAdatapter;
import com.example.jclzh.shoolsports.view.fragment.paiming.BanJiFragment;
import com.example.jclzh.shoolsports.view.fragment.paiming.ShoolpaimingFragment;
import com.example.jclzh.shoolsports.view.fragment.paiming.XiPaiMingFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_climbFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_runFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_swimFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_walkFragment;

import java.util.ArrayList;
import java.util.List;

public class PaiMingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarFinsh;
    private TabLayout mTablayoutPaiming;
    private ViewPager mViewpaerPaiming;
    private List<Fragment> fragmentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_ming);
        initView();
        initdata();
    }

    private void initdata() {
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new ShoolpaimingFragment());
        fragmentlist.add(new XiPaiMingFragment());
        fragmentlist.add(new BanJiFragment());
        mViewpaerPaiming.setAdapter(new SportFragmentAdatapter(getSupportFragmentManager(), fragmentlist, ApplicationDate.TABPAIMING));
        mTablayoutPaiming.setupWithViewPager(mViewpaerPaiming);


    }

    private void initView() {
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
        mTablayoutPaiming = (TabLayout) findViewById(R.id.tablayout_paiming);
        mViewpaerPaiming = (ViewPager) findViewById(R.id.viewpaer_paiming);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_finsh:
                finish();
                break;
        }
    }
}
