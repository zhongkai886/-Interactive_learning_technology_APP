package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database;

import android.provider.BaseColumns;

public class SettingDBContract {
    private SettingDBContract(){}
    public static final class SettingDataEntry implements BaseColumns{
        public static final String  TABLE_NAME ="settingDataList";
        public static final String  COLUMN_ID ="id";
        public static final String  COLUMN_Name ="name";
        public static final String  COLUMN_Item ="item";
        public static final String  COLUMN_AttentionHigh ="attention_High";
        public static final String  COLUMN_AttentionLow ="attention_Low";
        public static final String  COLUMN_AttentionFeedBackWay ="attention_FeedBackWay";
        public static final String  COLUMN_RelaxationHigh ="relaxation_High";
        public static final String  COLUMN_RelaxationLow ="relaxation_Low";
        public static final String  COLUMN_RelaxationFeedBackWay ="relaxation_FeedBackWay";
        public static final String  COLUMN_FeedBackWaySecond ="feedBackWay_Second";
        public static final String  COLUMN_FeedBackWayStopTipSecond ="feedBackWay_StopTip_Second";

    }
}
