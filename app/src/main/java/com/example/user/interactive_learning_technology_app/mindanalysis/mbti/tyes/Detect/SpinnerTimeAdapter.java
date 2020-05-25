package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.interactive_learning_technology_app.R;

import java.util.ArrayList;

public class SpinnerTimeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> timeList;

    public SpinnerTimeAdapter(Context context, ArrayList timeList){
        this.inflater = LayoutInflater.from(context);
        this.timeList = timeList;

    }
    @Override
    public int getCount() {
        return timeList.size();
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
        view = inflater.inflate(R.layout.item_spinner_time_choice,null);
        TextView textView = (TextView) view.findViewById(R.id.time);
        textView.setText(timeList.get(i));
        return view;
    }
}
