package com.example.runpatrick.view.tracking;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.runpatrick.R;
import com.example.runpatrick.view.mapPrinter.MapPrinter;
import com.example.runpatrick.view.mapPrinter.MapPrinterImpl;
import com.example.runpatrick.viewModel.ViewModel;
import com.example.runpatrick.viewModel.ViewModelImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(AndroidJUnit4.class)
public class GPSTrackerServiceTest {

    private static final String TEST_MOCK_GPS_LOCATION = "locationtestprovider";


    @Rule
    public ActivityScenarioRule<TrackingActivity> trackingActivityTestRule = new ActivityScenarioRule<>(TrackingActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
    }

    @After
    public void tearDown() {
        Intents.release();
        GPSTrackerService.setProvider(LocationManager.GPS_PROVIDER);
        //stop tracking
        onView(withId(R.id.btStop)).perform(click());
    }

    @Test
    public void serviceStartetWithoutGPSUpdates_listIsNotNull() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        //start tracking
        onView(withId(R.id.btStart)).perform(click());

        assertNotNull(GPSTrackerService.getLocationList());
    }

    @Test
    public void serviceStartetWithoutGPSUpdates_listSizeIsZero() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        //start tracking
        onView(withId(R.id.btStart)).perform(click());

        assertEquals(0, GPSTrackerService.getLocationList().size());
    }

    @Test
    public void trackingStartsTwice_listIsCleared() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        //start tracking
        onView(withId(R.id.btStart)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        Location location = new Location(TEST_MOCK_GPS_LOCATION);
        location.setLatitude(34.1233400);
        location.setLongitude(15.6777880);
        location.setAccuracy(Criteria.ACCURACY_FINE);
        location.setTime(System.currentTimeMillis());
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location);

        assertEquals(1, GPSTrackerService.getLocationList().size());

        //stop tracking
        onView(withId(R.id.btStop)).perform(click());

        //start tracking again
        onView(withId(R.id.btStart)).perform(click());

        assertEquals(0, GPSTrackerService.getLocationList().size());
    }

    @Test
    public void numberOfRecordedLocationsIsCorrect() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);

        //start tracking
        onView(withId(R.id.btStart)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        Location location1 = new Location(TEST_MOCK_GPS_LOCATION);
        location1.setLatitude(33.1233400);
        location1.setLongitude(15.6777880);
        location1.setAccuracy(Criteria.ACCURACY_FINE);
        location1.setTime(System.currentTimeMillis());
        location1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location2 = new Location(TEST_MOCK_GPS_LOCATION);
        location2.setLatitude(31.1233400);
        location2.setLongitude(15.6777880);
        location2.setAccuracy(Criteria.ACCURACY_FINE);
        location2.setTime(System.currentTimeMillis());
        location2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos() + 200);
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location3 = new Location(TEST_MOCK_GPS_LOCATION);
        location3.setLatitude(33.1233400);
        location3.setLongitude(16.6777880);
        location3.setAccuracy(Criteria.ACCURACY_FINE);
        location3.setTime(System.currentTimeMillis());
        location3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location3);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location4 = new Location(TEST_MOCK_GPS_LOCATION);
        location4.setLatitude(33.1233400);
        location4.setLongitude(15.6777880);
        location4.setAccuracy(Criteria.ACCURACY_FINE);
        location4.setTime(System.currentTimeMillis());
        location4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location4);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Location> originalList = new ArrayList<>();
        originalList.add(location1);
        originalList.add(location2);
        originalList.add(location3);
        originalList.add(location4);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Location> serviceList = GPSTrackerService.getLocationList();

        assertEquals(originalList.size(), serviceList.size());
    }

    @Test
    public void valuesOfRecordedLocationsAreCorrect() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);

        //start tracking
        onView(withId(R.id.btStart)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        Location location1 = new Location(TEST_MOCK_GPS_LOCATION);
        location1.setLatitude(33.1233400);
        location1.setLongitude(15.6777880);
        location1.setAccuracy(Criteria.ACCURACY_FINE);
        location1.setTime(System.currentTimeMillis());
        location1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location2 = new Location(TEST_MOCK_GPS_LOCATION);
        location2.setLatitude(31.1233400);
        location2.setLongitude(15.6777880);
        location2.setAccuracy(Criteria.ACCURACY_FINE);
        location2.setTime(System.currentTimeMillis());
        location2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos() + 200);
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location3 = new Location(TEST_MOCK_GPS_LOCATION);
        location3.setLatitude(33.1233400);
        location3.setLongitude(16.6777880);
        location3.setAccuracy(Criteria.ACCURACY_FINE);
        location3.setTime(System.currentTimeMillis());
        location3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location3);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location4 = new Location(TEST_MOCK_GPS_LOCATION);
        location4.setLatitude(33.1233400);
        location4.setLongitude(15.6777880);
        location4.setAccuracy(Criteria.ACCURACY_FINE);
        location4.setTime(System.currentTimeMillis());
        location4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location4);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Location> originalList = new ArrayList<>();
        originalList.add(location1);
        originalList.add(location2);
        originalList.add(location3);
        originalList.add(location4);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Location> serviceList = GPSTrackerService.getLocationList();

        for (int i = 0; i < originalList.size(); i++) {
            Location originalLocation = originalList.get(i);
            Location serviceLocation = serviceList.get(i);

            assertEquals(originalLocation.getAltitude(), serviceLocation.getAltitude(), 0.1);
            assertEquals(originalLocation.getLongitude(), serviceLocation.getLongitude(), 0.1);
        }
    }

    @Test
    public void locationsAreFilteredByAccuracy() {
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);

        //start tracking
        onView(withId(R.id.btStart)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List providers = locationManager.getAllProviders();
        if (!providers.contains(TEST_MOCK_GPS_LOCATION)) {
            locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION,
                    false, false, false,
                    false, false, false, false,
                    Criteria.POWER_HIGH, Criteria.ACCURACY_FINE);
        }
        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);

        Location location1 = new Location(TEST_MOCK_GPS_LOCATION);
        location1.setLatitude(33.1233400);
        location1.setLongitude(15.6777880);
        location1.setAccuracy(Criteria.ACCURACY_FINE);
        location1.setTime(System.currentTimeMillis());
        location1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        location1.setAccuracy(GPSTrackerService.MIN_ACCURACY + 2f); //invalid
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location2 = new Location(TEST_MOCK_GPS_LOCATION);
        location2.setLatitude(31.1233400);
        location2.setLongitude(15.6777880);
        location2.setAccuracy(Criteria.ACCURACY_FINE);
        location2.setTime(System.currentTimeMillis());
        location2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos() + 200);
        location2.setAccuracy(GPSTrackerService.MIN_ACCURACY - 1f); //valid
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location3 = new Location(TEST_MOCK_GPS_LOCATION);
        location3.setLatitude(33.1233400);
        location3.setLongitude(16.6777880);
        location3.setAccuracy(Criteria.ACCURACY_FINE);
        location3.setTime(System.currentTimeMillis());
        location3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        location3.setAccuracy(GPSTrackerService.MIN_ACCURACY + 0.1f); //slightly invalid
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location3);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Location location4 = new Location(TEST_MOCK_GPS_LOCATION);
        location4.setLatitude(-33.1233400);
        location4.setLongitude(15.6777880);
        location4.setAccuracy(Criteria.ACCURACY_FINE);
        location4.setTime(System.currentTimeMillis());
        location4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        location4.setAccuracy(GPSTrackerService.MIN_ACCURACY + 5616f); //invalid
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location4);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Location> serviceList = GPSTrackerService.getLocationList();

        assertEquals(1, serviceList.size());
    }

//    @Test
//    public void TrackingActivityGetsMessageWhenLocationIsChanged(){
//
//        MapPrinter mockedMapPrinter = mock(MapPrinterImpl.class);
//        //        ((TrackingActivity)getActivity(InstrumentationRegistry.getInstrumentation().getContext())).mapPrinter
////                = mockedMapPrinter;
//
////        ((TrackingActivity) Objects.requireNonNull(getActivity(InstrumentationRegistry.getInstrumentation().getContext()))).mapPrinter
////                = mockedMapPrinter;
//
//                ((TrackingActivity) getActivity(InstrumentationRegistry.getInstrumentation().getContext())).viewModel
//                = mock(ViewModelImpl.class);
//
//        verify(mockedMapPrinter, times(1)).update(any());
//    }
}



