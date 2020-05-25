package com.example.user.interactive_learning_technology_app.widget;

import com.example.user.interactive_learning_technology_app.mindwave.MindColorAlgorithm.Type;

public class MindColorLeader {

    static public enum LeaderColor {
        LY,//淺黃
        DY,//深黃
        LB,//淺藍
        DB,//深藍
        LG,//淺綠
        DG,//深綠
        LO,//淺橘
        DO//深橘
    }

    static public enum LeaderType {
        ZE,//澤型人
        SHUI,//水型人
        TIAN,//天型人
        FONG,//風型人
        LEI,//雷型人
        DE,//地型人
        HUO,//火型人
        SHAN//山型人
    }

    static public LeaderColor calcColor(int color, int lowGamma) {

        if (color == Type.Orange.ordinal()) {
            if (lowGamma > 60) return LeaderColor.LO;
            else return LeaderColor.DO;
        } else if (color == Type.Green.ordinal()) {
            if (lowGamma > 50) return LeaderColor.LG;
            else return LeaderColor.DG;
        } else if (color == Type.Blue.ordinal()) {
            if (lowGamma < 50) return LeaderColor.LB;
            else return LeaderColor.DB;
        } else if (color == Type.Yellow.ordinal()) {
            if (lowGamma > 60) return LeaderColor.LY;
            else return LeaderColor.DY;
        }
        return null;
    }

    static public LeaderType calcType(int color, int attention, int meditation, int lowGamma, int midGamma) {

        if (color == Type.Orange.ordinal()) {
            return calcOrange(lowGamma, attention, meditation);
        } else if (color == Type.Green.ordinal()) {
            return calcGreen(lowGamma);
        } else if (color == Type.Blue.ordinal()) {
            return calcBlue(lowGamma);
        } else if (color == Type.Yellow.ordinal()) {
            return calcYellow(lowGamma, midGamma);
        }
        return null;
    }

    static public LeaderType calcOrange(int lowGamma, int attention, int meditation) {
        if (lowGamma > 60) {
            if (attention + meditation > 170) return LeaderType.SHUI;
            else return LeaderType.ZE;
        } else {
            if (attention + meditation > 170) return LeaderType.FONG;
            else return LeaderType.TIAN;
        }
    }

    static public LeaderType calcGreen(int lowGamma) {
        if (lowGamma > 50) return LeaderType.HUO;
        else return LeaderType.SHAN;
    }

    static public LeaderType calcBlue(int lowGamma) {
        if (lowGamma < 50) return LeaderType.LEI;
        else return LeaderType.DE;
    }

    static public LeaderType calcYellow(int lowGamma, int midGamma) {
        if (lowGamma > 60) {
            if (midGamma > 60) return LeaderType.ZE;
            else return LeaderType.SHUI;
        } else {
            if (midGamma > 60) return LeaderType.TIAN;
            else return LeaderType.FONG;
        }
    }

}
