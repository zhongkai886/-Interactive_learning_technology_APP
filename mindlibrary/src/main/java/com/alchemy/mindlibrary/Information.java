package com.alchemy.mindlibrary;

public class Information {

    public static class MindColorAlgorithmInfo extends MindColorAlgorithm {

        public static String msg(){
            String text = "";
            text += String.format("腦色參數區間 = F1[B,T]; F2[B,T]\n");
            text += String.format("%6s = F1[%.2f, %.2f] F2[%.2f, %.2f]\n", "Orange", ORANGE_BTN_F1, ORANGE_TOP_F1, ORANGE_BTN_F2, ORANGE_TOP_F2);
            text += String.format("%6s = F1[%.2f, %.2f] F2[%.2f, %.2f]\n", "Green", GREEN_BTN_F1, GREEN_TOP_F1, GREEN_BTN_F2, GREEN_TOP_F2);
            text += String.format("%6s = F1[%.2f, %.2f] F2[%.2f, %.2f]\n", "Blue", BLUE_BTN_F1, BLUE_TOP_F1, BLUE_BTN_F2, BLUE_TOP_F2);
            text += String.format("%6s = F1[%.2f, %.2f] F2[%.2f, %.2f]\n", "Yellow", YELLOW_BTN_F1, YELLOW_TOP_F1, YELLOW_BTN_F2, YELLOW_TOP_F2);
            text += String.format("%6s = F1[%.2f, %.2f] F2[%.2f, %.2f]", "Orange2", ORANGE2_BTN_F1, ORANGE2_TOP_F1, ORANGE2_BTN_F2, ORANGE2_TOP_F2);
            return text;
        }
    }


    public static class MindColorYijingInfo extends MindColorYijing {

        public static String msg(){
            String text = "";
            text += String.format("├Orange\n");
            text += String.format("│└%s\n", MindColorYijingType.XUN.Chinese);
            text += String.format("├Green\n");
            text += String.format("│├Orange\n");
            text += String.format("││└%s\n", MindColorYijingType.KAN.Chinese);
            text += String.format("│├Blue\n");
            text += String.format("││├t>%d %s\n", MindColorYijing.THRESHOLD_THETA, MindColorYijingType.ZHEN.Chinese);
            text += String.format("││└t<%d %s\n", MindColorYijing.THRESHOLD_THETA, MindColorYijingType.GEN.Chinese);
            text += String.format("│└Yellow\n");
            text += String.format("│ └%s\n", MindColorYijingType.QIAN.Chinese);
            text += String.format("├Blue\n");
            text += String.format("│├Orange\n");
            text += String.format("││└%s\n", MindColorYijingType.KUN.Chinese);
            text += String.format("│├Green\n");
            text += String.format("││├t>%d %s\n", MindColorYijing.THRESHOLD_THETA, MindColorYijingType.ZHEN.Chinese);
            text += String.format("││└t<%d %s\n", MindColorYijing.THRESHOLD_THETA, MindColorYijingType.GEN.Chinese);
            text += String.format("│└Yellow\n");
            text += String.format("│ └%s\n", MindColorYijingType.LI.Chinese);
            text += String.format("└Yellow\n");
            text += String.format(" └%s\n", MindColorYijingType.DUI.Chinese);
            return text;
        }
    }

}
