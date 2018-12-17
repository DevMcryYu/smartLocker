package com.devmcryyu.smartlocker.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.devmcryyu.smartlocker.model.utils.CommonUtils;


/**
 * Created by 92075 on 2018/3/28.
 */

public class BaseActivity extends Activity{
    protected long firstTime;
    protected Context mContext;
    protected boolean allowToExit=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
    }

    /**
     * 双击退出
     */
    @Override
    public void onBackPressed(){
        if(allowToExit) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                CommonUtils.showToast(mContext,"再按一次退出");
                firstTime = secondTime;
            } else {
                System.exit(0);
            }
        }
    }


}
