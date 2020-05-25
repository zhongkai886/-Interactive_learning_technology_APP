package com.example.user.interactive_learning_technology_app.widget;

public class StringMultiple {
    public static final String SPLITCHAR = ";";

    public static String encode(String... datas) {
        if (datas.length <= 0) return null;
        String multi = "";
        for (int i = 0; i < datas.length; i++) {
            if (multi.length() <= 0) multi += datas[i];
            else if (datas[i].length() > 0) multi += SPLITCHAR + datas[i];
        }

        return multi;
    }

    public static String[] decode(String data) {
        return data.split(SPLITCHAR);
    }

}
