package com.alchemy.wjk.mind.view;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RemoteDeviceViewHolder {

    public TextView scanText;
    public ListView pairList, scanList;
    public Button scanBut;

    public RemoteDeviceViewHolder(View v) {
        pairList = (ListView) v.findViewById(R.id.wjk_mindset_dialog_pairedDevices);
        scanList = (ListView) v.findViewById(R.id.wjk_mindset_dialog_scanDevices);
        scanBut = (Button) v.findViewById(R.id.wjk_mindset_dialog_btnScan);
        scanText = (TextView) v.findViewById(R.id.wjk_mindset_dialog_scanDevicesTextbar);

    }
}
