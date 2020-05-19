package wjk.android.chart.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextPaint;

import wjk.android.chart.Adapter;
import wjk.android.chart.Frame;
import wjk.android.chart.Render;

public class BarChartRender extends Render {

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

        final float barWidth = mBarWidth;

        canvas.save();
        canvas.clipRect(left, top, right, bottom, Region.Op.REPLACE);
        canvas.translate(left + cellWidth - barWidth / 2, bottom);

        Object obj = adapter.get(0);
        if (obj instanceof Number) {

            int start = mValueLengthStart;
            int end = (mValueLengthEnd < adapter.count()) ? mValueLengthEnd : adapter.count();
            RectF rect = new RectF();

            for (int w = 0; start < end; w++, start++) {
                float h = -(((Number) adapter.get(start)).intValue() - valueRangeMin);

                rect.top = h * cellHeight;
                rect.bottom = 0;
                rect.left = w * cellWidth;
                rect.right = rect.left + barWidth;

                canvas.drawRect(rect, drawPaint);
            }

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

    private float mBarWidth;

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

        mDrawPaint.setStyle(Paint.Style.FILL);
        mDrawPaint.setColor(0xffff0000);

        mCellWidth = (getWidth() + 0f) / (mValueLength);
        mCellHeight = (getHeight() + 0f) / (mValueRange);

        mBarWidth = mCellWidth / 3;
    }
}
