package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.util.List;

public class AltitudeCalculator {
    public int calculatePosAltitude(List<Location> locationList) {
        if (locationList.size() < 2) {
            return 0;
        }
        int positiveAltitude = 0;
        int altitudeBetweenTwoLocations;
        for (int i = 0; i <= locationList.size() - 2; i++) {
            altitudeBetweenTwoLocations = (int) (locationList.get(i + 1).getAltitude() - locationList.get(i).getAltitude());
            if (altitudeBetweenTwoLocations > 0) {
                positiveAltitude += altitudeBetweenTwoLocations;
            }
        }
        return positiveAltitude;
    }

    public int calculateNegAltitude(List<Location> locationList) {
        if (locationList.size() < 2) {
            return 0;
        }
        int negativeAltitude = 0;
        int altitudeBetweenTwoLocations;
        for (int i = 0; i <= locationList.size() - 2; i++) {
            altitudeBetweenTwoLocations = (int) (locationList.get(i + 1).getAltitude() - locationList.get(i).getAltitude());
            if (altitudeBetweenTwoLocations < 0) {
                negativeAltitude += altitudeBetweenTwoLocations;
            }
        }
        return negativeAltitude;
    }
}
