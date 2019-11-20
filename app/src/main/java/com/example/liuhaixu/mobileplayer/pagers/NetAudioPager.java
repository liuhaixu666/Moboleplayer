package com.example.liuhaixu.mobileplayer.pagers;

import android.content.Context;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.base.BasePager;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;


public class NetAudioPager extends BasePager {


    private TextView textview;
    private Button btn_hor;
    private Button btn_ver;
    public NetAudioPager(Context context){

        super(context);

    }



    @Override
    public View initView() {
        LogUtil.e("123", "本地视频被初始化了");
        View view = View.inflate(context, R.layout.net_audio_pager, null);
        btn_hor = view.findViewById(R.id.button_hor);
        btn_ver =view.findViewById(R.id.button_ver);
        btn_hor.setOnClickListener(new MyOnClickListener());
//        btn_ver.setOnClickListener(new MyOnClickListener());
        return view;
    }
    public void initData(){
        super.initData();
        LogUtil.v("123","网络音乐被初始化了");
//        textview.setText("网络音乐页面");

    }



    class MyOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_hor){
                LogUtil.d("h","横屏");
            }else {
                LogUtil.v("h","竖屏");

            }

        }
    }


}
