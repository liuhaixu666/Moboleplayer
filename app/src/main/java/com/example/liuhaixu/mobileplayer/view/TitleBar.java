package com.example.liuhaixu.mobileplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liuhaixu.mobileplayer.R;
import com.example.liuhaixu.mobileplayer.utils.LogUtil;

import androidx.annotation.Nullable;

public class TitleBar extends LinearLayout implements View.OnClickListener {

    private View tv_search;
    private View rl_game;
    private View iv_history;
    private Context context;
    /*代码实例化*/

    public TitleBar(Context context) {
        this(context,null);
    }



    /*布局文件使用*/
    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    /*需要设置样式是，使用该方法*/
    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /*当布局完成时，使用该方法*/
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_search = getChildAt(1);
        rl_game = getChildAt(2);
        iv_history = getChildAt(3);

        tv_search.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_search:
                LogUtil.v("123","点击");
                Toast.makeText(context,"搜索",Toast.LENGTH_SHORT).show();

                break;
            case R.id.rl_game:
                Toast.makeText(context,"游戏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_history:
                Toast.makeText(context,"记录",Toast.LENGTH_SHORT).show();
                break;

        }



    }
}
