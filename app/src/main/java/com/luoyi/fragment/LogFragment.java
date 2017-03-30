package com.luoyi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;

public class LogFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";

    public LogFragment() {
        // Required empty public constructor
    }

    public static LogFragment newInstance(String param1) {
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false);
    }


}
