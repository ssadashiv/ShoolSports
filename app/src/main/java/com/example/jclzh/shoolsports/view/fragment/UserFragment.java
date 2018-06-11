package com.example.jclzh.shoolsports.view.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.jclzh.shoolsports.utils.AlertDialogUtils;
import com.example.jclzh.shoolsports.utils.BlurImageview;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.example.jclzh.shoolsports.view.customview.CircleImageView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * 个人中心模块
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    public final int CODE_SELECT_IMAGE = 2;//相册RequestCode

    private ImageView mIvHeadBg;
    private CircleImageView mIvHead;
    private LinearLayout mLlContent;
    private TextView mTvUserAttention;
    private TextView mTvUserFans;
    private TextView mTvUserState;
    private ListView mLvUser;
    private ArrayList<PersonageItemBean> mList;
    private static final String TAG = "UserFragment";


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
        mLvUser.setAdapter(new PersonageAdapter(getActivity(), mList));
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        //图片高斯模糊
        mIvHeadBg.setBackground(BlurImageview.BlurImages(bitmap, getActivity()));

        int[] images = new int[]{R.mipmap.user_data, R.mipmap.body_data, R.mipmap.mydrill, R.mipmap.star, R.mipmap.message, R.mipmap.setting};
        String[] body = new String[]{"个人资料", "打卡记录", "我的训练", "购物记录", "消息", "设置"};
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
        mIvHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                //弹出对话框选择图片
                popupAlertDialog();
                break;
        }
    }

    /**
     * 弹出对话框选择图片
     */
    private void popupAlertDialog() {
        AlertDialogUtils dialogUtils = new AlertDialogUtils();
        dialogUtils.showDialog(getActivity(),"更换头像","从相册选择图片");
        dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
            @Override
            public void NeutralListener(DialogInterface dialog, int which, String string) {
                //调用系统相册
                Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(albumIntent, CODE_SELECT_IMAGE);
                dialog.dismiss();
            }

            @Override
            public void NegativeListener(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }

    /**
     * 调用系统相册以后返回照片并且显示
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    String mPicPath = UtilsImp.getPicPath(getActivity(), data);
                    //上传图片
                    if (mPicPath != null && !mPicPath.equals("")) {
                        Bitmap bitmap = BitmapFactory.decodeFile(mPicPath);

                        mIvHead.setImageBitmap(bitmap);
                        mIvHeadBg.setBackground(BlurImageview.BlurImages(bitmap, getActivity()));
                        MLog.i("test","上传图片");
                        NetUtils.doUploadTest(mPicPath);
                    }
                }
                break;
        }
    }
}
