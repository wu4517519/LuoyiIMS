package com.luoyi.common;

import android.util.Log;

import com.google.gson.Gson;
import com.luoyi.bean.AlarmLog;
import com.luoyi.bean.Device;
import com.luoyi.utils.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Date;

/**
 * Created by wwc on 2017/10/6.
 */

public class AlarmLogManager {
    private static AlarmLogManager alarmLogManager;

    private AlarmLog alarmLog;

    public AlarmLog getAlarmLog(){
        return alarmLog;
    }

    public static AlarmLogManager getInstance(){
        if(alarmLogManager == null){
            alarmLogManager = new AlarmLogManager();
        }
        return alarmLogManager;
    }

    public static void addAlarmLog(Device device){
        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setAndroidId(device.getAndroidId());
        alarmLog.setType(Constant.ALARM_TYPE_INVASION);
        alarmLog.setTime(new Date()+"");
        RequestParams requestParams = new RequestParams(Constant.ADD_ALARM_LOG);
        requestParams.addBodyParameter("alarmLog",new Gson().toJson(alarmLog));
        x.http().post(requestParams, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                if(result != null || !result.equals("") || !result.equals("null")){
                    Log.d(Constant.TAG,"addAlarmLog success");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(Constant.TAG,"addAlarmLog error");
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
