package com.example.user.interactive_learning_technology_app.widget;

import com.example.user.interactive_learning_technology_app.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class ProductReader {

    private JSONArray mRoot;
    private JSONObject mElement;


    public ProductReader(File file) {
        try {
            _initRoot(_readString(new FileInputStream(file)));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProductReader(InputStream is) {
        try {
            _initRoot(_readString(is));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String _readString(InputStream is) throws IOException {
        String json = null;
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }

    private void _initRoot(String jsonStr) throws JSONException {
        mRoot = new JSONArray(jsonStr);
    }


    public boolean moveToPosition(int pos) {
        if (pos < 0 || pos >= length()) return false;

        try {
            mElement = mRoot.getJSONObject(pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int length() {
        return mRoot.length();
    }

    //Get attributes

    private int _resources_string(String attr) {
        try {
            Field idField = R.string.class.getDeclaredField(mElement.getString(attr));
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    private int _resources_drawable(String attr) {
        try {
            Field idField = R.drawable.class.getDeclaredField(mElement.getString(attr));
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getName() {
        return _resources_string("name");
    }

    public int getIcon() {
        return _resources_drawable("icon");
    }

    public int getText() {
        return _resources_string("text");
    }

    public void getMBTIElements(int mbti) {
        try {
            mRoot = mRoot.getJSONArray(mbti);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
