package com.example.runpatrick.model.datastructure;

public class SpeedCalculator {
    public int[] calculateSpeedInMinPerKilometer(double distanceInKilometers, long occupationTimeInSeconds) {
        return new int[]{this.calculateMinutesPerKilometer(distanceInKilometers, occupationTimeInSeconds),
                this.calculateSecondsPerKilometer(distanceInKilometers, occupationTimeInSeconds)};
    }

    private int calculateSecondsPerKilometer(double distanceInKilometers, long occupationTimeInSeconds) {
        long totalSecondsPerKilometer = (long) (occupationTimeInSeconds / distanceInKilometers);
        int secondsPerKilometer = (int) (totalSecondsPerKilometer % 60);
        return secondsPerKilometer;
    }

    private int calculateMinutesPerKilometer(double distanceInKilometers, long occupationTimeInSeconds) {
        long totalSecondsPerKilometer = (long) (occupationTimeInSeconds / distanceInKilometers);
        int minutesPerKilometer = (int) (totalSecondsPerKilometer / 60);
        return minutesPerKilometer;
    }
}
