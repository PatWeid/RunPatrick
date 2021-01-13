package com.example.runpatrick.model.timeCalculator;

/**
 * a time calculator. It calculates the time difference since tracking started
 */
public interface TimeCalculator {

    /**
     * when this method is called, the time calculator saves the current time
     */
    void startTracking();

    /**
     * when this method is called, the time calculator calculates the time difference since tracking started
     */
    void update();

    /**
     * this method returns the calculated time since tracking started
     *
     * @return the time since tracking started in milliseconds
     */
    Long getOccupationTime();
}
