package com.example.runpatrick.model.occupationMaker;

import android.location.Location;

import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;

import java.util.Date;
import java.util.List;

public class OccupationMakerImpl implements OccupationMaker{
    private Date startDate;

    @Override
    public void startTracking() {
        this.startDate = new Date();
    }

    @Override
    public Occupation stopTracking(List<Location> locationList) {
        Date endDate = new Date();
        return OccupationFactory.produceOccupation(locationList, startDate, endDate);
    }
}
