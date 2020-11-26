package com.example.runpatrick.view.mapPrinter;

import android.content.Context;
import android.location.Location;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.List;

public class MapPrinterImpl implements MapPrinter {
    private static final double ZOOMLEVEL = 18.0;

    private MapView map;

    @Override
    public void setMap(MapView map) {
        this.map = map;
    }

    public MapView getMap() {
        return map;
    }

    @Override
    public void update(List<Location> locationList) {

        //shouldnt happen:
        if (locationList.isEmpty()) {
            return;
        }

        map.getOverlayManager().clear();

        int indexOfLastLocation = locationList.size() - 1;
        Location lastLocation = locationList.get(indexOfLastLocation);

        IMapController mapController = map.getController();
        mapController.setZoom(ZOOMLEVEL);
        GeoPoint lastGeoPoint = new GeoPoint(lastLocation.getLatitude(), lastLocation.getLongitude());

        Marker marker = new Marker(map);
        marker.setPosition(lastGeoPoint);
        map.getOverlays().add(marker);
        mapController.animateTo(lastGeoPoint);

        List<GeoPoint> geoPoints = GeoPointCreator.createGeoPointList(locationList);
        Polyline polyline = new Polyline();
        polyline.setPoints(geoPoints);
        map.getOverlayManager().add(polyline);

        map.invalidate();
    }
}
