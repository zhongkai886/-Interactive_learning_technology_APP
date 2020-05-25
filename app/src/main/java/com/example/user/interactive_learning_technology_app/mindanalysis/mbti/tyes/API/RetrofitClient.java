package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API;

import android.content.Context;

import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Cookies.AddCookiesInterceptor;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Cookies.ReceivedCookiesInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2734043 on 2018/6/6.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    OkHttpClient client = new OkHttpClient();
    //    public final String BASE_URL = "http://braindna.co/";
    public final String URL = "http://54.174.149.227:8080";
    public final String NEWURL= "http://18.213.117.168:8087";

    //  http://54.174.149.227:8080/calculate16Personality


    public APIService getAPIService(Context context) {

        return getClient(NEWURL, context).create(APIService.class);
    }


    public OkHttpClient getBuilder(Context context) {
        return new OkHttpClient.Builder()
//                .addInterceptor(new AddCookiesInterceptor(context))  // VERY VERY IMPORTANT
//                .addInterceptor(new ReceivedCookiesInterceptor(context))  // VERY VERY IMPORTANT
                .build();


    }

    public Retrofit getClient(String baseUrl, Context context) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getBuilder(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public final String BASE_URL = "http://braindna.co/";

    public APIService getService(Context context) {

        return getTwiceClient(BASE_URL, context).create(APIService.class);
    }


    public OkHttpClient getTwiceBuilder(Context context) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(context))  // VERY VERY IMPORTANT
                .addInterceptor(new ReceivedCookiesInterceptor(context))  // VERY VERY IMPORTANT
                .build();


    }

    public Retrofit getTwiceClient(String baseUrl, Context context) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getTwiceBuilder(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
}
