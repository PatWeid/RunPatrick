package com.example.runpatrick.model.modelFacade;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.database.Repository;
import com.example.runpatrick.model.database.RepositoryImpl;
import com.example.runpatrick.model.datastructure.DistanceCalculator;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.occupationMaker.OccupationMaker;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;

import java.util.ArrayList;
import java.util.List;


public class ModelFacadeImpl implements  ModelFacade{
    private OccupationMaker occupationMaker;
    private Repository repository;
    private DistanceCalculator distanceCalculator;
    long occupationTime;
    double distance;

    public ModelFacadeImpl(DistanceCalculator distanceCalculator, OccupationMakerImpl occupationMaker, RepositoryImpl repository) {
        this.distanceCalculator = distanceCalculator;
        this.occupationMaker = occupationMaker;
        this.repository = repository;
    }

    public void setOccupationMaker(OccupationMaker occupationMaker) {
        this.occupationMaker = occupationMaker;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public void startTracking(){
        this.occupationMaker.startTracking();
    }

    @Override
    public void stopTracking(List<Location> locationList){
        Occupation trackedOccupation = this.occupationMaker.stopTracking(locationList);
        OccupationPojo trackedOccupationPojo = PojoConverter.convertToPojo(trackedOccupation);
        this.repository.insert(trackedOccupationPojo);
    }

    @Override
    public void update(List<Location> locationList) {
        this.distance = this.distanceCalculator.calculateDistanceInMeters(locationList);

    }

    @Override
    public LiveData<List<OccupationPojo>> getAllOccupations() {
        return repository.getAllOccupations();
    }

    @Override
    public Long getOccupationTime() {
       //todo
        return occupationTime;
    }

    @Override
    public Double getOccupationDistance() {
        return this.distance;
    }
}
