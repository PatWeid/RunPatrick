package com.example.runpatrick.util;

import android.location.Location;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class GeoPointCreator {
    public static List<GeoPoint> createGeoPointList(List<Location> locationList) {
        List<GeoPoint> geoPointList = new ArrayList<>();
        for (Location l : locationList) {
            GeoPoint geopoint = new GeoPoint(l.getLatitude(), l.getLongitude());
            geoPointList.add(geopoint);
        }
        return geoPointList;
    }
}
