package com.example.jclzh.shoolsports.view.fragment.sporttabfragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.bean.AllRenwu;
import com.example.jclzh.shoolsports.model.bean.User;
import com.example.jclzh.shoolsports.model.service.StepService;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.UpdateUiCallBack;
import com.example.jclzh.shoolsports.utils.UtilsImp;
import com.example.jclzh.shoolsports.view.activity.LoginActivity;
import com.example.jclzh.shoolsports.view.myview.StepArcView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是步行模块
 * A simple {@link Fragment} subclass.
 */
public class Sport_walkFragment extends Fragment implements View.OnClickListener {


    private StepArcView cc;
    private TextView tv_isSupport;
    private View view;
    private StepArcView mCc;
    /**
     * 该设备支持计步
     */
    private TextView mTvIsSupport;
    /**
     * 20000步
     */
    private TextView mTvMubiao;
    /**
     * 20000步
     */
    private TextView mTvJuli;
    /**
     * 卡路里
     */
    private TextView mTvKaluli;
    /**
     * 21%
     */
    private TextView mTvJindu;
    /**
     * 当前时速
     */
    private TextView mTvShisu;
    private Switch mSwitvhYuyin;
    private FrameLayout mRunFragementlayout;
    private String distance = "0";
    /**
     * 关闭
     */
    private TextView mTvYuyin;
    private Handler handler;
    private Runnable runnable;
    private User user;

