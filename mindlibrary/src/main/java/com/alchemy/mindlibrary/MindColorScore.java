package com.alchemy.mindlibrary;

public class MindColorScore {

    static int ScoreColorOne   = 3;
    static int ScoreColorTwo   = 2;
    static int ScoreColorThree = 1;
    static int ScoreColorFour  = 0;

    public static int Calc(int size){
        switch(size){
            case 1:
                return ScoreColorOne;
            case 2:
                return ScoreColorTwo;
            case 3:
                return ScoreColorThree;
            default:
                return ScoreColorFour;
        }
    }

    public static void SetUp(int one, int two, int three, int four){
        ScoreColorOne = one;
        ScoreColorTwo = two;
        ScoreColorThree = three;
        ScoreColorFour = four;
    }

}
