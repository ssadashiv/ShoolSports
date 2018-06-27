package com.example.jclzh.shoolsports.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.ShopAdapter;
import com.example.jclzh.shoolsports.model.bean.ShopBean;
import com.example.jclzh.shoolsports.utils.AlertDialogUtils;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.view.myview.PullToRefresh;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private PullToRefresh mLvShop;
    private List<ShopBean.DataBean> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData(true);
        initAdapter();
    }

    private void initAdapter() {
        mLvShop.setOnRefreshListener(new PullToRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                initData(true);
                Toast.makeText(ShopActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                Toast.makeText(ShopActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();

                mLvShop.onRefreshComplete(false);
            }
        });
        mLvShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mList != null && mList.size() > 0) {
                    int integral = ApplicationDate.USER.getUser().getIntegral();
                    int headerViewsCount = mLvShop.getHeaderViewsCount();// 获取头布局数量
                    position = position - headerViewsCount;// 需要减去头布局的占位
                    final ShopBean.DataBean bean = mList.get(position);
                    //判断商品是否大于自己的积分
                    if (bean.getIntegral() > integral) {
                        AlertDialogUtils dialogUtils = new AlertDialogUtils();
                        dialogUtils.showDialog(ShopActivity.this, "兑换商品", "您当前积分：" + integral + "。积分不足！");
                        dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
                            @Override
                            public void NeutralListener(DialogInterface dialog, int which, String string) {
                                dialog.dismiss();
                            }

                            @Override
                            public void NegativeListener(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    } else {
                        AlertDialogUtils dialogUtils = new AlertDialogUtils();
                        dialogUtils.showDialog(ShopActivity.this, "兑换商品", "您当前积分：" + integral + "。所需积分" + bean.getIntegral());
                        dialogUtils.setDialogInterface(new AlertDialogUtils.DialogInterface() {
                            @Override
                            public void NeutralListener(DialogInterface dialog, int which, String string) {
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("user_id",ApplicationDate.USER.getUser().getUsers_id()+"");
                                hashMap.put("shop_id",bean.getShop_id()+"");
                                NetUtils.jsonget(ApplicationDate.API_BUYS_SHOP, hashMap, new NetListener() {
                                    @Override
                                    public void yeslistener(JSONObject jsonObject) {
                                        Toast.makeText(ShopActivity.this, "兑换成功"+jsonObject.toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void errorlistener(String error) {
                                        Toast.makeText(ShopActivity.this, "兑换失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.dismiss();
                            }

                            @Override
                            public void NegativeListener(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                    }

                }
            }
        });
    }

    private void initData(final boolean b) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("scholl_id",ApplicationDate.USER.getUser().getScholl_id()+"");
        NetUtils.jsonget(ApplicationDate.API_GET_ALL_SHOP, hashMap, new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {
                Gson gson = new Gson();
                ShopBean bean = gson.fromJson(jsonObject.toString(), ShopBean.class);
                mList = bean.getData();
                mLvShop.setAdapter(new ShopAdapter(ShopActivity.this, mList,R.layout.shop_item));
                mLvShop.onRefreshComplete(b);
            }

            @Override
            public void errorlistener(String error) {
                Toast.makeText(ShopActivity.this, "商品获取失败，请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        ImageView mIvBack = findViewById(R.id.toolbar_finsh);
        mLvShop = findViewById(R.id.lv_shop);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
