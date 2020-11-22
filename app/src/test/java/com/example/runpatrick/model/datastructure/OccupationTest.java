package com.example.runpatrick.model.datastructure;

import android.location.Location;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OccupationTest {

    @Test
    public void getLocationListTest(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();
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
        Occupation occupation = OccupationFactory.produceOccupation(locationList, endDate, startDate);

        List<Location> locationListFromOccupation = occupation.getLocationList();

        for(int i =0; i<locationListFromOccupation.size();i++){
            if(locationListFromOccupation.get(i).getLatitude() == locationList.get(i).getLatitude()
                    && locationListFromOccupation.get(i).getLongitude() == locationList.get(i).getLongitude() ){
                return;
            }else{
                fail();
            }
        }
    }

    @Test
    public void calculatePosAltitudeWithoutLocation(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(0, occupation.getPosAltitude());
    }

    @Test
    public void calculateNegAltitudeWithoutLocation(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(0, occupation.getNegAltitude());
    }

    @Test
    public void calculatePosAltitude_OnlyOneLocation(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        l1.setAltitude(9000.0);
        locationList.add(l1);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(0, occupation.getPosAltitude());
    }

    @Test
    public void calculateNegAltitude_OnlyOneLocation(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        l1.setAltitude(9000.0);
        locationList.add(l1);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(0, occupation.getNegAltitude());
    }

    @Test
    public void calculatePosAltitude_1(){
        Date startDate = new Date();
        Date endDate = new Date();
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(2, occupation.getPosAltitude());
    }

    @Test
    public void calculatePosAltitude_2(){
        Date startDate = new Date();
        Date endDate = new Date();
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(8902, occupation.getPosAltitude());
    }

    @Test
    public void calculatePosAltitude_3(){
        Date startDate = new Date();
        Date endDate = new Date();
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(5028, occupation.getPosAltitude());
    }

    @Test
    public void calculateNegAltitude_1(){
        Date startDate = new Date();
        Date endDate = new Date();
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(-8900, occupation.getNegAltitude());
    }

    @Test
    public void calculateNegAltitude_2(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(10.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.5);
        locationList.add(l1);
        locationList.add(l2);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(0, occupation.getNegAltitude());
    }

    @Test
    public void calculateNegAltitude_3(){
        Date startDate = new Date();
        Date endDate = new Date();
        List<Location> locationList = new ArrayList<>();
        Location l1 = mock(Location.class);
        when(l1.getAltitude()).thenReturn(10.0);
        Location l2 = mock(Location.class);
        when(l2.getAltitude()).thenReturn(9.0);
        locationList.add(l1);
        locationList.add(l2);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(-1, occupation.getNegAltitude());
    }

    @Test
    public void calculateNegAltitude_4(){
        Date startDate = new Date();
        Date endDate = new Date();
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        assertEquals(-5129, occupation.getNegAltitude());
    }

    @Test
    public void getOccupationTimeTest(){
        long startTime = 1312556L;
        long occupationTimeinMillisec = 1000 * 650; //650seconds
        Date startDate = new Date(startTime);
        Date endDate = new Date(startTime + occupationTimeinMillisec);

        Occupation occupation = OccupationFactory.produceOccupation(new ArrayList<>(), startDate, endDate);

        assertEquals(650, occupation.getOccupationTimeInSeconds());
    }

    @Test
    public void getDistanceInKilometersWithoutLocations(){
        Occupation occupation = OccupationFactory.produceOccupation(new ArrayList<>(), new Date(), new Date());

        assertEquals(0, occupation.getDistanceInKilometers(), 0);
    }

    @Test
    public void getDistanceInKilometersWithOneLocation(){
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(5.4410);
        when(l1.getLatitude()).thenReturn(10.5859);
        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        Occupation occupation = OccupationFactory.produceOccupation(locationList, new Date(), new Date());

        assertEquals(0, occupation.getDistanceInKilometers(), 0);
    }

    @Test
    public void getDistanceInKilometersWithTwoLocations(){
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(52.4410);
        when(l1.getLatitude()).thenReturn(13.5859);
        Location l2 = mock(Location.class);
        when(l2.getLongitude()).thenReturn(52.4412);
        when(l2.getLatitude()).thenReturn(13.5855);
        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        locationList.add(l2);
        Occupation occupation = OccupationFactory.produceOccupation(locationList, new Date(), new Date());

        assertEquals(0.05, occupation.getDistanceInKilometers(), 0.001);
    }

    @Test
    public void getDistanceBetweenSomeLocations(){
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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, new Date(), new Date());

        assertEquals(102, occupation.getDistanceInKilometers(), 0.1);
    }

    @Test
    public void calculateDistanceBetweenNearLocations(){
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


        Occupation occupation = OccupationFactory.produceOccupation(locationList, new Date(), new Date());

        //the expected value was calculated with two different online calculators:
        //https://rechner-tools.de/71-entfernung-luftlinie-ueber-koordinaten-berechnen.php
        //https://rechneronline.de/geo-koordinaten/#entfernung
        assertEquals(0.448, occupation.getDistanceInKilometers(), 0.001);
    }

    @Test
    public void getSpeedWithZeroTimeTest(){
        Date startAndEndDate = new Date();

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

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startAndEndDate, startAndEndDate);

        int[] speed = occupation.getSpeed();
        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(0, minutes);
        assertEquals(0, seconds);
    }

    @Test
    public void getSpeedWithZeroKilometersTest(){
        Occupation occupation = OccupationFactory.produceOccupation(new ArrayList<>(), new Date(), new Date());

        int[] speed = occupation.getSpeed();
        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(0, minutes);
        assertEquals(0, seconds);
    }

    @Test
    public void getSpeedForMarathonIn4Hours(){
        //Distance between l1 and l2 = Marathon(~42.2km)
        Location l1 = mock(Location.class);
        when(l1.getLongitude()).thenReturn(52.0000);
        when(l1.getLatitude()).thenReturn(13.0000);
        Location l2 = mock(Location.class);
        when(l2.getLongitude()).thenReturn(52.0000);
        when(l2.getLatitude()).thenReturn(13.3791);
        List<Location> locationList = new ArrayList<>();
        locationList.add(l1);
        locationList.add(l2);

        long startTime = 65581L;
        long endTime = startTime + 4*60*60*1000; //4hours more than startTime
        Date startDate = new Date(startTime);
        Date endDate = new Date(endTime);

        Occupation occupation = OccupationFactory.produceOccupation(locationList, startDate, endDate);

        int[] speed = occupation.getSpeed();
        int minutes = speed[0];
        int seconds = speed[1];

        assertEquals(5, minutes);
        assertEquals(41, seconds, 2);
    }
}
