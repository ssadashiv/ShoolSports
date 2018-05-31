package com.example.jclzh.shoolsports.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.jclzh.shoolsports.R;

/**
 * 地图模块
 * A simple {@link Fragment} subclass.
 */
public class MapPathFragment extends Fragment {


    private View view;
    private MapView mHomeMapview;

    public MapPathFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_path, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mHomeMapview = (MapView) view.findViewById(R.id.home_mapview);
        BaiduMap map = mHomeMapview.getMap();

    }
}
