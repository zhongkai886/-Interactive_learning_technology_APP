package com.example.user.interactive_learning_technology_app.mindwave;

public class MindValueTop {

    public static final int delta = 98000;
    public static final int lowAlpha = 50000;
    public static final int highAlpha = 50000;
    public static final int lowBeta = 50000;
    public static final int highBeta = 50000;
    public static final int lowGamma = 10000;
    public static final int midGamma = 10000;
    public static final int theta = 98000;

    public static int delta(int v) {
        return (v > delta) ? delta : v;
    }

    public static int lowAlpha(int v) {
        return (v > lowAlpha) ? lowAlpha : v;
    }

    public static int highAlpha(int v) {
        return (v > highAlpha) ? highAlpha : v;
    }

    public static int lowBeta(int v) {
        return (v > lowBeta) ? lowBeta : v;
    }

    public static int highBeta(int v) {
        return (v > highBeta) ? highBeta : v;
    }

    public static int lowGamma(int v) {
        return (v > lowGamma) ? lowGamma : v;
    }

    public static int midGamma(int v) {
        return (v > midGamma) ? midGamma : v;
    }

    public static int theta(int v) {
        return (v > theta) ? theta : v;
    }
}
