package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.text.DecimalFormat;
import java.util.List;

public class DistanceCalculator {
    public double calculateDistance(List<Location> locationList) {

        double distanceInMeters = 0.0;

        //not enough locations to calculate - avoid nullpointer exception:
        if (locationList.size() < 2) {
            return distanceInMeters;
        }

        Location location1, location2;

        for (int i = 0; i < locationList.size() - 1; i++) {
            location1 = locationList.get(i);
            location2 = locationList.get((i + 1));
            distanceInMeters += (double) location1.distanceTo(location2);

        }

        double distanceInKilometers = distanceInMeters / 1000;

        return Math.round(distanceInKilometers * 100.0) / 100.0;
    }
}
