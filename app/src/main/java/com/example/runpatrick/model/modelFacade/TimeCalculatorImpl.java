package com.example.runpatrick.model.modelFacade;

public class TimeCalculatorImpl implements TimeCalculator {
    private long startTime;
    private long occupationTime;

    public TimeCalculatorImpl() {
        this.startTime = 0L;
        this.occupationTime = 0L;
        this.reset();
    }

    @Override
    public void startTracking(){
        this.reset();
        this.startTime = System.currentTimeMillis();
    }


    @Override
    public void update(){
        long timeNow = System.currentTimeMillis();
        long difference = timeNow-startTime;
        occupationTime = difference;
    }

    @Override
    public Long getOccupationTime() {
        return this.occupationTime;
    }



    private void reset() {
        this.startTime = 0L;
        this.occupationTime = 0L;
    }
}
