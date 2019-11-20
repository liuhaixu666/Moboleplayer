package com.example.liuhaixu.mobileplayer.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.base.BasePager;
import com.example.liuhaixu.mobileplayer.pagers.AudoPager;
import com.example.liuhaixu.mobileplayer.pagers.NetAudioPager;
import com.example.liuhaixu.mobileplayer.pagers.NetVideoPager;
import com.example.liuhaixu.mobileplayer.pagers.VideoPager;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private RadioGroup rg_bottom;
    private FrameLayout fl_main_content;
    private ArrayList<BasePager> basePagers; // 页面集合
    /*选中的位置*/
    private int position;
    private VideoPager videoPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom_tag);
        fl_main_content = findViewById(R.id.fl_main);
        /*页面集合*/
        basePagers = new ArrayList<>();
        videoPager = new VideoPager(this);
        basePagers.add(videoPager);
        basePagers.add(new AudoPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        // 设置radiogroup 监听
        rg_bottom.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_bottom.check(R.id.rb_video);

    }
    class MyOnCheckedChangeListener implements  RadioGroup.OnCheckedChangeListener{


        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            switch (i){
                default:
                    position = 0;
                    break;
                case R.id.rb_audio:
                    position = 1;
                    break;
                case R.id.net_video:
                    position = 2;
                    break;
                case R.id.net_audio:
                    position = 3;
                    break;

            }
            setFragment();

        }
    }
    /*把页面添加到fragment中*/
    private  void setFragment(){
     // 1 得到fragmentmanager 管理
        FragmentManager manager = getSupportFragmentManager();
        //2 开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //3 替换

        ft.replace(R.id.fl_main,new ReplaceFragment(getBasePager()));
        // 4 提交
        ft.commit();


    }
    // 根据位置得到对应的页面
    private BasePager getBasePager(){

        BasePager basePager = basePagers.get(position);
        if (basePager != null && !basePager.isinitdata){
            basePager.initData();
            basePager.isinitdata = true;
        }
        return basePager;

    }
    public static class ReplaceFragment extends Fragment{


        private BasePager currPager;

        public ReplaceFragment(BasePager pager) {
            this.currPager=pager;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return currPager.rootview;
        }
    }
    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        //PermissionUtils.requestPermissionsResult((MainActivity)context, requestCode, permissions, grantResults, mPermissionGrant);
        if(grantResults[0] == 0){
            videoPager.getDataFromLocal();
        }
    }
    /*竖屏*/
    public void verAction(View view){
        int currentOrientation = getResources().getConfiguration().orientation;
        //判断并设置用户点击全屏/半屏按钮的显示逻辑
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            //如果屏幕当前是横屏显示，则设置屏幕锁死为竖屏显示
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            //如果屏幕当前是竖屏显示，则设置屏幕锁死为横屏显示
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


}

