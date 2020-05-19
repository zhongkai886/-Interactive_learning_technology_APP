package com.alchemy.mindlibrary;

import java.util.HashSet;
import java.util.Set;

public class MindColorYijing {

    public static MindColorYijingType Calc(MindColorType c1, MindColorType c2, int t){
        Set<MindColorType> s = new HashSet<>();
        s.add(c1);s.add(c2);

        if(s.contains(MindColorType.Yellow) && s.contains(MindColorType.Green))
            return MindColorYijingType.QIAN;

        if(c1 == (MindColorType.Yellow) && c2 == (MindColorType.Yellow))
            return MindColorYijingType.DUI;
        if(s.contains(MindColorType.Yellow) && s.contains(MindColorType.Orange))
            return MindColorYijingType.DUI;

        if(s.contains(MindColorType.Yellow) && s.contains(MindColorType.Blue))
            return MindColorYijingType.LI;

        if(s.contains(MindColorType.Green) && s.contains(MindColorType.Blue) && t > THRESHOLD_THETA)
            return MindColorYijingType.ZHEN;

        if(c1 == (MindColorType.Orange) && c2 == (MindColorType.Orange))
            return MindColorYijingType.XUN;
        if(s.contains(MindColorType.Orange) && s.contains(MindColorType.Yellow))
            return MindColorYijingType.XUN;

        if(s.contains(MindColorType.Orange) && s.contains(MindColorType.Green))
            return MindColorYijingType.KAN;

        if(s.contains(MindColorType.Green) && s.contains(MindColorType.Blue) && t <= THRESHOLD_THETA)
            return MindColorYijingType.GEN;

        if(s.contains(MindColorType.Orange) && s.contains(MindColorType.Blue))
            return MindColorYijingType.KUN;

        return null;
    }

    static int THRESHOLD_THETA = 75;

    public static void SetUp(int t){
        THRESHOLD_THETA = t;
    }
}
