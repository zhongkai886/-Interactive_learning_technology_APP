package com.alchemy.wjk.mind;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.alchemy.mindcontroller.MindsetValue;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MindsetBluetooth {
    //固定藍芽模組
    private static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //藍芽連接狀態
    public static final int STATE_IDLE = MindsetValue.STATE_IDLE; // 不動作
    public static final int STATE_CONNECTING = MindsetValue.STATE_CONNECTING; // 正在傳輸連結
    public static final int STATE_CONNECTED = MindsetValue.STATE_CONNECTED; // 已連結設備
    public static final int STATE_DISCONNECTED = MindsetValue.STATE_DISCONNECTED; // 已連結設備
    //藍芽緩衝大小
    private static final int BUFFER_SIZE = 0x80;

    //TODO ====================|callback|====================
    public interface CallBack{
        void onState(int state);
        void onRAW(int raw);
        void onEEG(int signal, int attention, int meditation,
                   int delta, int theta, int lowalpha, int highalpha,
                   int lowbeta, int highbeta, int lowgamma, int midgamma);
    }

    //TODO ====================|var|====================
    private CallBack mCallBack;
    private volatile ConnectThread mConnectThread;
    private volatile ConnectedThread mConnectedThread;
    private volatile int mState;
    private volatile MindsetBuffer mBuffer;

    public MindsetBluetooth(CallBack cb) {
        mState = STATE_IDLE;
        mBuffer = new MindsetBuffer(BUFFER_SIZE, false);
        mCallBack = cb;
    }

    //TODO ====================|method|====================
    public void connect(BluetoothDevice device) {
        if (mConnectThread != null) {mConnectThread.cancel();}
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
    }

    private void connected(BluetoothSocket socket) {
        if (mConnectThread != null) {mConnectThread = null;}

        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();
    }

    public void disconnect() {
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        state(STATE_IDLE);
    }

    private void state(int state){
        mState = state;
        if (mCallBack != null) {mCallBack.onState(state);}
    }

    //TODO ====================|getset|====================

    public void pause(boolean enable){
        mBuffer.pause(enable);
    }

    public void rawData(boolean enable){
        mBuffer.rawData(enable);
    }

    public int state(){
        return mState;
    }

    //TODO ====================|ConnectThread|====================
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;


        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket temp = null;
            try { temp = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID);
            } catch (IOException e) { e.printStackTrace();}
            mmSocket = temp;
        }

        public void run() {
            if(mmSocket == null) return;

            state(STATE_CONNECTING);
            try {
                mmSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
                try { mmSocket.close();
                } catch (IOException e2) { e2.printStackTrace();}
                state(STATE_DISCONNECTED);
                return;
            }

            connected(mmSocket);
        }

        public void cancel() {
            if(mmSocket == null) return;

            try { mmSocket.close();
            } catch (IOException e) { e.printStackTrace();}
        }

    }

    //TODO ====================|ConnectedThread|====================
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;

            InputStream temp = null;
            try { temp = socket.getInputStream();
            } catch (IOException e) { e.printStackTrace();}
            mmInStream = temp;
        }

        public void run() {
            MindsetBuffer buffer = mBuffer;
            buffer.inputStream(mmInStream);

            state(STATE_CONNECTED);
            try {
                while (true) {
                    buffer.read();
                    while(buffer.verify()){
                        if(buffer.isRaw())mCallBack.onRAW(buffer.raw_data);
                        else mCallBack.onEEG(buffer.poor_signal, buffer.attention, buffer.meditation,
                                buffer.eeg_delta, buffer.eeg_theta, buffer.eeg_lowalpha, buffer.eeg_highalpha,
                                buffer.eeg_lowbeta, buffer.eeg_highbeta, buffer.eeg_lowgamma, buffer.eeg_midgamma);
                    }
                }
            } catch (IOException e) { e.printStackTrace();}

            state(STATE_DISCONNECTED);
        }

        public void cancel() {
            try {
                mmInStream.close();
                mmSocket.close();
            } catch (IOException e) { e.printStackTrace();}
        }
    }

}
