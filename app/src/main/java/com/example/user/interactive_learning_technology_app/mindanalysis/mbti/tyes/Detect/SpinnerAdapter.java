package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.interactive_learning_technology_app.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> idList;
    private ArrayList<String> nameList;
    private ArrayList<String> itemList;

    public SpinnerAdapter(Context context, ArrayList<String> idList,ArrayList<String> nameList,ArrayList<String> itemList){
        this.inflater = LayoutInflater.from(context);
        this.idList = idList;
        this.nameList = nameList;
        this.itemList = itemList;
    }
    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner_feedback_choice,null);
        TextView textView = (TextView) view.findViewById(R.id.id);
        TextView textView2 = (TextView) view.findViewById(R.id.name);
        TextView textView3 = (TextView) view.findViewById(R.id.item);
        textView.setText(idList.get(i));
        textView2.setText(nameList.get(i));
        textView3.setText(itemList.get(i));
        return view;
    }

    public ArrayList<String> getIdList() {
        return idList;
    }
}
