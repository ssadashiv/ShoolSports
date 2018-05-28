package com.example.jclzh.shoolsports.view.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.presenter.GuidePresenter;
import com.example.jclzh.shoolsports.presenter.GuidePresenterInterface;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private GuidePresenterInterface guidePresenterInterface;
    /**
     * 跳过
     */
    private TextView mTvNextrunmian;
    private ViewPager mViewpagerGuide;
    private LinearLayout mLlGuidpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        concealtitle();
        setContentView(R.layout.activity_guide);
        initView();
        guidePresenterInterface = new GuidePresenter();
        Isfistrun();

    }


    /**
     * 判断用户是否第一次打开APP
     */
    private void Isfistrun() {

        boolean isfist = guidePresenterInterface.Isfist();
        //判断是否第一次进入APP
        if (isfist) {
            guidePresenterInterface.setviewpagerimgs(mViewpagerGuide,mLlGuidpoint);
        } else {
            guidePresenterInterface.setviewpagerimg(mViewpagerGuide);
            mTvNextrunmian.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置全屏显示
     */
    private void concealtitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void initView() {
        mTvNextrunmian = (TextView) findViewById(R.id.tv_nextrunmian);
        mViewpagerGuide = (ViewPager) findViewById(R.id.viewpager_guide);
        mTvNextrunmian.setOnClickListener(this);
        mViewpagerGuide.setOnClickListener(this);
        mLlGuidpoint = (LinearLayout) findViewById(R.id.ll_guidpoint);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_nextrunmian:
                break;
            case R.id.viewpager_guide:
                break;
        }
    }
}
