package com.example.user.interactive_learning_technology_app.mindwave;

import java.util.Arrays;

public class MindValueAlgorithm {

    public static double Average(int[] numArray) {
        if (numArray.length == 0) return 0;
        double ans = 0;

        for (int i = 0; i < numArray.length; i++) {
            ans += numArray[i];
        }
        ans /= numArray.length;
        return ans;
    }

    public static double Proportion(int[] numArray, int[] columnSumArray) {
        double ans = 0;

        for (int i = 0; i < numArray.length; i++) {
            if (columnSumArray[i] == 0) ans += (double) numArray[i] / 1;
            else ans += (double) numArray[i] / (double) columnSumArray[i];
        }
        return ans / numArray.length;
    }

    public static double Median(int[] numArray) {
        Arrays.sort(numArray);

        if (numArray.length % 2 == 0)
            return ((double) numArray[numArray.length / 2] + (double) numArray[numArray.length / 2 - 1]) / 2;
        else
            return (double) numArray[numArray.length / 2];

    }

    public static double ProportionRange(double value, double level1, double level2) {
        if (level1 > level2 || level1 < 0 || value <= 0) return 0;
        if (value >= level2) return 1;

        double ans = 0;
        double percent;
        if (value <= level1) {
            percent = value / level1;
            ans = percent * 0.5d;
        } else {
            percent = (value - level1) / (level2 - level1);
            ans = percent * 0.5d + 0.5d;
        }
        return ans;
    }
}
