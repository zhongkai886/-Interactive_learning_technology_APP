package wjk.android.chart.adapter;

import java.util.ArrayList;

import wjk.android.chart.Adapter;

public class NumberAdapter<T extends Number> extends Adapter<T> {
    ArrayList<T> array = new ArrayList<T>();
    public int color;

    @Override
    public void add(T i) {
        array.add(i);
    }

    @Override
    public void remove(int index) {
        array.remove(index);
    }

    public void removeAll() {
        array.clear();
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    @Override
    public int count() {
        return array.size();
    }

}
