package com.example.user.interactive_learning_technology_app.widget;


import com.example.user.interactive_learning_technology_app.mindwave.MindArray;
import com.example.user.interactive_learning_technology_app.mindwave.MindColorAlgorithm;
import com.example.user.interactive_learning_technology_app.mindwave.MindColorAlgorithmForCenter;
import com.example.user.interactive_learning_technology_app.mindwave.MindColorAlgorithmForPoint;
import com.example.user.interactive_learning_technology_app.mindwave.MindValueAlgorithm;
import com.example.user.interactive_learning_technology_app.mindwave.MindValueCalcHelper;


import java.util.ArrayList;

public class MindThan {
    private int[] mindColor_count;
    public ArrayList<Integer> mindColor_array;
    public ArrayList<String> logs;

    public int[] attPie;
    public int[] medPie;

    public MindThan() {
        mindColor_array = new ArrayList<Integer>();
        logs = new ArrayList<>();
}

    public static int calcBand(MindArray[] mas) {
        int index = 0;
        double score_best = 0;

        for (int i = 0; i < mas.length; i++) {
            MindValueCalcHelper calcHelper = new MindValueCalcHelper(MindValueCalcHelper.Algorithm.Proportion);

            int[] delta = mas[i].getArray(MindArray.key.delta);
            int[] highAlpha = mas[i].getArray(MindArray.key.highAlpha);
            int[] lowAlpha = mas[i].getArray(MindArray.key.lowAlpha);
            int[] highBeta = mas[i].getArray(MindArray.key.highBeta);
            int[] lowBeta = mas[i].getArray(MindArray.key.lowBeta);
            int[] lowGamma = mas[i].getArray(MindArray.key.lowGamma);
            int[] midGamma = mas[i].getArray(MindArray.key.midGamma);
            int[] theta = mas[i].getArray(MindArray.key.theta);

            calcHelper.calcColumnSumArray(delta, highAlpha, lowAlpha, highBeta, lowBeta, lowGamma, midGamma, theta);

            calcHelper.calcLowGamma(lowGamma);

            if (calcHelper.lowGamma > score_best) {
                score_best = calcHelper.lowGamma;
                index = i;
            }
        }


        return index;
    }

    public void calcPie(MindArray[] mas){
        int score = 0;
        for(int i = 0; i < mas.length; i++) {
            MindArray ma = mas[i];

            int[] atts = ma.getArray(MindArray.key.Attention);
            int[] meds = ma.getArray(MindArray.key.Meditation);
            atts = _calcMindPie(atts);
            meds = _calcMindPie(meds);
            int s = atts[1]+atts[2]+meds[1]+meds[2];
            if(s>score){
                attPie = atts;
                medPie = meds;
            }
        }

    }


    public int calcColor(MindArray[] mas, int order, int than, int countY, int countB, int countG) {
        int mindColor = 0;
        mindColor_count = new int[MindColorAlgorithm.Type.values().length];
        int[] count = new int[]{0, countG, countB, countY};

        for (int i = 0; i < mas.length; i++) {
            MindArray mMindArray = mas[i];

            int[] highAlpha = mMindArray.getArray(MindArray.key.highAlpha);
            int[] lowAlpha = mMindArray.getArray(MindArray.key.lowAlpha);
            int[] highBeta = mMindArray.getArray(MindArray.key.highBeta);
            int[] lowBeta = mMindArray.getArray(MindArray.key.lowBeta);
            int[] lowGamma = mMindArray.getArray(MindArray.key.lowGamma);
            int[] midGamma = mMindArray.getArray(MindArray.key.midGamma);

            double ha = MindValueAlgorithm.Average(highAlpha);
            double la = MindValueAlgorithm.Average(lowAlpha);
            double hb = MindValueAlgorithm.Average(highBeta);
            double lb = MindValueAlgorithm.Average(lowBeta);
            double lg = MindValueAlgorithm.Average(lowGamma);
            double mg = MindValueAlgorithm.Average(midGamma);

            int color_index = MindColorAlgorithmForCenter.calc(ha, la, hb, lb, lg, mg).ordinal();

            mindColor_count[color_index]++;
            mindColor_array.add(color_index);

            logs.add(String.format("f1=%.2f; f2=%.2f", (lg+mg)/(ha+la), (lg+mg)/(hb+lb)));
        }
        //顏色數量閥值
        for (int i = 0; i < mindColor_count.length; i++) {
            int t = count[i];
            if (mindColor_count[i] < t) {
                mindColor_count[i] = 0;
            }
        }

        //如果人有顏色則淘汰橘色，否則橘色淘汰
        if(mindColor_count[MindColorAlgorithm.Type.Blue.ordinal()] == 0 &&
                mindColor_count[MindColorAlgorithm.Type.Yellow.ordinal()] == 0 &&
                mindColor_count[MindColorAlgorithm.Type.Green.ordinal()] == 0)return MindColorAlgorithm.Type.Orange.ordinal();
        mindColor_count[MindColorAlgorithm.Type.Orange.ordinal()] = 0;

        //判斷剩餘顏色
        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < mindColor_count.length; i++) {
            if (mindColor_count[i] != 0) {
                index.add(i);
            }
        }
        if (index.size() == 1) {
            mindColor = index.get(0);
        }

        //顏色相互比較
        if (index.size() == 2) {
            if (_than_to_bool(than, index.get(0), index.get(1))) {
                mindColor = index.get(0);
            } else {
                mindColor = index.get(1);
            }
        }

        return mindColor;
    }

    private void _calcMindColor(MindArray ma){

        MindColorAlgorithmForPoint map = new MindColorAlgorithmForPoint();
        for (int i = 0; i < ma.size(); i++) {

            double ha = ma.get(MindArray.key.highAlpha);
            double la = ma.get(MindArray.key.lowAlpha);
            double hb = ma.get(MindArray.key.highBeta);
            double lb = ma.get(MindArray.key.lowBeta);
            double lg = ma.get(MindArray.key.lowGamma);
            double mg = ma.get(MindArray.key.midGamma);

            map.calc(ha, la, hb, lb, lg, mg);
        }


    }

    private static boolean _than_to_bool(int than, int color1, int color2) {
        color2 = 1 << color2;

        int i = (than >> 4 * color1) & 0xF;
        return (i & color2) > 0;
    }



    private int[] _calcMindPie(int[] arr) {
        int[] s = new int[3];
        for (int i : arr) {
            if (i > 70) s[2]++;
            else if (i > 40) s[1]++;
            else s[0]++;
        }
        int sum = arr.length;
        s[0] = (int) (s[0] * 100f / sum + 0.5f);
        s[1] = (int) (s[1] * 100f / sum + 0.5f);
        s[2] = (int) (s[2] * 100f / sum + 0.5f);
        return s;
    }

}
