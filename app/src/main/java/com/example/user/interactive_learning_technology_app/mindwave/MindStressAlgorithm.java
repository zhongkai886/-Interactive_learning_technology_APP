package com.example.user.interactive_learning_technology_app.mindwave;

public class MindStressAlgorithm {

    public static double calc(int[] midGamma, int[] lowAlpha) {
        double aveMidGamma = calcAve(midGamma, THR_MIDGAMMA_BOTTOM, THR_MIDGAMMA_TOP);
        double aveLowAlpha = calcAve(lowAlpha, THR_LOWALPHA_BOTTOM, THR_LOWALPHA_TOP);

        double ptMidGamma = calcPercent(aveMidGamma, THR_MIDGAMMA_BOTTOM, THR_MIDGAMMA_TOP);
        double ptLowAlpha = calcPercent(aveLowAlpha, THR_LOWALPHA_BOTTOM, THR_LOWALPHA_TOP);
        return PROPORTION_MIDGAMMA * ptMidGamma + PROPORTION_LOWALPHA * ptLowAlpha;
    }

    private static double calcAve(int[] value, int bottom, int top) {
        double sum = 0;
        for (int i : value) {
            sum += (i > top) ? top : (i < bottom) ? bottom : i;
        }
        return sum / value.length;
    }

    private static double calcPercent(double value, int bottom, int top) {
        return (value - bottom) / (top - bottom);
    }

    private static final int THR_MIDGAMMA_TOP = 6000;
    private static final int THR_MIDGAMMA_BOTTOM = 2000;
    private static final int THR_LOWALPHA_TOP = 25000;
    private static final int THR_LOWALPHA_BOTTOM = 4000;
    private static final int PROPORTION_MIDGAMMA = 50;
    private static final int PROPORTION_LOWALPHA = 50;
}
