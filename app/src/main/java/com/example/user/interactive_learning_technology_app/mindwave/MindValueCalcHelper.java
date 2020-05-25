package com.example.user.interactive_learning_technology_app.mindwave;

public class MindValueCalcHelper {

    public static enum Algorithm {
        Average, Proportion, Median
    }

    public double attention;
    public double meditation;
    public double delta;
    public double highAlpha;
    public double lowAlpha;
    public double highBeta;
    public double lowBeta;
    public double lowGamma;
    public double midGamma;
    public double theta;
    private Algorithm type;
    private int[] columnSumArray;

    public MindValueCalcHelper(Algorithm a) {
        type = a;
    }

    public void calcColumnSumArray(int[] delta, int[] highAlpha, int[] lowAlpha, int[] highBeta,
                                   int[] lowBeta, int[] lowGamma, int[] midGamma, int[] theta) {
        int[] sum = new int[delta.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] =
                    MindValueTop.delta(delta[i]) +
                            MindValueTop.theta(theta[i]) +
                            MindValueTop.highAlpha(highAlpha[i]) +
                            MindValueTop.highBeta(highBeta[i]) +
                            MindValueTop.lowAlpha(lowAlpha[i]) +
                            MindValueTop.lowBeta(lowBeta[i]) +
                            MindValueTop.lowGamma(lowGamma[i]) +
                            MindValueTop.midGamma(midGamma[i]);

            if (sum[i] == 0) sum[i] = 1;
        }
        columnSumArray = sum;
    }

    public void calcDelta(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.delta(arr[i]);
        }
        delta = calcValue(arr);
        if (type == Algorithm.Proportion)
            delta = MindValueAlgorithm.ProportionRange(delta, 0.6d, 0.8d);
    }

    public void calcHighAlpha(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.highAlpha(arr[i]);
        }
        highAlpha = calcValue(arr);
        if (type == Algorithm.Proportion)
            highAlpha = MindValueAlgorithm.ProportionRange(highAlpha, 0.1d, 0.2d);
    }

    public void calcLowAlpha(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.lowAlpha(arr[i]);
        }
        lowAlpha = calcValue(arr);
        if (type == Algorithm.Proportion)
            lowAlpha = MindValueAlgorithm.ProportionRange(lowAlpha, 0.1d, 0.2d);
    }

    public void calcHighBeta(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.highBeta(arr[i]);
        }
        highBeta = calcValue(arr);
        if (type == Algorithm.Proportion)
            highBeta = MindValueAlgorithm.ProportionRange(highBeta, 0.05d, 0.1d);
    }

    public void calcLowBeta(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.lowBeta(arr[i]);
        }
        lowBeta = calcValue(arr);
        if (type == Algorithm.Proportion)
            lowBeta = MindValueAlgorithm.ProportionRange(lowBeta, 0.05d, 0.1d);
    }

    public void calcLowGamma(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.lowGamma(arr[i]);
        }
        lowGamma = calcValue(arr);
        if (type == Algorithm.Proportion)
            lowGamma = MindValueAlgorithm.ProportionRange(lowGamma, 0.03d, 0.06d);
    }

    public void calcMidGamma(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.midGamma(arr[i]);
        }
        midGamma = calcValue(arr);
        if (type == Algorithm.Proportion)
            midGamma = MindValueAlgorithm.ProportionRange(midGamma, 0.03d, 0.06d);
    }

    public void calcTheta(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MindValueTop.theta(arr[i]);
        }

        theta = calcValue(arr);
        if (type == Algorithm.Proportion)
            theta = MindValueAlgorithm.ProportionRange(theta, 0.15d, 0.3d);
    }

    private double calcValue(int[] arr) {
        switch (type) {
            case Average:
                return MindValueAlgorithm.Average(arr);
            case Median:
                return MindValueAlgorithm.Median(arr);
            case Proportion:
                return MindValueAlgorithm.Proportion(arr, columnSumArray);
        }
        return 0;
    }

}
