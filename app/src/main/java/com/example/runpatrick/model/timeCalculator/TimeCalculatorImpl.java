package com.example.runpatrick.model.timeCalculator;

import android.util.Log;

import com.example.runpatrick.model.timeCalculator.TimeCalculator;

public class TimeCalculatorImpl implements TimeCalculator {
    private long startTime;
    private long occupationTime;

    public TimeCalculatorImpl() {
        this.startTime = 0;
        this.occupationTime = 0;
        this.reset();
    }

    @Override
    public void startTracking() {
        this.reset();
        this.startTime = System.currentTimeMillis();
    }


    @Override
    public void update() {
        long timeNow = System.currentTimeMillis();
        long difference = timeNow - startTime;
        occupationTime = difference;
    }

    @Override
    public Long getOccupationTime() {
        return this.occupationTime;
    }


    private void reset() {
        this.startTime = 0;
        this.occupationTime = 0;
    }
}
