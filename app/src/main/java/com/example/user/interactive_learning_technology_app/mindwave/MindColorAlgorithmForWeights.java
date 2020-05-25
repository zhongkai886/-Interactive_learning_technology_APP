package com.example.user.interactive_learning_technology_app.mindwave;


import java.util.ArrayList;

public class MindColorAlgorithmForWeights {

    public static MindColorAlgorithm.Type calc(double highAlpha, double lowAlpha, double highBeta,
                                               double lowBeta, double lowGamma, double midGamma, int weights) {

        double alpha = highAlpha + lowAlpha;
        double beta = highBeta + lowBeta;
        double gamma = lowGamma + midGamma;
        double f1 = gamma / alpha;
        double f2 = gamma / beta;
        int[] meets = new int[MindColorAlgorithm.Type.values().length];
        ArrayList<MindColorAlgorithm.Type> meets2 = new ArrayList<MindColorAlgorithm.Type>();

        for (MindColorAlgorithm.Type t : MindColorAlgorithm.Type.values()) {
            if (MindColorAlgorithm.factorFirstRange(t, f1)){
                meets[t.ordinal()]++;
                meets2.add(t);
            }
        }
        if (meets2.size() == 1) return meets2.get(0);

        for (MindColorAlgorithm.Type t : meets2) {
            if (!MindColorAlgorithm.factorFirstRange(t, f2)) meets[t.ordinal()]--;
        }

        for (; weights > 0; weights /= 10) {
            int index = weights % 10;
            if (meets[index] > 0) return MindColorAlgorithm.Type.values()[index];
        }

        return MindColorAlgorithm.Type.Orange;
    }

}
