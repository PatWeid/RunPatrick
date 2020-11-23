package com.example.runpatrick.model.occupationMaker;

import android.location.Location;

import com.example.runpatrick.model.datastructure.Occupation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OccupationMakerTest {

    @Test
    public void occupationDatesWereSetRight(){
        OccupationMaker occupationMaker = new OccupationMakerImpl();

        occupationMaker.startTracking();

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

        Occupation createdOccupation = occupationMaker.stopTracking(locationList);

        assertTrue(createdOccupation.getStartDate().before(createdOccupation.getEndDate()));
    }

    @Test
    public void locationsWereSetRight(){
        OccupationMaker occupationMaker = new OccupationMakerImpl();

        occupationMaker.startTracking();

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

        Occupation createdOccupation = occupationMaker.stopTracking(locationList);

        List<Location> listFromCreatedOccupation = createdOccupation.getLocationList();

        for(int i =0;i<listFromCreatedOccupation.size();i++){
            if(locationList.get(i).getLongitude() == listFromCreatedOccupation.get(i).getLongitude()
            && locationList.get(i).getLatitude() == listFromCreatedOccupation.get(i).getLatitude()){
                return;
            }else{
                fail();
            }
        }
    }
}
