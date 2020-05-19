package com.alchemy.mindlibrary.tool;

public interface MindsetValue {

    int STATE_IDLE          = 0;
    int STATE_CONNECTING    = 1;
    int STATE_CONNECTED     = 2;
    int STATE_DISCONNECTED  = 3;

    int MSG_STATE_CHANGE = 0x01;
    int MSG_POOR_SIGNAL  = 0x02;
    int MSG_ATTENTION    = 0x04;
    int MSG_MEDITATION   = 0x05;
    int MSG_RAW_DATA     = 0x80;
    int MSG_EEG_POWER    = 0x83;

    int MSG_EEG_DELTA 		= 0x31;
    int MSG_EEG_HIGHALPHA 	= 0x32;
    int MSG_EEG_LOWALPHA 	= 0x33;
    int MSG_EEG_HIGHBETA 	= 0x34;
    int MSG_EEG_LOWBETA 	= 0x35;
    int MSG_EEG_LOWGAMMA 	= 0x36;
    int MSG_EEG_MIDGAMMA 	= 0x37;
    int MSG_EEG_THETA 		= 0x38;
    
}
