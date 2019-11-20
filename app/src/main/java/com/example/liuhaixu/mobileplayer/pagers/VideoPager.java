package com.example.liuhaixu.mobileplayer.pagers;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.activity.MainActivity;
import com.example.liuhaixu.mobileplayer.activity.SystemVideoPlayer;
import com.example.liuhaixu.mobileplayer.adapter.VideoAdapter;
import com.example.liuhaixu.mobileplayer.base.BasePager;
import com.example.liuhaixu.mobileplayer.pagers.mediaitem.MediaItem;
import com.example.liuhaixu.mobileplayer.permission.PermissionUtils;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

import java.io.Console;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class VideoPager extends BasePager {


    private ListView listView;
    private TextView te_nomedia;
    private ProgressBar pd_liading;
    private ArrayList<MediaItem> mediaItems;
    private VideoAdapter videoAdapt;


    public VideoPager(Context context) {

        super(context);
    }

    public void initData() {
        super.initData();
        LogUtil.v("123", "本地视频数据初始化了");
        PermissionUtils.requestPermission((MainActivity) context, PermissionUtils.CODE_READ_EXTERNAL_STORAGE, mPermissionGrant);
        getDataFromLocal();
    }

    @Override
    public View initView() {
        LogUtil.e("123", "本地视频被初始化了");
        View view = View.inflate(context, R.layout.video_pager, null);
        listView = view.findViewById(R.id.listview);
        te_nomedia = view.findViewById(R.id.tv_nomedia);
        pd_liading = view.findViewById(R.id.pd_loadong);
        listView.setOnItemClickListener(new MyOnItemClickListener());


        return view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MediaItem mediaItem = mediaItems.get(i);
//        Toast.makeText(context,"点击",Toast.LENGTH_LONG).show();
        //1.调起系统播放器
//            Intent intent = new Intent();
//            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
//            context.startActivity(intent);
//
        // 2.调用自己写的播放器
            Intent intent = new Intent(context, SystemVideoPlayer.class);
            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
            context.startActivity(intent);

        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (mediaItems != null && mediaItems.size() > 0) {
                // 有数据
                LogUtil.v("1", "来视频数据了哦");
                videoAdapt = new VideoAdapter(context, mediaItems);
                listView.setAdapter(videoAdapt);
                te_nomedia.setVisibility(View.GONE);


            } else {
                // 无数据
                te_nomedia.setVisibility(View.VISIBLE);
            }
            //隐藏加载
            pd_liading.setVisibility(View.GONE);

        }
    };


    public void getDataFromLocal() {

        new Thread() {

            @Override
            public void run() {
                super.run();

//                isGrantExternalRW((Activity) context);

                mediaItems = new ArrayList<>();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objc = {

                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.ARTIST,

                };
                Cursor cursor = resolver.query(uri, objc, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        MediaItem mediaItem = new MediaItem();
                        mediaItems.add(mediaItem);
                        String name = cursor.getString(0);
                        mediaItem.setName(name);
                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);
                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cursor.getString(3);
                        mediaItem.setData(data);
                        String artist = cursor.getString(4);
                        mediaItem.setArtist(artist);

                    }
                    cursor.close();

                }

                // 发消息
                handler.sendEmptyMessage(1);

            }
        }.start();

    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(context, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    Toast.makeText(context, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(context, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    Toast.makeText(context, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    Toast.makeText(context, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(context, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(context, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(context, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(context, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    class VideoAdapt extends BaseAdapter {


        @Override
        public int getCount() {
            return mediaItems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHoder viewHoder;
            if (view == null) {
                view = view.inflate(context, R.layout.item_video_pages, null);
                viewHoder = new ViewHoder();
                viewHoder.iv_icon = view.findViewById(R.id.iv_icon);
                viewHoder.ic_name = view.findViewById(R.id.ic_name);
                viewHoder.ic_time = view.findViewById(R.id.ic_time);
                viewHoder.ic_size = view.findViewById(R.id.ic_size);
                view.setTag(viewHoder);

            } else {
                viewHoder = (ViewHoder) view.getTag();

            }
            //  根据position 得到位置数据
            MediaItem mediaItem = mediaItems.get(i);
            viewHoder.ic_name.setText(mediaItem.getName());
            viewHoder.ic_size.setText(Formatter.formatFileSize(context, mediaItem.getSize()));
//            viewHoder.ic_time.setText(getTimeFormat(mediaItem.getDuration()));

            viewHoder.ic_time.setText(getFormatedDateTime("mm:ss", mediaItem.getDuration()));
            return view;
        }
    }

    public static class ViewHoder {
        ImageView iv_icon;
        TextView ic_name;
        TextView ic_time;
        TextView ic_size;
    }

    public static String getFormatedDateTime(String pattern, long dateTime) {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    public static String getTimeFormat(long time) {
//        long time=Long.parseLong(timeStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = addZero(month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = addZero(day);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
        String hourStr = addZero(hour);
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr = addZero(minute);
        int second = calendar.get(Calendar.SECOND);
        String secondStr = addZero(second);
        return (year + "-" + monthStr + "-" + dayStr + " "
                + hourStr + ":" + minuteStr + ":" + secondStr);
    }

    private static String addZero(int param) {
        String paramStr = param < 10 ? "0" + param : "" + param;
        return paramStr;
    }
    /**
     * @param activity  解决6.0 动态获取权限
     * @return
     */
    public static  boolean isGrantExternalRW(Activity activity){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            activity.requestPermissions(new  String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            },1);

            return false;
        }

        return  true;


    }

}
