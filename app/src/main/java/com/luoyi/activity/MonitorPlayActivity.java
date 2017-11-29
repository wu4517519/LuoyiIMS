package com.luoyi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.luoyi.bean.SwitchVideoModel;
import com.luoyi.fragment.MonitorControlFragment;
import com.luoyi.fragment.MonitorLogFragment;
import com.luoyi.listener.SampleListener;
import com.luoyi.luoyiims.R;
import com.luoyi.utils.Constant;
import com.luoyi.view.SampleVideo;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MonitorPlayActivity extends AppCompatActivity implements View.OnClickListener, MonitorControlFragment.OnFragmentInteractionListener, MonitorLogFragment.OnFragmentInteractionListener {

    private static final int HIDE_MEDIACONTROLLER = 2;

    private ImageView monitor_control;
    private ImageView monitor_log;
    private ImageView iv_fullscreen;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MonitorControlFragment mcf;
    private MonitorLogFragment mlf;

    private OrientationUtils orientationUtils;
    private boolean isPlay;
    private boolean isPause;
    private boolean isRelease;

    private SampleVideo  myVideo;
    private String path;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setActionBar();
        setContentView(R.layout.activity_monitor_play);
        Bundle bundle = getIntent().getExtras();
        path = bundle.getString("playUrl");
        SwitchVideoModel switchVideoModel = new SwitchVideoModel("标准", path);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);
        monitor_control = (ImageView) findViewById(R.id.monitor_control);
        monitor_log = (ImageView) findViewById(R.id.monitor_log);
        myVideo= (SampleVideo ) findViewById(R.id.myVideo);

        myVideo.setUp(list, true, "");

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, myVideo);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);


        monitor_control.setOnClickListener(this);
        monitor_log.setOnClickListener(this);



        mcf = new MonitorControlFragment();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, mcf);
        transaction.commit();

        myVideo.setIsTouchWiget(true);
        //detailPlayer.setIsTouchWigetFull(false);
        //关闭自动旋转
        myVideo.setRotateViewAuto(false);
        myVideo.setLockLand(false);
        myVideo.setShowFullAnimation(false);
        myVideo.setNeedLockFull(true);
        myVideo.setSeekRatio(1);
        //detailPlayer.setOpenPreView(false);
        myVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                myVideo.startWindowFullscreen(MonitorPlayActivity.this, true, true);
            }
        });

        myVideo.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        myVideo.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });



    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.monitor_control:
                Drawable ic_menu2 = getResources().getDrawable(R.drawable.ic_menu2);
                monitor_log.setBackground(ic_menu2);

                Drawable ic_clockgreen = getResources().getDrawable(R.drawable.ic_clockgreen);
                monitor_control.setBackground(ic_clockgreen);
                if (null == mcf) {

                    mcf = new MonitorControlFragment();
                }
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, mcf);
                transaction.commit();
                break;
            case R.id.monitor_log:
                Drawable ic_menugreen = getResources().getDrawable(R.drawable.ic_menugreen);
                monitor_log.setBackground(ic_menugreen);

                Drawable ic_clockgrey = getResources().getDrawable(R.drawable.ic_clockgrey);
                monitor_control.setBackground(ic_clockgrey);
                if (null == mlf) {

                    mlf = new MonitorLogFragment();
                }
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, mlf);
                transaction.commit();





        }

        handler.removeMessages(HIDE_MEDIACONTROLLER);
        handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 4000);

    }





    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void setActionBar() {


        ActionBar bar = getSupportActionBar();
        // 显示返回按钮
        bar.setDisplayHomeAsUpEnabled(true);
        // 去掉logo图标
        bar.setDisplayShowHomeEnabled(false);

        bar.setTitle("监控");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id



                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRelease = true;
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            myVideo.onConfigurationChanged(this, newConfig, orientationUtils);
        }
    }



    private GSYVideoPlayer getCurPlay() {
        if (myVideo.getFullWindowPlayer() != null) {
            return  myVideo.getFullWindowPlayer();
        }
        return myVideo;
    }


    private void resolveNormalVideoUI() {
        //增加title
        myVideo.getTitleTextView().setVisibility(View.GONE);
        myVideo.getBackButton().setVisibility(View.GONE);
    }
}
