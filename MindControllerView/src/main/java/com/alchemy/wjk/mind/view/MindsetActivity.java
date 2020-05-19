package com.alchemy.wjk.mind.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.alchemy.mindcontroller.MindController;
import com.alchemy.mindcontroller.MindControllerFactory;
import com.alchemy.mindcontroller.MindsetValue;
import com.alchemy.wjk.mind.widget.WavechartHandle;

public final class MindsetActivity extends Activity implements View.OnClickListener {

    private static final String KEY_STATE_ADDRESS = "key_state_address";
    public static final String KEY_SHOW_BACK = "key_show_back";

    private MindsetViewHolder mViewHolder;
    private boolean mRaw;

    private MindController mCenter;
    private WavechartHandle line1, line2, line3, line4, line5, line6, line7, line8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wjk_mindset_activity);

        MindsetViewHolder vh = new MindsetViewHolder(this);
        mViewHolder = vh;

        vh.connectBut.setOnClickListener(this);
        line1 = vh.chartHandle.createLine(10);
        line1.setLineColor(0xff4D4D4D);
        line2 = vh.chartHandle.createLine(10);
        line2.setLineColor(0xffFF81FF);
        line3 = vh.chartHandle.createLine(10);
        line3.setLineColor(0xffFF0000);
        line4 = vh.chartHandle.createLine(10);
        line4.setLineColor(0xffFF8080);
        line5 = vh.chartHandle.createLine(10);
        line5.setLineColor(0xff00CD00);
        line6 = vh.chartHandle.createLine(10);
        line6.setLineColor(0xff80FF80);
        line7 = vh.chartHandle.createLine(10);
        line7.setLineColor(0xff0000FF);
        line8 = vh.chartHandle.createLine(10);
        line8.setLineColor(0xff8080FF);


        mCenter = MindControllerFactory.obtain(getApplicationContext(), new Handler(mindMail));
        if (savedInstanceState == null) {
        } else {
            mViewHolder.connectBut.setText(savedInstanceState.getString(KEY_STATE_ADDRESS));
        }
        mCenter.start();

        Intent intent = getIntent();
        if (intent != null) if (intent.getBooleanExtra(KEY_SHOW_BACK, false)) {
            vh.backBut.setVisibility(View.VISIBLE);
            vh.backBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        ApplicationInfo info;
        try {
            info = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            mRaw = info.metaData.getBoolean("com.alchemy.wjk.mind.view.MindsetActivity.raw");
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCenter != null) mCenter.pause(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_STATE_ADDRESS, mViewHolder.connectBut.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCenter != null) mCenter.pause(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCenter != null) mCenter.stop();
    }

    @Override
    public void onClick(View v) {
        RemoteDeviceDialog dialog = RemoteDeviceDialog.newInstance();
        dialog.setListener(new RemoteDeviceDialog.Listener() {
            @Override
            public void onSelectAddress(String address) {
                mCenter.rawData(mRaw);
                mCenter.connect(address);
                mViewHolder.connectBut.setText(address);
            }
        });
        dialog.show(getFragmentManager(), RemoteDeviceDialog.TAG);
    }

    Handler.Callback mindMail = new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            int w = msg.what;
            int a1 = msg.arg1;

            switch (w) {
                case MindsetValue.MSG_STATE_CHANGE:
                    switch (a1) {
                        case MindsetValue.STATE_IDLE:
                            mViewHolder.connectBut.setText(R.string.wjk_mindset_activity_string_btnDialog);
                            setResult(Activity.RESULT_CANCELED);
                            mViewHolder.reading.setVisibility(View.GONE);
                            mViewHolder.stateText.setText(R.string.wjk_mindset_activity_string_statelink_none);
                            break;
                        case MindsetValue.STATE_CONNECTED:
                            mViewHolder.stateText.setText(R.string.wjk_mindset_activity_string_statelink_connected);

                            setResult(Activity.RESULT_OK);
                            break;
                        case MindsetValue.STATE_CONNECTING:
                            mViewHolder.stateText.setText(R.string.wjk_mindset_activity_string_statelink_connecting);
                            break;
                    }
                    break;
                case MindsetValue.MSG_POOR_SIGNAL:
                    if (a1 == 0)
                        mViewHolder.reading.setVisibility(View.GONE);
                    else
                        mViewHolder.reading.setVisibility(View.VISIBLE);
                    break;
                case MindsetValue.MSG_ATTENTION:
                    mViewHolder.pieLeft.setPointerAnime(a1);
                    mViewHolder.pieLeft.startAnime();
                    break;
                case MindsetValue.MSG_MEDITATION:
                    mViewHolder.pieRight.setPointerAnime(a1);
                    mViewHolder.pieRight.startAnime();
                    break;
                case MindsetValue.MSG_EEG_DELTA:
                    line1.put(a1);
                    break;
                case MindsetValue.MSG_EEG_HIGHALPHA:
                    line3.put(a1);
                    break;
                case MindsetValue.MSG_EEG_LOWALPHA:
                    line4.put(a1);
                    break;
                case MindsetValue.MSG_EEG_HIGHBETA:
                    line6.put(a1);
                    break;
                case MindsetValue.MSG_EEG_LOWBETA:
                    line5.put(a1);
                    break;
                case MindsetValue.MSG_EEG_LOWGAMMA:
                    line7.put(a1);
                    break;
                case MindsetValue.MSG_EEG_MIDGAMMA:
                    line8.put(a1);
                    break;
                case MindsetValue.MSG_EEG_THETA:
                    line2.put(a1);
                    break;
                case MindsetValue.MSG_EEG_POWER:
                    mViewHolder.chart.invalidate();
                    break;
            }

            return false;
        }
    };

}