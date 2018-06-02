package com.devmcryyu.smartlocker;

/**
 * Created by 92075 on 2018/3/19.
 *
 * @author: DevMcryYu
 * @classDescription: 参数配置
 */

public class InterfaceParameters {
    //请求URL
    public final static String LOGIN_REQUEST = "http://115.159.115.250:7800/api/login";
    public final static String SIGNUP_REQUEST = "http://115.159.115.250:7800/api/signup";
    public final static String MODIFY_PASSWORD_REQUEST = "http://115.159.115.250:7800/api/modify-password";
    public final static String MODIFY_PAYCHECK_REQUEST = "http://115.159.115.250:7800/api/modify-paycheck";
    public final static String MODIFY_BALANCE_REQUEST = "http://115.159.115.250:7800/api/modify-balance";
    public final static String SELECT_BALANCE_REQUEST = "http://115.159.115.250:7800/api/select-balance";
    public final static String QUERY_ALL_DEVICES_REQUSET = "http://115.159.115.250:7800/api/query-all-devices";
    public final static String QUERY_X_DEVICE_REQUEST = "http://115.159.115.250:7800/api/query-device?";//id=x
    public final static String SET_DEVICES_REQUEST = "http://115.159.115.250:7800/api/set-device?";//id=x&status=y
    //接口返回结果名称
    public final static String MESSAGE = "msg";
    public final static String BALANCE = "balance";
    //接口返回错误码
    public final static String CODE = "code";
}
