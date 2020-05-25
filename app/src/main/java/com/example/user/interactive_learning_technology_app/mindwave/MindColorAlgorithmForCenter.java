package com.example.user.interactive_learning_technology_app.mindwave;


import java.util.ArrayList;

public class MindColorAlgorithmForCenter {

    private static float BLUE_F1 = 0.425f;
    private static float BLUE_F2 = 0.525f;
    private static float GREEN_F1 = 0.325f;
    private static float GREEN_F2 = 0.275f;
    private static float ORANGE_F1 = 0.325f;
    private static float ORANGE_F2 = 0.425f;
    private static float YELLOW_F1 = 0.25f;
    private static float YELLOW_F2 = 0.2f;

    public static MindColorAlgorithm.Type calc(double highAlpha, double lowAlpha, double highBeta,
                                               double lowBeta, double lowGamma, double midGamma) {

        double alpha = highAlpha + lowAlpha;
        double beta = highBeta + lowBeta;
        double gamma = lowGamma + midGamma;
        double f1 = gamma / alpha;
        double f2 = gamma / beta;
        int[] meets = new int[MindColorAlgorithm.Type.values().length];
        ArrayList<MindColorAlgorithm.Type> meets2 = new ArrayList<MindColorAlgorithm.Type>();
        ArrayList<MindColorAlgorithm.Type> meets3 = new ArrayList<MindColorAlgorithm.Type>();

        for (MindColorAlgorithm.Type t : MindColorAlgorithm.Type.values()) {
            if (MindColorAlgorithm.factorFirstRange(t, f1)){
                meets[t.ordinal()]++;
                meets2.add(t);
            }
        }
        if (meets2.size() == 1) return meets2.get(0);

        for (MindColorAlgorithm.Type t : meets2) {
            if (MindColorAlgorithm.factorFirstRange(t, f2)) meets3.add(t);
        }
        if (meets3.size() == 1) return meets2.get(0);

        MindColorAlgorithm.Type rc = null;
        double dc = 100;
        for (MindColorAlgorithm.Type t : meets3) {
            double dcn = distance(f1, f2, t);
            if (dcn < dc){
                rc = t;
                dc = dcn;
            }
        }
        if(rc != null)return rc;

        return MindColorAlgorithm.Type.Orange;
    }

    private static double distance(double f1, double f2, MindColorAlgorithm.Type t){
        switch(t){
            case Orange:
                return distance(f1, f2, ORANGE_F1, ORANGE_F2);
            case Green:
                return distance(f1, f2, GREEN_F1, GREEN_F2);
            case Blue:
                return distance(f1, f2, BLUE_F1, BLUE_F2);
            case Yellow:
                return distance(f1, f2, YELLOW_F1, YELLOW_F2);
        }
        return 100;
    }

    private static double distance(double f1, double f2, double cf1, double cf2){
        double x = Math.abs(f1 - cf1);
        double y = Math.abs(f2 - cf2);
        return x*x+y*y;
    }

}
