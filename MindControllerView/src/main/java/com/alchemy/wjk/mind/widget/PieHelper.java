package com.alchemy.wjk.mind.widget;

import java.text.NumberFormat;

public class PieHelper {

    private float[] datas;
    float[] startAngle;
    float[] sweepAngle;
    float[] textAngle;
    int[] colors;
    String[] dataName;
    String dataNamelongest;
    int length;

    //TODO 建構式
    public PieHelper(int... data) {
        float[] dataf = new float[data.length];
        for (int i = 0; i < data.length; i++) dataf[i] = data[i];
        create(dataf);
    }

    public PieHelper(float... data) {
        create(data);
    }

    private void create(float... data) {
        this.datas = data;
        length = datas.length;
        calcAngles();
        //設置默認顏色，文字與最常文字
        colors = new int[length];
        dataName = new String[length];
        for (int i = 0; i < length; i++) {
            int color = (i * 4) % 24;
            colors[i] = 0xff333333 | ((0xa0 << color));
            dataName[i] = "" + i;
        }
        dataNamelongest = calcNamelongest(dataName);
    }

    //TODO 內部項
    void calcAngles() {
        float sum = 0;
        startAngle = new float[length];
        sweepAngle = new float[length];
        textAngle = new float[length];
        for (float d : datas) sum += d;
        float start = 0;
        for (int i = 0, s = length; i < s; i++) {
            startAngle[i] = start;
            sweepAngle[i] = 360 * datas[i] / sum;
            textAngle[i] = start + sweepAngle[i] / 2;
            start += sweepAngle[i];
        }
    }

    String calcNamelongest(String[] strSet) {
        String longest = "M";
        for (String s : strSet) {
            if (s.length() > longest.length()) {
                longest = s;
            }
        }
        return longest;
    }

    //TODO 外部項
    public float getstartAngle(int index) {
        return startAngle[index];
    }

    public float getsweepAngle(int index) {
        return sweepAngle[index];
    }

    public int getColor(int index) {
        return colors[index];
    }

    public String getName(int index) {
        return dataName[index];
    }

    public String getNamelongest() {
        return dataNamelongest;
    }

    public void setColor(int... color) {
        if (color.length < length) {
            for (int i = 0; i < color.length; i++) colors[i] = color[i];
        } else {
            colors = color.clone();
        }
    }

    public void setName(String... name) {
        if (name.length < length) {
            for (int i = 0; i < name.length; i++) dataName[i] = name[i];
        } else {
            dataName = name.clone();
        }
    }

    public void setTextPercent() {
        float sum = 0;
        for (float d : datas) sum += d;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        for (int i = 0; i < length; i++) {
            dataName[i] = nf.format(datas[i] / sum) + "%";
        }
    }

    public void reCale() {
        calcAngles();
    }
}
