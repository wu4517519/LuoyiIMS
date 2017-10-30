package com.luoyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luoyi.activity.MonitorPlayActivity;
import com.luoyi.adapter.MyAdapter;
import com.luoyi.bean.Device;
import com.luoyi.common.DeviceCallback;
import com.luoyi.fragment.base.BaseFragment;
import com.luoyi.luoyiims.R;
import com.luoyi.utils.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.List;
import org.xutils.x;

public class MonitorFragment extends BaseFragment implements DeviceCallback.DeviceOperCallback{

    private RecyclerView monitor_rv;
    private RecyclerView monitor_rv2;
    private ImageView iv_menu;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myadapter;
    private MyAdapter myadapter2;
    private String userId;
    private List<Device> deviceList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MonitorFragment() {

    }

    public static MonitorFragment newInstance(String param1) {
        MonitorFragment fragment = new MonitorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getDeviceList();
        return view;
    }



    private void set_adapter() {
        myadapter = new MyAdapter(deviceList,R.layout.item_monitor);
        monitor_rv.setAdapter(myadapter);
        myadapter.setDeviceOperCallback(this);
        myadapter2 = new MyAdapter(deviceList,R.layout.item_monitor2);
        myadapter.setDeviceOperCallback(this);
        monitor_rv2.setAdapter(myadapter2);

    }

    @Override
    public void onViewDevice(Device device) {
        Intent intent =new Intent();
        intent.setClass(getActivity(), MonitorPlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("playUrl", device.getPushUrl());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getDeviceList(){
        RequestParams requestParams = new RequestParams(Constant.FIND_DEVICE_LIST);
        requestParams.addBodyParameter("userId",userId);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(!TextUtils.isEmpty(result) && !result.equals(Constant.FAILURE)){
                    Gson gson = new Gson();
                    deviceList = gson.fromJson(result, new TypeToken<List<Device>>(){}.getType());
                    set_adapter();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "获取设备列表时出错",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
