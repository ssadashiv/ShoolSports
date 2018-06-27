package com.example.jclzh.shoolsports.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.utils.ToastUtil;

public class JianKangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarFinsh;
    private EditText mEtTizhong;
    /**
     * cm/厘米
     */
    private TextView mEtShengao;
    /**
     * 测试健康值
     */
    private Button mBtCeshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_kang);
        initView();
    }

    private void initView() {
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
        mEtTizhong = (EditText) findViewById(R.id.et_tizhong);
        mEtShengao = (TextView) findViewById(R.id.et_shengao);
        mBtCeshi = (Button) findViewById(R.id.bt_ceshi);
        mBtCeshi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_finsh:
                finish();
                break;
            case R.id.bt_ceshi:
                String shengao = mEtShengao.getText().toString().trim();
                String tizhong = mEtTizhong.getText().toString().trim();
                if (shengao.equals("")|| tizhong.equals("")||shengao==null||tizhong==null){

                    ToastUtil.showShort(JianKangActivity.this,"请您输入您的正确的身体信息");

                }else {

                    Intent intent = new Intent(JianKangActivity.this, JianKangJieguoActivity.class);
                    intent.putExtra("shengao",Integer.parseInt(shengao));
                    intent.putExtra("tizhong",Integer.parseInt(tizhong));
                    startActivity(intent);
                    finish();

                }
                break;
        }
    }
}
