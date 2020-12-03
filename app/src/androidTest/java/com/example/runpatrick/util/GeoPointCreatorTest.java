package com.example.runpatrick.util;

import android.location.Location;

import org.junit.Test;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeoPointCreatorTest {

    @Test
    public void convertEmtpyListToEmptyList() {
        List<Location> locationList = new ArrayList<>();
        List<GeoPoint> geoPointList = GeoPointCreator.createGeoPointList(locationList);

        assertEquals(locationList.size(), geoPointList.size());
    }

    @Test
    public void convertList_verifySizeDidntChange() {
        List<Location> locationList = this.getLocationList();
        List<GeoPoint> geoPointList = GeoPointCreator.createGeoPointList(locationList);

        assertEquals(locationList.size(), geoPointList.size());
    }

    @Test
    public void convertList_verifyValuesDidntChange() {
        List<Location> locationList = this.getLocationList();
        List<GeoPoint> geoPointList = GeoPointCreator.createGeoPointList(locationList);

        for (int i = 0; i < locationList.size(); i++) {
            assertEquals(locationList.get(i).getLongitude(), geoPointList.get(i).getLongitude(), 0.1);
            assertEquals(locationList.get(i).getLatitude(), geoPointList.get(i).getLatitude(), 0.1);
            assertEquals(locationList.get(i).getAltitude(), geoPointList.get(i).getAltitude(), 0.1);
        }
    }


    private List<Location> getLocationList() {
        double lat1 = 13.53;
        double lat2 = -14.323242;
        double lat3 = 18.45;
        double lat4 = 13.5616656;
        double lat5 = 13.5;

        double lon1 = 50.645968;
        double lon2 = 36.456;
        double lon3 = -8.45;
        double lon4 = 1.5616656;
        double lon5 = -30.5;

        double alt1 = 5.4;
        double alt2 = 4592.1258;
        double alt3 = -125.1;
        double alt4 = 1.0;
        double alt5 = 9000.0;


        Location location1 = new Location("");
        location1.setLatitude(lat1);
        location1.setLongitude(lon1);
        location1.setAltitude(alt1);
        Location location2 = new Location("");
        location2.setLatitude(lat2);
        location2.setLongitude(lon2);
        location2.setAltitude(alt2);
        Location location3 = new Location("");
        location3.setLatitude(lat3);
        location3.setLongitude(lon3);
        location3.setAltitude(alt3);
        Location location4 = new Location("");
        location4.setLatitude(lat4);
        location4.setLongitude(lon4);
        location4.setAltitude(alt4);
        Location location5 = new Location("");
        location5.setLatitude(lat5);
        location5.setLongitude(lon5);
        location5.setAltitude(alt5);

        List<Location> locationList = new ArrayList<>();

        locationList.add(location1);
        locationList.add(location2);
        locationList.add(location3);
        locationList.add(location4);
        locationList.add(location5);


        return locationList;
    }
}
