package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Cookies;

import android.content.Context;
import android.util.Log;

import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SharedPreferencesHelper.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by a2734043 on 2018/6/7.
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;
    SharedPreferencesHelper sharedPreferencesHelper;
    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    } // AddCookiesInterceptor()
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

                  String csrftoken = null;
                String sessionid = null;

            for (String header : originalResponse.headers("Set-Cookie")) {
                Log.e(";;;;;;;;;;;",   header );
             String[] string =header.split("=")  ;

                if(  string[0].equals("csrftoken")){
                    csrftoken= string[1].split(";")[0];
                    Log.e("csrftoken",  string[1].split(";")[0]   );


                }else if(string[0].equals("sessionid")){
                    sessionid=string[1].split(";")[0];
                    Log.e("sessionid", string[1].split(";")[0]  );

                }
                Log.e("result","csrftoken="+ csrftoken+";sessionid="+sessionid);
                sharedPreferencesHelper = new SharedPreferencesHelper(context);
                sharedPreferencesHelper.setCookie( "csrftoken="+ csrftoken+";sessionid="+sessionid);


//                cookies.add(header);
            }

//


//        }

        return originalResponse;
    }
}
