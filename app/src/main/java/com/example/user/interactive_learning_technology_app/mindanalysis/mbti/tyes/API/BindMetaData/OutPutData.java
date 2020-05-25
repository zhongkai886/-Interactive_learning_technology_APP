package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/6/11.
 */

public class OutPutData {

    @SerializedName("meta")
    @Expose
    private String meta;

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

}