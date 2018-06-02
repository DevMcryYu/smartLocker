//package com.devmcryyu.smartlocker.Login;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//
//import com.devmcryyu.smartlocker.R;
//import com.devmcryyu.smartlocker.model.utils.CommonUtils;
//import com.devmcryyu.smartlocker.view.activity.BaseActivity;
//
//
///**
// * Created by 92075 on 2018/3/28.
// */
//
//public class LoginActivity extends BaseActivity implements LoginContract.View {
//    private LoginContract.Presenter mPresenter;
//    private EditText account;
//    private EditText password;
//    private Button sign_in_button;
//    private ProgressBar login_process;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        init();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//    }
//
//    void init() {
//        mPresenter = new LoginPresenter(this);
//        account = findViewById(R.id.account);
//        password = findViewById(R.id.password);
//        login_process = findViewById(R.id.login_progress);
//        sign_in_button = findViewById(R.id.account_sign_in_button);
//        sign_in_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.startLogin(getAccount(), getPassword());
//            }
//        });
//    }
//
//    @Override
//    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public String getAccount() {
//        return String.valueOf(account.getText());
//    }
//
//    @Override
//    public String getPassword() {
//        return String.valueOf(password.getText());
//    }
//
//    @Override
//    public void setProgressBar(Boolean active) {
//        if (active)
//            login_process.setVisibility(View.VISIBLE);
//        login_process.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void showToast(String msg) {
//        CommonUtils.showToast(mContext, msg);
//    }
//
//    @Override
//    public void showErrorToast(String errorMsg) {
//        CommonUtils.showErrorToast(mContext, errorMsg);
//    }
//
//    @Override
//    public void startActivity(Class mClass) {
//        startActivity(new Intent(mContext, mClass));
//    }
//}
