package com.example.user.interactive_learning_technology_app.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

public class ProgressbarTip extends ProgressBar {
    //TODO 成員
    //畫筆
    Paint tipPaint;
    Paint.FontMetrics tipFM;
    //文字
    float fontBase;
    float fontHeight;
    float density;
    int textSize = 15;
    int textColor = 0xff000000;
    //旗子
    boolean FLAG_TEXTPERCENT = true;

    //TODO 建構式
    public ProgressbarTip(Context context) {
        this(context, null);
    }

    public ProgressbarTip(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.progressBarStyleHorizontal);
    }

    public ProgressbarTip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //layout.xml 屬性參數
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        density = dm.density;
        final Resources.Theme theme = context.getTheme();
        int[] attrsArray = new int[]{
                android.R.attr.textSize,
                android.R.attr.textColor};
        TypedArray ta = theme.obtainStyledAttributes(
                attrs, attrsArray, defStyleAttr, 0);
        textSize = ta.getDimensionPixelSize(0, (int) (textSize * dm.scaledDensity));
        textColor = ta.getColor(1, textColor);
        ta.recycle();
        //畫筆
        tipPaint = new Paint();
        tipPaint.setTextSize(textSize);
        tipPaint.setColor(textColor);
        tipFM = new Paint.FontMetrics();
        tipPaint.getFontMetrics(tipFM);
        tipPaint.setTextAlign(Paint.Align.RIGHT);
        tipPaint.setSubpixelText(true);
        tipPaint.setAntiAlias(true);
        //文字高度
        fontHeight = tipFM.bottom - tipFM.top;
        fontBase = -tipFM.top;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT ||
                getMeasuredHeight() < fontHeight) {//當自訂時設置最小長寬
            int pieHeight = (int) fontHeight;
            setMeasuredDimension(getMeasuredWidth(), pieHeight);
        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //進度
        float progress = (float) getProgress() / getMax();
        int vw = getWidth();
        //文字測量
        String text;
        if (FLAG_TEXTPERCENT) text = String.format("%d%%", (int) (progress * 100 + 0.5));
        else text = "" + getProgress();
        float textX = vw * progress;
        float textWidth = tipPaint.measureText(text);
        //繪圖
        if ((textX + density * 8) > textWidth)
            canvas.drawText(text, textX - density * 4, fontBase, tipPaint);
        else {
            tipPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(text, density * 4, fontBase, tipPaint);
        }

    }

    public void showTextPercent(boolean flag) {
        FLAG_TEXTPERCENT = flag;
    }

}
