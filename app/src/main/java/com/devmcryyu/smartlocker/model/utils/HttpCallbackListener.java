package com.devmcryyu.smartlocker.model.utils;

/**
 * Created by 92075 on 2018/3/26.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
