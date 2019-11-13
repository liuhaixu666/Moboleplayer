package com.example.liuhaixu.mobileplayer.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {

    public final Context context;
    public View rootview;
    public boolean isinitdata;

    /*
   *  上下文
   * */
   public BasePager(Context context){

       this.context = context;
       rootview = initView();
   }

   /*强制孩子执行，抽象方法*/
   public abstract View initView();

   // 数据处理

    public void  initData(){



    }

}
