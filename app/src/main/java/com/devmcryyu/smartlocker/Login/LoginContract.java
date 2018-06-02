//package com.devmcryyu.smartlocker.Login;
//
//import com.devmcryyu.smartlocker.BasePresenter;
//import com.devmcryyu.smartlocker.BaseView;
//
///**
// * Created by 92075 on 2018/3/30.
// */
//
//public interface LoginContract {
//    interface View extends BaseView<Presenter>{
//        String getAccount();
//        String getPassword();
//        void setProgressBar(Boolean active);
//        void showToast(String msg);
//        void showErrorToast(String errorMsg);
//        void startActivity(Class mClass);
//    }
//    interface Presenter extends BasePresenter{
//        void startLogin(String account,String password);
//        void startRegister(String account,String password);
//    }
//}
