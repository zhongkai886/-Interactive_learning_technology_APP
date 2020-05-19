package com.alchemy.mindlibrary;

public enum MindColorYijingType {
    QIAN("乾"), //乾
    KUN("坤"), //坤
    KAN("坎"), //坎
    LI("離"),  //離
    ZHEN("震"), //震
    GEN("艮"), //艮
    XUN("巽"), //巽
    DUI("兌");  //兌

    public final String Chinese;
    MindColorYijingType(String s){
        Chinese = s;
    }
}
