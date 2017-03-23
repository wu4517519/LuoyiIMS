package com.luoyi.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.luoyi.activity.base.BaseActivity;
import com.luoyi.fragment.LogFragment;
import com.luoyi.fragment.MineFragment;
import com.luoyi.fragment.MonitorFragment;
import com.luoyi.fragment.PlaybackFragment;
import com.luoyi.luoyiims.R;

import org.xutils.view.annotation.ContentView;

/**
 * 主页面
 * @autor wwc
 * @date 2017年3月19日
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private MonitorFragment monitorFragment;
    private PlaybackFragment paybackFragment;
    private LogFragment logFragment;
    private MineFragment mineFragment;

    private BottomNavigationBar bottomNavigationBar;
    private int lastSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar .
                setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        /*bottomNavigationBar.setActiveColor("#FFFFFF");
        bottomNavigationBar.setInActiveColor("#CCCCCC");*/
        bottomNavigationBar.setBarBackgroundColor("#8bc34a");
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_monitor, "监控").setActiveColorResource(R.color.grey).setInActiveColorResource(R.color.lavenderblush))
                .addItem(new BottomNavigationItem(R.mipmap.ic_playback ,"回放").setActiveColorResource(R.color.grey).setInActiveColorResource(R.color.lavenderblush))
                .addItem(new BottomNavigationItem(R.mipmap.ic_monitor, "日志").setActiveColorResource(R.color.grey).setInActiveColorResource(R.color.lavenderblush))
                .addItem(new BottomNavigationItem(R.mipmap.ic_monitor,  "我的").setActiveColorResource(R.color.grey).setInActiveColorResource(R.color.lavenderblush))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        monitorFragment = monitorFragment.newInstance("位置");
        transaction.replace(R.id.tb, monitorFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getFragmentManager() ;
        FragmentTransaction ft = fm.beginTransaction();
        switch(position){
            case 0:{
                if(null == monitorFragment){
                    monitorFragment = MonitorFragment.newInstance("监控");
                }
                ft.replace(R.id.tb,monitorFragment);
            }
            break;
            case 1:{
                if(null == paybackFragment){
                    paybackFragment = paybackFragment.newInstance("回放");
                }
                ft.replace(R.id.tb,paybackFragment);
            }
            break;
            case 2:{
                if(null == logFragment){
                    logFragment = logFragment.newInstance("日志");
                }
                ft.replace(R.id.tb,logFragment);
            }
            break;
            case 3:{
                if(null == mineFragment){
                    mineFragment = mineFragment.newInstance("我的");
                }
                ft.replace(R.id.tb,mineFragment);
            }
            break;
            default:break;
        }
        ft.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
