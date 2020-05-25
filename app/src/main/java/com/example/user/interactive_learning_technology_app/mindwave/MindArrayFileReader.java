package com.example.user.interactive_learning_technology_app.mindwave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MindArrayFileReader extends MindArray {

    /**
     *
     */
    private static final long serialVersionUID = 3540087178470517205L;

    public void read(File file) throws NumberFormatException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Matcher key, value;
        String hashstr;
        HashMap<String, Integer> temp;
        while (br.ready()) {
            hashstr = br.readLine();
            key = Pattern.compile("(?<=\\{|\\, )[^,{}\\=\\{\\}]+(?=\\=)").matcher(hashstr);
            value = Pattern.compile("(?<=\\=)[^,{}\\=\\{\\}]+(?=\\,|\\})").matcher(hashstr);

            temp = new HashMap<String, Integer>();
            while (key.find() && value.find()) {
                temp.put(key.group(), Integer.valueOf(value.group()));
            }
            add(temp);
            temp = null;
        }
        br.close();
        fr.close();
    }

    public static int[] read(File file, MindArray.key key) throws NumberFormatException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String regex = String.format("(?<=%s=)[^,{}\\=\\{\\}]+", key.name());
        Matcher value;
        String hashstr;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        while (br.ready()) {
            hashstr = br.readLine();
            value = Pattern.compile(regex).matcher(hashstr);
            while (value.find()) {
                temp.add(Integer.valueOf(value.group()));
            }
        }

        int[] arr = new int[temp.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.get(i);
        }

        br.close();
        fr.close();

        return arr;
    }
}
