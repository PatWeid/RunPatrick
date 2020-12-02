package com.example.runpatrick.view.mapPrinter;

import android.location.Location;

import org.osmdroid.views.MapView;

import java.util.List;

/**
 * A class for printing an osmdroid.MapView - displaying the track
 * with a polyline
 */
public interface MapPrinter {
    /**
     * set reference to the MapView
     * @param map the MapView (osmdroid - not android.MapView)
     */
    void setMap(MapView map);

    /**
     * draw polyline
     * @param locationList list with all coordinates
     */
    void update(List<Location> locationList);

    /**
     * getter method for the printed map
     * @return the printed map
     */
    MapView getMap();
}
