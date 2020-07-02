package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

public class DetectData {

    private String Id;
    private String Name;
    private String DetectTime;
    private String Item;
    private String FeedBackCount;

    private String AttentionHigh;
    private String AttentionLow;

    private String RelaxationHigh;
    private String RelaxationLow;

    private String AttentionMax;
    private String AttentionMin;

    private String RelaxationMax;
    private String RelaxationMin;
    private String FeedbackSecondsGap;
    private String FeedbackPassSecond;

    private String AverageAttention;
    private String AverageRelaxation;

    private String PointInTime;



    public DetectData(String Id , String Name , String DetectTime,String Item,String FeedBackCount,
                        String AttentionHigh, String AttentionLow,
                        String RelaxationHigh, String RelaxationLow,
                        String AttentionMax, String AttentionMin,
                        String RelaxationMax, String RelaxationMin,
                        String FeedbackSecondsGap , String FeedbackPassSecond,
                        String AverageAttention , String AverageRelaxation,
                        String PointInTime){
        this.Id=Id;
        this.Name=Name;
        this.DetectTime=DetectTime;
        this.Item=Item;
        this.FeedBackCount=FeedBackCount;
        this.AttentionHigh=AttentionHigh;
        this.AttentionLow=AttentionLow;
        this.RelaxationHigh=RelaxationHigh;
        this.RelaxationLow=RelaxationLow;
        this.AttentionMax=AttentionMax;
        this.AttentionMin=AttentionMin;
        this.RelaxationMax=RelaxationMax;
        this.RelaxationMin=RelaxationMin;
        this.FeedbackSecondsGap=FeedbackSecondsGap;
        this.FeedbackPassSecond=FeedbackPassSecond;
        this.AverageAttention=AverageAttention;
        this.AverageRelaxation=AverageRelaxation;
        this.PointInTime=PointInTime;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDetectTime() {
        return DetectTime;
    }

    public String getItem() {
        return Item;
    }

    public String getFeedBackCount() {
        return FeedBackCount;
    }

    public String getAttentionHigh() {
        return AttentionHigh;
    }

    public String getAttentionLow() {
        return AttentionLow;
    }

    public String getRelaxationHigh() {
        return RelaxationHigh;
    }

    public String getRelaxationLow() {
        return RelaxationLow;
    }

    public String getAttentionMax() {
        return AttentionMax;
    }

    public String getAttentionMin() {
        return AttentionMin;
    }

    public String getRelaxationMax() {
        return RelaxationMax;
    }

    public String getRelaxationMin() {
        return RelaxationMin;
    }

    public String getFeedbackSecondsGap() {
        return FeedbackSecondsGap;
    }

    public String getFeedbackPassSecond() {
        return FeedbackPassSecond;
    }

    public String getAverageAttention() {
        return AverageAttention;
    }

    public String getAverageRelaxation() {
        return AverageRelaxation;
    }

    public String getPointInTime() {
        return PointInTime;
    }
}
