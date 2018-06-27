package com.example.jclzh.shoolsports.view.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.adatapter.HistoricalSportAdatapter;
import com.example.jclzh.shoolsports.model.bean.StepData;
import com.example.jclzh.shoolsports.utils.DbUtils;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.viewutils.LineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import java.util.List;

/**
 * 历史记录activity
 */
public class HistoricalActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListvieHistorical;
    private ImageView mToolbarFinsh;
    private LineChart lineChart1 ;
    private List<StepData> stepDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        initView();
        inintdata();
        initchar();


    }

    /**
     *
     */
    private void initchar() {


        //设置图表的描述
        lineChart1.setDescription("周运动表一览");
        //设置x轴的数据
        int numX = 7;
        //设置y轴的数据
        float[] datas1 = new float[stepDatas.size()];//步数数据
        for (int i = 0; i < stepDatas.size(); i++) {
                datas1[i] =Float.parseFloat(stepDatas.get(i).getStep());
        }
        //设置折线的名称
        LineChartManager.setLineName("当月运动量");
        //设置第二条折线y轴的数据
        LineChartManager.setLineName1("上月值");
        //创建两条折线的图表
            LineData lineData = LineChartManager.initSingleLineChart(this, lineChart1, stepDatas, datas1);
        LineChartManager.initDataStyle(lineChart1, lineData, this);


    }

    private void initView() {
        mListvieHistorical = (ListView) findViewById(R.id.listvie_historical);
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        lineChart1  = findViewById(R.id.rl_histoiachar_root);
        mToolbarFinsh.setOnClickListener(this);
    }


    private void inintdata() {
        if(DbUtils.getLiteOrm()==null){
            DbUtils.createDb(this, "jingzhi");
        }
        stepDatas = DbUtils.getQueryAll(StepData.class);
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
