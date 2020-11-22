package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.util.Date;
import java.util.List;

public interface Occupation {
    List<Location> getLocationList();

    Date getStartDate();

    Date getEndDate();

    int getPosAltitude();

    int getNegAltitude();

    double getDistanceInKilometers();

    long getOccupationTimeInSeconds();

    int[] getSpeed();
}
