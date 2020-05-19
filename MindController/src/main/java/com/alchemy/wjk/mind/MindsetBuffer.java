package com.alchemy.wjk.mind;

import com.alchemy.mindcontroller.MindsetValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class MindsetBuffer {

    static final int VALUE_UNSIGNED = 0xFF;
    static final int VALUE_NULL = 0xAA;
    static final int RAW_SIZE = 0x04;

    ReadableByteChannel mChannel;
    ByteBuffer buffer;
    ByteBuffer data;
    int data_check;
    volatile int verify = 1;
    volatile boolean rawEnable = false;

    public int stream_what;
    public int poor_signal;
    public int attention;
    public int meditation;
    public int raw_data;
    public int eeg_delta;
    public int eeg_theta;
    public int eeg_lowalpha;
    public int eeg_highalpha;
    public int eeg_lowbeta;
    public int eeg_highbeta;
    public int eeg_lowgamma;
    public int eeg_midgamma;

    public MindsetBuffer(int bufferSize, boolean rawData){
        buffer = ByteBuffer.allocate(bufferSize);
        data = ByteBuffer.allocate(bufferSize);
        rawEnable = rawData;
    }

    public boolean isRaw(){
        return (stream_what == MindsetValue.MSG_RAW_DATA) & rawEnable;
    }

    public void pause(boolean enable){
        verify = enable?0:1;
    }


    public void rawData(boolean enable){
        rawEnable = enable;
    }

    public void inputStream(InputStream in){
        mChannel = Channels.newChannel(in);
    }

    public void read() throws IOException {
        mChannel.read(buffer);
        buffer.flip();
    }

    public boolean verify(){
        while(buffer.hasRemaining()){
            int stream = buffer.get() & VALUE_UNSIGNED;
            switch(verify){
                case 1: //階段1
                    if(stream == VALUE_NULL) verify = 2;
                    break;
                case 2: //階段2
                    if(stream == VALUE_NULL) verify = 3;
                    else verify = 2;
                    break;
                case 3: //長度
                    if(stream >= VALUE_NULL){verify = 1;break;}
                    data.clear();
                    data.limit(stream);
                    data_check = 0;
                    if(rawEnable) verify = 4;
                    else verify = (stream > RAW_SIZE) ? 4 : 1;
                    break;
                case 4: //收集
                    data.put((byte)stream);
                    data_check += stream;
                    if(!data.hasRemaining()){verify = 5;}
                    break;
                case 5: //資料正確性
                    boolean check = stream == (~data_check & VALUE_UNSIGNED);
                    data.flip();
                    verify = 1;
                    if(check){
                        decode();
                        return check;
                    }

                default: //IDLE
                    buffer.clear();
                    return false;
            }
        }
        buffer.clear();
        return false;
    }

    public void decode(){
        int stream, len;
        while(data.hasRemaining()) {
            stream = data.get() & VALUE_UNSIGNED;
            stream_what = stream;
            switch(stream){
                case MindsetValue.MSG_POOR_SIGNAL:
                    poor_signal = data.get() & VALUE_UNSIGNED;
                    break;
                case MindsetValue.MSG_ATTENTION:
                    attention   = data.get() & VALUE_UNSIGNED;
                    break;
                case MindsetValue.MSG_MEDITATION:
                    meditation  = data.get() & VALUE_UNSIGNED;
                    break;
                case MindsetValue.MSG_RAW_DATA:
                    len = data.get() & VALUE_UNSIGNED;
                    if(len == 2){
                        raw_data = merge(data.get(), data.get());
                        if (raw_data > 0x8000)  raw_data -= 0x10000;
                    }
                    break;
                case MindsetValue.MSG_EEG_POWER:
                    len = data.get() & VALUE_UNSIGNED;
                    if(len == 24){
                        eeg_delta       = merge(data.get(), data.get(), data.get());
                        eeg_theta       = merge(data.get(), data.get(), data.get());
                        eeg_lowalpha    = merge(data.get(), data.get(), data.get());
                        eeg_highalpha   = merge(data.get(), data.get(), data.get());
                        eeg_lowbeta     = merge(data.get(), data.get(), data.get());
                        eeg_highbeta    = merge(data.get(), data.get(), data.get());
                        eeg_lowgamma    = merge(data.get(), data.get(), data.get());
                        eeg_midgamma    = merge(data.get(), data.get(), data.get());
                    }
                    break;

                default:
                    data.get();
                    break;
            }

        }
    }

    private static int merge(byte b0, byte b1){
        return ((b0 & VALUE_UNSIGNED) << 8) | (b1 & VALUE_UNSIGNED) ;
    }

    private static int merge(byte b0, byte b1, byte b2){
        return ((b0 & VALUE_UNSIGNED) << 16) | ((b1 & VALUE_UNSIGNED) << 8) | (b2 & VALUE_UNSIGNED) ;
    }

    public String toString(){
        if(isRaw())
            return String.format("rawEnable:%04X", raw_data);
        else
            return String.format("sig:%03d, att:%03d, med:%03d, de:%06X, th:%06X, la:%06X, ha:%06X, lb:%06X, hb:%06X, lg:%06X, mg:%06X",
                poor_signal, attention, meditation, eeg_delta, eeg_theta, eeg_lowalpha, eeg_highalpha, eeg_lowbeta, eeg_highbeta, eeg_lowgamma, eeg_midgamma);
    }
}
