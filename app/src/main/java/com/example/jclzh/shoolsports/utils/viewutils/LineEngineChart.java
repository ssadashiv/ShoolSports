package com.example.jclzh.shoolsports.utils.viewutils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LineEngineChart {

    private LineChart mChart;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public LineChart getView(Context context) {
        mChart = new LineChart(context);
        mChart.setDescription("");
        mChart.setScaleEnabled(false);
        LineData data = new LineData();
        data.setValueTextSize(12f);
        data.setDrawValues(true);
        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis left = mChart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setAxisMinValue(0);
        left.setTextSize(12f);
        left.setAxisMaxValue(50000);

        YAxis right = mChart.getAxisRight();
        right.setDrawGridLines(false);
        right.setDrawAxisLine(false);
        right.setEnabled(false);

        return mChart;
    }

    public void update(int value,String date) {
        LineData data = mChart.getData();
        LineDataSet dataSet = data.getDataSetByIndex(0);
        if (dataSet == null) {
            dataSet = createSet();
            data.addDataSet(dataSet);
        }
        data.addXValue(date);

        Entry entry = new Entry(value, dataSet.getEntryCount());
        data.addEntry(entry,0);

        mChart.notifyDataSetChanged();
        mChart.setVisibleXRangeMaximum(6);
        mChart.moveViewToX(mChart.getXValCount()-6);
    }

    private String getTime() {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("ss");
        return simpleDateFormat.format(new Date());
    }

    private LineDataSet createSet() {
        LineDataSet dataSet = new LineDataSet(null,"");
        dataSet.setDrawCircles(true);
        dataSet.setValueTextSize(10f);
        dataSet.setCircleSize(2f);
        dataSet.setLineWidth(2f);
        dataSet.setColor(Color.BLACK);
        return dataSet;
    }

}
