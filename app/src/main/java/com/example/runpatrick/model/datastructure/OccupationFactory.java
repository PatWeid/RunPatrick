package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.util.Date;
import java.util.List;

public class OccupationFactory {
    public static Occupation produceOccupation(List<Location> locationList, Date startDate, Date endDate){
        return new OccupationImpl(locationList, startDate, endDate);
    }
}
