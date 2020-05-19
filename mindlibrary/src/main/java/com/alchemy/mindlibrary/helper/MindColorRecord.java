package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MindColorType;
import com.alchemy.mindlibrary.MindKey;

import java.util.ArrayList;

import static com.alchemy.mindlibrary.MindColorAlgorithm.FactorOne;
import static com.alchemy.mindlibrary.MindColorAlgorithm.FactorTwo;
import static com.alchemy.mindlibrary.MindColorAlgorithm.RangeColor;
import static com.alchemy.mindlibrary.MindLimit.Upper;

public class MindColorRecord {

    public final int highAlpha;
    public final int lowAlpha;
    public final int highBeta;
    public final int lowBeta;
    public final int lowGamma;
    public final int midGamma;

    public final int alpha;
    public final int beta;
    public final int gamma;

    public final double f1;
    public final double f2;

    public final ArrayList<MindColorType> colors;

    public MindColorRecord(int ha, int la, int hb,
                           int lb, int lg, int mg){
        this.highAlpha = Upper(MindKey.highAlpha, ha);
        this.lowAlpha = Upper(MindKey.lowAlpha, la);
        this.highBeta = Upper(MindKey.highBeta, hb);
        this.lowBeta = Upper(MindKey.lowBeta, lb);
        this.lowGamma = Upper(MindKey.lowGamma, lg);
        this.midGamma = Upper(MindKey.midGamma, mg);

        alpha = ha + la;
        beta = hb + lb;
        gamma = lg + mg;

        f1 = FactorOne(gamma, alpha);
        f2 = FactorTwo(gamma, beta);

        colors = RangeColor(f1, f2);
    }

}
