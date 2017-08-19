package com.luoyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luoyi.activity.MonitorPlayActivity;
import com.luoyi.adapter.MyAdapter;
import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;

public class MonitorFragment extends BaseFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String[] datas = {"a","b", "c","d"};//测试
    private RecyclerView monitor_rv;
    private RecyclerView monitor_rv2;
    private ImageView iv_menu;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    public MonitorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MonitorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonitorFragment newInstance(String param1) {
        MonitorFragment fragment = new MonitorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_monitor, container, false);
        monitor_rv = (RecyclerView)view.findViewById(R.id.monitor_rv);
        monitor_rv2 = (RecyclerView)view.findViewById(R.id.monitor_rv2);

        linearLayoutManager=new LinearLayoutManager(container.getContext());
        monitor_rv.setLayoutManager(linearLayoutManager);
        gridLayoutManager = new GridLayoutManager(container.getContext(),2);
        monitor_rv2.setLayoutManager(gridLayoutManager);

        iv_menu = (ImageView)view.findViewById(R.id.iv_menu);


        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.GONE==monitor_rv.getVisibility()){
                    monitor_rv.setVisibility(View.VISIBLE);
                    monitor_rv2.setVisibility(View.GONE);
                }else{
                    monitor_rv.setVisibility(View.GONE);
                    monitor_rv2.setVisibility(View.VISIBLE);
                }
            }
        });
        set_adapter();

        return view;
    }


    private MyAdapter myadapter;
    private MyAdapter myadapter2;
    private void set_adapter() {


        myadapter = new MyAdapter(datas,R.layout.item_monitor);
        monitor_rv.setAdapter(myadapter);
        myadapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {

                Intent intent =new Intent();
                intent.setClass(getActivity(), MonitorPlayActivity.class);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {


            }
        });

        myadapter2 = new MyAdapter(datas,R.layout.item_monitor2);
        monitor_rv2.setAdapter(myadapter2);
        myadapter2.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {

                Intent intent =new Intent();
                intent.setClass(getActivity(), MonitorPlayActivity.class);
                startActivity(intent);
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {

            }
        });

    }




}
