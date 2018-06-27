package com.example.jclzh.shoolsports.view.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.bean.PersonageItemBean;
import com.example.jclzh.shoolsports.model.adatapter.PersonageAdapter;
import com.example.jclzh.shoolsports.utils.AlertDialogUtils;
import com.example.jclzh.shoolsports.utils.BitmapUtil;
import com.example.jclzh.shoolsports.utils.BlurImageview;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.utils.Net.UploadInterface;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.example.jclzh.shoolsports.view.customview.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * 个人中心模块
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 2;
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
        //网络路径的图片
        String netPath = ApplicationDate.API_IMG_URL+ApplicationDate.USER.getUser().getImage();
        //本地路径的图片
        final String localPath = (String) UtilsImp.spget("net_head", "");
        if (!netPath.equals("")) {
            BitmapUtil.getUrlBitmap(netPath, new BitmapUtil.BitmapInterface() {
                @Override
                public void onSuccess(Bitmap response) {
                    mIvHead.setImageBitmap(BitmapUtil.createCircleImage(response));
                    //图片高斯模糊
                    mIvHeadBg.setBackground(BlurImageview.BlurImages(response, getActivity()));
                }

                @Override
                public void onError(VolleyError error) {
                    if (localPath != null && !localPath.equals("")) {
                        Bitmap response = BitmapUtil.getSmallBitmap(localPath);
                        mIvHead.setImageBitmap(BitmapUtil.createCircleImage(response));
                        //图片高斯模糊
                        mIvHeadBg.setBackground(BlurImageview.BlurImages(response, getActivity()));
                    }

                }
            });
//            Bitmap bitmap = BitmapUtil.getSmallBitmap(dirPath);

        }

        int[] images = new int[]{R.mipmap.user_data, R.mipmap.body_data, R.mipmap.mydrill, R.mipmap.message, R.mipmap.setting};
        String[] body = new String[]{"个人资料", "打卡记录", "我的训练", "消息", "设置"};
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
        dialogUtils.showDialog(getActivity(), "更换头像", "从相册选择图片");
        dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
            @Override
            public void NeutralListener(DialogInterface dialog, int which, String string) {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission
                            .WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(getActivity(), "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                    }
                    //申请权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    //调用系统相册
                    Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(albumIntent, CODE_SELECT_IMAGE);
                }

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
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    //压缩图片，获得新的路径
                    final String mPicPath = BitmapUtil.compressImage(UtilsImp.getPicPath(getActivity(), data));
                    //上传图片
                    if (mPicPath != null && !mPicPath.equals("")) {
                        final ProgressDialog dialog = new ProgressDialog(getActivity());
                        dialog.setMessage("图片上传中");
                        dialog.show();
                        MLog.i("test", "上传图片");
                        NetUtils.doUploadTest(mPicPath, new UploadInterface() {
                            @Override
                            public void onSuccess(String response) {
                                Toast.makeText(AppApplication.getContext(), "上传成功", Toast.LENGTH_SHORT).show();

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String image = jsonObject.optString("image");
                                    if (image != null && !image.equals("")) {
                                        Log.i("userFragment", "上传成功,response = " + response);
                                        Bitmap bitmap = BitmapUtil.getSmallBitmap(mPicPath);
                                        if (bitmap != null) {
                                            //存储图片路径
//                                            UtilsImp.spput("net_head", ApplicationDate.API_IMG_URL+image);
                                            UtilsImp.spput("local_head", mPicPath);
                                            mIvHead.setImageBitmap(BitmapUtil.createCircleImage(bitmap));
                                            mIvHeadBg.setBackground(BlurImageview.BlurImages(bitmap, getActivity()));

                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                dialog.dismiss();
                            }

                            @Override
                            public void onError(VolleyError error) {
                                Toast.makeText(AppApplication.getContext(), "上传失败,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("zb", "error,response = " + error.getMessage());
                                dialog.dismiss();
                            }
                        });
                    }
                }
                break;
        }
    }
}