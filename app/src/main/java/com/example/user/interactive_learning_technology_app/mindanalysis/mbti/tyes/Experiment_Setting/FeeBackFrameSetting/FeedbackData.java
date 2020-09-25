package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

public class FeedbackData {


    private String Id;
    private String Name;
    private String Item;
    private String AttentionHigh;
    private String AttentionLow;
    private String AttentionWay;
    private String AttentionMp3Uri;
    private String RelaxationHigh;
    private String RelaxationLow;
    private String RelaxationWay;
    private String RelaxationMp3Uri;
    private String WaySecond;
    private String HoldSecond;
    private String WayStopTipSecond;
    private Boolean check=false;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }




    public FeedbackData(String Id , String Name , String Item,
                        String AttentionHigh, String AttentionLow, String AttentionWay,
                        String AttentionMp3Uri,
                        String RelaxationHigh, String RelaxationLow, String RelaxationWay,
                        String RelaxationMp3Uri,
                        String WaySecond, String HoldSecond , String WayStopTipSecond){
        this.Id=Id;
        this.Name=Name;
        this.Item=Item;
        this.AttentionHigh=AttentionHigh;
        this.AttentionLow=AttentionLow;
        this.AttentionWay=AttentionWay;
        this.AttentionMp3Uri=AttentionMp3Uri;
        this.RelaxationHigh=RelaxationHigh;
        this.RelaxationLow=RelaxationLow;
        this.RelaxationWay=RelaxationWay;
        this.RelaxationMp3Uri=RelaxationMp3Uri;
        this.WaySecond=WaySecond;
        this.HoldSecond=HoldSecond;
        this.WayStopTipSecond=WayStopTipSecond;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getItem() {
        return Item;
    }

    public String getAttentionHigh() {
        return AttentionHigh;
    }

    public String getAttentionLow() {
        return AttentionLow;
    }

    public String getAttentionWay() {
        return AttentionWay;
    }

    public String getRelaxationHigh() {
        return RelaxationHigh;
    }

    public String getRelaxationLow() {
        return RelaxationLow;
    }

    public String getRelaxationWay() {
        return RelaxationWay;
    }

    public String getWaySecond() {
        return WaySecond;
    }

    public String getHoldSecond() {
        return HoldSecond;
    }

    public String getWayStopTipSecond() {
        return WayStopTipSecond;
    }

    public String getAttentionMp3Uri() {
        return AttentionMp3Uri;
    }

    public String getRelaxationMp3Uri() {
        return RelaxationMp3Uri;
    }

}
