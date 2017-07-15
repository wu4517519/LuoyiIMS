package com.luoyi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;

public class LogFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private ListView log_lv;
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

        View view =inflater.inflate(R.layout.fragment_log, container, false);
        log_lv = (ListView)view.findViewById(R.id.log_lv);
        refreshView();
        return view;
    }

    private MyAdapter myadapter;
    private void refreshView() {


            myadapter = new MyAdapter();
            log_lv.setAdapter(myadapter);

    }

    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view ;

            if(convertView==null){
                view=View.inflate(parent.getContext(), R.layout.item_log, null);
            }
            else {
                view=convertView;
            }

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
