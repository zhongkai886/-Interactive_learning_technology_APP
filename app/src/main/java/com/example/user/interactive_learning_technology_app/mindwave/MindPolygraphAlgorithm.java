package com.example.user.interactive_learning_technology_app.mindwave;

public class MindPolygraphAlgorithm {

    public static int level(double theta, double highAlpha, double lowAlpha,
                            double highBeta, double lowBeta) {
        double alpha = highAlpha + lowAlpha;
        double beta = highBeta + lowBeta;
        double f1 = theta / beta;
        double f2 = alpha / beta;

        return factorRange(f1, f2);
    }

    private static int factorRange(double factor1, double factor2) {
        double thr1, thr2;
        for (int i = MAX_LEVEL; i > 1; i--) {
            switch (i) {
                case 5:
                    thr1 = LEVEL_5_FACTOR_FIRST;
                    thr2 = LEVEL_5_FACTOR_SECOND;
                    break;
                case 4:
                    thr1 = LEVEL_4_FACTOR_FIRST;
                    thr2 = LEVEL_4_FACTOR_SECOND;
                    break;
                case 3:
                    thr1 = LEVEL_3_FACTOR_FIRST;
                    thr2 = LEVEL_3_FACTOR_SECOND;
                    break;
                case 2:
                    thr1 = LEVEL_2_FACTOR_FIRST;
                    thr2 = LEVEL_2_FACTOR_SECOND;
                    break;
                default:
                    thr1 = 99d;
                    thr2 = 0d;
                    break;
            }
            if (factor1 > thr1 && factor2 < thr2) return i;
        }

        return 1;
    }

    public static int percentage(double theta, double highAlpha, double lowAlpha,
                                 double highBeta, double lowBeta) {
        double alpha = highAlpha + lowAlpha;
        double beta = highBeta + lowBeta;
        double f1 = theta / beta;
        double f2 = alpha / beta;

        double p1 = 100 * (f1 - LEVEL_FACTOR_FIRST_BOTTOM) / (LEVEL_FACTOR_FIRST_RANGE);
        double p2 = 100 * (LEVEL_FACTOR_SECOND_TOP - f2) / (LEVEL_FACTOR_SECOND_RANGE);

        if (p1 > 100 && p2 > 100) return 100;
        if (p1 < 0 || p2 < 0) return 0;
        return (int) (p1 < p2 ? p1 : p2);
    }


    private static final int MAX_LEVEL = 5;
    private static final double LEVEL_2_FACTOR_FIRST = 5.5d;
    private static final double LEVEL_2_FACTOR_SECOND = 2.5d;
    private static final double LEVEL_3_FACTOR_FIRST = 6d;
    private static final double LEVEL_3_FACTOR_SECOND = 2d;
    private static final double LEVEL_4_FACTOR_FIRST = 6.5d;
    private static final double LEVEL_4_FACTOR_SECOND = 1.5d;
    private static final double LEVEL_5_FACTOR_FIRST = 7d;
    private static final double LEVEL_5_FACTOR_SECOND = 1d;

    private static final double LEVEL_FACTOR_FIRST_TOP = LEVEL_5_FACTOR_FIRST;
    private static final double LEVEL_FACTOR_FIRST_BOTTOM = LEVEL_2_FACTOR_FIRST;
    private static final double LEVEL_FACTOR_SECOND_TOP = LEVEL_2_FACTOR_SECOND;
    private static final double LEVEL_FACTOR_SECOND_BOTTOM = LEVEL_5_FACTOR_SECOND;
    private static final double LEVEL_FACTOR_FIRST_RANGE = LEVEL_FACTOR_FIRST_TOP - LEVEL_FACTOR_FIRST_BOTTOM;
    private static final double LEVEL_FACTOR_SECOND_RANGE = LEVEL_FACTOR_SECOND_TOP - LEVEL_FACTOR_SECOND_BOTTOM;

}
