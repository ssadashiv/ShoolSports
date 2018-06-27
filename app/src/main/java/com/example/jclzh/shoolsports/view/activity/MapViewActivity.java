package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;

public class MapViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarFinsh;
    private WebView mMapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        initView();
    }

    private void initView() {
        mToolbarFinsh = (ImageView) findViewById(R.id.toolbar_finsh);
        mToolbarFinsh.setOnClickListener(this);
        mMapview = (WebView) findViewById(R.id.mapview);
        mMapview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings settings = mMapview.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        mMapview.getSettings().setUseWideViewPort(true);
        mMapview.getSettings().setLoadWithOverviewMode(true);

        settings.setAllowFileAccess(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 9);// 设置缓冲大小
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        mMapview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mMapview.loadUrl(ApplicationDate.MAPH5URL);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_finsh:
                finish();
                break;
        }
    }
}
