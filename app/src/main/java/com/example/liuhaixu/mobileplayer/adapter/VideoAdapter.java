package com.example.liuhaixu.mobileplayer.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.pagers.VideoPager;
import com.example.liuhaixu.mobileplayer.pagers.mediaitem.MediaItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VideoAdapter extends BaseAdapter {

  
    private final ArrayList<MediaItem> mediaItems;
    private final Context context;
   private ViewHoder viewHoder;

    public VideoAdapter(Context context, ArrayList<MediaItem>mediaItems){
        this.context = context;
        this.mediaItems = mediaItems;
    }


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

        if (view == null) {
            view = view.inflate(context, R.layout.item_video_pages, null);
            viewHoder = new ViewHoder();
            viewHoder.iv_icon = view.findViewById(R.id.iv_icon);
            viewHoder.ic_name = view.findViewById(R.id.ic_name);
            viewHoder.ic_time = view.findViewById(R.id.ic_time);
            viewHoder.ic_size = view.findViewById(R.id.ic_size);
            view.setTag(viewHoder);

        }else  {
            viewHoder = (ViewHoder) view.getTag();

        }
        //  根据position 得到位置数据
        MediaItem mediaItem = mediaItems.get(i);
        viewHoder.ic_name.setText(mediaItem.getName());
        viewHoder.ic_size.setText(Formatter.formatFileSize(context,mediaItem.getSize()));
//            viewHoder.ic_time.setText(getTimeFormat(mediaItem.getDuration()));

        viewHoder.ic_time.setText(getFormatedDateTime("mm:ss",mediaItem.getDuration()));
        return view;
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



}


