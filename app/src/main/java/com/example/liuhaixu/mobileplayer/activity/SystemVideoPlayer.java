package com.example.liuhaixu.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

import androidx.annotation.Nullable;

public class SystemVideoPlayer extends Activity implements View.OnClickListener {

    /*视频进度*/
    private static final int PROGRESS = 1;
    private VideoView videoview;
    private Uri uri;
    private LinearLayout llTop;
    private ImageView ivBattery;
    private TextView tvName;
    private TextView tvSystemTime;
    private Button btnVoice;
    private SeekBar voiceSeekbar;
    private Button btnSwichPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar videoSeekbar;
    private TextView tvDuration;
    private Button btnExit;
    private Button btnVideoPre;
    private Button btnStartPause;
    private Button btnVideoNext;
    private Button btnVideoSwichScreen;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-11-20 10:33:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_system_video_player);
        videoview = findViewById(R.id.videoview);
        llTop = (LinearLayout) findViewById(R.id.ll_top);
        ivBattery = (ImageView) findViewById(R.id.iv_battery);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvSystemTime = (TextView) findViewById(R.id.tv_system_time);
        btnVoice = (Button) findViewById(R.id.btn_voice);
        voiceSeekbar = (SeekBar) findViewById(R.id.voice_seekbar);
        btnSwichPlayer = (Button) findViewById(R.id.btn_swich_player);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        videoSeekbar = (SeekBar) findViewById(R.id.video_seekbar);
        tvDuration = (TextView) findViewById(R.id.tv_duration_);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnVideoPre = (Button) findViewById(R.id.btn_video_pre);
        btnStartPause = (Button) findViewById(R.id.btn_start_pause);
        btnVideoNext = (Button) findViewById(R.id.btn_video_next);
        btnVideoSwichScreen = (Button) findViewById(R.id.btn_video_swich_screen);

        btnVoice.setOnClickListener(this);
        btnSwichPlayer.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnVideoPre.setOnClickListener(this);
        btnStartPause.setOnClickListener(this);
        btnVideoNext.setOnClickListener(this);
        btnVideoSwichScreen.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-11-20 10:33:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnVoice) {
            // Handle clicks for btnVoice
        } else if (v == btnSwichPlayer) {
            // Handle clicks for btnSwichPlayer
        } else if (v == btnExit) {
            // Handle clicks for btnExit
        } else if (v == btnVideoPre) {
            // Handle clicks for btnVideoPre
        } else if (v == btnStartPause) {
            if (videoview.isPlaying()) {
                //视频在播放，设置暂停，按钮设置播放
                videoview.pause();
                btnStartPause.setBackgroundResource(R.drawable.btn_start_selector);

            } else {
                //视频暂停中，设置播放，按钮设置暂停
                videoview.start();
                btnStartPause.setBackgroundResource(R.drawable.btn_start_pause_selector);
            }


            // Handle clicks for btnStartPause
        } else if (v == btnVideoNext) {
            // Handle clicks for btnVideoNext
        } else if (v == btnVideoSwichScreen) {
            // Handle clicks for btnVideoSwichScreen
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case PROGRESS:
                    // 得到当前播放进度
                    int currentPosition = videoview.getCurrentPosition();

                    // 设置当前进度
                    videoSeekbar.setProgress(currentPosition);
                    // 更新文本播放进度
                    tvCurrentTime.setText(millisToStringShort(currentPosition,true,true));
                    // 每秒更新一次
                    handler.removeMessages(PROGRESS);
                    handler.sendEmptyMessageDelayed(PROGRESS, 1000);

                    break;

            }

        }
    };
    public static String millisToStringShort(long millis, boolean isWhole, boolean isFormat) {
        LogUtil.v("12","总时长"+millis);
        String h = "";
        String m = "";
//        if (isWhole) {
//            h = isFormat ? "00小时" : "0小时";
//            m = isFormat ? "00分钟" : "0分钟";
//        }
        long temp = millis;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
        if (temp / hper > 0) {
            if (isFormat) {
                h = temp / hper < 10 ? "0" + temp / hper : temp / hper + "";
            } else {
                h = temp / hper + "";
            }
            h += "小时";
        }
//        temp = temp % hper;
        if (temp / mper > 0) {
            if (isFormat) {
                m = temp / mper < 10 ? "0" + temp / mper : temp / mper + "";
            } else {
                m = temp / mper + "";
            }
            m += "分钟";
        }
        if (millis / sper > 0) {
            if (isFormat) {
                m = millis / sper < 60 ? "00:" + millis / sper : temp / sper + "";
            } else {
                m = temp / mper + "";
            }
            return m;
        }

        return h + m;
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
        setlistener();


        uri = getIntent().getData();

        if (uri != null) {
            videoview.setVideoURI(uri);
        }

        // 设置控制面板
//        videoview.setMediaController(new MediaController(this));


    }

    private void setlistener() {
        /*添加监听*/
        // 准备好的监听
        videoview.setOnPreparedListener(new MyOnPreparedListener());


        // 出错的监听
        videoview.setOnErrorListener(new MyOnErrorListener());

        // 完成的监听
        videoview.setOnCompletionListener(new MyOnCompletionListener());
        //得到播放地址

        //设置seekbar 监听
        videoSeekbar.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());

    }
    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{


        /**
         * @param seekBar
         * @param i
         * @param b   如果是用户引起拖动的，b是true，自动的是false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        //当手指回调的时候回调
        if (b){

            videoview.seekTo(i);

        }

        }
        // 当手指触碰是回调
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        // 当手指离开时回调的方法
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {



        }
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {


        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {


            videoview.start(); // 开始播放
            int duration = videoview.getDuration();
            videoSeekbar.setMax(duration);
            tvDuration.setText(millisToStringShort(duration,false,true));
            handler.sendEmptyMessage(PROGRESS);

        }
    }

    class MyOnErrorListener implements MediaPlayer.OnErrorListener {


        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            Toast.makeText(SystemVideoPlayer.this, "播放出错", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {


        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(SystemVideoPlayer.this, "当前的" + uri + "播放完成", Toast.LENGTH_LONG).show();
        }
    }


}
