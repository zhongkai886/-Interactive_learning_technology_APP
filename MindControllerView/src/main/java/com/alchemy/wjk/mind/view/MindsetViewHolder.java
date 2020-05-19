package com.alchemy.wjk.mind.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alchemy.wjk.mind.widget.PieView;
import com.alchemy.wjk.mind.widget.WavechartHandle;
import com.alchemy.wjk.mind.widget.WavechartView;

public class MindsetViewHolder {

    public TextView stateText;
    public Button connectBut;
    public PieView pieLeft, pieRight;
    public WavechartView chart;
    public WavechartHandle chartHandle;
    public ProgressBar reading;
    public ImageView backBut;


    public MindsetViewHolder(View v) {
        stateText = (TextView) v.findViewById(R.id.wjk_mindset_activity_logText);
        connectBut = (Button) v.findViewById(R.id.wjk_mindset_activity_btnDialog);
        pieLeft = (PieView) v.findViewById(R.id.wjk_mindset_activity_pieLeft);
        pieRight = (PieView) v.findViewById(R.id.wjk_mindset_activity_pieRight);
        chart = (WavechartView) v.findViewById(R.id.wjk_mindset_activity_chartMind);
        reading = (ProgressBar) v.findViewById(R.id.wjk_mindset_activity_progressReady);
        backBut = (ImageView) v.findViewById(R.id.wjk_mindset_activity_back);
    }

    public MindsetViewHolder(Activity v) {
        stateText = (TextView) v.findViewById(R.id.wjk_mindset_activity_logText);
        connectBut = (Button) v.findViewById(R.id.wjk_mindset_activity_btnDialog);
        pieLeft = (PieView) v.findViewById(R.id.wjk_mindset_activity_pieLeft);
        pieRight = (PieView) v.findViewById(R.id.wjk_mindset_activity_pieRight);
        chart = (WavechartView) v.findViewById(R.id.wjk_mindset_activity_chartMind);
        reading = (ProgressBar) v.findViewById(R.id.wjk_mindset_activity_progressReady);
        backBut = (ImageView) v.findViewById(R.id.wjk_mindset_activity_back);

        initChartView(chart);
        chartHandle = new WavechartHandle();
        chart.setHandle(chartHandle);

        initPieView(pieLeft);
        pieLeft.setPieBackground(0xffffaaaa);

        initPieView(pieRight);
        pieRight.setPieBackground(0xffaaffaa);
    }

    private void initPieView(PieView pie) {
        pie.enableAnime(true);
        pie.enablePie(false);
        pie.enableCalibration(true);
        pie.setPointerMax(100);
        pie.setTextsizeDPI(8);
    }

    private void initChartView(WavechartView chart) {
        chart.setRangeHeight(200000);
        chart.setRangeWidth(10);
        chart.setPaintSizePX(3);
        chart.enablePoint(false);
        chart.enableLine(true);
    }
}
