package wjk.android.chart.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;

import wjk.android.chart.Adapter;
import wjk.android.chart.Frame;
import wjk.android.chart.Render;
import wjk.android.chart.adapter.NumberAdapter;

public class PolygonRender extends Render {

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void draw(Canvas canvas, Adapter adapter) {

        if (adapter == null) return;
        if (adapter.count() < 1) return;
        if (adapter instanceof NumberAdapter == false) return;

        NumberAdapter mAdapter = (NumberAdapter) adapter;

        final Paint drawPaint = mDrawPaint;
        final Paint textPaint = mTextPaint;
        final int[] colors = mColors;

        //member
        final int grid_cell = _grid_cell;
        final String[] grid_label = _grid_label;
        final int grid_unit = _grid_unit;
        final int grid_side = mAdapter.count();
        //view
        final float text_height = -textPaint.ascent() + textPaint.descent();
        final RectF rectF = new RectF(mRectF.left, mRectF.top + text_height - textPaint.ascent(), mRectF.right, mRectF.bottom - text_height);
        final float centerX = rectF.centerX();
        final float centerY = rectF.centerY();
        final float radius = rectF.width() < rectF.height() ? rectF.width() / 2 : rectF.height() / 2;


        {//內容
//			canvas.translate(rectF.left, rectF.top);

            drawPaint.setColor(mColors[0]);
            drawPaint.setStyle(Paint.Style.FILL);

            Path path = new Path();
            for (int i = 0; i < grid_side; i++) {
                float value = mAdapter.get(i).floatValue();
                float target = (float) (((2f * i / grid_side) - (0.5f)) * Math.PI);
                float x = (float) (centerX + radius * Math.cos(target) * (value / grid_unit));
                float y = (float) (centerY + radius * Math.sin(target) * (value / grid_unit));

                if (i == 0) path.moveTo(x, y);
                else path.lineTo(x, y);
            }
            path.close();
            canvas.drawPath(path, drawPaint);

        }

        {//邊
            drawPaint.setStrokeWidth(1);
            drawPaint.setColor(textPaint.getColor());
            drawPaint.setStyle(Paint.Style.STROKE);

            Path path = new Path();
            for (int j = 1; j <= grid_cell; j++) {//框
                float r = radius * j / grid_cell;
                for (int i = 0; i < grid_side; i++) {
                    float target = (float) (((2f * i / grid_side) - (0.5f)) * Math.PI);
                    float x = (float) (centerX + r * Math.cos(target));
                    float y = (float) (centerY + r * Math.sin(target));

                    if (i == 0) path.moveTo(x, y);
                    else path.lineTo(x, y);
                }
                path.close();
                canvas.drawPath(path, drawPaint);
            }

            for (int i = 0; i < grid_side; i++) {//直線
                float target = (float) (((2f * i / grid_side) - (0.5f)) * Math.PI);
                float x = (float) (centerX + radius * Math.cos(target));
                float y = (float) (centerY + radius * Math.sin(target));

                canvas.drawLine(centerX, centerY, x, y, drawPaint);
            }

        }

        {//單位
            float textsize_o = textPaint.getTextSize();
            textPaint.setTextSize(radius / 2 / grid_cell);
            float target = (float) ((-(0.5f)) * Math.PI);
            for (float i = 0; i <= grid_cell; i++) {
                float f = i / grid_cell;
                float x = (float) (centerX + radius * f * Math.cos(target));
                float y = (float) (centerY + radius * f * Math.sin(target));

                canvas.drawText("" + (int) (grid_unit * f + 0.5f), x, y, textPaint);
            }
            textPaint.setTextSize(textsize_o);
        }

        {//數值標題

            float ascent = -textPaint.ascent();
            float ascent_half = -textPaint.ascent() / 2;
            float grid_side_half = grid_side / 2 + 0.5f;
            for (int i = 0; i < grid_side; i++) {//直線
                float target = (float) (((2f * i / grid_side) - (0.5f)) * Math.PI);
                float x = (float) (centerX + radius * Math.cos(target));
                float y = (float) (centerY + radius * Math.sin(target));

                if (i == 0) {//第一位必定正上
                    y -= ascent;
                    textPaint.setTextAlign(Paint.Align.CENTER);
                } else {
                    //判斷左右數，偶數中間數情況
                    if (i > grid_side_half) {
                        x -= ascent_half;
                        y += ascent_half;
                        textPaint.setTextAlign(Paint.Align.RIGHT);
                    } else if (i < grid_side_half) {
                        x += ascent_half;
                        y += ascent_half;
                        textPaint.setTextAlign(Paint.Align.LEFT);
                    } else {
                        y += ascent;
                        textPaint.setTextAlign(Paint.Align.CENTER);
                    }
                    //奇數情況
                    if ((grid_side & 1) > 0) {
                        if ((((int) (grid_side_half)) == i) ||
                                ((((int) (grid_side_half)) + 1) == i)) {
                            y += ascent_half;
//							textPaint.setTextAlign(Paint.Align.CENTER);
                        }
                    }

                }

                canvas.drawText(grid_label[i % grid_label.length], x, y, textPaint);
            }
        }

    }


    private int _grid_cell;
    private String[] _grid_label;
    private int _grid_unit;


    @Override
    public void onAttributeSet(Context context, AttributeSet attrs) {
        _grid_cell = 5;
        _grid_label = new String[]{"A", "B", "C"};
        _grid_unit = 100;
        if (attrs == null) return;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            if (attrName != null && attrName.toLowerCase().equals("polygonrender.gridcell")) {
                _grid_cell = attrs.getAttributeIntValue(i, _grid_cell);
            } else if (attrName != null && attrName.toLowerCase().equals("polygonrender.gridlabel")) {
                int rId = attrs.getAttributeResourceValue(null, "polygonrender.gridlabel", 0);
                if (rId != 0) _grid_label = context.getResources().getStringArray(rId);
            } else if (attrName != null && attrName.toLowerCase().equals("polygonrender.gridunit")) {
                _grid_unit = attrs.getAttributeIntValue(i, _grid_unit);
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

        mRectF = new RectF(getLeft(), getTop(), getRight(), getBottom());

    }
}
