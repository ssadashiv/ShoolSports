package com.example.jclzh.shoolsports.view.activity;

import android.content.Intent;
import android.gesture.GestureUtils;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.presenter.GuidePresenter;
import com.example.jclzh.shoolsports.presenter.GuidePresenterInterface;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.example.jclzh.shoolsports.view.fragment.GuideFragment;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private GuidePresenterInterface guidePresenterInterface;
    /**
     * 跳过
     */
    private RelativeLayout mGudieCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        concealtitle();
        super.onCreate(savedInstanceState);
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

        if (isfist) {
            //用户首次启动APP 引导界面
            getSupportFragmentManager().beginTransaction().replace(R.id.gudie_cont, new GuideFragment()).commit();

        } else {
            //用户非首次启动APP
            startanimode();
        }
    }

    /**
     * 启用开场动画
     */
    private void startanimode() {
        mGudieCont.setAnimation(AnimationUtils.loadAnimation(this,R.anim.guide_start_animator));
        //3秒后进入登录界面
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //todo  判断是否为自动登录

                //判断是否自动登录
                if ((Boolean) UtilsImp.spget("checked_islogin",false)){
                    startActivity(new Intent(GuideActivity.this,HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                    finish();
                }


            }
        },1500);


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
        mGudieCont = (RelativeLayout) findViewById(R.id.gudie_cont);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;



        }
    }
}
