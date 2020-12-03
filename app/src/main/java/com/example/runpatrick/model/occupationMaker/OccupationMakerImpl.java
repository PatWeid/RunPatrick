package com.example.runpatrick.model.occupationMaker;

import android.location.Location;

import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;
import com.example.runpatrick.model.modelFacade.wrongSequenceException;

import java.util.Date;
import java.util.List;

public class OccupationMakerImpl implements OccupationMaker{
    private Date startDate;

    @Override
    public void startTracking() throws wrongSequenceException {
        if(startDate != null){
                throw new wrongSequenceException("tracking already running");
        }
        this.startDate = new Date();
    }

    @Override
    public Occupation stopTracking(List<Location> locationList) throws wrongSequenceException {
        if(startDate == null){
            throw new wrongSequenceException("tracking already running");
        }
        Date endDate = new Date();
        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        startDate = null;

        return occupation;
    }
}
