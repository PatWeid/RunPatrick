package com.example.runpatrick.model.occupationMaker;

import android.location.Location;

import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.modelFacade.wrongSequenceException;

import java.util.List;

/**
 * the interface for an occupationMaker. When tracking starts the startTracking method should be called.
 * when tracking stops the occupationMaker will create a new Occupation and gives it back
 */
public interface OccupationMaker {
    /**
     * method that is called when tracking starts
     *
     * @throws wrongSequenceException when tracking is already running
     */
    void startTracking() throws wrongSequenceException;

    /**
     * method that is called when tracking stops
     *
     * @param locationList list with all locations collected during tracking
     * @return occupation that is created. Start- and endDate will be created automatically
     * @throws wrongSequenceException when trackings wasnt started before
     */
    Occupation stopTracking(List<Location> locationList) throws wrongSequenceException;
}
