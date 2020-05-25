package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Cookies;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SharedPreferencesHelper.SharedPreferencesHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by a2734043 on 2018/6/7.
 */

public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";

    // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    // I reccomend you do this, and don't change this default value.
    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());

        // Use the following if you need everything in one line.
        // Some APIs die if you do it differently.
        /*String cookiestring = "";
        for (String cookie : preferences) {
            String[] parser = cookie.split(";");
            cookiestring = cookiestring + parser[0] + "; ";
        }
        builder.addHeader("Cookie", cookiestring);
        */
        SharedPreferencesHelper sharedPreferencesHelper=new SharedPreferencesHelper(context);
        Log.e("jjjjjjjjjjj",  sharedPreferencesHelper.getCookie() );
            builder.addHeader("Cookie", sharedPreferencesHelper.getCookie()  ) ;





        return chain.proceed(builder.build());
    }

}