package com.example.jclzh.shoolsports.model.adatapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.bean.ShopBean;
import com.example.jclzh.shoolsports.utils.BitmapUtil;

import java.util.List;

public class ShopAdapter extends CommonAdapter<ShopBean.DataBean> {


    public ShopAdapter(Context context, List<ShopBean.DataBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convertView(View item, ShopBean.DataBean dataBean) {
        final ImageView iv_image = item.findViewById(R.id.iv_image);
        TextView tv_title = item.findViewById(R.id.tv_name);
        TextView tv_desc = item.findViewById(R.id.tv_desc);
        TextView tv_integral = item.findViewById(R.id.tv_integral);
        TextView tv_number = item.findViewById(R.id.tv_number);

        BitmapUtil.getUrlBitmap(ApplicationDate.API_IMG_URL + dataBean.getImages(), new BitmapUtil.BitmapInterface() {
            @Override
            public void onSuccess(Bitmap response) {
                iv_image.setImageBitmap(response);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        tv_title.setText(dataBean.getTitle());
        tv_desc.setText(dataBean.getDesc());
        tv_integral.setText("积分："+dataBean.getIntegral());
        tv_number.setText("仅剩"+dataBean.getPrice()+"件");
    }

}
