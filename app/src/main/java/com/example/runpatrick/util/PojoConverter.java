package com.example.runpatrick.util;

import android.location.Location;
import android.util.Log;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PojoConverter {
    private static final String DELIMITER = "|";


    public static OccupationPojo convertToPojo(Occupation occupation) {
        long startTime = occupation.getStartDate().getTime();
        long endTime = occupation.getEndDate().getTime();

        if (isOccupationWithNoLocations(occupation)) {
            Log.d("Pojo", "++++++++++++++++++++++++NO LOCATION++++++++++++++++++++++++++++++++++");
            return new OccupationPojo("", "", "", startTime, endTime);
        }
        String longitudeString = createLongitudeString(occupation);
        String latitudeString = createLatitudeString(occupation);
        String altitudeString = createAltitudeString(occupation);


        return new OccupationPojo(longitudeString, latitudeString, altitudeString, startTime, endTime);
    }


    public static Occupation convertToOccupation(OccupationPojo occupationPojo) {
        List<Location> locationList = createLocationList(occupationPojo);
        Date startDate = createStartDate(occupationPojo);
        Date endDate = createEndDate(occupationPojo);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);
        return occupation;
    }

    private static List<Location> createLocationList(OccupationPojo occupationPojo) {
        List<Location> locationList = new ArrayList<>();
        if (isPojoWithNoLocation(occupationPojo)) {
            return locationList;
        }
        String[] longitudeArray = occupationPojo.getLongitudeString().split("\\" + DELIMITER);
        String[] latitudeArray = occupationPojo.getLatitudeString().split("\\" + DELIMITER);
        String[] altitudeArray = occupationPojo.getAltitudeString().split("\\" + DELIMITER);

        if (arrayLengthAreOK(longitudeArray, latitudeArray, altitudeArray)) {
            for (int i = 0; i < longitudeArray.length; i++) {
                Location currentLocation = new Location("");
                currentLocation.setLongitude(Double.parseDouble(longitudeArray[i]));
                currentLocation.setLatitude(Double.parseDouble(latitudeArray[i]));
                currentLocation.setAltitude(Double.parseDouble(altitudeArray[i]));
                locationList.add(currentLocation);
            }
        }
        return locationList;
    }

    private static boolean isPojoWithNoLocation(OccupationPojo occupationPojo) {
        if (occupationPojo.getAltitudeString().isEmpty()
                || occupationPojo.getLongitudeString().isEmpty()
                || occupationPojo.getLatitudeString().isEmpty()) {
            return true;
        }
        return false;
    }

    private static boolean isOccupationWithNoLocations(Occupation occupation) {
        if (occupation.getLocationList().isEmpty()) {
            return true;
        }
        return false;
    }

    private static boolean arrayLengthAreOK(String[] longitudeArray, String[]
            latitudeArray, String[] altitudeArray) {
        int longSize = longitudeArray.length;
        int latSize = latitudeArray.length;
        int altSize = altitudeArray.length;

        if (longSize == latSize && latSize == altSize) {
            return true;
        } else {
            return false;
        }
    }


    private static Date createEndDate(OccupationPojo runningOccupationDojo) {
        long endTime = runningOccupationDojo.getEndTime();
        return new Date(endTime);
    }

    private static Date createStartDate(OccupationPojo runningOccupationDojo) {
        long startTime = runningOccupationDojo.getStartTime();
        return new Date(startTime);
    }


    private static String createAltitudeString(Occupation runningOccupation) {
        StringBuilder sb = new StringBuilder();
        for (Location l : runningOccupation.getLocationList()) {
            sb.append(l.getAltitude());
            sb.append(DELIMITER);
        }
        return sb.toString();
    }

    private static String createLatitudeString(Occupation runningOccupation) {
        StringBuilder sb = new StringBuilder();
        for (Location l : runningOccupation.getLocationList()) {
            sb.append(l.getLatitude());
            sb.append(DELIMITER);
        }
        return sb.toString();
    }

    private static String createLongitudeString(Occupation runningOccupation) {
        StringBuilder sb = new StringBuilder();
        for (Location l : runningOccupation.getLocationList()) {
            sb.append(l.getLongitude());
            sb.append(DELIMITER);
        }
        return sb.toString();
    }
}
