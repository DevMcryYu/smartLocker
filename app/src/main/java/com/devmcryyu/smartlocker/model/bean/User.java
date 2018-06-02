package com.devmcryyu.smartlocker.model.bean;

import android.media.Image;

/**
 * Created by 92075 on 2018/3/14.
 */

public class User {

    int userID;
    String userName;
    Image userImage;
    String userPassword;
    String userPayPassword;
    double userBalance;
    int userPermission;
    final int ORDINARY_USER=0;
    final int ADVANCED_USER=1;

    public Image getUserImage() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }


    public User(){
        userName="";
        userPassword="";
        userPayPassword="";
        userBalance=0.00;
        userPermission=ORDINARY_USER;
    }
    public User(int id,String name,String password){
        this();
        this.setUserID(id);
        this.setUserName(name);
        this.setUserPassword(password);
    }
    public User(User user,String payPassword){
        this(user.userID,user.userName,user.userPassword);
        this.setUserPayPassword(payPassword);
    }
    public void setUserID(int uid){
        this.userID=uid;
    }
    public void setUserName(String name){
        this.userName=name!=null&&!name.equals("")?name:"";
    }
    public void setUserPassword(String password){
        this.userPassword=password!=null&&!password.equals("")?password:"";
    }
    public void setUserPayPassword(String payPassword){
        this.userPayPassword=payPassword!=null&&!payPassword.equals("")?payPassword:"";
    }
    public void setUserBalance(double balance){
        this.userBalance=balance>=0?balance:0;
    }
    public void setUserPermission(int permission){
        this.userPermission=permission==ORDINARY_USER||permission==ADVANCED_USER?permission:ORDINARY_USER;
    }
    public int getUserID(){
        return this.userID;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserPassword() {
        return this.userPassword;
    }
    public String getUserPayPassword(){
        return this.userPayPassword;
    }
    public double getUserBalance(){
        return this.userBalance;
    }
    public int getUserPermission(){
        return this.userPermission;
    }

}
