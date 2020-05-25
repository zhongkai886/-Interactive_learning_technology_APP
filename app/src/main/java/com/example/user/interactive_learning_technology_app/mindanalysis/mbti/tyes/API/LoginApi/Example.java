package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.LoginApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/6/6.
 */


public class Example {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("store")
    @Expose
    private Store store;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}