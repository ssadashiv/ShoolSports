package com.example.jclzh.shoolsports.model.adatapter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.bean.MyTrainingBean;

import java.util.List;

public class TrainingAdapter extends CommonAdapter<MyTrainingBean.AlldataBean> {


    public TrainingAdapter(Context context, List<MyTrainingBean.AlldataBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convertView(View item, MyTrainingBean.AlldataBean alldataBean) {
        TextView tvNumber =  item.findViewById(R.id.tv_number);
        TextView tvStartTime =  item.findViewById(R.id.tv_start_time);
        TextView tvType =  item.findViewById(R.id.tv_type);
        TextView tvContent =  item.findViewById(R.id.tv_content);
        TextView tvEndTime =  item.findViewById(R.id.tv_end_time);

        tvNumber.setText("任务"+alldataBean.getTask_id());
        tvStartTime.setText("开始时间："+alldataBean.getStarttime());
        String tag = alldataBean.getTag();
        int parseInt = Integer.parseInt(tag);
        if (parseInt == 1) {
            tag = "游泳";
        } else if (parseInt == 2) {
            tag = "跑步";
        } else if (parseInt == 3) {
            tag = "爬山 ";
        } else if (parseInt == 4) {
            tag = "骑行";
        }
        tvType.setText("任务类型："+tag);
        tvContent.setText("任务内容："+alldataBean.getTitle());
        tvEndTime.setText("结束时间："+alldataBean.getEndtime());
    }
}
