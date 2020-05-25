package com.example.user.interactive_learning_technology_app.mindwave;

public class MindBalanceAlgorithm {

    public static int calc(int[] attention, int[] meditation) {
        int[] zoneAttention = calcZone(attention, ZONE_ATTENTION);
        int[] zoneMeditation = calcZone(meditation, ZONE_MEDITATION);
        return calcFactor1(zoneAttention, zoneMeditation) +
                calcFactor2(zoneAttention, zoneMeditation) +
                calcFactor3(zoneAttention, zoneMeditation);
    }

    private static int[] calcZone(int[] value, int[] threshold) {
        int[] zone = new int[threshold.length - 1];

        for (int i : value) {
            for (int j = 1; j < threshold.length; j++) {
                if (i >= threshold[j - 1] && i <= threshold[j]) {
                    zone[j - 1]++;
                    break;
                }
            }
        }
        return zone;
    }

    private static int calcFactor1(int[] value1, int[] value2) {
        int f = Math.abs((value1[1] + value1[2]) - (value2[1] + value2[2]));
        if (f > 20) return 10;
        else if (f > 15) return 20;
        else return 30;
    }

    private static int calcFactor2(int[] value1, int[] value2) {
        int f = Math.abs((value1[0] - value2[0])) + Math.abs((value1[1] - value2[1])) + Math.abs((value1[2] - value2[2]));
        if (f > 60) return 10;
        else if (f > 40) return 30;
        else return 50;
    }

    private static int calcFactor3(int[] value1, int[] value2) {
        int f = value1[2] + value2[2];
        if (f > 70) return 20;
        else if (f > 50) return 10;
        else return 5;
    }

    private static final int[] ZONE_ATTENTION = new int[]{0, 40, 70, 100};
    private static final int[] ZONE_MEDITATION = new int[]{0, 40, 70, 100};

    private static final int PROPORTION_FACTOR_1 = 30;
    private static final int PROPORTION_FACTOR_2 = 50;
    private static final int PROPORTION_FACTOR_3 = 20;

}