    private   int  startcontstep  = 0 ;
    public Sport_walkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sport_walk, container, false);
        volleypost();
        initView(view);
        initData();
        taskrun();


        return view;
    }

    /**
     * 每隔一段时间提交自己的运动信息到服务器
     */
    private void savenettask( final  String distance, final User user) {



                Map map   =   new HashMap();
                map.put("tag","1");
                map.put("distance",distance);
                MLog.d("提交当前米数",distance);
                map.put("user_id",user.getUser().getUsers_id()+"");
                map.put("scholl_id",user.getUser().getScholl_id()+"");
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

    private void volleypost() {
        //请求任务
        String   jsonuser = (String) UtilsImp.spget("user", "");
        Log.i("全局USER", "run: "+jsonuser);

            if (!jsonuser.equals("") ) {
                Gson gson = new Gson();
                user = gson.fromJson(jsonuser, User.class);
                Map map = new HashMap();
                map.put("user_id",String.valueOf( user.getUser().getUsers_id()));
                map.put("scholl_id",String.valueOf(user.getUser().getScholl_id()));
//                map.put("token",String.valueOf(user.getToken()));

                MLog.e("json解析", user.getUser().getUsers_id()+"");
                NetUtils.jsonget(ApplicationDate.API_LOGIN_RENWU, map, new NetListener() {
                    @Override
                    public void yeslistener(JSONObject jsonObject) {
                        try {
                            distance = jsonObject.getJSONArray("alldata").getJSONObject(0).getString("distance");
                            mTvMubiao.setText(distance+"步");
                            if (!distance.equals("0")) {
                                UtilsImp.spput("planWalk_QTY", distance);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int    bushucont   = (int) UtilsImp.spget("buxingbushu", 0);
                        mTvJindu.setText(UtilsImp.getbaifenbi(bushucont,Integer.valueOf(distance)));
                        mTvKaluli.setText(bushucont*0.1441+"千卡");


                    }

                    @Override
                    public void errorlistener(String error) {
                        tv_isSupport.setVisibility(View.VISIBLE);
                        tv_isSupport.setText("请检查网络是否开启");
                        ToastUtil.showShort(getActivity(),"请检查网络无法获取数据");

                    }
                });


            }else {
                Toast.makeText(getActivity(), "登录失效请重新登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }


    }


    /**
     * 实时根据网络刷新界面
     */
    private void taskrun() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                if (distance.equals("0")){
                    MLog.e("任务异常","学校目前还没有发布任务");
                }else {
                    mTvMubiao.setText(distance+"步");
                    handler.postDelayed(this,1*1000);
                }
            }
        };

        handler.post(runnable);
    }





    @Override
    public void onStart() {
        super.onStart();
    }


    private void initData() {

        //获取用户设置的计划锻炼步数，没有设置过的话默认7000
        String planWalk_QTY = (String) UtilsImp.spget("planWalk_QTY", "7000");
        //设置当前步数为0
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);

        setupService();

        int    bushucont   = (int) UtilsImp.spget("buxingbushu", 0);
        int stepnuber = bushucont;
        mTvJuli.setText(((float)(stepnuber*0.4))+"米");
        Log.i("获取当前步数", "run: "+cc.getstepNumber()+"步");
        if (distance.equals("0")){
            mTvJindu.setText("100%");
            return;
        }else if (stepnuber>Integer.valueOf(distance) ||stepnuber>Integer.valueOf(distance)){
            mTvJindu.setText("100%");
            return;
        }else  {
            String getbaifenbi = UtilsImp.getbaifenbi(stepnuber, Integer.valueOf(distance));
            mTvJindu.setText(getbaifenbi+"%");
        }
        mTvJindu.setText(UtilsImp.getbaifenbi(bushucont,Integer.valueOf(distance)));
        mTvKaluli.setText((float)((stepnuber*0.4)*0.01441)+"千卡");
    }


    private boolean isBind = false;

    /**23
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
            MLog.d("服务","步数监听开启");
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
//                    MLog.i("服务", "回调步数监听");
                    String planWalk_QTY = (String) UtilsImp.spget("planWalk_QTY", "7000");
                    cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                    UtilsImp.spput("buxingbushu",stepCount);
                    //服务监听回调刷新UI
                    int stepnuber = stepCount;
                    mTvJuli.setText(((float)(stepnuber*0.4))+"米");
                    Log.i("获取当前步数", "run: "+cc.getstepNumber()+"步");
                    if (distance.equals("0")){
                        mTvJindu.setText("100%");
                        return;
                    }else if (stepnuber>Integer.valueOf(distance) ||stepnuber>Integer.valueOf(distance)){
                        mTvJindu.setText("100%");
                        return;
                    }else  {
                        String getbaifenbi = UtilsImp.getbaifenbi(stepnuber, Integer.valueOf(distance));
                        mTvJindu.setText(getbaifenbi+"%");
                    }
                    mTvKaluli.setText((float)((stepnuber*0.4)*0.01441)+"千卡");


                    if (stepCount-startcontstep >40){
                        //如果走了500步就提交一次数据到服务器
                        savenettask(((float)(stepCount*0.4))+"", user);
                        startcontstep = stepCount ;
                    }


                }
            });;

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
        if (handler!=null&&runnable!=null){
            handler.removeCallbacks(runnable);
        }
    }


    private void initView(View view) {
        cc = (StepArcView) view.findViewById(R.id.cc);
        tv_isSupport = (TextView) view.findViewById(R.id.tv_isSupport);
        mCc = (StepArcView) view.findViewById(R.id.cc);
        mTvIsSupport = (TextView) view.findViewById(R.id.tv_isSupport);
        mTvMubiao = (TextView) view.findViewById(R.id.tv_mubiao);
        mTvJuli = (TextView) view.findViewById(R.id.tv_juli);
        mTvKaluli = (TextView) view.findViewById(R.id.tv_kaluli);
        mTvJindu = (TextView) view.findViewById(R.id.tv_jindu);
        mTvShisu = (TextView) view.findViewById(R.id.tv_shisu);
        mSwitvhYuyin = (Switch) view.findViewById(R.id.switvh_yuyin);
        mRunFragementlayout = (FrameLayout) view.findViewById(R.id.run_fragementlayout);
        mCc.setOnClickListener(this);
        mTvIsSupport.setOnClickListener(this);
        mTvMubiao.setOnClickListener(this);
        mTvJuli.setOnClickListener(this);
        mTvKaluli.setOnClickListener(this);
        mTvJindu.setOnClickListener(this);
        mTvShisu.setOnClickListener(this);
        mTvYuyin = (TextView) view.findViewById(R.id.tv_yuyin);
        mSwitvhYuyin.setOnClickListener(this);
        mRunFragementlayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.cc:
                break;
            case R.id.tv_isSupport:
                break;
            case R.id.tv_mubiao:
                break;
            case R.id.tv_juli:
                break;
            case R.id.tv_kaluli:
                break;
            case R.id.tv_jindu:
                break;
            case R.id.tv_shisu:
                break;
            case R.id.switvh_yuyin:
                break;
            case R.id.run_fragementlayout:
                break;
        }
    }
}
