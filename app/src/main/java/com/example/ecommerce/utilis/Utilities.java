package com.example.ecommerce.utilis;

import android.content.SharedPreferences;

import com.example.ecommerce.constants.AppConstants;
import com.example.ecommerce.helpers.MyApplication;
import com.example.ecommerce.models.Customers;
import com.example.ecommerce.models.Token;
import com.google.gson.GsonBuilder;

public class Utilities {


    public static void saveLoginResponse(Token token){
        String json=new GsonBuilder().create().toJson(token);
        SharedPreferences.Editor editor=MyApplication.getSharedPreference().edit();
        editor.putString(AppConstants.LOGIN_RESPONSE,json);
        editor.apply();
        setIsFirstLogin(true);
    }

    public static void saveUserInfo(Customers customers){
        String json=new GsonBuilder().create().toJson(customers);
        SharedPreferences.Editor editor=MyApplication.getSharedPreference().edit();
        editor.putString(AppConstants.REGISTER_RESPONSE,json);
        editor.apply();
    }



//
//
    public static Token getLoginResponse(){
        String savedUserResponse= MyApplication.getSharedPreference().getString(AppConstants.LOGIN_RESPONSE,null);
        return  new GsonBuilder().create().fromJson(savedUserResponse,Token.class);
    }

    public static Customers getUserInfo(){
        String savedUserResponse= MyApplication.getSharedPreference().getString(AppConstants.REGISTER_RESPONSE,null);
        return  new GsonBuilder().create().fromJson(savedUserResponse,Customers.class);
    }

    public static void setIsFirstLogin(boolean status){
        SharedPreferences.Editor editor=MyApplication.getSharedPreference().edit();
        editor.putBoolean(AppConstants.IS_FIRST_LOGIN,status);
        editor.apply();
    }



    public static boolean isLogin(){
        return MyApplication.getSharedPreference().getBoolean(AppConstants.IS_FIRST_LOGIN, false);
    }


    public static void saveUserTokenLogin(String token) {
    }
}
