package com.devmcryyu.smartlocker.model.bean;

/**
 * Created by 92075 on 2018/5/21.
 */

public class Locker {
    static final boolean LOCKER_BUSY=false;
    static final boolean LOCKER_FREE=true;
    private int ID;
    private String Name;
    private double lat;
    private double lon;
    private boolean status;
}
