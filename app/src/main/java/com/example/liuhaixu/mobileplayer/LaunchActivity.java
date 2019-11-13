package com.example.liuhaixu.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {
    private final  String TAG = LaunchActivity.class.getSimpleName();
    private Handler handler = new Handler();
    private boolean isStart = false; // 判断只进入一次主页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_launch);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"主线程"+ Thread.currentThread().getName());
                startMainActivity();
            }
        }, 2000);


    }
    // 子线程跳转
    private void threadMainActivity() {
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    sleep(3000);//使程序休眠一秒
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }

    // handler 主线程跳转
    private void startMainActivity() {
        if(!isStart){
            isStart = true;
            Intent intent=new Intent(LaunchActivity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,event.getAction()+"线程"+ Thread.currentThread().getName());
         startMainActivity();
         return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
