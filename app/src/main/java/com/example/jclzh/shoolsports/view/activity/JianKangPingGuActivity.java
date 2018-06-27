package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.jclzh.shoolsports.R;

public class JianKangPingGuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarFinsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang_ping_gu);
        initView();
    }

    private void initView() {
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
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
