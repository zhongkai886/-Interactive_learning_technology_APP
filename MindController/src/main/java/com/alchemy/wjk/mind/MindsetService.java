package com.alchemy.wjk.mind;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.alchemy.mindcontroller.MindsetValue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * <strong>MatchService in MatchMindwave</strong><br/>
 * 與耳機進行連接配對的背景服務，主要方法可透過 ServiceCenter 使用
 * @author Jun-kia Wang
 * @Date 2014/10/22 下午4:04:32 TODO
 */
public class MindsetService extends Service implements MindsetBluetooth.CallBack{

// TODO 靜態旗子
	// Message what
	public static final int MSG_CONNECT = 0x01;
	public static final int MSG_DISCONNECT = 0x04;
	public static final int MSG_REGISTER = 0x08;
	public static final int MSG_UNREGISTER = 0x09;
	public static final int MSG_STATE = 0x11;
	public static final int MSG_RAWDATA = 0x07;
	public static final int MSG_PAUSE = 0x05;
	public static final int MSG_DEBUG = 0x44;

	// Flag what
	public static final int FLAG_RAWDATA = 0x0A;
	public static final int FLAG_PAUSE = 0x0B;

// TODO 成員
	private Messenger mMessenger;
	
	private BluetoothAdapter mBluetoothAdapter;
	private MindsetBluetooth mMindsetBluetooth;

	
	private Set<Messenger> clientSet = new HashSet<Messenger>();
	
// TODO Service
	@Override
	public void onCreate() {
		//伺服器端信箱
		mMessenger = new Messenger(mHandler);
		//腦波耳機藍芽
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mMindsetBluetooth = new MindsetBluetooth(this);
	}
	@Override
	public IBinder onBind(Intent intent) {//綁定處理
		return mMessenger.getBinder();
	}
	@Override
	public void onDestroy() {
		mMindsetBluetooth.disconnect();
	}

// TODO method
	//寄發所有客戶端
	private void sendClients(Message msg){
		for(Messenger mer : clientSet){
			try {
				msg = Message.obtain(msg);
				mer.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
				clientSet.remove(mer);
			}
		}
	}

	//寄發單一客戶端
	private void sendClient(Messenger mer, Message msg){
		try {
			mer.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
			clientSet.remove(mer);
		}
	}
	
// TODO 伺服器服務端Handler
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_REGISTER:
				clientSet.add(msg.replyTo);
				break;
				
			case MSG_UNREGISTER:
				clientSet.remove(msg.replyTo);
				break;
				
			case MSG_CONNECT:
				BluetoothDevice device = mBluetoothAdapter.getRemoteDevice((String)msg.obj);
				mMindsetBluetooth.connect(device);
				break;
				
			case MSG_DISCONNECT:
				mMindsetBluetooth.disconnect();
				break;

			case MSG_STATE:
				Message msgr = Message.obtain(null, MindsetValue.MSG_STATE_CHANGE);
				msgr.arg1 = mMindsetBluetooth.state();
				sendClient(msg.replyTo, msgr);
				break;

			case MSG_RAWDATA:
				mMindsetBluetooth.rawData(msg.arg1 == FLAG_RAWDATA);
				break;

			case MSG_PAUSE:
				mMindsetBluetooth.pause(msg.arg1 == FLAG_PAUSE);
				break;

			case MSG_DEBUG:
				debug(msg.arg1>0);
				break;
			}
		}
	};

// TODO MindsetBluetooth.CallBack

	@Override
	public void onState(int state) {
		Message msg = Message.obtain(null, MindsetValue.MSG_STATE_CHANGE);
		msg.arg1 = state;
		sendClients(msg);
	}

	@Override
	public void onRAW(int raw) {
		Message msg = Message.obtain(null, MindsetValue.MSG_RAW_DATA);
		msg.arg1 = raw;
		sendClients(msg);
	}

	@Override
	public void onEEG(int signal, int attention, int meditation,
					  int delta, int theta, int lowalpha, int highalpha,
					  int lowbeta, int highbeta, int lowgamma, int midgamma) {
		Message msg;
		msg = Message.obtain(null, MindsetValue.MSG_POOR_SIGNAL);
		msg.arg1 = signal;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_ATTENTION);
		msg.arg1 = attention;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_MEDITATION);
		msg.arg1 = meditation;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_DELTA);
		msg.arg1 = delta;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_THETA);
		msg.arg1 = theta;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_LOWALPHA);
		msg.arg1 = lowalpha;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_HIGHALPHA);
		msg.arg1 = highalpha;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_LOWBETA);
		msg.arg1 = lowbeta;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_HIGHBETA);
		msg.arg1 = highbeta;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_LOWGAMMA);
		msg.arg1 = lowgamma;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_MIDGAMMA);
		msg.arg1 = midgamma;
		sendClients(msg);

		msg = Message.obtain(null, MindsetValue.MSG_EEG_POWER);
		sendClients(msg);
	}

	Thread t;
	public void debug(boolean enable) {
		if(t != null){
			t.interrupt();
			t = null;
		}
		if(enable){
			t = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						Random r = new Random(System.currentTimeMillis());
						onEEG(0,
							r.nextInt(100),
							r.nextInt(100),
							r.nextInt(100000),
							r.nextInt(100000),
							r.nextInt(40000),
							r.nextInt(40000),
							r.nextInt(40000),
							r.nextInt(40000),
							r.nextInt(20000),
							r.nextInt(20000));

						try {
							Thread.sleep(99);
						} catch (InterruptedException e) {
							e.printStackTrace();
							break;
						}
					}
				}
			});
			t.start();
		}
	}
}
