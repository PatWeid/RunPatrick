package com.example.runpatrick.view.mapPrinter;

import android.location.Location;

import org.osmdroid.views.MapView;

import java.util.List;

public interface MapPrinter {
    void setMap(MapView map);

    void update(List<Location> locationList);
}
