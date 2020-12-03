package com.example.runpatrick.model.modelFacade;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;

import java.util.List;

/**
 * a facade - it unites all components from the model
 */
public interface ModelFacade {
    /**
     * call this method to stop tracking. make sure startTracking() was called before
     * @param locationList list with the tracked locations.
     */
    void stopTracking(List<Location> locationList) throws wrongSequenceException;

    /**
     * call this method to start tracking. make sure to call it only once.
     */
    void startTracking() throws wrongSequenceException;

    /**
     * updates all data in the model
     * @param locationList the latest list with locations
     */
    void update(List<Location> locationList);

    /**
     * all occupationPojos saved in the Database as a List
     * @return LiveData with a List
     */
    LiveData<List<OccupationPojo>> getAllOccupations();

    /**
     * not implemented yet
     * @return
     */
    Long getOccupationTime();

    /**
     * calculates the distance of the current occupation
     * @return distance in meters
     */
    Double getOccupationDistance();
}
