package wjk.android.chart;


import android.graphics.Canvas;

public abstract class Adapter<T> {

    protected Render _render;

    public abstract void add(T i);

    public abstract void remove(int index);

    public abstract T get(int index);

    public abstract int count();

    public void draw(Canvas canvas) {
        _render.draw(canvas, this);
    }

    public void setRender(Render render) {
        _render = render;
    }

    public Render getRender() {
        return _render;
    }

}
