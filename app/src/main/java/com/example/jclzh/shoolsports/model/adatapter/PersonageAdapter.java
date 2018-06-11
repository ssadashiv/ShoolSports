package com.example.jclzh.shoolsports.model.adatapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.bean.PersonageItemBean;
import com.example.jclzh.shoolsports.utils.CommonViewHolder;
import com.example.jclzh.shoolsports.view.activity.MessageActivity;
import com.example.jclzh.shoolsports.view.activity.MyTrainingActivity;
import com.example.jclzh.shoolsports.view.activity.PersonalDataActivity;
import com.example.jclzh.shoolsports.view.activity.PunchingCardRecordActivity;
import com.example.jclzh.shoolsports.view.activity.SettingActivity;
import com.example.jclzh.shoolsports.view.activity.ShoppingRecordActivity;

import java.util.ArrayList;

public class PersonageAdapter extends BaseAdapter {
    private ArrayList<PersonageItemBean> list;
    private Context context;
    public PersonageAdapter(Context context,ArrayList<PersonageItemBean> mList) {
        this.context = context;
        this.list = mList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PersonageItemBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.personage_item, null);
        }
        LinearLayout item = CommonViewHolder.get(convertView, R.id.ll_personage);
        ImageView icon = CommonViewHolder.get(convertView, R.id.img);
        ImageView ivBack = CommonViewHolder.get(convertView, R.id.img_back);
        TextView body = CommonViewHolder.get(convertView, R.id.tv_body);

        PersonageItemBean bean = getItem(position);
        icon.setImageResource(bean.getIcon());
        body.setText(bean.getName());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        //个人资料
                        context.startActivity(new Intent(context, PersonalDataActivity.class));
                        break;
                    case 1:
                        //打卡记录
                        context.startActivity(new Intent(context, PunchingCardRecordActivity.class));
                        break;
                    case 2:
                        //我的训练
                        context.startActivity(new Intent(context, MyTrainingActivity.class));
                        break;
                    case 3:
                        //购物记录
                        context.startActivity(new Intent(context, ShoppingRecordActivity.class));
                        break;
                    case 4:
                        //消息
                        context.startActivity(new Intent(context, MessageActivity.class));
                        break;
                    case 5:
                        //设置
                        context.startActivity(new Intent(context, SettingActivity.class));
                        break;
                }
            }
        });

        return convertView;
    }
}