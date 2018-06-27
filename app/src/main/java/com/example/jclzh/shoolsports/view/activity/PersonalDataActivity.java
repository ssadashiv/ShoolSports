package com.example.jclzh.shoolsports.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.AppApplication;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.utils.AlertDialogUtils;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.QRCodeUtil;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.UtilsImp;

import java.io.File;

public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mIvBack;
    /**
     * 保存
     */
    private Button mBtnSave;
    /**
     * soha
     */
    private TextView mTvAccount;
    private LinearLayout mLlAccount;
    /**
     * 我是一只小小鸟
     */
    private TextView mTvNike;
    private LinearLayout mLlNick;
    /**
     * 男
     */
    private TextView mTvSex;
    private LinearLayout mLlSex;
    /**
     * 山西晋城
     */
    private TextView mTvAddress;
    private LinearLayout mLlAddress;
    private ImageView mIvCode;
    private LinearLayout mLlCode;
    /**
     * 13513****14
     */
    private TextView mTvPhone;
    private LinearLayout mLlPhone;
    /**
     * 1623845707@qq.com
     */
    private TextView mTvEmail;
    private LinearLayout mLlEmail;
    private AlertDialogUtils dialogUtils = new AlertDialogUtils();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        initView();
        initData();
    }

    private void initData() {
        String study_code = (String) UtilsImp.spget("ck_user", "");
        if (study_code != null && !study_code.equals("")) {
            mTvAccount.setText(study_code);
        }
        //设置姓名
        mTvNike.setText(ApplicationDate.USER.getUser().getName());

    }

    private void initView() {
        mIvBack = (ImageButton) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mTvAccount = (TextView) findViewById(R.id.tv_account);
        mLlAccount = (LinearLayout) findViewById(R.id.ll_account);
        mLlAccount.setOnClickListener(this);
        mTvNike = (TextView) findViewById(R.id.tv_nike);
        mLlNick = (LinearLayout) findViewById(R.id.ll_nick);
        mLlNick.setOnClickListener(this);
        mTvSex = (TextView) findViewById(R.id.tv_sex);
        mLlSex = (LinearLayout) findViewById(R.id.ll_sex);
        mLlSex.setOnClickListener(this);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mLlAddress = (LinearLayout) findViewById(R.id.ll_address);
        mLlAddress.setOnClickListener(this);
        mIvCode = (ImageView) findViewById(R.id.iv_code);
        mLlCode = (LinearLayout) findViewById(R.id.ll_code);
        mLlCode.setOnClickListener(this);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mLlPhone = (LinearLayout) findViewById(R.id.ll_phone);
        mLlPhone.setOnClickListener(this);
        mTvEmail = (TextView) findViewById(R.id.tv_email);
        mLlEmail = (LinearLayout) findViewById(R.id.ll_email);
        mLlEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_save:
                break;
            case R.id.ll_account:
                break;
            case R.id.ll_nick:
                break;
            case R.id.ll_sex:
                dialogUtils.singleDialog(this,"选择性别");
                dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
                    @Override
                    public void NeutralListener(DialogInterface dialog, int which, String string) {
                        mTvSex.setText(string);
                    }

                    @Override
                    public void NegativeListener(DialogInterface dialog, int which) {

                    }
                });
                break;
            case R.id.ll_address:
                break;
            case R.id.ll_code:
                //显示个人名片
                final ImageView imageView = new ImageView(this);

                final String filePath = getFileRoot(this) + File.separator
                        + "qr_" + System.currentTimeMillis() + ".jpg";

                //二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
                final String study_code = (String) UtilsImp.spget("ck_user", "");

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        boolean success = QRCodeUtil.createQRImage(study_code, 800, 800,null,filePath);

                        if (success) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                                }
                            });
                        }
                    }
                }).start();
                dialogUtils.showViewDialog(this,"名片",imageView);

                break;
            case R.id.ll_phone:
                dialogUtils.showEditDialog(this,"修改手机号");
                dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
                    @Override
                    public void NeutralListener(DialogInterface dialog, int which, String string) {
                        mTvPhone.setText(string);
                        dialog.dismiss();
                    }

                    @Override
                    public void NegativeListener(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.ll_email:
                dialogUtils.showEditDialog(this,"修改邮箱");
                dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
                    @Override
                    public void NeutralListener(DialogInterface dialog, int which, String string) {
                        mTvEmail.setText(string);
                        dialog.dismiss();
                    }

                    @Override
                    public void NegativeListener(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }


    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }
}