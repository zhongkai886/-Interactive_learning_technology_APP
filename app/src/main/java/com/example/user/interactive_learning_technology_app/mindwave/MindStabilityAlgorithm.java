package com.example.user.interactive_learning_technology_app.mindwave;

public class MindStabilityAlgorithm {

    public static int level(double highAlpha, double highBeta, double lowBeta) {
        double f = (lowBeta - highBeta) / highAlpha;

        double thr;
        for (int i = MAX_LEVEL; i > 1; i--) {
            switch (i) {
                case 4:
                    thr = LEVEL_4_FACTOR;
                    break;
                case 3:
                    thr = LEVEL_3_FACTOR;
                    break;
                case 2:
                    thr = LEVEL_2_FACTOR;
                    break;
                default:
                    thr = 0d;
                    break;
            }
            if (f > thr) return i;
        }
        return 1;
    }

    private static final int MAX_LEVEL = 4;
    private static final double LEVEL_2_FACTOR = -5d;
    private static final double LEVEL_3_FACTOR = 4d;
    private static final double LEVEL_4_FACTOR = 7d;

}
