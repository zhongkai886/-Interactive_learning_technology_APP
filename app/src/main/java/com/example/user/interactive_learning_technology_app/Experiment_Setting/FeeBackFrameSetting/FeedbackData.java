package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

public class FeedbackData {
    private String Id;
    private String Name;
    private String Item;
    private String AttentionHigh;
    private String AttentionLow;
    private String AttentionWay;
    private String RelaxationHigh;
    private String RelaxationLow;
    private String RelaxationWay;
    private String WaySecond;
    private String WayStopTipSecond;

    public FeedbackData(String Id , String Name , String Item,
                        String AttentionHigh, String AttentionLow, String AttentionWay,
                        String RelaxationHigh, String RelaxationLow, String RelaxationWay,
                        String WaySecond, String WayStopTipSecond){
        this.Id=Id;
        this.Name=Name;
        this.Item=Item;
        this.AttentionHigh=AttentionHigh;
        this.AttentionLow=AttentionLow;
        this.AttentionWay=AttentionWay;
        this.RelaxationHigh=RelaxationHigh;
        this.RelaxationLow=RelaxationLow;
        this.RelaxationWay=RelaxationWay;
        this.WaySecond=WaySecond;
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

    public String getWayStopTipSecond() {
        return WayStopTipSecond;
    }
}
