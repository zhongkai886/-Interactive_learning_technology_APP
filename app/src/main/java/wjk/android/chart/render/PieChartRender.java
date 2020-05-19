package wjk.android.chart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;

import wjk.android.chart.Adapter;
import wjk.android.chart.ColorDepth;
import wjk.android.chart.Frame;
import wjk.android.chart.Render;
import wjk.android.chart.adapter.NumberAdapter;

public class PieChartRender extends Render {

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void draw(Canvas canvas, Adapter adapter) {

        if (adapter == null) return;
        if (adapter.count() < 1) return;
        if (adapter instanceof NumberAdapter == false) return;

        NumberAdapter mAdapter = (NumberAdapter) adapter;
        float mValueSum = 0;
        for (int i = 0, s = mAdapter.count(); i < s; i++) {
            mValueSum += mAdapter.get(i).floatValue();
        }

        final RectF rectF = mRectF;
        final Paint drawPaint = mDrawPaint;
        final Paint textPaint = mTextPaint;
        float mPieTextRadius = rectF.width() / 2 - textPaint.measureText("" + mValueSum) / 2;
        final float valueSum = mValueSum;
        final int[] colors = mColors;
        final boolean labelEnabled = mLabelEnabled;
        final int labelRadius = mLabelRadius;
        final int labelBackground = mLabelBackground;
        final int labelTextColor = mLabelTextColor;
        final String labelText = mLabelText;
        final boolean pieValueTextEnabled = mPieValueTextEnabled;


        {
            canvas.save();
            canvas.clipRect(rectF, Region.Op.INTERSECT);

            for (int i = 0, s = mAdapter.count(), current = 0; i < s; i++) {
                float value = mAdapter.get(i).floatValue();
                int target = (int) (360 * value / valueSum + 0.5f);

                drawPaint.setColor(colors[i % colors.length]);
                canvas.drawArc(rectF, current, target, true, drawPaint);
                current += target;
            }

            canvas.restore();
        }
        if (pieValueTextEnabled) {
            canvas.save();
            canvas.clipRect(rectF, Region.Op.INTERSECT);

            float cx = rectF.centerX();
            float cy = rectF.centerY() - textPaint.ascent() / 2;
            float r = mPieTextRadius;

            for (int i = 0, s = mAdapter.count(), current = 0; i < s; i++) {
                float value = mAdapter.get(i).floatValue();
                if (value == 0) continue;
                float target = (float) (2 * Math.PI * (current + value / 2) / valueSum);
                float x = (float) (cx + r * Math.cos(target));
                float y = (float) (cy + r * Math.sin(target));

                textPaint.setColor((ColorDepth.isLight(colors[i % colors.length]))?0xFF000000: 0xFFFFFFFF);
                canvas.drawText("" + (int) (value + 0.5f), x, y, textPaint);
                canvas.drawPoint(x, y, textPaint);
                current += value;
            }

            canvas.restore();
        }

        if (labelEnabled) {
            float cx = rectF.centerX();
            float cy = rectF.centerY();
            float fw = rectF.width() * labelRadius / 200;
            float fh = rectF.height() * labelRadius / 200;

            RectF labelRectF = new RectF(
                    cx - fw,
                    cy - fh,
                    cx + fw,
                    cy + fh);
            drawPaint.setColor(labelBackground);

            canvas.drawArc(labelRectF, 0, 360, true, drawPaint);

            textPaint.setColor(labelTextColor);

            canvas.drawText(labelText, cx, cy + drawPaint.descent(), textPaint);
        }
    }

    public void setLabelText(String text) {
        mLabelText = text;
    }


    private boolean mLabelEnabled;
    private String mLabelText;
    private int mLabelBackground;
    private int mLabelTextColor;
    private int mLabelRadius;
    private boolean mPieValueTextEnabled;


    @Override
    public void onAttributeSet(Context context, AttributeSet attrs) {
        mLabelEnabled = false;
        mLabelText = "";
        mLabelBackground = 0xffFFFFFF;
        mLabelTextColor = 0xff000000;
        mLabelRadius = 60;
        mPieValueTextEnabled = true;
        if (attrs == null) return;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            if (attrName != null && attrName.toLowerCase().equals("piechartrender.labelenabled")) {
                mLabelEnabled = attrs.getAttributeBooleanValue(i, mLabelEnabled);
            } else if (attrName != null && attrName.toLowerCase().equals("piechartrender.labeltext")) {
                int rId = attrs.getAttributeResourceValue(null, "piechartrender.labeltext", 0);
                if (rId != 0) mLabelText = context.getString(rId);
            } else if (attrName != null && attrName.toLowerCase().equals("piechartrender.labelbackground")) {
                mLabelBackground = Color.parseColor(attrs.getAttributeValue(i));
            } else if (attrName != null && attrName.toLowerCase().equals("piechartrender.labeltextcolor")) {
                mLabelTextColor = Color.parseColor(attrs.getAttributeValue(i));
            } else if (attrName != null && attrName.toLowerCase().equals("piechartrender.labelradius")) {
                mLabelRadius = attrs.getAttributeIntValue(i, mLabelRadius);
            } else if (attrName != null && attrName.toLowerCase().equals("piechartrender.pievaluetextenabled")) {
                mPieValueTextEnabled = attrs.getAttributeBooleanValue(i, mPieValueTextEnabled);
            }
        }

    }


    private Paint mDrawPaint;
    private TextPaint mTextPaint;

    private RectF mRectF;

    private int[] mColors;

    @Override
    public void onFrame(Frame frame) {
        super.onFrame(frame);

        mColors = frame.getColors();
        mDrawPaint = new Paint(frame.getDrawPaint());
        mTextPaint = new TextPaint(frame.getTextPaint());
        mDrawPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mRectF = new RectF(getLeft(), getTop(), getRight(), getBottom());

        if (mRectF.width() > mRectF.height()) {
            float cx = mRectF.centerX();
            float fh = mRectF.height() / 2;
            mRectF.left = cx - fh;
            mRectF.right = cx + fh;
        } else {
            float cy = mRectF.centerY();
            float fw = mRectF.width() / 2;
            mRectF.top = cy - fw;
            mRectF.bottom = cy + fw;
        }
    }
}
