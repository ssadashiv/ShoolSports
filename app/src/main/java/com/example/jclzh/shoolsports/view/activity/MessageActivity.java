package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.adatapter.MessageAdapter;
import com.example.jclzh.shoolsports.model.bean.MessageBean;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private ListView mLvMessage;
    private ArrayList<MessageBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initView();
        initData();
        initAdpter();

    }

    private void initAdpter() {
        mLvMessage.setAdapter(new MessageAdapter(MessageActivity.this,mList,R.layout.message_item));

    }

    private void initData() {
        mList = new ArrayList<>();
        MessageBean bean = new MessageBean();
        bean.setTitle("程序发布");
        bean.setContent("  本程序发布于2018年6月。");
        bean.setTime("时间：2018年6月24日14:06:22");
        mList.add(bean);

    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mLvMessage = findViewById(R.id.lv_message);
    }

}
