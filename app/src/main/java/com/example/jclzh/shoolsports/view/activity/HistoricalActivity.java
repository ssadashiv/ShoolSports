package com.example.jclzh.shoolsports.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.adatapter.HistoricalSportAdatapter;
import com.example.jclzh.shoolsports.model.bean.StepData;
import com.example.jclzh.shoolsports.utils.DbUtils;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.viewutils.LineEngineChart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

/**
 * 历史记录activity
 */
public class HistoricalActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListvieHistorical;
    private ImageView mToolbarFinsh;
    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        initView();
        inintdata();

    }

    private void initView() {
        mListvieHistorical = (ListView) findViewById(R.id.listvie_historical);
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        layout = findViewById(R.id.rl_histoiachar_root);
        mToolbarFinsh.setOnClickListener(this);
    }


    private void inintdata() {
        if(DbUtils.getLiteOrm()==null){
            DbUtils.createDb(this, "jingzhi");
        }
        List<StepData> stepDatas =DbUtils.getQueryAll(StepData.class);
//        LineEngineChart engineChart = new LineEngineChart();
//        LineChart chart = engineChart.getView(this);
//        layout.addView(chart);
//        for (int i = 0; i < stepDatas.size(); i++) {
//            engineChart.update(Integer.parseInt(stepDatas.get(i).getStep()),stepDatas.get(i).getToday());
//        }
        if (stepDatas.size()==0){
            ToastUtil.showShort(HistoricalActivity.this,"您还没有运动数据哟赶紧出去走走吧！");

        }else {

            HistoricalSportAdatapter  adatapter  = new HistoricalSportAdatapter(stepDatas,HistoricalActivity.this);
            mListvieHistorical.setAdapter(adatapter);
        }

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
