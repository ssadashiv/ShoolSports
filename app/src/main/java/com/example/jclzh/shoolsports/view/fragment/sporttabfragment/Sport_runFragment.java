package com.example.jclzh.shoolsports.view.fragment.sporttabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jclzh.shoolsports.R;

/**
 * 这是跑步模块
 * A simple {@link Fragment} subclass.
 */
public class Sport_runFragment extends Fragment {


    public Sport_runFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport_run, container, false);
    }

}
