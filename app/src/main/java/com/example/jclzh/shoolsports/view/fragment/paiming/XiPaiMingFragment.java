package com.example.jclzh.shoolsports.view.fragment.paiming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.adatapter.PaiMingAdatabter;
import com.example.jclzh.shoolsports.model.bean.PaiMingBean;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiPaiMingFragment extends Fragment {


    private View view;
    private ListView mListviewXipaiming;

    public XiPaiMingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xi_pai_ming, container, false);
        initView(view);
        initdata();
        return view;
    }

    private void initView(View view) {
        mListviewXipaiming = (ListView) view.findViewById(R.id.listview_xipaiming);
    }

    /**
     * 获学校排名数据
     */
    private void initdata() {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", "day");
//        map.put("gettype", "school");
//        map.put("gettype", "class");
        map.put("gettype", "department");
        map.put("data", "" + ApplicationDate.USER.getUser().getDepartment());
        map.put("tag", "1");

        NetUtils.jsonget(ApplicationDate.API_PAIMINGMING, map, new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {
                MLog.i("排名数据", jsonObject.toString());

                PaiMingBean paiMingBean = new Gson().fromJson(jsonObject.toString(), PaiMingBean.class);
                mListviewXipaiming.setAdapter(new PaiMingAdatabter(getActivity(), paiMingBean.getData()));
            }

            @Override
            public void errorlistener(String error) {
                MLog.i("排名数据获取异常", error);
            }
        });

    }
}
