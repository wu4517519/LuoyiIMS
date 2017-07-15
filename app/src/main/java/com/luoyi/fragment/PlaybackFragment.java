package com.luoyi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;

public class PlaybackFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView playback_lv;

    public PlaybackFragment() {
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
    public static PlaybackFragment newInstance(String param1) {
        PlaybackFragment fragment = new PlaybackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_playback, container, false);
        playback_lv = (ListView)view.findViewById(R.id.playback_lv);
        refreshView();
        return view;
    }

    private MyAdapter myadapter;
    private void refreshView() {


        myadapter = new MyAdapter();
        playback_lv.setAdapter(myadapter);

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
                view=View.inflate(parent.getContext(), R.layout.item_playback, null);
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
