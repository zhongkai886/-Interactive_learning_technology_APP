package com.alchemy.wjk.mind.widget;

import java.util.ArrayList;

import android.util.Log;

public class WavechartHandle {

    ArrayList<WavechartHandle> lines;

    final boolean line;


//++------*Handle*------++// TODO Handle函式

    public WavechartHandle() {
        lines = new ArrayList<WavechartHandle>();
        line = false;
        limit = 0;
        positionMax = 0;
        buffer = null;
    }

    public WavechartHandle createLine(int size) {
        WavechartHandle line = new WavechartHandle(size);
        lines.add(line);
        return line;
    }

    public WavechartHandle createLine(int size, int baseScroll) {
        WavechartHandle line = new WavechartHandle(size, baseScroll);
        lines.add(line);
        return line;
    }

    public ArrayList<WavechartHandle> getLines() {
        return lines;
    }

    public WavechartHandle getLine(int index) {
        return lines.get(index);
    }

    public void removeLine(WavechartHandle line) {
        lines.remove(line);
    }

    public void removeLine(int index) {
        lines.remove(index);
    }

    public void allsetFrame(int frame) {
        for (WavechartHandle l : lines) l.setFrame(frame);
    }

    public void allreadyMove() {
        for (WavechartHandle l : lines) l.readyMove();
    }

    public boolean allMove() {
        boolean move = false;
        for (WavechartHandle l : lines) {
            if (l.move()) move = true;
        }
        return move;
    }

//++------*Line*------++// TODO Line函式

    final float[] buffer;
    final public int limit, positionMax;
    int position = 0;
    int movePosition = 0;
    int moveScroll = 0;
    float moveProgress = 0;
    int moveProgressCount = 60;
    float movePoint = 0;
    float moveDistance;

    boolean newPut = false;

    int lineColor = 0xff00dd00;
    int pointColor = 0xffaaaaaa;
    int arrowColor = 0xffdd0000;

    private WavechartHandle(int size) {
        this(size, 0);
    }

    private WavechartHandle(int size, int baseScroll) {
        if (size < 2) size = 2;
        buffer = new float[size];
        limit = size;
        positionMax = size - 1;
        line = true;
        moveScroll = baseScroll;
        movePosition = 0;
    }

    public void put(float data) {
        buffer[position++] = data;
        if (position >= limit) position = 0;
        movePosition(-1);
        newPut = true;
    }

    public void puts(float[] data) {
        for (float d : data) put(d);
    }

    public void setFrame(int frame) {
        moveProgressCount = frame;
    }

    public void movePosition(int coord) {
        movePosition += coord;
        if (movePosition > positionMax) movePosition = positionMax;
        else if (movePosition < 0) movePosition = 0;
    }

    public void readyMove() {
        upMove();
    }

    void upMove() {
        int offset = getMovePositionOffset();
        movePoint = getData(offset - 1);
        moveDistance = (getData(offset) - movePoint) / moveProgressCount;

    }

    public boolean move() {
        if (movePosition >= positionMax) {
            return false;
        }
        if (newPut) {
            newPut = false;
            upMove();
            movePoint += moveDistance * moveProgress;
        }

        moveProgress++;
        movePoint += moveDistance;
        if (moveProgress >= moveProgressCount) {
            moveProgress = 0;
            movePosition++;
            upMove();
        }

        return true;
    }

    public int getMovePositionOffset() {
        return movePosition - positionMax;
    }

    public float getMovePoint() {
        return movePoint;
    }

    public float getProgress() {
        return moveProgress / moveProgressCount;
    }

    public float getMoveProgress() {
        return movePosition + moveProgress / moveProgressCount;
    }

    public int getPosition(int offset) {
        offset %= limit;
        int p = position + offset;
        if (p < 0) p += limit;
        return (p < limit) ? p : p - limit;
    }

    public float getData(int offset) {
        return buffer[getPosition(offset)];
    }

    public void setLineColor(int color) {
        lineColor = color;
    }

    public void setPointColor(int color) {
        pointColor = color;
    }

    public void setArrowColor(int color) {
        arrowColor = color;
    }
}
