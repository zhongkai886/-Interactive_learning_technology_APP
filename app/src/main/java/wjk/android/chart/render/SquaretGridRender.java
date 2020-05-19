package wjk.android.chart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;

import wjk.android.chart.Adapter;
import wjk.android.chart.Frame;
import wjk.android.chart.Render;

public class SquaretGridRender extends Render {


    @Override
    public void draw(Canvas canvas, Adapter adapter) {
    }

    @Override
    public void draw(Canvas canvas) {
        final int left = getLeft();
        final int bottom = getBottom();

        final TextPaint textPaint = mTextPaint;
        final Paint drawPaint = mDrawPaint;

        final Rect inRect = mInRect;

        float cellWidth = mCellWidth;
        float cellHeight = mCellHeight;
        float cellValueWidth = mCellValueWidth;
        float cellValueHeight = mCellValueHeight;

        {
            canvas.save();
            canvas.translate(left, inRect.bottom);

            mTextPaint.setTextAlign(Paint.Align.LEFT);
            float textAscentHalf = textPaint.ascent() / 2;
            for (int i = 0, s = mValueRangeCount; i <= s; i++) {
                float y = -(cellHeight * i);
                String t = floatTstring((cellValueHeight * i) + mValueRangeMin);

                canvas.drawText(t, 0, y - textAscentHalf, textPaint);

                canvas.drawLine(-left + inRect.left, y, -left + inRect.right, y, drawPaint);
            }

            canvas.restore();
        }

        {
            canvas.save();
            canvas.translate(inRect.left, bottom);

            mTextPaint.setTextAlign(Paint.Align.CENTER);
            for (int i = 0, s = mValueLengthCount; i <= s; i++) {
                float x = cellWidth * i;
                String t = floatTstring((cellValueWidth * i) + mValueLengthStart);

                canvas.drawText(t, x, 0, textPaint);
                canvas.drawLine(x, -bottom + inRect.bottom, x, -bottom + inRect.top, drawPaint);
            }

            canvas.restore();
        }
    }

    private int mDrawPaintColor;
    private TextPaint mTextPaint;
    private Paint mDrawPaint;

    private int mValueLengthCount;
    private int mValueRangeCount;

    private float mCellWidth;
    private float mCellHeight;
    private float mCellValueWidth;
    private float mCellValueHeight;

    private float mValueLengthStart;
    private float mValueRangeMax;
    private float mValueLengthEnd;
    private float mValueRangeMin;
    private float mValueLength;
    private float mValueRange;

    private Rect mInRect;


    @Override
    public void onAttributeSet(Context context, AttributeSet attrs) {
        mValueLengthCount = 5;
        mValueRangeCount = 4;
        mDrawPaintColor = 0xff000000;
        if (attrs == null) return;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            if (attrName != null && attrName.toLowerCase().equals("squaretgridrender.valuelengthcount")) {
                mValueLengthCount = attrs.getAttributeIntValue(i, mValueLengthCount);
            } else if (attrName != null && attrName.toLowerCase().equals("squaretgridrender.valuerangecount")) {
                mValueRangeCount = attrs.getAttributeIntValue(i, mValueRangeCount);
            } else if (attrName != null && attrName.toLowerCase().equals("squaretgridrender.gridcolor")) {
                mDrawPaintColor = Color.parseColor(attrs.getAttributeValue(i));
            }
        }

    }

    @Override
    public void onFrame(Frame frame) {

        final int strokeWidth = 1;
        mTextPaint = new TextPaint(frame.getTextPaint());
        mDrawPaint = new Paint(frame.getDrawPaint());
        mDrawPaint.setStrokeWidth(strokeWidth);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setColor(mDrawPaintColor);

        mValueLengthStart = frame.getValueLengthStart();
        mValueLengthEnd = frame.getValueLengthEnd();
        mValueRangeMax = frame.getValueRangeMax();
        mValueRangeMin = frame.getValueRangeMin();
        mValueLength = frame.getValueLength();
        mValueRange = frame.getValueRange();

        float mLeftBorder = mTextPaint.measureText(".0" + (int) mValueRangeMax);
        float mRightBorder = mTextPaint.measureText("" + (int) mValueLengthEnd) / 2 + strokeWidth;
        float mTopBorder = strokeWidth - mTextPaint.ascent() / 2;
        float mBottomBorder = -mTextPaint.ascent();

        mValueLengthStart = (int) frame.getValueLengthStart();

        mInRect = new Rect(
                (int) (getLeft() + mLeftBorder),
                (int) (getTop() + mTopBorder),
                (int) (getRight() - mRightBorder),
                (int) (getBottom() - mBottomBorder));

        float mInWidth = mInRect.width();
        float mInHeight = mInRect.height();

        mCellWidth = (mInWidth + 0f) / (mValueLengthCount);
        mCellHeight = (mInHeight + 0f) / (mValueRangeCount);

        mCellValueWidth = mValueLength / (mValueLengthCount);
        mCellValueHeight = mValueRange / (mValueRangeCount);

    }


    private String floatTstring(float v) {
        String t = String.format("%.1f", v);
        if (t.indexOf(".0") > 0)
            return t.substring(0, t.length() - 2);
        return t;
    }


    @Override
    public void onChildRender(Render render) {
        render.setLayout(
                mInRect.left,
                mInRect.top,
                mInRect.right,
                mInRect.bottom);
    }

}
