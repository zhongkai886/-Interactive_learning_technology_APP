package com.alchemy.mindcontroller;

public interface MindController {

    int STATE_START = 0x01;
    int STATE_STOP = 0x02;
    int STATE_PAUSE = 0x03;

    void start();

    void stop();

    void pause(boolean pause);

    int state();

    void connect(String address);

    void disconnect();

    void register();

    void unregister();

    void rawData(boolean enable);

    void DEBUG(boolean enable);
}
