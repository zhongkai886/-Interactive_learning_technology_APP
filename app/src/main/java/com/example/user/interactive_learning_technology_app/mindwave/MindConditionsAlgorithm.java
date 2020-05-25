package com.example.user.interactive_learning_technology_app.mindwave;

/**
 * <strong>MindConditionsAlgorithm in yyyText</strong><br/>
 * 使用值為 MindValueAlgorithm.Proportion()<br/>
 *
 * @author Jun-kia Wang
 * @Date 2016/3/25 上午10:03:15 TODO
 */
public class MindConditionsAlgorithm {

    private static double calc(double value, double level1, double level2) {
        if (level1 > level2 || level1 < 0 || value <= 0) return 0;
        if (value >= level2) return 100;

        double percent;
        if (value <= level1) {
            percent = value / level1;
            return percent * 50;
        } else {
            percent = (value - level1) / (level2 - level1);
            return percent * 50 + 50;
        }
    }

    public static double delta(double i) {
        return calc(i, 0.6d, 0.8d);
    }

    public static double highAlpha(double i) {
        return calc(i, 0.1d, 0.2d);
    }

    public static double highBeta(double i) {
        return calc(i, 0.05d, 0.1d);
    }

    public static double lowAlpha(double i) {
        return calc(i, 0.1d, 0.2d);
    }

    public static double lowBeta(double i) {
        return calc(i, 0.05d, 0.1d);
    }

    public static double lowGamma(double i) {
        return calc(i, 0.03d, 0.06d);
    }

    public static double midGamma(double i) {
        return calc(i, 0.03d, 0.06d);
    }

    public static double theta(double i) {
        return calc(i, 0.15d, 0.30d);
    }


    public static boolean deltaGood(double i) {
        return i > THR_DELTA;
    }

    public static boolean highAlphaGood(double i) {
        return i > THR_HIGHALPHA;
    }

    public static boolean highBetaGood(double i) {
        return i > THR_HIGHBETA;
    }

    public static boolean lowAlphaGood(double i) {
        return i > THR_LOWALPHA;
    }

    public static boolean lowBetaGood(double i) {
        return i > THR_LOWBETA;
    }

    public static boolean lowGammaGood(double i) {
        return i > THR_LOWGAMMA;
    }

    public static boolean midGammaGood(double i) {
        return i > THR_MIDGAMMA;
    }

    public static boolean thetaGood(double i) {
        return i > THR_THETA;
    }

    private static final int THR_DELTA = Integer.MAX_VALUE;
    private static final int THR_HIGHALPHA = 65;
    private static final int THR_HIGHBETA = 65;
    private static final int THR_LOWALPHA = 65;
    private static final int THR_LOWBETA = 65;
    private static final int THR_LOWGAMMA = 65;
    private static final int THR_MIDGAMMA = 65;
    private static final int THR_THETA = 65;
}
