package com.luoyi.common;

import android.util.Log;

import com.google.gson.Gson;
import com.luoyi.bean.Device;
import com.luoyi.utils.Constant;
import com.luoyi.utils.StringUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
/**
 * Created by wwc on 2017/10/6.
 */

public class DeviceManager {

    private static DeviceManager deviceManager;
    private Device device;
    public Device getDevice(){
        return device;
    }

    public static DeviceManager getInstance(){
        if(deviceManager == null){
            deviceManager = new DeviceManager();
        }
        return deviceManager;
    }

    public Device addDevice(String userId, String androidId, String deviceToken){
        Device newDevice = new Device();
        newDevice.setUserid(userId);
        newDevice.setAndroidId(androidId);
        newDevice.setDeviceToken(deviceToken);
        newDevice.setDeviceType(1);
        newDevice.setIsOnline(1);
        newDevice.setPushUrl(Constant.PUSH_URL_PREFIX+userId+ StringUtil.getRandomString());
        RequestParams requestParams = new RequestParams(Constant.ADD_DEVICE);
        String deviceJson = new Gson().toJson(newDevice, Device.class);
        requestParams.addBodyParameter("deviceJson",deviceJson);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result != null || !result.equals("") || !result.equals("null")){
                    device = new Gson().fromJson(result, Device.class);
                    Log.d(Constant.TAG,"添加设备成功");
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                device = null;
                Log.d(Constant.TAG,"添加设备失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return device;
    }

    public void isDeviceExist(final String userId, final String ANDROID_ID,final String deviceToken){
        RequestParams requestParams = new RequestParams(Constant.FIND_DEVICE_BY_ANDROID_ID);
        requestParams.addBodyParameter("androidId",ANDROID_ID);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result.equals("null")){
                    Log.d(Constant.TAG, "查找设备不存在");
                    DeviceManager.getInstance().addDevice(userId, ANDROID_ID, deviceToken);
                }
                else if(result.equals("")){
                    Log.d(Constant.TAG, "查找设备失败");
                }
                else{
                    Log.d(Constant.TAG, "该设备已存在");
                    device = new Gson().fromJson(result, Device.class);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                device = null;
                Log.d(Constant.TAG, "onError 查找设备失败");
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
