package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

public class DetectData {

    private String Id;
    private String Number;
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

    private String DetectTimeCount;
    private String SecondsAttentionOutput;
    private String SecondsRelaxationOutput;


    private String FeedBackSecondOutput;

    private String PointInTime;

    public DetectData(String Id , String Number, String Name , String DetectTime, String Item, String FeedBackCount,
                      String AttentionHigh, String AttentionLow,
                      String RelaxationHigh, String RelaxationLow,
                      String AttentionMax, String AttentionMin,
                      String RelaxationMax, String RelaxationMin,
                      String FeedbackSecondsGap , String FeedbackPassSecond,
                      String AverageAttention , String AverageRelaxation,
                      String DetectTimeCount,
                      String SecondsAttentionOutput,
                      String SecondsRelaxationOutput,
                      String FeedBackSecondOutput,
                      String PointInTime){
        this.Id=Id;
        this.Number=Number;
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
        this.DetectTimeCount=DetectTimeCount;
        this.SecondsAttentionOutput=SecondsAttentionOutput;
        this.SecondsRelaxationOutput=SecondsRelaxationOutput ;
        this.FeedBackSecondOutput=FeedBackSecondOutput;
        this.PointInTime=PointInTime;
    }

    public String getId() {
        return Id;
    }

    public String getNumber(){return Number;}

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

    public String getDetectTimeCount() {
        return DetectTimeCount;
    }

    public String getSecondsAttentionOutput() {
        return SecondsAttentionOutput;
    }


    public String getSecondsRelaxationOutput() {
        return SecondsRelaxationOutput;
    }

    public String getFeedBackSecondOutput() {
        return FeedBackSecondOutput;
    }

    public String getPointInTime() {
        return PointInTime;
    }
}
