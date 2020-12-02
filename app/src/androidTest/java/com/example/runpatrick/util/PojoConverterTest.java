package com.example.runpatrick.util;

import android.location.Location;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PojoConverterTest {
    private final double lat1 = 13.523;
    private final double lat2 = 13.323242;
    private final double lat3 = 18.45;
    private final double lat4 = 1.5616656;
    private final double lat5 = 13.5;

    private final double lon1 = 50.645968;
    private final double lon2 = 36.456;
    private final double lon3 = 8.45;
    private final double lon4 = 51.5616656;
    private final double lon5 = 40.5;




    @Test
    public void convertToPojoAndBack_checkLocationListsAreEqual(){
        Date startDate = new Date(100L);
        Date endDate = new Date(50000L);
        Occupation originalOccupation = OccupationFactory.produceOccupation(this.getLocationList(), startDate, endDate);

        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);

        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        List<Location> originalList = originalOccupation.getLocationList();
        List<Location> reConvertedList = reConvertedOccupation.getLocationList();

        assertEquals(originalList.size(), reConvertedList.size());

        Location originalLocation, reConvertedLocation;

        for(int i =0;i<originalList.size();i++){
            originalLocation = originalList.get(i);
            reConvertedLocation = reConvertedList.get(i);

            assertEquals(originalLocation.getLongitude(), reConvertedLocation.getLongitude(), 0.1);
            assertEquals(originalLocation.getLatitude(), reConvertedLocation.getLatitude(), 0.1);
        }
    }

    @Test
    public void convertToPojoAndBack_checkStartDatesAreEqual(){
        Date originalStartDate = new Date(1286492L);

        Occupation originalOccupation = OccupationFactory.produceOccupation(this.getLocationList(), originalStartDate, new Date());
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getStartDate();

        assertEquals(originalStartDate.getTime(), reConvertedDate.getTime());
    }

    @Test
    public void convertToPojoAndBack_checkEndDatesAreEqual(){
        Date originalEndDate = new Date(16492L);

        Occupation originalOccupation = OccupationFactory.produceOccupation(this.getLocationList(), new Date(), originalEndDate);
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getEndDate();

        assertEquals(originalEndDate.getTime(), reConvertedDate.getTime());
    }

    @Test
    public void convertToPojoAndBack_emptyLocationList_checkLocationListsAreEqual(){
        Date startDate = new Date(100L);
        Date endDate = new Date(50000L);
        Occupation originalOccupation = OccupationFactory.produceOccupation(new ArrayList<>(), startDate, endDate);

        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);

        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        List<Location> originalList = originalOccupation.getLocationList();
        List<Location> reConvertedList = reConvertedOccupation.getLocationList();

        assertEquals(originalList.size(), reConvertedList.size());

        Location originalLocation, reConvertedLocation;

        for(int i =0;i<originalList.size();i++){
            originalLocation = originalList.get(i);
            reConvertedLocation = reConvertedList.get(i);

            assertEquals(originalLocation.getLongitude(), reConvertedLocation.getLongitude(), 0.1);
            assertEquals(originalLocation.getLatitude(), reConvertedLocation.getLatitude(), 0.1);
        }
    }

    @Test
    public void convertToPojoAndBack_emptyLocationList_checkStartDatesAreEqual(){
        Date originalStartDate = new Date(1286492L);

        Occupation originalOccupation = OccupationFactory.produceOccupation(new ArrayList<>(), originalStartDate, new Date());
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getStartDate();

        assertEquals(originalStartDate.getTime(), reConvertedDate.getTime());
    }

    @Test
    public void convertToPojoAndBack_emptyLocationList_checkEndDatesAreEqual(){
        Date originalEndDate = new Date(16492L);

        Occupation originalOccupation = OccupationFactory.produceOccupation(new ArrayList<>(), new Date(), originalEndDate);
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getEndDate();

        assertEquals(originalEndDate.getTime(), reConvertedDate.getTime());
    }

    @Test
    public void convertToPojoAndBack_oneLocationInList_checkLocationListsAreEqual(){
        Date startDate = new Date(100L);
        Date endDate = new Date(50000L);

        List<Location> listWithOneLocation = new ArrayList<>();
        listWithOneLocation.add(this.getLocationList().get(3));

        Occupation originalOccupation = OccupationFactory.produceOccupation(listWithOneLocation, startDate, endDate);

        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);

        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        List<Location> originalList = originalOccupation.getLocationList();
        List<Location> reConvertedList = reConvertedOccupation.getLocationList();

        assertEquals(originalList.size(), reConvertedList.size());

        Location originalLocation, reConvertedLocation;

        for(int i =0;i<originalList.size();i++){
            originalLocation = originalList.get(i);
            reConvertedLocation = reConvertedList.get(i);

            assertEquals(originalLocation.getLongitude(), reConvertedLocation.getLongitude(), 0.1);
            assertEquals(originalLocation.getLatitude(), reConvertedLocation.getLatitude(), 0.1);
        }
    }

    @Test
    public void convertToPojoAndBack_oneLocationInList_checkStartDatesAreEqual(){
        Date originalStartDate = new Date(1286492L);

        List<Location> listWithOneLocation = new ArrayList<>();
        listWithOneLocation.add(this.getLocationList().get(2));

        Occupation originalOccupation = OccupationFactory.produceOccupation(listWithOneLocation, originalStartDate, new Date());
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getStartDate();

        assertEquals(originalStartDate.getTime(), reConvertedDate.getTime());
    }

    @Test
    public void convertToPojoAndBack_oneLocationInList_checkEndDatesAreEqual(){
        Date originalEndDate = new Date(16492L);

        List<Location> listWithOneLocation = new ArrayList<>();
        listWithOneLocation.add(this.getLocationList().get(1));

        Occupation originalOccupation = OccupationFactory.produceOccupation(listWithOneLocation, new Date(), originalEndDate);
        OccupationPojo occupationPojo = PojoConverter.convertToPojo(originalOccupation);
        Occupation reConvertedOccupation = PojoConverter.convertToOccupation(occupationPojo);

        Date reConvertedDate = reConvertedOccupation.getEndDate();

        assertEquals(originalEndDate.getTime(), reConvertedDate.getTime());
    }


    private List<Location> getLocationList() {

        List<Location> locationList = new ArrayList<>();

        Location location1 = new Location("");
        location1.setLatitude(lat1);
        location1.setLongitude(lon1);
        Location location2 = new Location("");
        location2.setLatitude(lat2);
        location2.setLongitude(lon2);
        Location location3 = new Location("");
        location3.setLatitude(lat3);
        location3.setLongitude(lon3);
        Location location4 = new Location("");
        location4.setLatitude(lat4);
        location4.setLongitude(lon4);
        Location location5 = new Location("");
        location5.setLatitude(lat5);
        location5.setLongitude(lon5);

        locationList.add(location1);
        locationList.add(location2);
        locationList.add(location3);
        locationList.add(location4);
        locationList.add(location5);


        return locationList;
    }
}
