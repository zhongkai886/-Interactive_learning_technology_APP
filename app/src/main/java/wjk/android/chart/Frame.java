package wjk.android.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Frame extends View {
    private static final float DEFAULT_VALUERANGEMAX = 100;
    private static final float DEFAULT_VALUERANGEMIN = 0;

    private static final float DEFAULT_VALUELENGTHSTART = 0;
    private static final float DEFAULT_VALUELENGTHEND = 10;

    private static final String KEY_RENDER_LINE = "KEY_RENDER_LINE";

    private TextPaint textPaint;
    private Paint drawPaint;
    private ArrayList<Adapter> adapterList;
    private Render gridRender;
    private Render adapterRender;

    private float valueRangeMax;
    private float valueRangeMin;

    private float valueLengthStart;
    private float valueLengthEnd;
    private int[] colors;

    public Frame(Context context) {
        super(context);
        Log.e("init", "init");
        init(context, null);
    }

    public Frame(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("init", "init");
        init(context, attrs);
    }

    public Frame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("init", "init");
        init(context, attrs);
    }

    @SuppressLint("NewApi")
    public Frame(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e("init", "init");
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Log.e("init", "init2");
        drawPaint = obtainDrawPaint();
        textPaint = obtainTextPaint(context, attrs);
        valueRangeMax = DEFAULT_VALUERANGEMAX;
        valueRangeMin = DEFAULT_VALUERANGEMIN;
        valueLengthStart = DEFAULT_VALUELENGTHSTART;
        valueLengthEnd = DEFAULT_VALUELENGTHEND;
        colors = new int[]{
                0xffff0000,
                0xff00ff00,
                0xff0000ff};
        adapterList = new ArrayList<Adapter>();

        onAttributeSet(context, attrs);
        if (gridRender != null) gridRender.onAttributeSet(context, attrs);
        if (adapterRender != null) adapterRender.onAttributeSet(context, attrs);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (gridRender != null) {
            gridRender.setLayout(
                    getPaddingLeft(),
                    getPaddingTop(),
                    getWidth() - getPaddingRight(),
                    getHeight() - getPaddingBottom());
            gridRender.onFrame(this);
        }

        if (adapterRender != null) {
            adapterRender.setLayout(
                    getPaddingLeft(),
                    getPaddingTop(),
                    getWidth() - getPaddingRight(),
                    getHeight() - getPaddingBottom());
            if (gridRender != null) gridRender.onChildRender(adapterRender);
            adapterRender.onFrame(this);
        }

        for (Adapter a : adapterList) {
            Render r = a.getRender();
            if (r == adapterRender) continue;

            r.setLayout(
                    getPaddingLeft(),
                    getPaddingTop(),
                    getWidth() - getPaddingRight(),
                    getHeight() - getPaddingBottom());
            if (gridRender != null) gridRender.onChildRender(r);
            r.onFrame(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (gridRender != null) {
            gridRender.draw(canvas);
        }
        for (Adapter a : adapterList) {
            a.draw(canvas);
        }
    }

    private void onAttributeSet(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            if (attrName != null && attrName.toLowerCase().equals("paintstrokewidth")) {
                drawPaint.setStrokeWidth(strokeWidth(context, attrs.getAttributeValue(i)));
            } else if (attrName != null && attrName.toLowerCase().equals("colors")) {
                int id = attrs.getAttributeResourceValue(i, -1);
                if (id > 0) {
                    colors = context.getResources().getIntArray(id);
                }
            } else if (attrName != null && attrName.toLowerCase().equals("valuerangemax")) {
                valueRangeMax = attrs.getAttributeFloatValue(i, valueRangeMax);
            } else if (attrName != null && attrName.toLowerCase().equals("valuerangemin")) {
                valueRangeMin = attrs.getAttributeFloatValue(i, valueRangeMin);
            } else if (attrName != null && attrName.toLowerCase().equals("valuelengthstart")) {
                valueLengthStart = attrs.getAttributeFloatValue(i, valueLengthStart);
            } else if (attrName != null && attrName.toLowerCase().equals("valuelengthend")) {
                valueLengthEnd = attrs.getAttributeFloatValue(i, valueLengthEnd);
            } else if (attrName != null && attrName.toLowerCase().equals("render")) {
                try {
                    Class c = Class.forName(attrs.getAttributeValue(i));
                    adapterRender = (Render) c.newInstance();
                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (attrName != null && attrName.toLowerCase().equals("gridrender")) {
                try {
                    Class c = Class.forName(attrs.getAttributeValue(i));
                    gridRender = (Render) c.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private float strokeWidth(Context context, String value) {
        String unit = value.substring(value.length() - 2, value.length());
        float num = Float.valueOf(value.substring(0, value.length() - 2));
        if (unit.equals("dp")) {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    num, context.getResources().getDisplayMetrics());
        } else if (unit.equals("sp")) {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    num, context.getResources().getDisplayMetrics());
        } else if (unit.equals("sp")) {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    num, context.getResources().getDisplayMetrics());
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                num, context.getResources().getDisplayMetrics());
    }

    private TextPaint obtainTextPaint(Context context, AttributeSet attrs) {
        TextView t = new TextView(context, attrs);

        Log.e("mTextPaint", "" + Integer.toHexString(t.getCurrentTextColor()));
        return t.getPaint();
    }

    private Paint obtainDrawPaint() {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStrokeJoin(Paint.Join.ROUND);
        return p;
    }

    //TODO public method
    public void reDrow() {
        invalidate();
    }

    //TODO variable method
    public Paint getDrawPaint() {
        return drawPaint;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public int[] getColors() {
        return colors;
    }

    public Render getAdapterRender() {
        return adapterRender;
    }

    public void setAdapter(Adapter adapter) {
        addAdapter(adapter);
    }

    public void addAdapter(Adapter adapter) {
        if (adapter.getRender() == null) adapter.setRender(adapterRender);
        adapterList.add(adapter);
    }

    public Adapter getAdapter(int index) {
        return adapterList.get(index);
    }

    public void removeAdapter(int index) {
        adapterList.remove(index);
    }

    public void removeAdapter(Adapter adapter) {
        adapterList.remove(adapter);
    }

    public float getValueRangeMax() {
        return valueRangeMax;
    }

    public float getValueRangeMin() {
        return valueRangeMin;
    }

    public float getValueRange() {
        return Math.abs(valueRangeMax - valueRangeMin);
    }

    public float getValueLengthStart() {
        return valueLengthStart;
    }

    public float getValueLengthEnd() {
        return valueLengthEnd;
    }

    public float getValueLength() {
        return Math.abs(valueLengthEnd - valueLengthStart);
    }

}
