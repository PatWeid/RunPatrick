package com.example.runpatrick.viewModel;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;

import java.util.List;

/**
 * an interface for the ViewModel. All methods the UI needs are listed here. It gets its data from a repository one layer under the ViewModel,
 * offering the same methods (maybe the repository is implementing the same Interface like the ViewModel)
 */
public interface ViewModel {

    /**
     * @return the whole distance in km since tracking stared
     */
    LiveData<Double> getOccupationDistance();

    /**
     * @return all occupations saved in the database
     */
    LiveData<List<OccupationPojo>> getAllOccupations();

    /**
     * returns the occupation at the given index
     * not implemented yet
     * @param index
     * @return occupation at the given index
     * @params index: occupation's index - counting starts at zero
     */
    Occupation getOccupation(int index);

    /**
     * - starts gps tracking
     * - starts distance calculating <-LiveData
     * - starts timer <-LiveData
     */
    void startTracking();

    /**
     * * - stops tracking
     * * - stops distance calculating
     * * - stops timer
     * * - creates a new occupation with the tracked coordinates, starttime and endtime.
     * * - saves this occupation to the database
     *
     * @param locationList list with all locations at the end of tracking
     */
    void stopTracking(List<Location> locationList);


    /**
     * updates the LiveData
     *
     * @param locationList all locations since tracking started
     */
    void update(List<Location> locationList);
}
