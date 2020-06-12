package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase;

import android.provider.BaseColumns;

public class SearchDBContract {
    private SearchDBContract(){}
    public static final class SearchDataEntry implements BaseColumns{
        public static final String  TABLE_NAME ="searchDataList";
        public static final String  COLUMN_ID ="id";
        public static final String  COLUMN_Name ="name";
        public static final String  COLUMN_DetectTime ="detect_Time";
        public static final String  COLUMN_Item ="item";
        public static final String  COLUMN_FeedBackCount ="feed_back_Count";

        public static final String  COLUMN_AttentionHigh ="attention_High";
        public static final String  COLUMN_AttentionLow ="attention_Low";

        public static final String  COLUMN_RelaxationHigh ="relaxation_High";
        public static final String  COLUMN_RelaxationLow ="relaxation_Low";

        public static final String  COLUMN_AttentionMax ="attention_Max";
        public static final String  COLUMN_AttentionMin ="attention_Min";

        public static final String  COLUMN_RelaxationMax ="relaxation_Max";
        public static final String  COLUMN_RelaxationMin ="relaxation_Min";

        public static final String  COLUMN_FeedBackSecondsGap ="feedback_seconds_Gap";

        public static final String  COLUMN_FeedBackPassSeconds ="feedback_Pass_Second";

        public static final String  COLUMN_AverageAttention ="average_Attention";

        public static final String  COLUMN_AverageRelaxation ="average_Relaxation";

        public static final String COLUMN_PointInTime = "point_In_Time";
    }
}
