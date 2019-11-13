package com.example.liuhaixu.mobileplayer.pagers;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.liuhaixu.mobileplayer.base.BasePager;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

public class NetVideoPager extends BasePager {


    private TextView textview;

    public NetVideoPager(Context context){

        super(context);

    }



    @Override
    public View initView() {
        LogUtil.e("123","网络视频被初始化了");
        textview = new TextView(context);
        textview.setText("网络视频");
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(Color.RED);
        return textview;
    }
    public void initData(){
        super.initData();
        LogUtil.v("123","网络视频被初始化了");
        textview.setText("网络视频页面");

    }
}
