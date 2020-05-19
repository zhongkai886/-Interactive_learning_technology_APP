package com.alchemy.wjk.mind.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Dacer on 11/13/13.
 */
public class PieView extends View {

    //畫筆
    private Paint textPaint = new Paint();//圓餅上的數據文字用
    private Paint piePaint;//圓餅用
    private Paint piePoterPaint;//圓餅指針用
    private Paint piebglPaint;//背景用
    private Paint piebgPaint;//背景用
    private Paint calibrationPaint;//背景刻度
    //刻度背景
    int calibrationMax = 330;
    int calibrationCount = 10;
    int[] calibrationL1X, calibrationL1Y, calibrationL2X, calibrationL2Y, calibrationTX, calibrationTY;
    //動畫
    int frameRate = 60;
    int frameSpeed = 1000 / frameRate;
    //圓餅文字
    private float textSize;
    private int textColor = 0xff9B9A9B;
    //繪製圓餅尺寸
    float radiusBGL, radiusT, radiusC;
    private float pointer, pointerGoal, pointerMax = 330, pointerDegree = 1;
    private int pieRadius;
    private Point pieCenterPoint = new Point();
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    private RectF cirRect = new RectF();
    //計算布局用
    private float textWidth;
    private float paintSize;
    private float fontHeight;
    private final float display, scale;
    //數據資料
    PieHelper helper;
    //默認圓餅背景顏色
    private int piebgColor = 0xffD4D3D4;
    private int piebglColor = 0xffA0A0A0;
    //旗標
    private boolean FLAG_PIE = true, FLAG_ANIME = false, FLAG_CALIBRATION = false;
    private boolean ON_ANIMATOR;

    //指標動畫
    private Runnable animator = new Runnable() {
        @Override
        public void run() {
            if (pointer != pointerGoal)
                if (pointer > pointerGoal) {
                    pointer += pointerDegree;
                    if (pointer > pointerGoal) {
                        postDelayed(this, frameSpeed);
                    } else {
                        pointer = pointerGoal;
                        ON_ANIMATOR = false;
                    }
                } else {
                    pointer += pointerDegree;
                    if (pointer < pointerGoal) {
                        postDelayed(this, frameSpeed);
                    } else {
                        pointer = pointerGoal;
                        ON_ANIMATOR = false;
                    }
                }
            else ON_ANIMATOR = false;
            invalidate();
        }
    };

    //TODO 建構式
    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //文字尺寸計算
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        display = dm.density;
        scale = dm.scaledDensity;
        textSize = 24 * scale;
        paintSize = 2 * display;

        //系統字體
        final Resources.Theme theme = context.getTheme();
        int[] attrsArray = new int[]{
                android.R.attr.textSize
        };
        TypedArray a = theme.obtainStyledAttributes(attrs, attrsArray, defStyle, 0);
        textSize = a.getDimensionPixelSize(0, (int) textSize);
        a.recycle();

        //畫筆設定
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.getFontMetrics(mFontMetrics);

        piePaint = new Paint(textPaint);

        piePoterPaint = new Paint();
        piePoterPaint.setColor(piebgColor ^ 0x00ffffff);
        piePoterPaint.setStrokeWidth(paintSize);

        piebglPaint = new Paint();
        piebglPaint.setColor(piebglColor);
        piebglPaint.setStrokeWidth(paintSize);
        piebglPaint.setStyle(Style.STROKE);

        piebgPaint = new Paint();
        piebgPaint.setColor(piebgColor);

        calibrationPaint = new Paint();
        calibrationPaint.setColor(piebgColor ^ 0x00ffffff);
        calibrationPaint.setStrokeWidth(display);
        calibrationPaint.setStyle(Style.STROKE);


