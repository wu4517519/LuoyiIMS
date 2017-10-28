package com.luoyi.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.luoyi.bean.AlarmLog;
import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;
import com.luoyi.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LogFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String TAG ="LogFragment" ;
    private ListView log_lv;
    private static String userId;
    List<AlarmLog>  list;
    public LogFragment() {
        // Required empty public constructor
    }

    public static LogFragment newInstance(String param1, String userId_) {
        userId=userId_;
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
        Log.i("log=====","onCreateView");
        getServerData();

        return view;
    }

    private void getServerData() {
        RequestParams requestParams = new RequestParams(Constant.FIND_ALARM_LOG);
        requestParams.addBodyParameter("userId",userId);

        x.http().post(requestParams,alarm_logCallback);

    }
    Callback.CommonCallback<String> alarm_logCallback= new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            Gson gson = new GsonBuilder().create();

            list =gson.fromJson(result, new TypeToken<List<AlarmLog>>() {}.getType());
            Log.i("log=====",list.size()+"");
            refreshView();
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {

        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    };
    private MyAdapter myadapter;
    private void refreshView() {


            myadapter = new MyAdapter();
            log_lv.setAdapter(myadapter);

    }



    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {

            return list.size();
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
            AlarmLog alarmLog = list.get(position);


            ImageView log_iv= (ImageView) view.findViewById(R.id.log_iv);
            TextView alarm_type_tv= (TextView) view.findViewById(R.id.alarm_type_tv);
            TextView date_tv= (TextView) view.findViewById(R.id.date_tv);
            Log.i(TAG,Constant.BASE_PATH+alarmLog.getAlarmImgPath());
            try {
                URL url = new URL(Constant.BASE_PATH+alarmLog.getAlarmImgPath());
                log_iv.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            alarm_type_tv.setText(alarmLog.getType()+"");
            date_tv.setText(alarmLog.getTime()+"");

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
