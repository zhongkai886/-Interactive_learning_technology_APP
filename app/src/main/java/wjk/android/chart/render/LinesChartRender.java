package wjk.android.chart.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.text.TextPaint;

import java.util.List;

import wjk.android.chart.Adapter;
import wjk.android.chart.Frame;
import wjk.android.chart.Render;
import wjk.android.chart.adapter.NumberAdapter;

public class LinesChartRender extends Render {

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void draw(Canvas canvas, Adapter adapter) {
        if (adapter == null) return;
        if (adapter.count() < 1) return;

        final int left = getLeft();
        final int top = getTop();
        final int right = getRight();
        final int bottom = getBottom();

        final Paint drawPaint = mDrawPaint;

        final float cellWidth = mCellWidth;
        final float cellHeight = mCellHeight;

        final float valueRangeMin = mValueRangeMin;
        final int[] colors = mColors;


        canvas.save();
        canvas.clipRect(left, top, right, bottom, Region.Op.INTERSECT);
        canvas.translate(left + cellWidth, bottom);

        Object obj = adapter.get(0);
        if (obj instanceof List) {
            for (int i = 0, s = adapter.count(); i < s; i++) {
                List<Number> list = (List<Number>) adapter.get(i);
                drawPaint.setColor(colors[i % colors.length]);
                if (mValueLengthStart < list.size()) {

                    Path paht = new Path();
                    int start = mValueLengthStart;
                    int end = (mValueLengthEnd < list.size()) ? mValueLengthEnd : list.size();
                    if (start < end)
                        paht.moveTo(0, -list.get(start++).intValue() + valueRangeMin * cellHeight);
                    for (int w = 1; start < end; w++, start++) {
                        float h = -(list.get(start).intValue() - valueRangeMin);

                        paht.lineTo(w * cellWidth, h * cellHeight);
                    }
                    canvas.drawPath(paht, drawPaint);

                }
            }
        } else if (obj instanceof Number) {

            Path paht = new Path();
            int start = mValueLengthStart;
            int end = (mValueLengthEnd < adapter.count()) ? mValueLengthEnd : adapter.count();
            drawPaint.setColor(((NumberAdapter) adapter).color);

            if (start < end)
                paht.moveTo(0, -(((Number) adapter.get(start++)).intValue() + valueRangeMin) * cellHeight);
            for (int w = 1; start < end; w++, start++) {
                float h = -(((Number) adapter.get(start)).intValue() - valueRangeMin);

                paht.lineTo(w * cellWidth, h * cellHeight);
            }
            canvas.drawPath(paht, drawPaint);

        }

        canvas.restore();
    }


    private int[] mColors;
    private Paint mDrawPaint;
    private TextPaint mTextPaint;

    private float mValueRangeMax;
    private float mValueRangeMin;

    private int mValueLengthStart;
    private int mValueLengthEnd;

    private float mCellWidth;
    private float mCellHeight;

    @Override
    public void onFrame(Frame frame) {
        super.onFrame(frame);

        mDrawPaint = new Paint(frame.getDrawPaint());
        mTextPaint = new TextPaint(frame.getTextPaint());

        mColors = frame.getColors();
        mValueRangeMax = frame.getValueRangeMax();
        mValueRangeMin = frame.getValueRangeMin();
        float mValueRange = mValueRangeMax - mValueRangeMin;

        mValueLengthStart = (int) frame.getValueLengthStart();
        mValueLengthEnd = (int) frame.getValueLengthEnd();
        float mValueLength = mValueLengthEnd - mValueLengthStart;

        mDrawPaint.setStyle(Paint.Style.STROKE);

        mCellWidth = (getWidth() + 0f) / (mValueLength);
        mCellHeight = (getHeight() + 0f) / (mValueRange);
    }
}
