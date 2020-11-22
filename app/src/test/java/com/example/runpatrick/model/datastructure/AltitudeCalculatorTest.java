package com.example.runpatrick.model.datastructure;

import android.location.Location;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AltitudeCalculatorTest {

    @Test
    public void calculatePosAltitudeWithoutLocation(){
        List<Location> locationList = new ArrayList<>();

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        assertEquals(0, altitudeCalculator.calculatePosAltitude(locationList));
    }

    @Test
    public void calculateNegAltitudeWithoutLocation(){
        List<Location> locationList = new ArrayList<>();

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        assertEquals(0, altitudeCalculator.calculateNegAltitude(locationList));
    }

    @Test
    public void calculatePosAltitude_OnlyOneLocation(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        l1.setAltitude(9000.0);
        locationList.add(l1);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        assertEquals(0, altitudeCalculator.calculatePosAltitude(locationList));
    }

    @Test
    public void calculateNegAltitude_OnlyOneLocation(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        l1.setAltitude(9000.0);
        locationList.add(l1);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        assertEquals(0, altitudeCalculator.calculateNegAltitude(locationList));
    }

    @Test
    public void calculatePosAltitude_1(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(9000.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9001.0);
        Location l3 = mock(Location.class);
        when(l3.getAltitude()).thenReturn(9002.0);
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculatePosAltitude(locationList);
        assertEquals(2, altitude);
    }

    @Test
    public void calculatePosAltitude_2(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(9000.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(100.0);
        Location l3 = mock(Location.class);
        when(l3.getAltitude()).thenReturn(9002.0);
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculatePosAltitude(locationList);
        assertEquals(8902, altitude);
    }

    @Test
    public void calculatePosAltitude_3(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(101.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.0);
        Location l3 = mock(Location.class);
        when(l3.getAltitude()).thenReturn(5000.1);
        Location l4 = mock(Location.class);
        when(l4.getAltitude()).thenReturn(4563.0);
        Location l5 = mock(Location.class);
        when(l5.getAltitude()).thenReturn(4600.3);
        Location l6 = mock(Location.class);
        when(l6.getAltitude()).thenReturn(0.0);
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculatePosAltitude(locationList);
        assertEquals(5028, altitude);
    }

    @Test
    public void calculateNegAltitude_1(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(9000.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(100.0);
        Location l3 = mock(Location.class);
        when(l3.getAltitude()).thenReturn(9002.0);
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculateNegAltitude(locationList);
        assertEquals(-8900, altitude);
    }

    @Test
    public void calculateNegAltitude_2(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(10.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.5);
        locationList.add(l1);
        locationList.add(l2);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculateNegAltitude(locationList);
        assertEquals(0, altitude);
    }

    @Test
    public void calculateNegAltitude_3(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(10.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.0);
        locationList.add(l1);
        locationList.add(l2);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculateNegAltitude(locationList);
        assertEquals(-1, altitude);
    }

    @Test
    public void calculateNegAltitude_4(){
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(101.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.0);
        Location l3 = mock(Location.class);
        when(l3.getAltitude()).thenReturn(5000.1);
        Location l4 = mock(Location.class);
        when(l4.getAltitude()).thenReturn(4563.0);
        Location l5 = mock(Location.class);
        when(l5.getAltitude()).thenReturn(4600.3);
        Location l6 = mock(Location.class);
        when(l6.getAltitude()).thenReturn(0.0);
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);

        AltitudeCalculator altitudeCalculator = new AltitudeCalculator();

        int altitude = altitudeCalculator.calculateNegAltitude(locationList);
        assertEquals(-5129, altitude);
    }
}
