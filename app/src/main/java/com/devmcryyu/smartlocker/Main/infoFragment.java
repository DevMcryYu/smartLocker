package com.devmcryyu.smartlocker.Main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.devmcryyu.smartlocker.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by 92075 on 2018/5/2.
 */

public class infoFragment extends Fragment {
    private View view;
//    private FloatingActionButton info_fab;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);
        view.setVisibility(View.INVISIBLE);
//        info_fab =view.findViewById(R.id.info_fab);
//        info_fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toasty.info(getActivity(), "INFO_FAB被点了一下", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    public void show(Marker marker) {
        View infoViewLayout = view.findViewById(R.id.infoCoordinatorLayout);
        TextView lockerName = view.findViewById(R.id.lockerName);
        lockerName.setText(marker.getPosition().latitude+","+marker.getPosition().longitude);
    }

    public void setVisibility(boolean isVisible) {
        if (isVisible)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.INVISIBLE);

    }


}
