package com.alchemy.mindlibrary;

public class MindStressAlgorithm {

    public static double CalcArray(int[] midGamma, int[] lowAlpha) {
        double a1 = ave(midGamma, THR_MIDGAMMA_BOTTOM, THR_MIDGAMMA_TOP);
        double a2 = ave(lowAlpha, THR_LOWALPHA_BOTTOM, THR_LOWALPHA_TOP);
        return Calc((int)(a1 + 0.5f), (int)(a2 + 0.5f));
    }



    public static double Calc(int midGamma, int lowAlpha) {

        double ptMidGamma = percent(midGamma, THR_MIDGAMMA_BOTTOM, THR_MIDGAMMA_TOP);
        double ptLowAlpha = percent(lowAlpha, THR_LOWALPHA_BOTTOM, THR_LOWALPHA_TOP);
        return PROPORTION_MIDGAMMA * ptMidGamma + PROPORTION_LOWALPHA * ptLowAlpha;
    }

    static double ave(int[] value, int bottom, int top) {
        double sum = 0;
        for (int i : value) {
            sum += (i > top) ? top : (i < bottom) ? bottom : i;
        }
        return sum / value.length;
    }

    static double percent(double value, int bottom, int top) {
        return (value - bottom) / (top - bottom);
    }



    private static final int PROPORTION_MIDGAMMA = 50;
    private static final int PROPORTION_LOWALPHA = 50;

    static int THR_MIDGAMMA_TOP = 6000;
    static int THR_MIDGAMMA_BOTTOM = 2000;
    static int THR_LOWALPHA_TOP = 25000;
    static int THR_LOWALPHA_BOTTOM = 4000;

    public static void SetUpMidGamma(int bottom, int top){
        THR_MIDGAMMA_TOP = top;
        THR_MIDGAMMA_BOTTOM = bottom;
    }

    public static void SetUpLowAlpha(int bottom, int top){
        THR_LOWALPHA_TOP = bottom;
        THR_LOWALPHA_BOTTOM = top;
    }


}
