package com.alchemy.mindlibrary.helper;

import com.alchemy.mindlibrary.MBTypeIndicator;
import com.alchemy.mindlibrary.MindColorMBTI;
import com.alchemy.mindlibrary.MindColorType;

public class MindHelperLog {

    public static String MindColorRecordSetSecLog(MindColorRecordSet m){
        String t = "";

        for(MindColorRecord r: m.recordList){
            t += "==========\n";
            for(MindColorType c: r.colors)t += String.format("%s < ", c.name());

            t += String.format("\nha:[%2d], la:[%2d], hb:[%2d], lb:[%2d]\n", r.highAlpha, r.lowAlpha, r.highBeta, r.lowBeta);
            t += String.format("mg:[%2d], lg:[%2d]\n", r.midGamma, r.lowGamma);
            t += String.format("A:[%2d], B:[%2d], G:[%2d]\n", r.alpha, r.beta, r.gamma);
            t += String.format("F1:[%.2f], F2:[%.2f]\n", r.f1, r.f2);
        }

        return t.trim();
    }


    public static String MindColorRecordSetLog(MindColorRecordSet m){
        String t = "";

        t += String.format("比數:[%2d]", m.recordSize());
        for(int i = 0; i<4; i++){
            MindColorType c = m.rank(i);
            int s = m.score(c);
            t += String.format(" %s:[%2d]", c.name().charAt(0), s);
        }

        return t;
    }

    public static String MindColorRecordSetLog(MindColorRecordMultiSet m){
        String t = "";
        for(int i = 0; i< m.recordSetList.length; i++){
            MindColorRecordSet r= m.recordSetList[i];
            t += MindColorRecordSetLog(r) + "\n";
        }

        return t.trim();
    }


    public static String MindColorRecordMultiSetLog(MindColorRecordMultiSet m){
        String t = "";
        t = String.format("檢測次數[%d]", m.recordSetSize());
        for(int i = 0; i<4; i++){
            MindColorType c = m.rank(i);
            int s = m.score(c);
            int sc = m.scoreSum(c);
            t += String.format(" %s:[%2d; %2d]", c.name().charAt(0), s, sc);
        }

        return t;
    }

    public static String MindPieHelperMultiLog(MindPieHelperMulti m){
        String t = "";
        MindPieHelper base = m.bestScore();
        t += String.format("Att=%d[%d, %d, %d], Med=%d[%d, %d, %d]\n", base.AttScore, base.AttPart[0], base.AttPart[1], base.AttPart[2], base.MedScore, base.MedPart[0], base.MedPart[1], base.MedPart[2]);
        int i = 0;
        for(MindPieHelper h: m.Helpers){
            t += "\n=========\n";
            t += String.format("%d{Att=%d[%d, %d, %d], Med=%d[%d, %d, %d]}\n", i++, base.AttScore, base.AttPart[0], base.AttPart[1], base.AttPart[2], base.MedScore, base.MedPart[0], base.MedPart[1], base.MedPart[2]);
        }
        return t;
    }

    public static String MindWaveHelperMultiLog(MindWaveHelperMulti m){
        String t = "";
        t += String.format("bestTheta=%.2f\n", m.bestTheta().Theta);

        int i = 0;
        for(MindWaveHelper h: m.Helpers){
            t += "\n=========\n";
            t += "" + i++;
            t += String.format(" att[%.2f] med[%.2f] ha[%.2f] la[%.2f] hb[%.2f] lb[%.2f] lg[%.2f] mg[%.2f] t[%.2f] d[%.2f]",
                    h.Attention, h.Meditation, h.HighAlpha, h.LowAlpha, h.HighBeta, h.LowBeta, h.LowGamma, h.MidGamma, h.Theta, h.Delta);
        }
        return t;
    }


    public static String MindMBTIs(MindWaveHelperMulti m, MindColorRecordMultiSet rm){
        String log = "";

        for(int i = 0; i<rm.recordSetList.length; i++){
            MindColorRecordSet s =  rm.recordSetList[i];
            MindColorType r1 = s.rank(0);
            MindColorType r2 = s.score(1) != 0 ? s.rank(1) : r1;
            int theta = (int) (m.Helpers[i].Theta * 100);
            int bata = (int) ((m.Helpers[i].HighBeta + m.Helpers[i].LowBeta) * 100 / 2);
            int mbti = MindColorMBTI.Calc(r1, r2, theta, bata);

            log += "\n第一:" + r1.name().charAt(0);
            log += "; 第二:" + r2.name().charAt(0);
            log += "; Theta:" + theta;
            log += "; Bata:" + bata;
            log += "; mbtis:" + MBTypeIndicator.toStringMBTI(mbti);
        }

        return log;
    }



}
