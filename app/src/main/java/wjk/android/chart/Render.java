package wjk.android.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public abstract class Render {

    private int mLeft;
    private int mTop;
    private int mBottom;
    private int mRight;


    public void setLayout(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }

    public abstract void draw(Canvas canvas);

    public abstract void draw(Canvas canvas, Adapter adapter);

    public void onAttributeSet(Context context, AttributeSet attrs) {
    }

    public void onFrame(Frame frame) {
    }

    public void onChildRender(Render render) {
    }

    public int getLeft() {
        return mLeft;
    }

    public int getTop() {
        return mTop;
    }

    public int getRight() {
        return mRight;
    }

    public int getBottom() {
        return mBottom;
    }

    public int getWidth() {
        return mRight - mLeft;
    }

    public int getHeight() {
        return mBottom - mTop;
    }
}
