package com.example.user.interactive_learning_technology_app.widget;

import com.example.user.interactive_learning_technology_app.mindwave.MindArray;

public class MindArrayList {

    public void calc(String data) throws InstantiationException, IllegalAccessException {
        String[] data_array = StringMultiple.decode(data);
        MindArray[] mind_array = new MindArray[data_array.length];
        for (int i = 0; i < data_array.length; i++)
            mind_array[i] = (MindArray) MindArray.valueOf(MindArray.class, data_array[i]);
    }

}
