package com.luoyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.luoyi.activity.MonitorPlayActivity;
import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;

public class MonitorFragment extends BaseFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private ListView monitor_lv;
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
        monitor_lv = (ListView)view.findViewById(R.id.monitor_lv);

        refreshView();
//        monitor_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//               //parent.getItemAtPosition(position);
//                Intent intent=new Intent();
//                intent.setClass(getActivity(), MonitorPlayActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }


    private MyAdapter myadapter;
    private void refreshView() {


        myadapter = new MyAdapter();
        monitor_lv.setAdapter(myadapter);

    }

    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 10;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view ;

            if(convertView==null){
                view=View.inflate(parent.getContext(), R.layout.item_monitor, null);
            }
            else {
                view=convertView;
            }

            ImageView video_iv = (ImageView) view.findViewById(R.id.video_iv);
            video_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent();
                    intent.setClass(getActivity(), MonitorPlayActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }


        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }



    }


}
