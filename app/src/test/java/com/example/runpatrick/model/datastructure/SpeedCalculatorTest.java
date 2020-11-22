package com.example.runpatrick.model.datastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeedCalculatorTest {

    @Test
    public void zeroTimeTest(){
        SpeedCalculator speedCalculator = new SpeedCalculator();
        int[] speed = speedCalculator.calculateSpeedInMinPerKilometer(5654654656.0, 0L);

        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(0, minutes);
        assertEquals(0, seconds);
    }

    @Test
    public void zeroKilometersTest(){
        SpeedCalculator speedCalculator = new SpeedCalculator();
        int[] speed = speedCalculator.calculateSpeedInMinPerKilometer(0.0, 654614L);

        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(0, minutes);
        assertEquals(0, seconds);
    }

    @Test
    public void calculateSpeedTest_1(){
        SpeedCalculator speedCalculator = new SpeedCalculator();
        int[] speed = speedCalculator.calculateSpeedInMinPerKilometer(1.0, 60L);

        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(1, minutes);
        assertEquals(0, seconds);
    }

    @Test
    public void calculateSpeedForMarathonIn4Hours(){
        SpeedCalculator speedCalculator = new SpeedCalculator();
        long fourHours = 60*60*4;
        int[] speed = speedCalculator.calculateSpeedInMinPerKilometer(42.195, fourHours);

        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(5, minutes);
        assertEquals(41, seconds);
    }
}
