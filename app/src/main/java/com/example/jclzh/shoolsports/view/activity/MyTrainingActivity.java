package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.TrainingAdapter;
import com.example.jclzh.shoolsports.model.bean.MyTrainingBean;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrainingActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private ListView mLvTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mLvTraining = findViewById(R.id.lv_training);
        setSupportActionBar(toolbar);

        initView();
        initData();
    }


    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", ApplicationDate.USER.getUser().getUsers_id()+"");
        map.put("scholl_id", ApplicationDate.USER.getUser().getScholl_id()+"");
        NetUtils.jsonget(ApplicationDate.API_MY_TASK, map, new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {
                MyTrainingBean bean = gson.fromJson(jsonObject.toString(), MyTrainingBean.class);
                if (bean != null) {
                    List<MyTrainingBean.AlldataBean> beanList = bean.getAlldata();
                    mLvTraining.setAdapter(new TrainingAdapter(MyTrainingActivity.this,beanList,R.layout.training_item));
                }
            }

            @Override
            public void errorlistener(String error) {
                Toast.makeText(MyTrainingActivity.this, "错误："+error, Toast.LENGTH_SHORT).show();

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
    }

}