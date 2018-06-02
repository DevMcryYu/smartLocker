//package com.devmcryyu.smartlocker.Login;
//
//import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.devmcryyu.smartlocker.InterfaceParameters;
//import com.devmcryyu.smartlocker.model.utils.HttpUtil;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Response;
//
///**
// * Created by 92075 on 2018/3/28.
// */
//
//public class LoginPresenter implements LoginContract.Presenter {
//    private final LoginContract.View view;
//
//    public LoginPresenter(@NonNull LoginContract.View view) {
//        this.view = view;
//        view.setPresenter(this);
//    }
//
//    @Override
//    public void start() {
//        Log.i("Presenter","Presenter is started");
//    }
//
//    @Override
//    public void startLogin(final String account, final String password) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpUtil.sendOKHttpRequest("https://www.baidu.com", new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.i("Presenter",e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.i("Presenter",response.toString());
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public void startRegister(String account, String password) {
//    }
//}
