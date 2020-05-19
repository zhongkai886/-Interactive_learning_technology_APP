package wjk.android.chart.adapter;

import java.util.ArrayList;
import java.util.List;

import wjk.android.chart.Adapter;

public class ListNumberAdapter extends Adapter<List<? extends Number>> {
    ArrayList<List<? extends Number>> array = new ArrayList<List<? extends Number>>();

    @Override
    public void add(List<? extends Number> i) {
        array.add(i);
    }

    @Override
    public void remove(int index) {
        array.remove(index);
    }

    @Override
    public List<? extends Number> get(int index) {
        return array.get(index);
    }

    @Override
    public int count() {
        return array.size();
    }

}
