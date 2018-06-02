package com.devmcryyu.smartlocker.model.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.devmcryyu.smartlocker.view.activity.BaseActivity;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;

/**
 * Created by 92075 on 2018/3/28.
 */

public class CommonUtils {
    /**
     * 显示普通Toast
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg){
        Toasty.info(context, ""+msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示错误Toast
     * @param context
     * @param msg
     */
    public static void showErrorToast(Context context, String msg){
        Toasty.error(context,""+msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 普通跳转界面
     * @param mContext
     * @param mClass
     */
    public static void startActivity(Context mContext, Class<? extends BaseActivity> mClass){
        mContext.startActivity(new Intent(mContext,mClass));
    }

    /**
     * 带参跳转界面
     * @param mContext
     * @param mClass
     * @param key
     * @param t
     * @param <T>
     */
    public static <T extends Serializable> void startActivity(Context mContext, Class<? extends BaseActivity> mClass, String key, T t) {
        Intent intent = new Intent(mContext, mClass);
        intent.putExtra(key, t);
        mContext.startActivity(intent);
    }



}
