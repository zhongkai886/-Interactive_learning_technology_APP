package com.example.user.interactive_learning_technology_app.base;

import android.app.Application;

class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance(){
        if(mInstance == null){
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
