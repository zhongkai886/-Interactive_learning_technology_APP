package com.alchemy.mindlibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.alchemy.mindlibrary.MindColorType.Blue;
import static com.alchemy.mindlibrary.MindColorType.Green;
import static com.alchemy.mindlibrary.MindColorType.Orange;
import static com.alchemy.mindlibrary.MindColorType.Yellow;

public class MindColorDistance {

    public static void Sort(ArrayList<MindColorType> meets, final double f1, final double f2){
        double od = Dis(Orange, f1, f2);
        double gd = Dis(Green, f1, f2);
        double bd = Dis(Blue, f1, f2);
        double yd = Dis(Yellow, f1, f2);
        Sort(meets, od, gd, bd, yd);
    }


    public static void Sort(ArrayList<MindColorType> meets, final double od, final double gd, final double bd, final double yd){
        Collections.sort(meets, new Comparator<MindColorType>() {
            @Override
            public int compare(MindColorType type1, MindColorType type2) {
                double d1 = Dis(type1, od, gd, bd, yd);
                double d2 = Dis(type2, od, gd, bd, yd);
                return d2 > d1 ? -1 : 1;
            }
        });
    }

    public static double Dis(MindColorType t, double od, double gd, double bd, double yd){
        switch(t){
            case Orange:
                return od;
            case Green:
                return gd;
            case Blue:
                return bd;
            case Yellow:
                return yd;
        }
        return 100;
    }

    public static double Dis(MindColorType t, double f1, double f2){
        switch(t){
            case Orange:
                return Dis(f1, f2, ORANGE_ORIG_F1, ORANGE_ORIG_F2);
            case Green:
                return Dis(f1, f2, GREEN_ORIG_F1, GREEN_ORIG_F2);
            case Blue:
                return Dis(f1, f2, BLUE_ORIG_F1, BLUE_ORIG_F2);
            case Yellow:
                return Dis(f1, f2, YELLOW_ORIG_F1, YELLOW_ORIG_F2);
        }
        return 100;
    }

    public static double Dis(double f1, double f2, double f1o, double f2o){
        double x = Math.abs(f1 - f1o);
        double y = Math.abs(f2 - f2o);
        return x*x+y*y;
    }


    //TODO static
    public static float BLUE_ORIG_F1 = 0.55f;
    public static float BLUE_ORIG_F2 = 0.65f;

    public static float GREEN_ORIG_F1 = 0.45f;
    public static float GREEN_ORIG_F2 = 0.1f;

    public static float ORANGE_ORIG_F1 = 0.25f;
    public static float ORANGE_ORIG_F2 = 0.55f;

    public static float YELLOW_ORIG_F1 = 0.15f;
    public static float YELLOW_ORIG_F2 = 0.0f;

}
