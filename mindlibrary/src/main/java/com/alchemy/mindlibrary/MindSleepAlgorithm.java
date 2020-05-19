package com.alchemy.mindlibrary;


public class MindSleepAlgorithm {

    public static int Calc(float mentalEffort, float familiarity){

        int s = (int)(familiarity - mentalEffort + 0.5f);

        if(s < THRESHOLD_Level_1)s = 0;
        else if(s < THRESHOLD_Level_2)s = 1;
        else s = 2;

        return s;
    }


    static int THRESHOLD_Level_1 = 20;
    static int THRESHOLD_Level_2 = 50;

    public static void SetUp(int i1, int i2){
        THRESHOLD_Level_1 = i1;
        THRESHOLD_Level_2 = i2;
    }
}
