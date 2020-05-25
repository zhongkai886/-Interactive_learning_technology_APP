package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/6/11.
 */


public class  Esense {

    @SerializedName("ts")
    @Expose
    private Integer ts;
    @SerializedName("attention")
    @Expose
    private Integer attention;
    @SerializedName("meditation")
    @Expose
    private Integer meditation;
    @SerializedName("delta")
    @Expose
    private Integer delta;
    @SerializedName("theta")
    @Expose
    private Integer theta;
    @SerializedName("lowAlpha")
    @Expose
    private Integer lowAlpha;
    @SerializedName("highAlpha")
    @Expose
    private Integer highAlpha;
    @SerializedName("lowBeta")
    @Expose
    private Integer lowBeta;
    @SerializedName("highBeta")
    @Expose
    private Integer highBeta;
    @SerializedName("lowGamma")
    @Expose
    private Integer lowGamma;
    @SerializedName("midGamma")
    @Expose
    private Integer midGamma;

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getMeditation() {
        return meditation;
    }

    public void setMeditation(Integer meditation) {
        this.meditation = meditation;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public Integer getTheta() {
        return theta;
    }

    public void setTheta(Integer theta) {
        this.theta = theta;
    }

    public Integer getLowAlpha() {
        return lowAlpha;
    }

    public void setLowAlpha(Integer lowAlpha) {
        this.lowAlpha = lowAlpha;
    }

    public Integer getHighAlpha() {
        return highAlpha;
    }

    public void setHighAlpha(Integer highAlpha) {
        this.highAlpha = highAlpha;
    }

    public Integer getLowBeta() {
        return lowBeta;
    }

    public void setLowBeta(Integer lowBeta) {
        this.lowBeta = lowBeta;
    }

    public Integer getHighBeta() {
        return highBeta;
    }

    public void setHighBeta(Integer highBeta) {
        this.highBeta = highBeta;
    }

    public Integer getLowGamma() {
        return lowGamma;
    }

    public void setLowGamma(Integer lowGamma) {
        this.lowGamma = lowGamma;
    }

    public Integer getMidGamma() {
        return midGamma;
    }

    public void setMidGamma(Integer midGamma) {
        this.midGamma = midGamma;
    }

}
