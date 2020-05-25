package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/6/11.
 */

public class OutputPost {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("raw")
    @Expose
    private Object raw;
    @SerializedName("esense")
    @Expose
    private String esense;
    @SerializedName("recordingType")
    @Expose
    private Object recordingType;
    @SerializedName("meta")
    @Expose
    private Object meta;
    @SerializedName("data")
    @Expose
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public Object getRaw() {
        return raw;
    }

    public void setRaw(Object raw) {
        this.raw = raw;
    }

    public String getEsense() {
        return esense;
    }

    public void setEsense(String esense) {
        this.esense = esense;
    }

    public Object getRecordingType() {
        return recordingType;
    }

    public void setRecordingType(Object recordingType) {
        this.recordingType = recordingType;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
