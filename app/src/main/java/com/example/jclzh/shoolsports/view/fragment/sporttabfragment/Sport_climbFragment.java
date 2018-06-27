package com.example.jclzh.shoolsports.view.fragment.sporttabfragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.service.StepService;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.utils.UpdateUiCallBack;
import com.example.jclzh.shoolsports.utils.UtilsImp;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *这是骑行运动模块
 * A simple {@link Fragment} subclass.
 */
public class Sport_climbFragment extends Fragment {


    private View view;
    /**
     * 开始骑行
     */
    private TextView mTvStart;
    /**
     * 该设备支持计步
     */
    private TextView mTvIsSupport;
    /**
     * 0步
     */
    private TextView mTvMubiao;
    /**
     * 0步
     */
    private TextView mTvJuli;
    /**
     * 0卡
     */
    private TextView mTvKaluli;
    /**
     * 0%
     */
    private TextView mTvJindu;
    /**
     * 当前时速
     */
    private TextView mTvShisu;
    /**
     * 关闭
     */
    private TextView mTvYuyin;
    private Switch mSwitvhYuyin;
    private long time;
    private Runnable runnable;

    public Sport_climbFragment() {
        // Required empty public constructor
    }
    private   boolean  kaiguan   =false;

    Handler handler  = new Handler();

    private  int   stepstart = 0 ;
    SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("HH:mm:ss");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sport_climb, container, false);
        initView(view);
        initdown();
        return view;
    }

    /**
     * 获取下面那一边栏的信息
     */
    private void initdown() {


        //todo 任务信息





    }

    private void savenettask( final  String distance, String userid, String schoolid) {

        Map map   =   new HashMap();
        map.put("tag","4");
        map.put("distance",distance);
        MLog.d("提交当前米数",distance);
        map.put("user_id",userid);
        map.put("scholl_id",schoolid);
        NetUtils.jsonget(ApplicationDate.API_LOGIN_SAVERENWU, map, new NetListener() {
            @Override
            public void yeslistener(JSONObject jsonObject) {

                MLog.i("运动步行界面保存到网络服务器成功",jsonObject.toString());
            }

            @Override
            public void errorlistener(String error) {
                MLog.e("运动步行界面","保存到远程服务器失败"+error);
            }
        });


    }

    /**
     * 向服务器提交数据
     */
    private void initservice() {

        ServiceConnection serviceconnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                StepService stepService = ((StepService.StepBinder) service).getService();
                stepService.registerCallback(new UpdateUiCallBack() {
                    @Override
                    public void updateUi(int stepCount) {
                        UtilsImp.spput("sports4",stepCount);

                        //判断骑行是否开启
                        if (kaiguan){
                            if (stepstart==0){
                                stepstart = stepCount ;
                            }
                            int nowrun  = stepCount - stepstart ;
                            mTvMubiao.setText(nowrun+"步");
                            mTvJuli.setText(((float)(nowrun*0.4))+"米");
                            mTvKaluli.setText((Double)((nowrun*0.4)*0.01441)+"千卡");
                            savenettask(nowrun+"",ApplicationDate.USER.getUser().getUsers_id()+"",ApplicationDate.USER.getUser().getScholl_id()+"");
                        }else {
                            stepstart= 0 ;

                        }

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }  ;

        //开启计步服务
        Intent intent = new Intent(getActivity(), StepService.class);
        getActivity().bindService(intent, serviceconnection, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);


    }

    private void initView(View view) {
        mTvStart = (TextView) view.findViewById(R.id.tv_start);
        mTvIsSupport = (TextView) view.findViewById(R.id.tv_isSupport);
        mTvMubiao = (TextView) view.findViewById(R.id.tv_mubiao);
        mTvJuli = (TextView) view.findViewById(R.id.tv_juli);
        mTvKaluli = (TextView) view.findViewById(R.id.tv_kaluli);
        mTvJindu = (TextView) view.findViewById(R.id.tv_jindu);
        mTvShisu = (TextView) view.findViewById(R.id.tv_shisu);
        mTvYuyin = (TextView) view.findViewById(R.id.tv_yuyin);
        mSwitvhYuyin = (Switch) view.findViewById(R.id.switvh_yuyin);
        mTvStart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (mTvStart.getText().equals("结束骑行")) {
                    mTvStart.setBackgroundResource(R.drawable.greed_shaperyuan);
                    mTvIsSupport.setText("长按按钮即可开始骑行");
                    mTvStart.setText("开始骑行");
                    Toast.makeText(getActivity(), "您这次骑行已结束,棒棒哒~", Toast.LENGTH_SHORT).show();
                    kaiguan =false;
                    inittime(false);

                }else {
                    mTvIsSupport.setText("长按按钮即可结束骑行");

                    mTvStart.setBackgroundResource(R.drawable.reed_shaperyuan);
                    mTvStart.setText("结束骑行");
                    Toast.makeText(getActivity(), "您这次骑行已开启，加油呀~", Toast.LENGTH_SHORT).show();
                    initservice();
                    inittime(true);

                    kaiguan =true ;
                }



                return true;
            }
        });
    }

    /**
     * 定时器开启关闭
     * @param b
     */
    private void inittime(boolean b) {
        time = 576000000;

        if (b){
            runnable = new Runnable() {
                @Override
                public void run() {
                    time = time + 500 ;

                    mTvShisu.setText( simpleDateFormat.format(new Date(time)));
                    handler.postDelayed(this,500);
                }
            };
            handler.post(runnable);

        }else {

            handler.removeCallbacks(runnable);

        }



    }

}
