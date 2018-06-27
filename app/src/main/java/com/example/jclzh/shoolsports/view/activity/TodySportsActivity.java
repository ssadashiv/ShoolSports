package com.example.jclzh.shoolsports.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TodySportsActivity extends AppCompatActivity implements View.OnClickListener {

    PieChart mPiechart;
    private String[] Stars = new String[]{"步行", "跑步", "爬山", "游泳"};
    private int zongbu = (int) UtilsImp.spget("sports1", 0);
    private int paobu = (int) UtilsImp.spget("sports2", 0);
    private int panshan = (int) UtilsImp.spget("sports3", 0);
    private int youyong = (int) UtilsImp.spget("sports4", 0);
    private int[] number = new int[]{zongbu, paobu, panshan, youyong};
    private ImageView mToolbarFinsh;
    /**
     * 您今天还没有运动呢，快出去走走吧~
     */
    private TextView mTvTishi;
    /**
     * 0步
     */
    private TextView mTvBushu1;
    /**
     * 0m
     */
    private TextView mTvJuli1;
    /**
     * 0卡
     */
    private TextView mTvKaluli1;
    /**
     * 运动比例
     */
    private TextView mTvShuoming;
    /**
     * 0步
     */
    private TextView mTvBushu2;
    /**
     * 0m
     */
    private TextView mTvJuli2;
    /**
     * 0卡
     */
    private TextView mTvKaluli2;
    /**
     * 0步
     */
    private TextView mTvBushu3;
    /**
     * 0m
     */
    private TextView mTvJuli3;
    /**
     * 0卡
     */
    private TextView mTvKaluli3;
    /**
     * 0步
     */
    private TextView mTvBushu4;
    /**
     * 0m
     */
    private TextView mTvJuli4;
    /**
     * 0卡
     */
    private TextView mTvKaluli4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tody_sports);
        mPiechart = (PieChart) findViewById(R.id.mPieChart);
        initView();
        ifnull();
        initdata();
    }


    private double getTwoDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String yearString = dFormat.format(num);
        Double temp = Double.valueOf(yearString);
        return temp;
    }

    private void initdata() {
        mTvBushu1.setText(zongbu+"步");
        mTvBushu2.setText(paobu+"步");
        mTvBushu3.setText(panshan+"步");
        mTvBushu4.setText(youyong+"转");

        mTvJuli1.setText(getjuli(zongbu));
        mTvJuli2.setText(getjuli(paobu));
        mTvJuli3.setText(getjuli(panshan));
        mTvJuli4.setText(getjuli(youyong));

        mTvKaluli1.setText(getkaluli(zongbu));
        mTvKaluli2.setText(getkaluli(paobu));
        mTvKaluli3.setText(getkaluli(panshan));
        mTvKaluli4.setText(getkaluli(youyong));
    }

    private void ifnull() {
        if (zongbu == 0 && panshan == 0 && paobu == 0 && youyong == 0) {
            mTvTishi.setVisibility(View.VISIBLE);
            mTvShuoming.setVisibility(View.GONE);
        } else {
            mTvTishi.setVisibility(View.GONE);
            mTvShuoming.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        setData();
        //设置X轴的动画
        mPiechart.animateX(1400);
        //使用百分比
        mPiechart.setUsePercentValues(true);
        //设置图列可见
        mPiechart.getLegend().setEnabled(true);
        //设置图列标识的大小
        mPiechart.getLegend().setFormSize(14);
        //设置图列标识文字的大小
        mPiechart.getLegend().setTextSize(14);
        //设置图列的位置
        mPiechart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        //设置图列标识的形状
        mPiechart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        //设置图表描述
        mPiechart.setDescription("今日运动总分析");
        //设置图表描述的字体
        mPiechart.setDescriptionTextSize(14);
        //设置图表描述的位置
        mPiechart.setDescriptionPosition(950, 950);
        //设置是否可转动
        mPiechart.setRotationEnabled(true);
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
        mTvTishi = (TextView) findViewById(R.id.tv_tishi);
        mTvBushu1 = (TextView) findViewById(R.id.tv_bushu1);
        mTvJuli1 = (TextView) findViewById(R.id.tv_juli1);
        mTvKaluli1 = (TextView) findViewById(R.id.tv_kaluli1);
        mTvBushu1.setOnClickListener(this);
        mTvJuli1.setOnClickListener(this);
        mTvKaluli1.setOnClickListener(this);
        mTvTishi.setOnClickListener(this);
        mTvShuoming = (TextView) findViewById(R.id.tv_shuoming);
        mTvShuoming.setOnClickListener(this);
        mTvBushu2 = (TextView) findViewById(R.id.tv_bushu2);
        mTvJuli2 = (TextView) findViewById(R.id.tv_juli2);
        mTvKaluli2 = (TextView) findViewById(R.id.tv_kaluli2);
        mTvBushu3 = (TextView) findViewById(R.id.tv_bushu3);
        mTvJuli3 = (TextView) findViewById(R.id.tv_juli3);
        mTvKaluli3 = (TextView) findViewById(R.id.tv_kaluli3);
        mTvBushu4 = (TextView) findViewById(R.id.tv_bushu4);
        mTvJuli4 = (TextView) findViewById(R.id.tv_juli4);
        mTvKaluli4 = (TextView) findViewById(R.id.tv_kaluli4);
    }

    /**
     * 计算距离
     * @param cout
     * @return
     */
    private  String getjuli(int  cout){
        return (getTwoDecimal(cout*0.4))+""+"米";
    }

    private  String getkaluli(int cout){
        return  (getTwoDecimal(cout*0.014))+""+"千卡" ;
    }

    private void setData() {
        //设置标题
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < Stars.length; i++) {
            titles.add(Stars[i]);
        }
        ArrayList<Entry> entrys = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            entrys.add(new Entry(number[i], i));
        }
        //饼图数据集
        PieDataSet dataset = new PieDataSet(entrys, "");
        //设置饼状图Item之间的间隙
        dataset.setSliceSpace(3f);
        //饼图Item被选中时变化的距离
        dataset.setSelectionShift(10f);
        //颜色填充
        dataset.setColors(new int[]{Color.rgb(0, 255, 255), Color.rgb(60, 179, 113), Color.rgb(255, 165, 0), Color.rgb(124, 252, 0), Color.rgb(255, 182, 1193)});
        //数据填充
        PieData piedata = new PieData(titles, dataset);
        //设置饼图上显示数据的字体大小
        piedata.setValueTextSize(15);
        mPiechart.setData(piedata);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_finsh:
                finish();
                break;
            case R.id.tv_bushu1:
                break;
            case R.id.tv_juli1:
                break;
            case R.id.tv_kaluli1:
                break;
            case R.id.tv_tishi:
                break;
            case R.id.tv_shuoming:
                break;
        }
    }
}
