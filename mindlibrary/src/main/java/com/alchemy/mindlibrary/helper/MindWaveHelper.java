package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MindKey;
import com.alchemy.mindlibrary.MindLimit;

import wjk.java.array.ArrayMap;

public class MindWaveHelper {

    public final ArrayMap Array;
    public double Attention;
    public double Meditation;
    public double HighAlpha;
    public double LowAlpha;
    public double HighBeta;
    public double LowBeta;
    public double LowGamma;
    public double MidGamma;
    public double Theta;
    public double Delta;


    public MindWaveHelper() {
        Array = new ArrayMap();
    }

    public MindWaveHelper(ArrayMap d) {
        Array = d;
    }

    int array(MindKey m, int i){
        return MindLimit.Upper(m, Array.getInt(m, i));
    }

    public void analysisAverage() {
        clear();
        int s = Array.size();
        
        for(int i = 0; i<s; i++){
            Attention = array(MindKey.Attention, i);
            Meditation += array(MindKey.Meditation, i);
            HighAlpha += array(MindKey.highAlpha, i);
            LowAlpha += array(MindKey.lowAlpha, i);
            HighBeta += array(MindKey.highBeta, i);
            LowBeta += array(MindKey.lowBeta, i);
            LowGamma += array(MindKey.midGamma, i);
            MidGamma += array(MindKey.lowGamma, i);
            Theta += array(MindKey.theta, i);
            Delta += array(MindKey.delta, i);
        }
        Attention /= s;
        Meditation /= s;
        HighAlpha /= s;
        LowAlpha /= s;
        HighBeta /= s;
        LowBeta /= s;
        LowGamma /= s;
        MidGamma /= s;
        Theta /= s;
        Delta /= s;
        
    }

    public void analysisProportion() {
        clear();
        int s = Array.size();

        for(int i = 0; i<s; i++){
            Attention += array(MindKey.Attention, i);
            Meditation += array(MindKey.Meditation, i);
            double ha = array(MindKey.highAlpha, i);
            double la = array(MindKey.lowAlpha, i);
            double hb = array(MindKey.highBeta, i);
            double lb = array(MindKey.lowBeta, i);
            double mg = array(MindKey.midGamma, i);
            double lg = array(MindKey.lowGamma, i);
            double t = array(MindKey.theta, i);
            double d = array(MindKey.delta, i);

            double sum = ha + la + hb + lb + mg + lg + t + d;
            HighAlpha += ProportionRange(ha/sum, 0.1d, 0.2d);
            LowAlpha += ProportionRange(la/sum, 0.1d, 0.2d);
            HighBeta += ProportionRange(hb/sum, 0.05d, 0.1d);
            LowBeta += ProportionRange(lb/sum, 0.05d, 0.1d);
            LowGamma += ProportionRange(lg/sum, 0.03d, 0.06d);
            MidGamma += ProportionRange(mg/sum, 0.03d, 0.06d);
            Theta += ProportionRange(t/sum, 0.15d, 0.3d);
            Delta += ProportionRange(d/sum, 0.6d, 0.8d);
        }
        Attention /= s;
        Meditation /= s;
        HighAlpha /= s;
        LowAlpha /= s;
        HighBeta /= s;
        LowBeta /= s;
        LowGamma /= s;
        MidGamma /= s;
        Theta /= s;
        Delta /= s;

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
    
    public void clear(){
        Attention = 0;
        Meditation = 0;
        HighAlpha = 0;
        LowAlpha = 0;
        HighBeta = 0;
        LowBeta = 0;
        LowGamma = 0;
        MidGamma = 0;
        Theta = 0;
        Delta = 0;
    }
}
