package com.example.jclzh.shoolsports.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.adatapter.HomepagerAdater;
import com.example.jclzh.shoolsports.utils.viewutils.BottomNavigationViewHelper;
import com.example.jclzh.shoolsports.view.fragment.CommunityFragment;
import com.example.jclzh.shoolsports.view.fragment.MapPathFragment;
import com.example.jclzh.shoolsports.view.fragment.SportFragment;
import com.example.jclzh.shoolsports.view.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MenuItem menuItem;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private ViewPager viewPagercont;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private Bundle savedInstanceState ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.savedInstanceState =savedInstanceState;
        initview();
        initicon();

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
                        break;
                    case R.id.navigation_mappath:
                        viewPagercont.setCurrentItem(1);
                        break;
                    case R.id.navigation_communtiy:
                        viewPagercont.setCurrentItem(2);
                        break;
                    case R.id.navigation_user:
                        viewPagercont.setCurrentItem(3);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
