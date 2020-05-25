package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeedBackWayRoom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FeedBackFrameSQL")
public class FeedBackWayNote {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Id")
    public String mId;

    @ColumnInfo(name = "FeedBackWay_Name")
    public String mFeedBackWayName;

    @ColumnInfo(name = "Detect_Item")
    public String mDetectItem;

    @ColumnInfo(name = "Attention_High")
    public Integer mAttentionHigh;

    @ColumnInfo(name = "Attention_Low")
    public Integer mAttentionLow;

    @ColumnInfo(name = "Detect_Attention_FeedBackWay")
    public String mDetectAttentionFeedBackWay;

    @ColumnInfo(name = "Relaxation_High")
    public Integer mRelaxationHigh;

    @ColumnInfo(name = "Relaxation_Low")
    public Integer mRelaxationLow;

    @ColumnInfo(name = "Detect_Relaxation_FeedBackWay")
    public String mDetectRelaxationFeedBackWay;

    public FeedBackWayNote(@NonNull String mId,
                           String mFeedBackWayName,
                           String mDetectItem,
                           Integer mAttentionHigh,
                           Integer mAttentionLow,
                           String mDetectAttentionFeedBackWay,
                           Integer mRelaxationHigh,
                           Integer mRelaxationLow,
                           String mDetectRelaxationFeedBackWay
                           ){
        this.mId=mId;
        this.mFeedBackWayName=mFeedBackWayName;
        this.mDetectItem=mDetectItem;
        this.mAttentionHigh=mAttentionHigh;
        this.mAttentionLow=mAttentionLow;
        this.mDetectAttentionFeedBackWay=mDetectAttentionFeedBackWay;
        this.mRelaxationHigh=mRelaxationHigh;
        this.mRelaxationLow=mRelaxationLow;
        this.mDetectRelaxationFeedBackWay=mDetectRelaxationFeedBackWay;

    }

}
