package com.alchemy.mindlibrary.helper;

import wjk.java.array.ArrayMap;

public class MindPieHelperMulti {
    ArrayMap[] data;
    public MindPieHelper[] Helpers;

    public MindPieHelperMulti(ArrayMap... d) {
        data = d;
    }

    public void analysis() {
        Helpers = new MindPieHelper[data.length];

        for(int i = 0 ; i < data.length; i++) {
            Helpers[i] = new MindPieHelper(data[i]);
            Helpers[i].analysis();
        }
    }


    public MindPieHelper bestScore(){
        MindPieHelper helper = null;
        int score = 0;
        for(MindPieHelper h :Helpers){
            int s = h.AttScore + h.MedScore;
            if(s>score){
                helper = h;
            }
        }
        return helper;
    }
}
