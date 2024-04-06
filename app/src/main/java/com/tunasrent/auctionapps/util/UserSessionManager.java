package com.tunasrent.auctionapps.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by yuli on 30/12/2017.
 */

public class UserSessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "SessionLogin";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String SES_NAME = "username";
    public static final String SES_FULLNAME = "fullname";
    public static final String SES_STOREID = "storeid";
    public static final String SES_LOGO = "logo";
    public static final String SES_APPID = "appid";
    public static final String SES_CCODE = "ccode";
    public static final String SES_TOKEN = "token";
    public static final String SES_GROUP = "group";
    public static final String SES_POOL = "pool";

    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String username, String fullname, String appid, String ccode, String token, String group, String pool){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(SES_NAME, username);
        editor.putString(SES_FULLNAME, fullname);
//        editor.putString(SES_STOREID, storeid);
//        editor.putString(SES_LOGO, logo);
        editor.putString(SES_APPID,appid);
        editor.putString(SES_CCODE,ccode);
        editor.putString(SES_TOKEN,token);
        editor.putString(SES_GROUP,group);
        editor.putString(SES_POOL,pool);
        editor.commit();
    }

//    public boolean checkLogin(){
//        if (!this.isUserLoggedIn()){
//            Intent i = new Intent(_context,SigninActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//            return true;
//        }
//        return false;
//    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(SES_NAME,pref.getString(SES_NAME,null));
        user.put(SES_FULLNAME,pref.getString(SES_FULLNAME,null));
//        user.put(SES_STOREID,pref.getString(SES_STOREID,null));
//        user.put(SES_LOGO,pref.getString(SES_LOGO,null));
        user.put(SES_APPID,pref.getString(SES_APPID,null));
        user.put(SES_CCODE,pref.getString(SES_CCODE,null));
        user.put(SES_TOKEN,pref.getString(SES_TOKEN,null));
        user.put(SES_GROUP,pref.getString(SES_GROUP,null));
        user.put(SES_POOL,pref.getString(SES_POOL,null));
        return user;
    }
    public void logoutUser(){
        editor.clear();
        editor.commit();

//        Intent i = new Intent(_context, LoginActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
    }
    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }


}
