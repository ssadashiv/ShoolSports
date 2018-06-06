package com.example.jclzh.shoolsports.view.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.bean.PersonageItemBean;
import com.example.jclzh.shoolsports.model.adatapter.PersonageAdapter;
import com.example.jclzh.shoolsports.utils.BlurImageview;
import com.example.jclzh.shoolsports.view.customview.CircleImageView;

import java.util.ArrayList;

/**
 * 个人中心模块
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    private ImageView mIvHeadBg;
    private CircleImageView mIvHead;
    private LinearLayout mLlContent;
    private TextView mTvUserAttention;
    private TextView mTvUserFans;
    private TextView mTvUserState;
    private ListView mLvUser;
    private ArrayList<PersonageItemBean> mList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        initData();
        initAdapter();

        return view;
    }

    private void initAdapter() {
        mLvUser.setAdapter(new PersonageAdapter(getActivity(),mList));
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        //图片高斯模糊
        mIvHeadBg.setBackground(BlurImageview.BlurImages(bitmap, getActivity()));

        int[] images = new int[]{R.mipmap.user_data,R.mipmap.body_data,R.mipmap.mydrill,R.mipmap.star,R.mipmap.message,R.mipmap.setting};
        String[] body = new String[]{"个人资料","身体数据","我的训练","训练等级","消息","设置"};
        PersonageItemBean bean;
        mList = new ArrayList<>();
        for (int i = 0; i < body.length; i++) {
            bean = new PersonageItemBean();
            bean.setIcon(images[i]);
            bean.setName(body[i]);
            mList.add(bean);
        }
    }

    private void initView(View view) {
        mIvHeadBg = (ImageView) view.findViewById(R.id.iv_head_bg);
        mIvHead = (CircleImageView) view.findViewById(R.id.iv_head);
        mLlContent = (LinearLayout) view.findViewById(R.id.ll_content);
        mTvUserAttention = (TextView) view.findViewById(R.id.tv_user_attention);
        mTvUserFans = (TextView) view.findViewById(R.id.tv_user_fans);
        mTvUserState = (TextView) view.findViewById(R.id.tv_user_state);
        mLvUser = (ListView) view.findViewById(R.id.lv_user);
    }
}
