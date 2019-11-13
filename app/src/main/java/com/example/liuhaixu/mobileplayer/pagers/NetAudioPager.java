package com.example.liuhaixu.mobileplayer.pagers;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.liuhaixu.mobileplayer.base.BasePager;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

public class NetAudioPager extends BasePager {


    private TextView textview;

    public NetAudioPager(Context context){

        super(context);

    }



    @Override
    public View initView() {
        LogUtil.e("123","网络音乐被初始化了");
        textview = new TextView(context);
        textview.setText("网络音乐");
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(Color.RED);
        return textview;
    }
    public void initData(){
        super.initData();
        LogUtil.v("123","网络音乐被初始化了");
        textview.setText("网络音乐页面");

    }
}
