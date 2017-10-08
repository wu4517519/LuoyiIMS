package com.luoyi;

import android.app.Application;
import android.util.Log;

import com.luoyi.utils.Constant;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by wyouflf on 15/10/28.
 */
public class MyApplication extends Application {

    public static  PushAgent mPushAgent;//友盟推送代理

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        mPushAgent = PushAgent.getInstance(this);
        initRegisterUmengPush();
    }

    /**
     * desc 友盟注册代码
     * @param
     * @return
     * @author wwc
     * Created on 2017/10/5 17:37
     */
    private void initRegisterUmengPush() {

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d(Constant.TAG, "友盟注册成功，设备token为：" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d(Constant.TAG, "友盟注册失败，错误码：" + s + ",错误信息：" + s1);
            }
        });
    }
}
