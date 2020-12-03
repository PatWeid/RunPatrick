package com.example.runpatrick.model.modelFacade;

import android.location.Location;

import androidx.lifecycle.LiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.database.Repository;
import com.example.runpatrick.model.datastructure.DistanceCalculator;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.occupationMaker.OccupationMaker;
import com.example.runpatrick.util.PojoConverter;

import java.util.List;


public class ModelFacadeImpl implements  ModelFacade{
    private OccupationMaker occupationMaker;
    private Repository repository;
    private TimeCalculator timeCalculator;
    private DistanceCalculator distanceCalculator;
    long occupationTime;
    double distance;


    public void setOccupationMaker(OccupationMaker occupationMaker) {
        this.occupationMaker = occupationMaker;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public void setTimeCalculator(TimeCalculator timeCalculator) {
        this.timeCalculator = timeCalculator;
    }

    @Override
    public void startTracking() throws wrongSequenceException {
        this.occupationMaker.startTracking();
        this.timeCalculator.startTracking();
    }

    @Override
    public void stopTracking(List<Location> locationList) throws wrongSequenceException {
        Occupation trackedOccupation = this.occupationMaker.stopTracking(locationList);
        OccupationPojo trackedOccupationPojo = PojoConverter.convertToPojo(trackedOccupation);
        this.repository.insert(trackedOccupationPojo);
    }

    @Override
    public void update(List<Location> locationList) {
        this.timeCalculator.update();
        this.occupationTime = this.timeCalculator.getOccupationTime();
        this.distance = this.distanceCalculator.calculateDistanceInMeters(locationList);

    }

    @Override
    public LiveData<List<OccupationPojo>> getAllOccupations() {
        return repository.getAllOccupations();
    }

    @Override
    public Long getOccupationTime() {
       return timeCalculator.getOccupationTime();
    }

    @Override
    public Double getOccupationDistance() {
        return this.distance;
    }
}
