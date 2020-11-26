package com.example.runpatrick.model.modelFacade;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;

import java.util.List;

public interface ModelFacade {
    //sets all values to zero
    void stopTracking(List<Location> locationList);

    void startTracking();

    void update(List<Location> locationList);

    LiveData<List<OccupationPojo>> getAllOccupations();

    Long getOccupationTime();

    Double getOccupationDistance();
}
