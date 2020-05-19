package com.alchemy.wjk.mind.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class WavechartView extends View {

    int rangeHeight, rangeWidth;
    int frameRate = 60;
    int frameSpeed = 1000 / frameRate;
    int gridlineX = 3;

    int gridColor = 0xff000000;
    int textColor = 0xff000000;
    int paintSize = 2;
    int textSize = 12;
    float density = 1.0f, scaled = 1.0f;
    Paint pointPaint, arrowPaint, pathPaint, gridPaint, textPaint;

    WavechartHandle lines;
    boolean DRAW_ANIMA = false, DRAW_POINT = true, DRAW_LINE = false, DRAW_GRIDLINE = true,
            DRAW_ANIMATAIL = true;
    boolean ON_ANIMATOR = false;

    private Runnable animator = new Runnable() {
        @Override
        public void run() {
            if (lines.allMove()) postDelayed(this, frameSpeed);
            else ON_ANIMATOR = false;
            invalidate();
        }
    };

    public WavechartView(Context context) {
        this(context, null);
    }

    public WavechartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WavechartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        density = context.getResources().getDisplayMetrics().density;
        scaled = context.getResources().getDisplayMetrics().scaledDensity;
//		Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);

        pointPaint = new Paint();
        pointPaint.setStrokeWidth(paintSize * 4 * density);
//		pointPaint.setXfermode(xfermode);

        arrowPaint = new Paint();
        arrowPaint.setStrokeWidth(paintSize * 4 * density);
        arrowPaint.setStrokeCap(Cap.ROUND);
//		arrowPaint.setXfermode(xfermode);

        pathPaint = new Paint();
        pathPaint.setStrokeWidth(paintSize * density);
        pathPaint.setStrokeCap(Cap.ROUND);
//		pathPaint.setXfermode(xfermode);

        gridPaint = new Paint();
        gridPaint.setColor(gridColor);
        gridPaint.setStrokeWidth(2);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize * scaled);
        textPaint.setTextAlign(Align.LEFT);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setLayoutParams(lp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lines == null) return;
        float vh = getHeight();
        float vw = getWidth();

        int rangeC = rangeWidth - 1;

        float gaugeH = -vh / rangeHeight;
        float gaugeW = vw / rangeC;
        float basePointX = vh;

        for (WavechartHandle line : lines.getLines()) {
            if (DRAW_POINT) {
                pointPaint.setColor(line.pointColor);
                for (int i = 0; i < rangeWidth; i++) {
                    float pointY = gaugeH * line.getData(i - rangeWidth) + basePointX;
                    float pointX = gaugeW * (i - line.moveScroll);
                    canvas.drawPoint(pointX, pointY, pointPaint);

                }
            }
            if (DRAW_LINE) {
                pathPaint.setColor(line.lineColor);
                for (int i = 0; i < rangeWidth; i++) {
                    int indexY = i - line.moveScroll;
                    float pointY1 = gaugeH * line.getData(i - rangeWidth) + basePointX;
                    float pointX1 = gaugeW * indexY;
                    float pointY2 = gaugeH * line.getData(i - rangeWidth + 1) + basePointX;
                    float pointX2 = gaugeW * (indexY + 1);
                    canvas.drawLine(pointX1, pointY1, pointX2, pointY2, pathPaint);

                }
            }

            if (DRAW_ANIMA) {
                pathPaint.setColor(line.lineColor);
                arrowPaint.setColor(line.arrowColor);

                float coordX = line.getProgress();
                if (coordX > rangeC) coordX = rangeC;

                float pointY4 = gaugeH * line.getMovePoint() + basePointX;
                float pointX4 = DRAW_ANIMATAIL ? vw : gaugeW * line.getMoveProgress();
                if (pointX4 > vw) pointX4 = vw;
                float pointY3 = gaugeH * line.getData(line.getMovePositionOffset() - 1) + basePointX;
                float pointX3 = pointX4 + -gaugeW * line.getProgress();

                int lineSize = (line.movePosition < rangeC && DRAW_ANIMATAIL) ? line.movePosition : rangeC;
                for (int i = 0, j = line.getMovePositionOffset() - 1, s = lineSize
                     ; i <= s; i++, j--) {
                    float pointY1 = gaugeH * line.getData(j) + basePointX;
                    float pointX1 = pointX3 - gaugeW * (i);
                    float pointY2 = gaugeH * line.getData(j - 1) + basePointX;
                    float pointX2 = pointX3 - gaugeW * (i + 1);
                    canvas.drawLine(pointX1, pointY1, pointX2, pointY2, pathPaint);
                }

                canvas.drawLine(pointX3, pointY3, pointX4, pointY4, pathPaint);
                canvas.drawPoint(pointX4, pointY4, arrowPaint);

            }
        }
        if (DRAW_GRIDLINE) {
            float gridsizeX = 8 * density;
            float gridsizeX2 = 4 * density;
            float vh2 = vh / 2;
            for (int i = 0; i < rangeWidth; i++) {
                float lineX = gaugeW * i;
                canvas.drawLine(lineX, 0, lineX, gridsizeX, gridPaint);
                canvas.drawLine(lineX, vh2 - gridsizeX2, lineX, vh2 + gridsizeX2, gridPaint);
                canvas.drawLine(lineX, vh - gridsizeX, lineX, vh, gridPaint);

//				if(i!=0 && i!=rangeWidth-1)canvas.drawText(""+(i+1), lineX, vh, textPaint);
            }
            for (int i = 0; i < gridlineX; i++) {
                float lineY = vh * i / (gridlineX - 1);
                canvas.drawLine(0, lineY, vw, lineY, gridPaint);
            }
            float textBottom = textPaint.getFontMetrics().bottom;
            for (int i = 0, s = 11; i < s; i++) {
                float lineY = vh * i / (s - 1);
                int range = rangeHeight * (s - i - 1) / (s - 1);
                if (i != 0 && i != s - 1 && i != 5)
                    canvas.drawText("" + range, 0, lineY + textBottom, textPaint);
            }
        }
    }

    public void setPaintSizePX(int size) {

        pointPaint.setStrokeWidth(size * 4);
        arrowPaint.setStrokeWidth(size * 4);
        pathPaint.setStrokeWidth(size);
    }

    public void setRangeHeight(int height) {
        rangeHeight = height;
    }

    public void setRangeWidth(int width) {
        rangeWidth = width;
    }

    public void setGridColor(int color) {
        gridColor = color;
        gridPaint.setColor(gridColor);
    }

    public void setTextColor(int color) {
        textColor = color;
        textPaint.setColor(textColor);
    }

    public void setTextSize(int sp) {
        textPaint.setTextSize(sp * scaled);
    }

    public void setHandle(WavechartHandle handle) {
        lines = handle;
    }

    public void enableLine(boolean enable) {
        DRAW_LINE = enable;
    }

    public void enablePoint(boolean enable) {
        DRAW_POINT = enable;
    }

    public void enableAnima(boolean enable) {
        DRAW_ANIMA = enable;
    }

    public void enableAnimaTail(boolean enable) {
        DRAW_ANIMATAIL = enable;
    }

    public void enableGridline(boolean enable) {
        DRAW_GRIDLINE = enable;
    }

    public boolean isAnima() {
        return ON_ANIMATOR;
    }

    public void startAnima() {
        startAnima(0);
    }

    public void startAnima(int delay) {
        if (!DRAW_ANIMA) return;
        ON_ANIMATOR = true;
        lines.allsetFrame(frameRate);
        removeCallbacks(animator);
        if (delay == 0) post(animator);
        else postDelayed(animator, delay);
    }
}
