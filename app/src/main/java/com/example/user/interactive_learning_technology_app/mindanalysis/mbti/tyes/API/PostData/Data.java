package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by a2734043 on 2018/6/11.
 */

public class Data {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("esense")
    @Expose
    private List<Esense> esense = null;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Esense> getEsense() {
        return esense;
    }

    public void setEsense(List<Esense> esense) {
        this.esense = esense;
    }

}