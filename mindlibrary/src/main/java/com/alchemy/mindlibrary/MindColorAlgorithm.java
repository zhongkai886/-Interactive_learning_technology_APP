package com.alchemy.mindlibrary;

import java.util.ArrayList;

public class MindColorAlgorithm {

    public static ArrayList<MindColorType> Calc(double highAlpha, double lowAlpha, double highBeta,
                                                double lowBeta, double lowGamma, double midGamma){
        return Calc(highAlpha + lowAlpha, highBeta + lowBeta, lowGamma + midGamma);
    }

    public static ArrayList<MindColorType> Calc(double alpha, double beta, double gamma){
        double f1 = FactorOne(gamma, alpha);
        double f2 = FactorTwo(gamma, beta);
        ArrayList<MindColorType> array = RangeColor(f1, f2);

        return array;
    }

    public static ArrayList<MindColorType> RangeColor(double f1, double f2) {
        ArrayList<MindColorType> meets = new ArrayList<>();
        for (MindColorType t : MindColorType.values()) {
            if (FactorRange(t, f1, f2)) meets.add(t);
        }
        return meets;
    }

    public static double FactorOne(double gamma, double alpha){
        return gamma / alpha;
    }

    public static double FactorTwo(double gamma, double beta){
        return gamma / beta;
    }

    public static boolean FactorRange(MindColorType c, double f1, double f2) {
        switch (c) {
            case Blue:
                return (BLUE_BTN_F1 < f1 && f1 < BLUE_TOP_F1) && (BLUE_BTN_F2 < f2 && f2 < BLUE_TOP_F2);
            case Green:
                return (GREEN_BTN_F1 < f1 && f1 < GREEN_TOP_F1) && (GREEN_BTN_F2 < f2 && f2 < GREEN_TOP_F2);
            case Orange:
                return (ORANGE_BTN_F1 < f1 && f1 < ORANGE_TOP_F1) && (ORANGE_BTN_F2 < f2 && f2 < ORANGE_TOP_F2) ||
                        (ORANGE2_BTN_F1 < f1 && f1 < ORANGE2_TOP_F1) && (ORANGE2_BTN_F2 < f2 && f2 < ORANGE2_TOP_F2);
            case Yellow:
                return (YELLOW_BTN_F1 < f1 && f1 < YELLOW_TOP_F1) && (YELLOW_BTN_F2 < f2 && f2 < YELLOW_TOP_F2);
        }
        return false;
    }

    public static boolean FactorRangeOne(MindColorType c, double f) {
        switch (c) {
            case Blue:
                return BLUE_BTN_F1 < f && f < BLUE_TOP_F1;
            case Green:
                return GREEN_BTN_F1 < f && f < GREEN_TOP_F1;
            case Orange:
                return (ORANGE_BTN_F1 < f && f < ORANGE_TOP_F1) || (ORANGE2_BTN_F1 < f && f < ORANGE2_TOP_F1);
            case Yellow:
                return YELLOW_BTN_F1 < f && f < YELLOW_TOP_F1;
        }
        return false;
    }

    public static boolean FactorRangeTwo(MindColorType c, double f) {
        switch (c) {
            case Blue:
                return BLUE_BTN_F2 < f && f < BLUE_TOP_F2;
            case Green:
                return GREEN_BTN_F2 < f && f < GREEN_TOP_F2;
            case Orange:
                return (ORANGE_BTN_F2 < f && f < ORANGE_TOP_F2) || (ORANGE2_BTN_F2 < f && f < ORANGE2_TOP_F2);
            case Yellow:
                return YELLOW_BTN_F2 < f && f < YELLOW_TOP_F2;
        }
        return false;
    }

    //TODO static
    public static float BLUE_TOP_F1 = 0.55f;
    public static float BLUE_BTN_F1 = 0.3f;

    public static float BLUE_TOP_F2 = 0.65f;
    public static float BLUE_BTN_F2 = 0.4f;

    public static float GREEN_TOP_F1 = 0.45f;
    public static float GREEN_BTN_F1 = 0.2f;

    public static float GREEN_TOP_F2 = 0.45f;
    public static float GREEN_BTN_F2 = 0.1f;

    public static float ORANGE_TOP_F1 = 0.4f;
    public static float ORANGE_BTN_F1 = 0.25f;

    public static float ORANGE_TOP_F2 = 0.55f;
    public static float ORANGE_BTN_F2 = 0.3f;

    public static float YELLOW_TOP_F1 = 0.35f;
    public static float YELLOW_BTN_F1 = 0.15f;

    public static float YELLOW_TOP_F2 = 0.4f;
    public static float YELLOW_BTN_F2 = 0.0f;

    public static float ORANGE2_TOP_F1 = 0.3f;
    public static float ORANGE2_BTN_F1 = 0.0f;

    public static float ORANGE2_TOP_F2 = 0.65f;
    public static float ORANGE2_BTN_F2 = 0.45f;
}
