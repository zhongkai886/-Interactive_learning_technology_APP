package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MindKey;

import wjk.java.array.ArrayMap;

public class MindPieHelper {
    ArrayMap data;
    public int AttScore;
    public int[] AttPart;
    public int MedScore;
    public int[] MedPart;

    public MindPieHelper(ArrayMap d) {
        data = d;
    }

    public void analysis() {

        AttScore = 0;
        AttPart = new int[3];
        MedScore = 0;
        MedPart = new int[3];

        for(int i = 0, s = data.size(); i < s; i++) {
            int a = data.getInt(MindKey.Attention, i);
            int m = data.getInt(MindKey.Meditation, i);

            if (a > 70) AttPart[2]++;
            else if (a > 40) AttPart[1]++;
            else AttPart[0]++;

            if (m > 70) MedPart[2]++;
            else if (m > 40) MedPart[1]++;
            else MedPart[0]++;
        }
        AttScore = AttPart[1]+AttPart[2];
        MedScore = MedPart[1]+MedPart[2];
    }
}
