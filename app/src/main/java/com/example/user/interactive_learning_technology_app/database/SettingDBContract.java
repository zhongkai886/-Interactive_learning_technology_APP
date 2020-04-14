package com.example.user.interactive_learning_technology_app.database;

import android.provider.BaseColumns;

public class SettingDBContract {
    private SettingDBContract(){}
    public static final class SettingDataEntry implements BaseColumns{
        public static final String  TABLE_NAME ="settingDataList";
        public static final String  COLUMN_ID ="id";
        public static final String  COLUMN_FeedBackWayName ="feedBackWay_Name";
        public static final String  COLUMN_DetectItem ="detect_Item";
        public static final String  COLUMN_AttentionHigh ="attention_High";
        public static final String  COLUMN_AttentionLow ="attention_Low";
        public static final String  COLUMN_DetectAttentionFeedBackWay ="detect_Attention_FeedBackWay";
        public static final String  COLUMN_RelaxationHigh ="relaxation_High";
        public static final String  COLUMN_RelaxationLow ="relaxation_Low";
        public static final String  COLUMN_DetectRelaxationFeedBackWay ="detect_Relaxation_FeedBackWay";
    }
}
