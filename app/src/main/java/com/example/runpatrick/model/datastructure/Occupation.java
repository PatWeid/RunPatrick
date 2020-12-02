package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.util.Date;
import java.util.List;

/**
 * the basic datastructure for a tracked running session. It provides different methods for the view and other components, so that
 * relevant data can be displayed or used for calculation
 */
public interface Occupation {

    /**
     * a method for getting the tracked locations
     *
     * @return a list with all locations that were tracked during a running session
     */
    List<Location> getLocationList();

    /**
     * @return the date when the running session started
     */
    Date getStartDate();

    /**
     * @return the date when the running session ended
     */
    Date getEndDate();

    /**
     * @return the positive vertical meters during the running session
     */
    int getPosAltitude();

    /**
     * @return the negative vertical meters during the running session
     */
    int getNegAltitude();

    /**
     * @return the covered track in kilometers during the running session
     */
    double getDistanceInKilometers();

    /**
     * @return the duration in seconds for the running session
     */
    long getOccupationTimeInSeconds();

    /**
     * the average speed in a running session is calculated in min/km
     *
     * @return an array with the average speed in min/km
     * int[0] - the minutes
     * int[1] - the seconds
     */
    int[] getSpeed();
}
