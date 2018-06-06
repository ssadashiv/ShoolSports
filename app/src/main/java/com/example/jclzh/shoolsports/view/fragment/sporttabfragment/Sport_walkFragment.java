package com.example.jclzh.shoolsports.view.fragment.sporttabfragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.service.StepService;
import com.example.jclzh.shoolsports.utils.UpdateUiCallBack;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.example.jclzh.shoolsports.view.myview.StepArcView;

/**
 * 这是步行模块
 * A simple {@link Fragment} subclass.
 */
public class Sport_walkFragment extends Fragment {


    private StepArcView cc;
    private TextView tv_isSupport;
    public Sport_walkFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view  = inflater.inflate(R.layout.fragment_sport_walk, container, false);
        initview(view);
        initData();
        return  view  ;
    }

    private void initview(View view) {
        cc = (StepArcView) view.findViewById(R.id.cc);
        tv_isSupport = (TextView) view.findViewById(R.id.tv_isSupport);
    }




    private void initData() {

        //获取用户设置的计划锻炼步数，没有设置过的话默认7000
        String planWalk_QTY = (String) UtilsImp.spget("planWalk_QTY", "7000");
        //设置当前步数为0
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);
        tv_isSupport.setText("计步中...");
        setupService();
    }


    private boolean isBind = false;

    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(getActivity(), StepService.class);
        isBind = getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
       getActivity().startService(intent);
    }

    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            //设置初始化数据
            String planWalk_QTY = (String) UtilsImp.spget("planWalk_QTY", "7000");
            cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepService.getStepCount());

            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    String planWalk_QTY = (String) UtilsImp.spget("planWalk_QTY", "7000");
                    cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            getActivity().unbindService(conn);
        }
    }



}
