package com.luoyi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.luoyi.fragment.MonitorControlFragment;
import com.luoyi.fragment.MonitorLogFragment;
import com.luoyi.luoyiims.R;
import com.pili.pldroid.player.widget.PLVideoTextureView;

public class MonitorPlayActivity extends AppCompatActivity implements View.OnClickListener, MonitorControlFragment.OnFragmentInteractionListener, MonitorLogFragment.OnFragmentInteractionListener {

    private static final int HIDE_MEDIACONTROLLER = 2;
    private PLVideoTextureView mVideoView;
    private ImageView monitor_control;
    private ImageView monitor_log;
    private ImageView iv_fullscreen;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MonitorControlFragment mcf;
    private MonitorLogFragment mlf;
    private RelativeLayout rll_video_blank;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/areyouok.3gp";
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


//        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
//        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
//        jcVideoPlayerStandard.startVideo();
        monitor_control = (ImageView) findViewById(R.id.monitor_control);
        monitor_log = (ImageView) findViewById(R.id.monitor_log);
        mVideoView = (PLVideoTextureView) findViewById(R.id.videoView);

        iv_fullscreen = (ImageView) findViewById(R.id.iv_fullscreen);
        rll_video_blank = (RelativeLayout) findViewById(R.id.rll_video_blank);

        mVideoView.setVideoPath(path);
        mVideoView.start();
        monitor_control.setOnClickListener(this);
        monitor_log.setOnClickListener(this);

        iv_fullscreen.setOnClickListener(this);
        rll_video_blank.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    startAndPause();
                }

                return false;
            }
        });


        mcf = new MonitorControlFragment();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, mcf);
        transaction.commit();

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

                break;
            //播放全屏
            case R.id.iv_fullscreen:

                startWindowFullscreen();


                break;


            //点击空白处暂停播放
            case R.id.rll_video_blank:

                startAndPause();


                break;

        }

        handler.removeMessages(HIDE_MEDIACONTROLLER);
        handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 4000);

    }

    private void startWindowFullscreen() {


        mVideoView.setDisplayOrientation(90);

    }

    private void startAndPause() {

        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        } else {
            mVideoView.start();
        }

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

               // mVideoView.pause();
               // mVideoView.releaseSurfactexture();

                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
//        super.onBackPressed();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        JCVideoPlayer.releaseAllVideos();
//    }
}
