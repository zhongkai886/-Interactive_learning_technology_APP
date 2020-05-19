package com.alchemy.wjk.mind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.alchemy.mindcontroller.MindController;

public class MindControllerImpl implements MindController {

    Context mContext;
    Messenger mClientMessenger;
    Messenger mServiceMessenger;
    boolean pause_modify = false;
    boolean pause = false;
    boolean rawData_modify = false;
    boolean rawData = false;
    boolean register = false;
    boolean debug_modify = false;
    boolean debug = false;
    int state = STATE_STOP;

    public MindControllerImpl(Context context, Handler handler){
        mContext = context;
        if(handler != null){
            mClientMessenger = new Messenger(handler);
            register = true;
        }else{
            register = false;
        }

    }

    @Override
    public void start() {
        Intent intent = new Intent(mContext, MindsetService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void stop() {
        unregister();
        mContext.unbindService(mServiceConnection);
    }

    @Override
    public void pause(boolean enable) {
        pause = enable;
        if(mServiceMessenger == null){
            pause_modify = true;
            return;
        }
        state = pause ? STATE_PAUSE : STATE_START;

        Message msg = Message.obtain(null, MindsetService.MSG_PAUSE);
        msg.arg1 = enable? MindsetService.FLAG_PAUSE:-1;
        sendService(msg);

    }

    @Override
    public int state() {
        int s = state;
        if(mClientMessenger == null)return s;
        if(mServiceMessenger == null)return s;

        Message msg = Message.obtain(null, MindsetService.MSG_STATE);
        msg.replyTo = mClientMessenger;
        sendService(msg);
        return s;
    }

    @Override
    public void connect(String address) {
        if(mServiceMessenger == null)return;

        Message msg = Message.obtain(null, MindsetService.MSG_CONNECT);
        msg.obj = address;
        sendService(msg);
    }

    @Override
    public void disconnect() {
        if(mServiceMessenger == null)return;

        Message msg = Message.obtain(null, MindsetService.MSG_DISCONNECT);
        sendService(msg);
    }

    @Override
    public void register() {
        register = true;
        if(mClientMessenger == null)return;
        if(mServiceMessenger == null)return;

        Message msg = Message.obtain(null, MindsetService.MSG_REGISTER);
        msg.replyTo = mClientMessenger;
        sendService(msg);
    }

    @Override
    public void unregister() {
        register = false;
        if(mClientMessenger == null)return;
        if(mServiceMessenger == null)return;

        Message msg = Message.obtain(null, MindsetService.MSG_UNREGISTER);
        msg.replyTo = mClientMessenger;
        sendService(msg);
    }

    @Override
    public void rawData(boolean enable) {
        rawData = enable;
        if(mServiceMessenger == null){
            rawData_modify = true;
            return ;
        }

        Message msg = Message.obtain(null, MindsetService.MSG_RAWDATA);
        msg.arg1 = enable? MindsetService.FLAG_RAWDATA:-1;
        sendService(msg);
    }

    @Override
    public void DEBUG(boolean enable) {
        debug = enable;
        if(mServiceMessenger == null){
            debug_modify = true;
            return;
        }

        Message msg = Message.obtain(null, MindsetService.MSG_DEBUG);
        msg.arg1 = enable? 1 : -1;
        sendService(msg);
    }

    //TODO ==========|Method|==========
    void sendClient(Message msg){
        if(mClientMessenger == null)return;

        try {mClientMessenger.send(msg);
        } catch (RemoteException e) {
            mClientMessenger = null;
            e.printStackTrace();
        }
    }

    void sendService(Message msg){
        if(mServiceMessenger == null)return;

        try {mServiceMessenger.send(msg);}
        catch (RemoteException e) {
            mServiceMessenger = null;
            e.printStackTrace();
        }
    }

    //TODO ==========|ServiceConnection|==========
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServiceMessenger = new Messenger(iBinder);
            state = STATE_START;

            if(register)register();
            if(pause_modify)pause(pause);
            if(rawData_modify)rawData(rawData);
            if(debug_modify)DEBUG(debug);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mServiceMessenger = null;
            state = STATE_STOP;
        }
    };

}
