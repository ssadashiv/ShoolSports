package com.example.jclzh.shoolsports.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.utils.MLog;

import java.text.DecimalFormat;

public class JianKangJieguoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarFinsh;
    /**
     * 返回主页
     */
    private Button mBtFinshhome;
    /**
     * 再试一次
     */
    private Button mBtNext;
    private int shengao;
    private int tizhong;
    /**
     * 192
     */
    private TextView mTvShengao;
    /**
     * 52
     */
    private TextView mTvTizhong;
    /**
     * 56.2
     */
    private TextView mTvBim;
    /**
     * 您太瘦啦！
     */
    private TextView mTvJieguo;
    private Double bim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_jieguo);
        initView();

    }

    private void initView() {

        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
        mBtFinshhome = (Button) findViewById(R.id.bt_finshhome);
        mBtFinshhome.setOnClickListener(this);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtNext.setOnClickListener(this);
        mTvShengao = (TextView) findViewById(R.id.tv_shengao);
        mTvTizhong = (TextView) findViewById(R.id.tv_tizhong);
        mTvBim = (TextView) findViewById(R.id.tv_bim);
        mTvJieguo = (TextView) findViewById(R.id.tv_jieguo);
        shengao = getIntent().getExtras().getInt("shengao");
        tizhong = getIntent().getExtras().getInt("tizhong");
        mTvShengao.setText(""+shengao);
        mTvTizhong.setText(""+tizhong);
        bim = getBim(shengao, tizhong);
        mTvBim.setText(bim+"");
        mTvJieguo.setText(getpingjia(bim));
    }




    private  Double   getBim (int shengao ,int tizhong){
       Double sg = Double.valueOf(shengao);
        sg=sg/100;
        Double tz = Double.valueOf(tizhong);
        MLog.i("身高：",sg+"米");
        MLog.i("体重：",tizhong+"kg");
        Double   bim   = tz/(sg*sg);

        return  getTwoDecimal(bim) ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_finsh:
                finish();
                break;
            case R.id.bt_finshhome:
                startActivity(new Intent(JianKangJieguoActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.bt_next:
                startActivity(new Intent(JianKangJieguoActivity.this, JianKangActivity.class));
                finish();

                break;
        }
    }

    /**
     * 将数据保留两位小数
     */
    private double getTwoDecimal(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.00");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        return temp;
    }


    /**
     * 根据BMI的不同的值返回不同的建议
     * @param BMI
     * @return
     */
    private  String   getpingjia(Double BMI){


        String   jieguo  =  "" ;
            if (BMI<18.5){

                jieguo = "您的体重偏瘦，建议您利用空闲时间多出去走走，要注意饮食睡眠规律，才能保持合格的身材哟~";
                return  jieguo ;
            }else if (BMI>=18.5 && BMI<24){
                jieguo="您的体重是一个标准的体重，希望您在日后的时间里不要忘记规律的运动计划哟！加油~";
                return   jieguo ;
            }else if (BMI>=24 && BMI<27.9){
                jieguo  = "您的体重最近有所偏高啦！您只需要注意运动，相信就可以恢复完美身材啦！";
                return   jieguo ;
            }else if (BMI>=27.9 && BMI<30){
                jieguo ="您这体重要注意啦！！！要时刻注意运动！不然就要身材走形啦~";
                return   jieguo ;

            }else if (BMI>=30){
                jieguo ="您的体重已经严重超标了....不要灰心严格要求自己，还是可以慢慢的将自己一步步变好，加油！相信自己！";
                return   jieguo ;
            }else {

                return "希望每天可以看到你自信满满的样子，加油~";

            }
    }
}
