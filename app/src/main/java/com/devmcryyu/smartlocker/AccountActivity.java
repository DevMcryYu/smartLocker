package com.devmcryyu.smartlocker;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;

        import java.util.zip.Inflater;

/**
 * Created by 92075 on 2018/3/15.
 */

public class AccountActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_imformation);
        init();
    }
    private void init(){

    }
}
