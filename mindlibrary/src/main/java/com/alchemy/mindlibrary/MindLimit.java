package com.alchemy.mindlibrary;

public class MindLimit {
    
    final public static int MAX_SIGNAL = 200;
    final public static int MAX_ATTENTION = 100;
    final public static int MAX_MEDITATION = 100;
    final public static int MAX_DELTA = 98000;
    final public static int MAX_LOWALPHA = 50000;
    final public static int MAX_HIGHALPHA = 50000;
    final public static int MAX_LOWBETA = 50000;
    final public static int MAX_HIGHBETA = 50000;
    final public static int MAX_LOWGAMMA = 10000;
    final public static int MAX_MIDGAMMA = 10000;
    final public static int MAX_THETA = 98000;

    public static int Upper(MindKey t, int v){
        if(!ENABEL)return v;
        switch(t){
            case Signal:
                return (v < MAX_SIGNAL)? v : MAX_SIGNAL;
            case Attention:
                return (v < MAX_ATTENTION)? v : MAX_ATTENTION;
            case Meditation:
                return (v < MAX_MEDITATION)? v : MAX_MEDITATION;
            case delta:
                return (v < MAX_DELTA)? v : MAX_DELTA;
            case highAlpha:
                return (v < MAX_HIGHALPHA)? v : MAX_HIGHALPHA;
            case lowAlpha:
                return (v < MAX_LOWALPHA)? v : MAX_LOWALPHA;
            case highBeta:
                return (v < MAX_HIGHBETA)? v : MAX_HIGHBETA;
            case lowBeta:
                return (v < MAX_LOWBETA)? v : MAX_LOWBETA;
            case lowGamma:
                return (v < MAX_LOWGAMMA)? v : MAX_LOWGAMMA;
            case midGamma:
                return (v < MAX_MIDGAMMA)? v : MAX_MIDGAMMA;
            case theta:
                return (v < MAX_THETA)? v : MAX_THETA;
        }
        return 0;
    }


    static boolean ENABEL = false;
    public static void SetUp(boolean b){
        ENABEL = b;
    }
}
