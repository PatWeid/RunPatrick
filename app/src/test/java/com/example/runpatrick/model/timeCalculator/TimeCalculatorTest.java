package com.example.runpatrick.model.timeCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeCalculatorTest {

    @Test
    public void calculateTimeTest() {
        TimeCalculator timeCalculator = new TimeCalculatorImpl();

        Long startTime = System.currentTimeMillis();
        timeCalculator.startTracking();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        timeCalculator.update();

        Long timeDifference = endTime - startTime;

        assertEquals(timeDifference, timeCalculator.getOccupationTime(), 100);
    }

    @Test
    public void calculateTimeTwice() {

        //first run
        TimeCalculator timeCalculator = new TimeCalculatorImpl();
        timeCalculator.startTracking();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeCalculator.update();


        //start again
        Long startTime = System.currentTimeMillis();
        timeCalculator.startTracking();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();
        timeCalculator.update();

        Long timeDifference = endTime - startTime;

        assertEquals(timeDifference, timeCalculator.getOccupationTime(), 100);
    }
}
