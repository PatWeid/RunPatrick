package com.example.runpatrick.model.datastructure;

import android.location.Location;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DistanceCalculatorTest {

    @Test
    public void calculateDistanceWithoutLocations() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();

        double distance = distanceCalculator.calculateDistanceInMeters(new ArrayList<>());

        assertEquals(0, distance, 1);
    }

    @Test
    public void calculateDistanceWithOneLocation() {
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(5.4410);
        when(l1.getLatitude()).thenReturn(10.5859);
        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        DistanceCalculator distanceCalculator = new DistanceCalculator();

        double distance = distanceCalculator.calculateDistanceInMeters(locationList);

        assertEquals(0, distance, 1);
    }

    @Test
    public void calculateDistanceBetweenTwoLocations() {
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(52.4410);
        when(l1.getLatitude()).thenReturn(13.5859);


        Location l2 = mock(Location.class);
        when(l2.getLongitude()).thenReturn(52.4412);
        when(l2.getLatitude()).thenReturn(13.5855);


        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        locationList.add(l2);

        DistanceCalculator distanceCalculator = new DistanceCalculator();

        double distance = distanceCalculator.calculateDistanceInMeters(locationList);

        assertEquals(50, distance, 1);
    }

    @Test
    public void calculateDistanceBetweenSomeLocations() {
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(52.6210);
        when(l1.getLatitude()).thenReturn(13.1724);

        Location l2 = mock(Location.class);
        when(l2.getLongitude()).thenReturn(52.0000);
        when(l2.getLatitude()).thenReturn(13.0000);

        Location l3 = mock(Location.class);
        when(l3.getLongitude()).thenReturn(52.2685);
        when(l3.getLatitude()).thenReturn(13.1212);

        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);


        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double distance = distanceCalculator.calculateDistanceInMeters(locationList);


        assertEquals(102044, distance, 20);
    }

    @Test
    public void calculateDistanceBetweenNearLocations() {
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(52.4588);
        when(l1.getLatitude()).thenReturn(13.5258);

        Location l2 = mock(Location.class);
        when(l2.getLongitude()).thenReturn(52.4588);
        when(l2.getLatitude()).thenReturn(13.5259);

        Location l3 = mock(Location.class);
        when(l3.getLongitude()).thenReturn(52.4587);
        when(l3.getLatitude()).thenReturn(13.5259);

        Location l4 = mock(Location.class);
        when(l4.getLongitude()).thenReturn(52.4587);
        when(l4.getLatitude()).thenReturn(13.5260);

        Location l5 = mock(Location.class);
        when(l5.getLongitude()).thenReturn(52.4586);
        when(l5.getLatitude()).thenReturn(13.5259);

        Location l6 = mock(Location.class);
        when(l6.getLongitude()).thenReturn(52.4585);
        when(l6.getLatitude()).thenReturn(13.5257);

        Location l7 = mock(Location.class);
        when(l7.getLongitude()).thenReturn(52.4583);
        when(l7.getLatitude()).thenReturn(13.5256);

        Location l8 = mock(Location.class);
        when(l8.getLongitude()).thenReturn(52.4579);
        when(l8.getLatitude()).thenReturn(13.5252);

        Location l9 = mock(Location.class);
        when(l9.getLongitude()).thenReturn(52.4571);
        when(l9.getLatitude()).thenReturn(13.5243);

        Location l10 = mock(Location.class);
        when(l10.getLongitude()).thenReturn(52.4562);
        when(l10.getLatitude()).thenReturn(13.5232);

        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);
        locationList.add(l7);
        locationList.add(l8);
        locationList.add(l9);
        locationList.add(l10);

        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double distance = distanceCalculator.calculateDistanceInMeters(locationList);

        //the expected value was calculated with two different online calculators:
        //https://rechner-tools.de/71-entfernung-luftlinie-ueber-koordinaten-berechnen.php
        //https://rechneronline.de/geo-koordinaten/#entfernung
        assertEquals(448, distance, 1);
    }
}
