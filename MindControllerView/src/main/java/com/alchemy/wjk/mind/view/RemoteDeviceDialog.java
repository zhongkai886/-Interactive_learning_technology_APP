package com.alchemy.wjk.mind.view;

import java.util.Set;

import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class RemoteDeviceDialog extends DialogFragment implements
        View.OnClickListener, AdapterView.OnItemClickListener {

    //====================[static instance]====================
    public static final String TAG = RemoteDeviceDialog.class.getName();

    public static RemoteDeviceDialog newInstance() {
        return new RemoteDeviceDialog();
    }

    //====================[BroadcastReceiver]====================
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {//搜尋設備
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//    			if (device.getBondState() != BluetoothDevice.BOND_BONDED)已在列表則不增加
                mScanAdapter.add(listItemtext(device.getName(), device.getAddress()));
//                if (device.getName() != null && device.getName().equals("alchemy")||device.getName().equals("Alchemy")) {
//                    mScanAdapter.add(listItemtext(device.getName(), device.getAddress()));
//                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {//搜尋設備結束
                if (mScanAdapter.getCount() == 0)
                    mScanAdapter.add(getResources().getString(R.string.wjk_mindset_dialog_string_notfound));

                mViewHolder.scanBut.setEnabled(true);
                mViewHolder.scanBut.setText(R.string.wjk_mindset_dialog_string_startsearch);
            }
        }
    };

    private String listItemtext(String name, String address) {
        return name + "\n" + address;
    }

    //====================[cycle method]====================
    private static final String KEY_STATE_SCAN_DATA = "key_state_scan_data";

    private RemoteDeviceViewHolder mViewHolder;
    private ArrayAdapter<String> mPairAdapter, mScanAdapter;
    private BluetoothAdapter mBtAdapter;
    private int iListviewHeightMax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScanAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        mPairAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        //檢查藍芽
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter != null) {
            Set<BluetoothDevice> devices = mBtAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                if (device.getName() != null && device.getName().equals("alchemy")||device.getName().equals("Alchemy")) {
                    mPairAdapter.add(listItemtext(device.getName(), device.getAddress()));
                }
//                mPairAdapter.add(listItemtext(device.getName(), device.getAddress()));
            }
            if (!mBtAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 1);
            }
        } else {
            Toast.makeText(getActivity(), R.string.wjk_mindset_dialog_string_unbluetooth, Toast.LENGTH_SHORT).show();
        }

        if (savedInstanceState != null) {
            String[] data = savedInstanceState.getStringArray(KEY_STATE_SCAN_DATA);
            for (int i = 0; i < data.length; i++) {
                mScanAdapter.add(data[i]);
            }
        }

        //計算畫面長度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        iListviewHeightMax = dm.heightPixels / 3;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wjk_mindset_dialog, container, false);
        mViewHolder = new RemoteDeviceViewHolder(v);

        mViewHolder.scanBut.setOnClickListener(this);
        if (mScanAdapter.getCount() > 0 && mViewHolder.scanText.getVisibility() != View.VISIBLE)
            mViewHolder.scanText.setVisibility(View.VISIBLE);

        //newlist
        mViewHolder.scanList.setAdapter(mScanAdapter);
        mViewHolder.scanList.setOnItemClickListener(this);
        //pairlist
        mViewHolder.pairList.setAdapter(mPairAdapter);
        mViewHolder.pairList.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        getActivity().registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mReceiver);
        if (mBtAdapter != null) {//關閉搜尋
            mBtAdapter.cancelDiscovery();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] data = new String[mScanAdapter.getCount()];
        for (int i = 0; i < data.length; i++) {
            data[i] = mScanAdapter.getItem(i);
        }
        outState.putStringArray(KEY_STATE_SCAN_DATA, data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
            if (btAdapter != null) {
                Set<BluetoothDevice> devices = btAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    mPairAdapter.add(listItemtext(device.getName(), device.getAddress()));
                }
            }
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

    //====================[view listener]====================
    @Override
    public void onClick(View v) {
        if (v == mViewHolder.scanBut) {
            if (mViewHolder.pairList.getHeight() > iListviewHeightMax) {
                LayoutParams lp = (LayoutParams) mViewHolder.pairList.getLayoutParams();
                lp.height = iListviewHeightMax;
            }

            if (mBtAdapter != null) {
                if (!mBtAdapter.isDiscovering()) {
                    mBtAdapter.startDiscovery();
                    mScanAdapter.clear();
                    mViewHolder.scanBut.setEnabled(false);
                    mViewHolder.scanBut.setText(R.string.wjk_mindset_dialog_string_scaning);
                }
            }
            if (mViewHolder.scanText.getVisibility() != View.VISIBLE)
                mViewHolder.scanText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = ((TextView) view).getText().toString();
        String address = text.substring(text.length() - 17);

        if (l != null) l.onSelectAddress(address);
        dismiss();
    }

    //====================[dialog listener]====================
    private Listener l;

    public void setListener(Listener l) {
        this.l = l;
    }

    public static interface Listener {
        public void onSelectAddress(String address);
    }
}
