package com.example.jclzh.shoolsports.model.adatapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.bean.MessageBean;

import java.util.List;

public class MessageAdapter extends CommonAdapter<MessageBean> {

    public MessageAdapter(Context context, List<MessageBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convertView(View item, MessageBean messageBean) {
        TextView mTvTitle = item.findViewById(R.id.tv_title);
        TextView mTvContent = item.findViewById(R.id.tv_content);
        TextView mTvTime = item.findViewById(R.id.tv_time);

        mTvTitle.setText(messageBean.getTitle());
        mTvContent.setText(messageBean.getContent());
        mTvTime.setText(messageBean.getTime());
    }
}
