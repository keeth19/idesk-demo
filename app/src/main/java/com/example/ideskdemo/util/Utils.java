package com.example.ideskdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ideskdemo.R;


public class Utils {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static SharedPreferences sharedpreferences;

    public static String getSelectedLanguage(Context mContext) {
        String language = "";
        try {
            SharedPreferences settingsPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_language), Context.MODE_PRIVATE);
            language = settingsPreferences.getString(mContext.getResources().getString(R.string.user_language), "");
        } catch (Exception e) {
            Log.e("getUserPreferences", e.toString());
        }
        return language;
    }

    public static void updateSelectedLanguage(String language, Context mContext) {
        try {
            SharedPreferences loyaltyPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_language), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loyaltyPreferences.edit();
            editor.putString(mContext.getResources().getString(R.string.user_language), language);
            editor.apply();
        } catch (Exception e) {
            Log.e("updateStoreID", e.toString());
        }
    }

    public static boolean getSingIn(Context mContext) {
        boolean singIn = false;
        try {
            SharedPreferences settingsPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_singin), Context.MODE_PRIVATE);
            singIn = settingsPreferences.getBoolean(mContext.getResources().getString(R.string.user_singin),false);
        } catch (Exception e) {
            Log.e("getUserPreferences", e.toString());
        }
        return singIn;
    }

    public static void updateSingIn(boolean singIn, Context mContext) {
        try{
            SharedPreferences loyaltyPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_singin), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loyaltyPreferences.edit();
            editor.putBoolean(mContext.getResources().getString(R.string.user_singin), singIn);
            editor.apply();
        } catch (Exception e) {
            Log.e("updateStoreID", e.toString());
        }
    }

    public static boolean getInitial(Context mContext) {
        boolean initial = false;
        try {
            SharedPreferences settingsPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_singin), Context.MODE_PRIVATE);
            initial = settingsPreferences.getBoolean(mContext.getResources().getString(R.string.user_singin),false);
        } catch (Exception e) {
            Log.e("getUserPreferences", e.toString());
        }
        return initial;
    }

    public static void updateInitial(boolean initial, Context mContext) {
        try{
            SharedPreferences loyaltyPreferences = mContext.getSharedPreferences(mContext.getResources().getString(R.string.user_preference_singin), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loyaltyPreferences.edit();
            editor.putBoolean(mContext.getResources().getString(R.string.user_singin), initial);
            editor.apply();
        } catch (Exception e) {
            Log.e("updateStoreID", e.toString());
        }
    }

//    public boolean isConnected() {
//        boolean connected = false;
//        try {
//            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo nInfo = cm.getActiveNetworkInfo();
//            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
//            return connected;
//        } catch (Exception e) {
//            Log.e("Connectivity Exception", e.getMessage());
//        }
//        return connected;
//    }

    public static void sharedUerId(Context context, String userId){
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Client_id", userId);
        editor.apply();
    }

    public static String getSharedUerId(Context mContext){
        String userID = "";
        try {
            sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            userID = sharedpreferences.getString(MyPREFERENCES,"");
        } catch (Exception e) {
            Log.e("getUserPreferences", e.toString());
        }
        return userID;
    }

}





