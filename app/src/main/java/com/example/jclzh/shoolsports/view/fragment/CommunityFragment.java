package com.example.jclzh.shoolsports.view.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.base.adapter.recycle.CRecycleAdapter;
import com.example.jclzh.shoolsports.base.adapter.recycle.ViewHolder;
import com.example.jclzh.shoolsports.base.imageloder.ImageHelper;
import com.example.jclzh.shoolsports.base.imageloder.config.ImageConfig;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.bean.CommunityBean;
import com.example.jclzh.shoolsports.utils.GsonUtil;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主界面社区模块
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment implements CommunityInterface {


    private XRecyclerView community_recycle;
    private CommunityAdapter communityAdapter;
    private int position = 1;

    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_community, container, false);
        initView(inflate);
        NetWork();
        return inflate;
    }

    private ArrayList<CommunityBean.DataBean> list;

    private void initView(View inflate) {
        community_recycle = (XRecyclerView) inflate.findViewById(R.id.community_recycle);
        community_recycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        list = new ArrayList<>();
        position = 1;
        communityAdapter = new CommunityAdapter(getActivity(), this, list);
        community_recycle.setAdapter(communityAdapter);
        community_recycle.addItemDecoration(decoration);
        community_recycle.setLoadingMoreEnabled(false);
        community_recycle.setPullRefreshEnabled(false);
        community_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                position = 1;
                NetWork();
            }

            @Override
            public void onLoadMore() {
                NetWork();
            }
        });
    }

    CommunityBean communityBean = null;


    public void NetWork() {
        NetUtils.jsonget(ApplicationDate.API_getchallenge_URL, null, new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {
                new GsonUtil<CommunityBean>(jsonObject.toString()) {
                    @Override
                    protected void Success(CommunityBean communityBean) {
                        CommunityFragment.this.communityBean = communityBean;
                    }
                };
                if (position <= 1) {
                    list.clear();
                } else {

                }
                if (communityBean.data == null) {
                    return;
                }
                list.addAll(communityBean.data);
                communityAdapter.notifyDataSetChanged();
            }

            @Override
            public void errorlistener(String error) {

            }
        });

    }

    @Override
    public void onSuccess(final CommunityBean.DataBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setText(bean.title);
        textView.setPadding(10, 5, 10, 5);
        builder.setTitle("确定接受挑战？").setView(textView).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String, String> map = new HashMap<>();
                map.put("news_id", bean.newId);
                map.put("user_id", ApplicationDate.USER.getUser().getUsers_id() + "");
                NetUtils.jsonget(ApplicationDate.API_acceptchallenge_URL, map, new NetListener() {
                    @Override
                    public void yeslistener(JSONObject jsonObject) {
                        try {
                            String status = jsonObject.getString("status");
                            if (status.equals("1")) {
                                Toast.makeText(getActivity(), "接受挑战成功", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void errorlistener(String error) {

                    }
                });
            }
        }).create().show();
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }


    public class CommunityAdapter extends CRecycleAdapter<CommunityBean.DataBean> {
        private CommunityInterface communityInterface;

        public CommunityAdapter(Context context, CommunityInterface communityInterface, List<CommunityBean.DataBean> datas) {
            super(context, R.layout.community_adapter, datas);
            this.communityInterface = communityInterface;
        }

        @Override
        protected void convert(ViewHolder holder, final CommunityBean.DataBean communityBean, int position) {
            holder.setText(R.id.community_item_distance, "距离:" + communityBean.distance);
            Log.i("----", "convert: " + ApplicationDate.API_IMG_URL + communityBean.image);
            ImageHelper.obtain().load(new ImageConfig<>(mContext).setUrl(ApplicationDate.API_IMG_URL + communityBean.image).setImageView((ImageView) holder.getView(R.id.community_item_img)));
            ImageHelper.obtain().load(new ImageConfig<>(mContext).setUrl(ApplicationDate.API_IMG_URL + communityBean.icon).setImageView((ImageView) holder.getView(R.id.community_item_icon)));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (communityInterface != null) {
                        communityInterface.onSuccess(communityBean);
                    }
                }
            });
        }
    }
}
