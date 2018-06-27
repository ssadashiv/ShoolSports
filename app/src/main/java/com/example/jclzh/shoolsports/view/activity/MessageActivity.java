package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.MessageAdapter;
import com.example.jclzh.shoolsports.model.bean.MessageBean;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private ListView mLvMessage;
    private List<MessageBean.DataBean> mList;
    private Gson gson = new Gson();

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

    }

    private void initData() {
        NetUtils.jsonget(ApplicationDate.API_GET_MESSAGE, new HashMap<String, String>(), new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {
                MLog.e("ssts","消息："+jsonObject.toString());
                MessageBean messageBean = gson.fromJson(jsonObject.toString(), MessageBean.class);
                mList = messageBean.getData();
                mLvMessage.setAdapter(new MessageAdapter(MessageActivity.this,mList,R.layout.message_item));

            }

            @Override
            public void errorlistener(String error) {
                Toast.makeText(MessageActivity.this, "消息获取失败", Toast.LENGTH_SHORT).show();
            }
        });

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
