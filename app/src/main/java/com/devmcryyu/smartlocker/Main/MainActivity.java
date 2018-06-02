package com.devmcryyu.smartlocker.Main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.navisdk.adapter.BNCommonSettingParam;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.devmcryyu.smartlocker.R;
import com.devmcryyu.smartlocker.view.activity.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import pub.devrel.easypermissions.EasyPermissions;

import static com.baidu.navisdk.adapter.BaiduNaviManager.NaviInitListener;
import static com.baidu.navisdk.adapter.BaiduNaviManager.TTSPlayStateListener;

/**
 * Created by 92075 on 2018/3/31.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivityLog";
    private static final int PERMISSIONS = 100;
    private static final String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.BODY_SENSORS};
    private static final int LOCATIONMODE_NORMAL = 0;
    private static final int LOCATIONMODE_FOLLOWING = 1;
    private static final int LOCATIONMODE_COMPASS = 2;
    public static final String ROUTE_PLAN_NODE = "routePlanNode";
    private String mSDcardPath;
    private final String appFolderName = "smartlocker";
    private Handler ttsHandler = null;
    private LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private MapView mapView;
    private BaiduMap baiduMap;
    private FloatingActionButton fab;
    private FloatingActionButton gps_fab;
    private FloatingActionButton info_fab;
    private Marker marker;
    protected infoFragment infoFragment;
    private boolean isFirstLocate = true;
    private double lat;
    private double lon;
    private float dirc;
    private int mLocationMode = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());                              //注意以下语句的顺序
        mLocationClient.registerLocationListener(myListener);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        init();
        initBaiduMap();
        initNavi();
        if (EasyPermissions.hasPermissions(this, perms)) {                                  //如获取到权限则开始定位
            requestLocation();
        } else {
            EasyPermissions.requestPermissions(this, "", PERMISSIONS, perms);        //获取权限
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setBuildingsEnabled(false);
        mapView = null;
    }

    private void init() {
        //初始化

        Bundle bundle = new Bundle();
        // 必须设置APPID，否则会静音
        bundle.putString(BNCommonSettingParam.TTS_APP_ID, "10941708");
        BNaviSettingManager.setNaviSdkParam(bundle);
        infoFragment = (infoFragment) getFragmentManager().findFragmentById(R.id.info);
        info_fab = findViewById(R.id.info_fab);
        info_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(mContext, "INFO_FAB被点了一下", Toast.LENGTH_SHORT).show();
                //TODO: 开始导航
            }
        });
        gps_fab = findViewById(R.id.gps_fab);
        gps_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018/5/6 点击按钮返回当前位置
                Toasty.info(mContext, "GPS_FAB被点了一下", Toast.LENGTH_SHORT).show();
            }
        });
        fab = findViewById(R.id.account_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018/5/6 点击按钮进入用户设置界面
                Toasty.info(mContext, "FAB被点了一下", Toast.LENGTH_SHORT).show();
                infoFragment.setVisibility(true);
            }
        });

    }

    private void initNavi() {
        BaiduNaviManager.getInstance().init(this, mSDcardPath, appFolderName, new NaviInitListener() {
            @Override
            public void onAuthResult(int i, String s) {
                final String authinfo;
                if (0 == i) {
                    authinfo = "key校验成功!";
                } else {
                    authinfo = "key校验失败, " + s;
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toasty.info(mContext, authinfo, Toast.LENGTH_LONG).show();
                        Log.i(TAG, authinfo);
                    }
                });
            }

            @Override
            public void initStart() {
//                Toasty.info(mContext, "百度导航引擎初始化成功", Toast.LENGTH_LONG).show();
                Log.i(TAG, "百度导航引擎初始化成功");
            }

            @Override
            public void initSuccess() {
//                Toasty.info(mContext, "百度导航引擎初始化开始", Toast.LENGTH_LONG).show();
                Log.i(TAG, "百度导航引擎初始化开始");
            }


            @Override
            public void initFailed() {
//                Toasty.error(mContext, "百度导航引擎初始化失败", Toast.LENGTH_LONG).show();
                Log.i(TAG, "百度导航引擎初始化失败");
            }
        }, null, ttsHandler, new TTSPlayStateListener() {
            @Override
            public void playStart() {

            }

            @Override
            public void playEnd() {

            }
        });
    }

    private void initBaiduMap() {
        mapView = findViewById(R.id.baiduMapView);
        mapView.showZoomControls(false);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                infoFragment.setVisibility(false);
            }
        });

        //定义Marker坐标点
        LatLng point = new LatLng(31.97156765233811,118.80076892893804);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.marker);

        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        baiduMap.addOverlay(option);

        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MainActivity.this.setMarker(marker);
                // TODO: 2018/5/6 将Marker显示在fragment上
                if (infoFragment.isVisible())
                    infoFragment.show(marker);
                else {
                    infoFragment.setVisibility(true);
                    infoFragment.show(marker);
                    //TODO: 计算导航路线
                    final BNRoutePlanNode sNode = new BNRoutePlanNode(lat, lon, null, null, BNRoutePlanNode.CoordinateType.BD09LL);
                    BNRoutePlanNode eNode = new BNRoutePlanNode(marker.getPosition().latitude, marker.getPosition().longitude, null, null, BNRoutePlanNode.CoordinateType.BD09LL);
                    List<BNRoutePlanNode> list = new ArrayList<>();
                    list.add(sNode);
                    list.add(eNode);
                    Log.i(TAG,list.toString());
                    BaiduNaviManager.getInstance().launchNavigator(MainActivity.this, list, 1, true, new BaiduNaviManager.RoutePlanListener() {
                        private BNRoutePlanNode mBNRoutePlanNode = sNode;
                        @Override
                        public void onJumpToNavigator() {
                            Intent intent = new Intent(MainActivity.this, BNDemoGuideActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(ROUTE_PLAN_NODE, mBNRoutePlanNode);
                            Log.i(TAG,"开始导航跳转");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        @Override
                        public void onRoutePlanFailed() {
                            Log.i(TAG,"RoutePlanFailed");
                        }
                    });
                }
                return false;
            }
        });
        baiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.i(TAG, "点击位置: {" + latLng.latitude + "," + latLng.longitude + "}");
            }
        });
    }

    private void setMarker(Marker marker) {
        this.marker = marker;
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setNeedDeviceDirect(true);
        option.setLocationNotify(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        changeDefaultBaiduMapView(mapView);
    }

    private void navigateTo(boolean requestMyLocation) {
        if (isFirstLocate || requestMyLocation) {
            LatLng ll = new LatLng(lat, lon);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(20.0f);
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mLocationMode = LOCATIONMODE_COMPASS;
            baiduMap.setMyLocationConfiguration(myLocationConfiguration);
            isFirstLocate = false;
        }
        MyLocationData locationData = new MyLocationData.Builder()
                .accuracy(0)
                .direction(dirc)
                .latitude(lat)
                .longitude(lon).build();
        baiduMap.setMyLocationData(locationData);
        Log.i(TAG, "经度: " + lat + " 纬度: " + lon + " 方向: " + dirc);
    }

    protected void NaviToMarker(Marker marker) {

    }

    private boolean initDirs() {
        mSDcardPath = getSdcardDir();
        if ( mSDcardPath == null ) {
            return false;
        }
        File f = new File(mSDcardPath, appFolderName);
        if ( !f.exists() ) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    private void changeDefaultBaiduMapView(MapView mapView) {
        mapView.showScaleControl(false);
        View child = this.mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView)) {
            child.setVisibility(View.INVISIBLE);
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                dirc = location.getDirection();
                navigateTo(false);
            }
        }
    }
}
