package com.example.jclzh.shoolsports.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapapi.SDKInitializer;
import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.adatapter.HomepagerAdater;
import com.example.jclzh.shoolsports.utils.NetWorkUtil;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.viewutils.BottomNavigationViewHelper;
import com.example.jclzh.shoolsports.view.fragment.CommunityFragment;
import com.example.jclzh.shoolsports.view.fragment.MapPathFragment;
import com.example.jclzh.shoolsports.view.fragment.SportFragment;
import com.example.jclzh.shoolsports.view.fragment.UserFragment;
import com.example.jclzh.shoolsports.view.fragment.sporttabfragment.Sport_climbFragment;
import com.example.jclzh.shoolsports.view.myview.HomeViewpager;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MenuItem menuItem;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private HomeViewpager viewPagercont;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private Bundle savedInstanceState ;
    private TextView mHometvtoobar;
    //记录用户首次点击返回键的时间
    private long firstTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.savedInstanceState =savedInstanceState;
        //初始化百度地图

        isgps();
        initview();
        initicon();


    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(HomeActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 初始化主界面布局
     */
    private void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPagercont = findViewById(R.id.viewpager_home);
        viewPagercont.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //设置底部标签栏
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        mHometvtoobar = findViewById(R.id.hometexttoolbar);
       //取消底部的动画效果
       BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
       initfragmet();
    }

    /**
     * 初始化fragment
     */
    private void initfragmet() {

        List<Fragment> list  = new ArrayList<>();
        list.add(new SportFragment());
        list.add(new MapPathFragment());
        list.add(new CommunityFragment());
        list.add(new UserFragment());
        viewPagercont.setAdapter(new HomepagerAdater(getSupportFragmentManager(),list));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                menuItem = item;
                switch (item.getItemId()){
                    case R.id.navigation_sport:
                        viewPagercont.setCurrentItem(0);
                        mHometvtoobar.setText("运动");
                        break;
                    case R.id.navigation_mappath:
                        viewPagercont.setCurrentItem(1);
                        mHometvtoobar.setText("路线");
                        break;
                    case R.id.navigation_communtiy:
                        viewPagercont.setCurrentItem(2);
                        mHometvtoobar.setText("社区");
                        break;
                    case R.id.navigation_user:
                        viewPagercont.setCurrentItem(3);
                        mHometvtoobar.setText("我的");
                        break;

                }


                return false;
            }
        });

    }


    /**
     * 初始化icon   （建议32*32 大小做相应的处理）
     */
    private void initicon() {
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.icon_girle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {
            //今日运动
        }else if (id == R.id.nav_gallery) {
            //历史记录
            startActivity(new Intent(HomeActivity.this,HistoricalActivity.class));

        } else if (id == R.id.nav_slideshow) {
            //运动路线

        } else if (id == R.id.nav_manage) {
            //运动排名.

        }else if (id ==R.id.nav_shequ){
            //健康评估

        } else if (id==R.id.nav_shop){
            //积分商场
        }else if (id == R.id.nav_share) {
            //在线换肤

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 判断GPS是够开启
     * @return
     */
    public void isgps() {

        boolean gps = NetWorkUtil.isGps(HomeActivity.this);
        if (!gps){
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

            builder.setMessage("检测到您目前暂未开启GPS定位服务,这将会影响到您的用户体验");

            builder.setNegativeButton("暂时不开启", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            builder.setPositiveButton("现在去开启", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // 转到手机设置界面，用户设置GPS
                    Intent intent = new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.show();
        }

    }
}
