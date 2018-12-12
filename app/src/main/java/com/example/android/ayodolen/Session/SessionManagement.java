package com.example.android.ayodolen.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.ayodolen.MainActivity;

import java.util.HashMap;

public class SessionManagement {

    //Share preference
    private SharedPreferences mSharedPreference;
    //Editor for Shared preference
    private SharedPreferences.Editor mEditor;
    //context
    private Context mContext;
    //Shared pref mode
    int PRIVATE_MODE;
    //Shared pref name
    private static final String PREF_NAME = "SharedPrefLatihan";
    //Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id_user";
    public static final String KEY_Username = "username";
    public static final String KEY_password = "password";


    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPreference = this.mContext.getSharedPreferences(PREF_NAME,
                PRIVATE_MODE);
        mEditor = mSharedPreference.edit();
    }

    public void createLoginSession(String username, String password,Boolean cek){
// Storing login value as TRUE
        if(cek){
            mEditor.putBoolean(IS_LOGIN, true);
        }else{
            mEditor.putBoolean(IS_LOGIN, false);
        }

// Storing id
//        mEditor.putString(KEY_ID, id_user.toString());
        mEditor.putString(KEY_Username, username.toString());
        mEditor.putString(KEY_password, password.toString());

        mEditor.commit();
    }

    public HashMap<String, String> getUserInformation(){
        HashMap<String, String> user = new HashMap<String, String>();
// user email
        user.put(KEY_ID, mSharedPreference.getString(KEY_ID, null));
// return user
        return user;
    }

    public boolean isLoggedIn(){
        return mSharedPreference.getBoolean(IS_LOGIN, false);
    }

    public void checkIsLogin() {
// Check login status
        if (!isLoggedIn()) {
// user is not logged in redirect to MainActivity
            Intent i = new Intent(mContext, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
// Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }

    public void logoutUser(){
// Clearing all data from Shared Preferences
        mEditor.clear();
        mEditor.commit();
// After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

    public String getUsername(){
        return mSharedPreference.getString("username",null);
    }

    public String getPassword(){
        return mSharedPreference.getString("password",null);
    }

}
