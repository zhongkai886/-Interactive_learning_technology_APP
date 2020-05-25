package com.example.user.interactive_learning_technology_app.mindwave;

import java.util.ArrayList;

public class MindColorAlgorithm {

    public enum Type {
        Orange, Green, Blue, Yellow
    }

    public static Type calc(double highAlpha, double lowAlpha, double highBeta,
                            double lowBeta, double lowGamma, double midGamma) {
        double alpha = highAlpha + lowAlpha;
        double beta = highBeta + lowBeta;
        double gamma = lowGamma + midGamma;
        double f1 = gamma / alpha;
        double f2 = gamma / beta;

        ArrayList<Type> meets = new ArrayList<Type>();
        for (Type t : Type.values()) {
            if (factorFirstRange(t, f1)) meets.add(t);
        }
        if (meets.size() == 1) return meets.get(0);

        for (Type t : meets) {
            if (factorSecondRange(t, f2)) return t;
        }
        if (meets.size() == 0) return Type.Orange;
        else return meets.get(0);
    }

    protected static boolean factorFirstRange(Type color, double factor) {
        double top, bottom;
        switch (color) {
            case Blue:
                top = 0.55d;
                bottom = 0.3d;
                break;
            case Green:
                top = 0.45d;
                bottom = 0.2d;
                break;
            case Orange:
                top = 0.4d;
                bottom = 0.25d;
                break;
            case Yellow:
                top = 0.35d;
                bottom = 0.15d;
                break;
            default:
                return false;
        }

        return bottom < factor && factor < top;
    }

    protected static boolean factorSecondRange(Type color, double factor) {
        double top, bottom;
        switch (color) {
            case Blue:
                top = 0.65d;
                bottom = 0.4d;
                break;
            case Green:
                top = 0.45d;
                bottom = 0.1d;
                break;
            case Orange:
                top = 0.55d;
                bottom = 0.3d;
                break;
            case Yellow:
                top = 0.4d;
                bottom = 0d;
                break;
            default:
                return false;
        }

        return bottom < factor && factor < top;
    }

}
