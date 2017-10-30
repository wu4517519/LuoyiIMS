package com.luoyi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyi.bean.Device;
import com.luoyi.common.DeviceCallback;
import com.luoyi.luoyiims.R;
import com.luoyi.utils.Constant;

import org.xutils.x;

import java.util.List;
import java.util.Map;

/**
 * Created by CSIR on 2017/8/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private MyAdapter.ViewHolder viewHolder;
    private List<Device> datas;
    private int resource;

    public MyAdapter(List<Device> datas, int resource) {
        this.datas = datas;
        this.resource = resource;
    }

    private DeviceCallback.DeviceOperCallback deviceOperCallback;

    public void setDeviceOperCallback(DeviceCallback.DeviceOperCallback deviceOperCallback) {
        this.deviceOperCallback = deviceOperCallback;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        viewHolder = new MyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        //viewHolder.cameraName.setText(datas[position]);
        if(datas != null){
            holder.setDevice(datas.get(position));
            holder.getDeviceName().setText(holder.getDevice().getName());
            x.image().bind(holder.getCover(), Constant.BASE_PATH+datas.get(position).getCoverImgPath());
        }
        if (deviceOperCallback != null && deviceOperCallback instanceof DeviceCallback.DeviceOperCallback){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deviceOperCallback.onViewDevice(datas.get(position));
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;
        private TextView deviceName;
        private Device device;

        public ViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            deviceName = (TextView) itemView.findViewById(R.id.tv_device_name);
        }

        public ImageView getCover() {
            return cover;
        }

        public void setCover(ImageView cover) {
            this.cover = cover;
        }

        public TextView getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(TextView deviceName) {
            this.deviceName = deviceName;
        }

        public Device getDevice() {
            return device;
        }

        public void setDevice(Device device) {
            this.device = device;
        }
    }

    public List<Device> getDatas() {
        return datas;
    }

    public void setDatas(List<Device> datas) {
        this.datas = datas;
    }
}