package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.jclzh.shoolsports.R;

public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mIvBack;
    /**
     * 保存
     */
    private Button mBtnSave;
    /**
     * soha
     */
    private TextView mTvAccount;
    private LinearLayout mLlAccount;
    /**
     * 我是一只小小鸟
     */
    private TextView mTvNike;
    private LinearLayout mLlNick;
    /**
     * 男
     */
    private TextView mTvSex;
    private LinearLayout mLlSex;
    /**
     * 山西晋城
     */
    private TextView mTvAddress;
    private LinearLayout mLlAddress;
    private ImageView mIvCode;
    private LinearLayout mLlCode;
    /**
     * 13513****14
     */
    private TextView mTvPhone;
    private LinearLayout mLlPhone;
    /**
     * 1623845707@qq.com
     */
    private TextView mTvEmail;
    private LinearLayout mLlEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        System.out.println("跳转成功");
        initView();
    }

    private void initView() {
        mIvBack = (ImageButton) findViewById(R.id.iv_back);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mTvAccount = (TextView) findViewById(R.id.tv_account);
        mLlAccount = (LinearLayout) findViewById(R.id.ll_account);
        mLlAccount.setOnClickListener(this);
        mTvNike = (TextView) findViewById(R.id.tv_nike);
        mLlNick = (LinearLayout) findViewById(R.id.ll_nick);
        mLlNick.setOnClickListener(this);
        mTvSex = (TextView) findViewById(R.id.tv_sex);
        mLlSex = (LinearLayout) findViewById(R.id.ll_sex);
        mLlSex.setOnClickListener(this);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mLlAddress = (LinearLayout) findViewById(R.id.ll_address);
        mLlAddress.setOnClickListener(this);
        mIvCode = (ImageView) findViewById(R.id.iv_code);
        mLlCode = (LinearLayout) findViewById(R.id.ll_code);
        mLlCode.setOnClickListener(this);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mLlPhone = (LinearLayout) findViewById(R.id.ll_phone);
        mLlPhone.setOnClickListener(this);
        mTvEmail = (TextView) findViewById(R.id.tv_email);
        mLlEmail = (LinearLayout) findViewById(R.id.ll_email);
        mLlEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_save:
                break;
            case R.id.ll_account:
                break;
            case R.id.ll_nick:
                break;
            case R.id.ll_sex:
                break;
            case R.id.ll_address:
                break;
            case R.id.ll_code:
                break;
            case R.id.ll_phone:
                break;
            case R.id.ll_email:
                break;
        }
    }
}
