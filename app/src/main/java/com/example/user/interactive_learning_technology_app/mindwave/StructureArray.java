package com.example.user.interactive_learning_technology_app.mindwave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StructureArray<E extends Enum<?>> extends ArrayList<HashMap<String, Integer>> {

    /**
     * serialVersionUID = -5988183259596571848L
     */
    private static final long serialVersionUID = -5988183259596571848L;

    private HashMap<String, Integer> temp;

    public final void put(E key, int value) {
        if (temp == null) temp = new HashMap<String, Integer>();
        temp.put(key.name(), value);
    }
    public final int get(E key) {
        if (temp == null) temp = new HashMap<String, Integer>();
        return temp.get(key.name());
    }

    public final void store() {
        add(temp);
        temp = null;
    }

    public final int get(E key, int index) {
        return get(index).get(key.name());
    }

    public final int[] getArray(E key) {
        int[] array = new int[size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = get(key, i);
        }
        return array;
    }

    public static final Object valueOf(Class<?> cls, String data) throws InstantiationException, IllegalAccessException {

        Object obj = cls.newInstance();
        if (!(obj instanceof StructureArray)) return null;

        StructureArray array = (StructureArray) obj;
        HashMap<String, Integer> temp;

        Matcher hash, key, value;
        String hashstr;
        hash = Pattern.compile("\\{[^\\{\\}]+\\}").matcher(data);
        while (hash.find()) {
            hashstr = hash.group();
            key = Pattern.compile("(?<=\\{|\\, )[^,{}\\=\\{\\}]+(?=\\=)").matcher(hashstr);
            value = Pattern.compile("(?<=\\=)[^,{}\\=\\{\\}]+(?=\\,|\\})").matcher(hashstr);

            temp = new HashMap<String, Integer>();
            while (key.find() && value.find()) {
                temp.put(key.group(), Integer.valueOf(value.group()));
            }
            array.add(temp);
            temp = null;
        }
        return array;
    }
}
