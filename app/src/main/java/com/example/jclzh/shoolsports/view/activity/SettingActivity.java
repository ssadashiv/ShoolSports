package com.example.jclzh.shoolsports.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.utils.UtilsImp;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Switch mSwVoice;
    private Switch mSwShake;
    private Switch mSwNewMessage;
    private Switch mSwShowNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initData();
    }

    private void initData() {
        mSwVoice.setOnCheckedChangeListener(new CheckedListener());
        mSwShake.setOnCheckedChangeListener(new CheckedListener());
        mSwNewMessage.setOnCheckedChangeListener(new CheckedListener());
        mSwShowNotify.setOnCheckedChangeListener(new CheckedListener());
    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        Button mBtnCut = findViewById(R.id.btn_cut);
        Button mBtnLogout = findViewById(R.id.btn_logout);
        mBtnCut.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mSwVoice = findViewById(R.id.sw_voice);
        mSwShake = findViewById(R.id.sw_shake);
        mSwNewMessage = findViewById(R.id.sw_new_message);
        mSwShowNotify = findViewById(R.id.sw_show_notify);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cut:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_logout:
                UtilsImp.spput("checked_ispass",false);
                UtilsImp.spput("checked_islogin",false);
                UtilsImp.spput("ck_user","");
                UtilsImp.spput("ck_pass","");
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    private class CheckedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(SettingActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
        }
    }
}
