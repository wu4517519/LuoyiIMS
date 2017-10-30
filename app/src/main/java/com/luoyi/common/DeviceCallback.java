package com.luoyi.common;

import com.luoyi.bean.Device;

/**
 * Created by wwc on 2017/10/24.
 */

public interface DeviceCallback {

    /**
     * 设备状态回调
     */
    interface DeviceNetWorkCallback {
        
        void onDeviceOnline();

        void onDeviceOffline();
    }
    
    interface DeviceOperCallback{

        void onViewDevice(Device device);
    }
}
