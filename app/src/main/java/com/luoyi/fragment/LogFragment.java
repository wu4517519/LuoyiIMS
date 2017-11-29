package com.luoyi.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;
import java.util.List;

public class LogFragment extends BaseFragment {

    private ListView log_lv;
    private static String userId;
    List<AlarmLog>  list;
    private ListViewAdapter listViewAdapter;

    public LogFragment() {

    }

    public static LogFragment newInstance(String fragmentName, String userId) {
        Log.d(Constant.TAG, "LogFragment NewInstance");
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        args.putString("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(Constant.TAG, "LogFragment onCreate");
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            userId = (String)getArguments().get("userId");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(Constant.TAG, "LogFragment onCreateView");
        View view =inflater.inflate(R.layout.fragment_log, container, false);
        log_lv = (ListView)view.findViewById(R.id.log_lv);
        Log.i(Constant.TAG,"LogFragment onCreateView");
        getAlarmLog();
        return view;
    }

    private void getAlarmLog() {
        RequestParams requestParams = new RequestParams(Constant.FIND_ALARM_LOG);
        requestParams.addBodyParameter("userId",userId);
        x.http().post(requestParams,alarm_logCallback);

    }



    private void refreshView() {
        listViewAdapter = new ListViewAdapter();
        log_lv.setAdapter(listViewAdapter);
    }


    Callback.CommonCallback<String> alarm_logCallback= new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            if(!TextUtils.isEmpty(result) && !result.equals(Constant.FAILURE)){
                Gson gson = new GsonBuilder().create();
                list = gson.fromJson(result, new TypeToken<List<AlarmLog>>() {}.getType());
                refreshView();
            }
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            ex.printStackTrace();
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    };

    private class ListViewAdapter extends BaseAdapter {

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

            ImageView log_iv= (ImageView) view.findViewById(R.id.iv_alarmlog);
            TextView alarm_type_tv= (TextView) view.findViewById(R.id.alarm_type_tv);
            TextView date_tv= (TextView) view.findViewById(R.id.tv_alarm_time);
            x.image().bind(log_iv, Constant.BASE_PATH+list.get(position).getAlarmImgPath());
            /*try {
                URL url = new URL(Constant.BASE_PATH+alarmLog.getAlarmImgPath());
                log_iv.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }*/

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