        //計算圓餅文字寬度
        textMeasure();
    }

    //TODO 繼承項
    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLayoutParams().width == LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == LayoutParams.WRAP_CONTENT) {//當自訂時設置最小長寬
            int pieWidth = (int) textWidth * 8;
            setMeasuredDimension(pieWidth, pieWidth);
        } else if (getLayoutParams().width == LayoutParams.MATCH_PARENT &&
                getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            int mw = getMeasuredWidth();
            setMeasuredDimension(mw, mw);
        } else if (getLayoutParams().width == LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == LayoutParams.MATCH_PARENT) {
            int mh = getMeasuredHeight();
            setMeasuredDimension(mh, mh);
        } else {
            int mh = getMeasuredHeight();
            int mw = getMeasuredWidth();
            int s = (mh < mw) ? mh : mw;
            setMeasuredDimension(s, s);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int mViewWidth = getMeasuredWidth();
        int mViewHeight = getMeasuredHeight();

        //計算元件半徑，中心，矩形
        pieRadius = mViewWidth > mViewHeight ? mViewHeight / 2 : mViewWidth / 2;
        pieRadius -= (paintSize);
        pieCenterPoint.set(mViewWidth / 2, mViewHeight / 2);
        cirRect.set(pieCenterPoint.x - pieRadius,
                pieCenterPoint.y - pieRadius,
                pieCenterPoint.x + pieRadius,
                pieCenterPoint.y + pieRadius);
        calcRadius();
        calcCalibration();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景
        canvas.drawCircle(pieCenterPoint.x, pieCenterPoint.y, radiusBGL, piebglPaint);
        canvas.drawCircle(pieCenterPoint.x, pieCenterPoint.y, pieRadius, piebgPaint);
        if (helper != null && FLAG_PIE) {
            //圓餅
            for (int k = 0; k < helper.length; k++) {
                piePaint.setColor(helper.colors[k]);
                textPaint.setColor(helper.colors[k] ^ 0x00ffffff);
                canvas.drawArc(cirRect, helper.startAngle[k], helper.sweepAngle[k], true, piePaint);

                float x = (float) calcCoordX(pieCenterPoint.x, helper.textAngle[k], radiusT);
                float y = (float) calcCoordY(pieCenterPoint.y, helper.textAngle[k], radiusT);
                canvas.drawText(helper.dataName[k], x, y + fontHeight, textPaint);
            }
        }
        if (FLAG_CALIBRATION) {
            canvas.drawArc(cirRect, 0, calibrationMax, true, calibrationPaint);
            canvas.drawCircle(pieCenterPoint.x, pieCenterPoint.y, radiusC, piebgPaint);
            textPaint.setColor(piebgColor ^ 0x00ffffff);

            for (int i = 0; i <= calibrationCount; i++) {
                float v = pointerMax * (float) i / calibrationCount;

                int xt = calibrationTX[i];
                int yt = calibrationTY[i];
                int x1 = calibrationL1X[i];
                int y1 = calibrationL1Y[i];
                int x2 = calibrationL2X[i];
                int y2 = calibrationL2Y[i];
                canvas.drawText("" + (int) v, xt, yt + fontHeight, textPaint);
                canvas.drawLine(x1, y1, x2, y2, calibrationPaint);
            }
        }
        if (FLAG_ANIME) {
            float x = (float) calcCoordX(pieCenterPoint.x, pointer, radiusT);
            float y = (float) calcCoordY(pieCenterPoint.y, pointer, radiusT);
            canvas.drawLine(pieCenterPoint.x, pieCenterPoint.y, x, y, piePoterPaint);
        }
    }

    //TODO 內部項
    private void textMeasure() {
        String longest = "M";
        if (helper != null) longest = helper.dataNamelongest;
        textWidth = textPaint.measureText(longest);
        textPaint.getFontMetrics(mFontMetrics);
        fontHeight = (mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom;
    }

    private double calcCoordX(double offset, double angle, double radius) {
        return (offset + Math.cos(Math.toRadians(angle)) * radius);
    }

    private double calcCoordY(double offset, double angle, double radius) {
        return (offset + Math.sin(Math.toRadians(angle)) * radius);
    }

    private void calcCalibration() {
        int s = calibrationCount + 1;
        calibrationTX = new int[s];
        calibrationTY = new int[s];
        calibrationL1X = new int[s];
        calibrationL1Y = new int[s];
        calibrationL2X = new int[s];
        calibrationL2Y = new int[s];
        for (int i = 0; i < s; i++) {
            float d = (float) calibrationMax * i / calibrationCount;
            calibrationTX[i] = (int) calcCoordX(pieCenterPoint.x, d, radiusT);
            calibrationTY[i] = (int) calcCoordY(pieCenterPoint.y, d, radiusT);
            calibrationL1X[i] = (int) calcCoordX(pieCenterPoint.x, d, pieRadius);
            calibrationL1Y[i] = (int) calcCoordY(pieCenterPoint.y, d, pieRadius);
            calibrationL2X[i] = (int) calcCoordX(pieCenterPoint.x, d, radiusC);
            calibrationL2Y[i] = (int) calcCoordY(pieCenterPoint.y, d, radiusC);

        }
    }

    private void calcRadius() {
        radiusBGL = pieRadius + paintSize / 2;
        radiusT = pieRadius - (textWidth * 2);
        radiusC = pieRadius - 4 * display;
    }

    //TODO 外部項
    //傳入園餅數據
    public void setDate(PieHelper helperList) {
        helper = helperList;
        textMeasure();
    }

    public void setTextsizeDPI(int dpi) {
        textSize = dpi * scale;
        textPaint.setTextSize(textSize);
        textMeasure();
    }

    public void setTextsize(int px) {
        textSize = px;
        textPaint.setTextSize(textSize);
        textMeasure();
    }

    public void setTextColor(int color) {
        textColor = color;
        textPaint.setColor(textColor);
    }

    public void setPieBackground(int color) {
        piebgColor = color;
        piebgPaint.setColor(piebgColor);
        piePoterPaint.setColor(piebgColor ^ 0x00ffffff);
        calibrationPaint.setColor(piebgColor ^ 0x00ffffff);
    }

    public void setPieBackgroundLine(int color) {
        piebglColor = color;
        piebglPaint.setColor(piebglColor);
    }

    public void setPointerMax(float v) {
        if (v < 0) v = 0;
        pointerMax = v;
    }

    public void setPointer(float v) {
        if (v < 0) v = 0;
        else if (v > pointerMax) v = pointerMax;
        pointerGoal = calibrationMax * v / pointerMax;
        pointer = pointerGoal;
        pointerDegree = 0;
    }

    public void setPointerAnime(float v) {
        if (v < 0) v = 0;
        else if (v > pointerMax) v = pointerMax;
        pointerGoal = calibrationMax * v / pointerMax;
        pointerDegree = (pointerGoal - pointer) / frameRate;
    }

    public boolean isAnimetor() {
        return ON_ANIMATOR;
    }

    public void enableAnime(boolean e) {
        FLAG_ANIME = e;
    }

    public void enablePie(boolean e) {
        FLAG_PIE = e;
    }

    public void enableCalibration(boolean e) {
        FLAG_CALIBRATION = e;
    }

    public void startAnime() {
        if (!ON_ANIMATOR) {
            ON_ANIMATOR = true;
            post(animator);
        }
    }
}
