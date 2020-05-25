package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SharedPreferencesHelper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by a2734043 on 2018/6/7.
 */

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    private String cookieKey = "cookieKey";
    private String username = "username";
    private String password = "password";
    private String Boolean = "boolean";
    private String UserId = "userid";
    private String MetaId = "metaid";
    private String ResultId = "resultid";
    private String Count = "count";
    private String Mail="mail";


    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("sharedPreferencesKey", Context.MODE_PRIVATE);
    }

    public void setCookie(String string) {
        sharedPreferences.edit().putString(cookieKey, string).apply();

    }
    public String getCookie() {

        return sharedPreferences.getString(cookieKey, "");
    }

    public String getMetaId() {

        return sharedPreferences.getString(MetaId, "");
    }

    public void setMetaId(String string) {
        sharedPreferences.edit().putString(MetaId, string).apply();

    }
    public int getResultId() {

        return sharedPreferences.getInt(ResultId, 0);
    }

    public void setResultId(int integer) {
        sharedPreferences.edit().putInt(ResultId, integer).apply();

    }
    public int getCount() {

        return sharedPreferences.getInt(Count, 0);
    }

    public void setCount(int integer) {
        sharedPreferences.edit().putInt(Count, integer).apply();

    }


    public void setUsername(String string) {
        sharedPreferences.edit().putString(username, string).apply();


    }

    public String getUsername() {

        return sharedPreferences.getString(username, "");
    }

    public void setPassword(String string) {
        sharedPreferences.edit().putString(password, string).apply();

    }

    public String getPassword() {

        return sharedPreferences.getString(password, "");
    }

    public void setLogin(Boolean aBoolean) {
        sharedPreferences.edit().putBoolean(Boolean, aBoolean).apply();

    }

    public Boolean getLogin() {

        return sharedPreferences.getBoolean(Boolean, false);
    }


    public void setUserId(String string) {
        sharedPreferences.edit().putString(UserId, string).apply();

    }

    public String getUserId() {

        return sharedPreferences.getString(UserId, "");
    }



    public void setMail(String string) {
        sharedPreferences.edit().putString(Mail, string).apply();

    }

    public String getMail() {

        return sharedPreferences.getString(Mail, "");
    }

}
