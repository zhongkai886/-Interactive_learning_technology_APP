package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a2734043 on 2018/7/17.
 */

public class Datum {

    @SerializedName("eegDelta")
    @Expose
    private Integer eegDelta;
    @SerializedName("eegTheta")
    @Expose
    private String eegTheta;
    @SerializedName("eegLowAlpha")
    @Expose
    private String eegLowAlpha;
    @SerializedName("eegHighAlpha")
    @Expose
    private String eegHighAlpha;
    @SerializedName("eegLowBeta")
    @Expose
    private String eegLowBeta;
    @SerializedName("eegHighBeta")
    @Expose
    private String eegHighBeta;
    @SerializedName("eegLowGamma")
    @Expose
    private String eegLowGamma;
    @SerializedName("eegHighGamma")
    @Expose
    private String eegHighGamma;

    public Integer getEegDelta() {
        return eegDelta;
    }

    public void setEegDelta(Integer eegDelta) {
        this.eegDelta = eegDelta;
    }

    public String getEegTheta() {
        return eegTheta;
    }

    public void setEegTheta(String eegTheta) {
        this.eegTheta = eegTheta;
    }

    public String getEegLowAlpha() {
        return eegLowAlpha;
    }

    public void setEegLowAlpha(String eegLowAlpha) {
        this.eegLowAlpha = eegLowAlpha;
    }

    public String getEegHighAlpha() {
        return eegHighAlpha;
    }

    public void setEegHighAlpha(String eegHighAlpha) {
        this.eegHighAlpha = eegHighAlpha;
    }

    public String getEegLowBeta() {
        return eegLowBeta;
    }

    public void setEegLowBeta(String eegLowBeta) {
        this.eegLowBeta = eegLowBeta;
    }

    public String getEegHighBeta() {
        return eegHighBeta;
    }

    public void setEegHighBeta(String eegHighBeta) {
        this.eegHighBeta = eegHighBeta;
    }

    public String getEegLowGamma() {
        return eegLowGamma;
    }

    public void setEegLowGamma(String eegLowGamma) {
        this.eegLowGamma = eegLowGamma;
    }

    public String getEegHighGamma() {
        return eegHighGamma;
    }

    public void setEegHighGamma(String eegHighGamma) {
        this.eegHighGamma = eegHighGamma;
    }

}