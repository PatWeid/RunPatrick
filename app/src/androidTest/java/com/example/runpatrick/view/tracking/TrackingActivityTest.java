package com.example.runpatrick.view.tracking;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.runpatrick.R;
import com.example.runpatrick.model.occupationMaker.OccupationMaker;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;
import com.example.runpatrick.view.showHistory.ShowHistoryActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TrackingActivityTest {

    private static final String TEST_MOCK_GPS_LOCATION = "locationtestprovider";


    @Rule
    public ActivityScenarioRule<TrackingActivity> trackingActivityTestRule = new ActivityScenarioRule<>(TrackingActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void startButtonIsDisplayed() {
        onView(withId(R.id.btStart)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void stopButtonIsDisplayed() {
        onView(withId(R.id.btStop)).check(matches(isDisplayed()));
    }

    @Test
    public void historyButtonIsDisplayed() {
        onView(withId(R.id.btHistory)).check(matches(isDisplayed()));
    }

    @Test
    public void mapViewIsDisplayed() {
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewDistanceIsDisplayed() {
        onView(withId(R.id.tvDistance)).check(matches(isDisplayed()));
    }


    @Test
    public void historyButtonStartsShowHistoryActivity() {
        onView(withId(R.id.btHistory)).perform(click());

        intended(hasComponent(ShowHistoryActivity.class.getName()));
    }

    @Test
    public void gpsTrackerServiceNotRunningByDefault() {
        assertFalse(isMyServiceRunning(GPSTrackerService.class, InstrumentationRegistry.getInstrumentation().getContext()));
    }

    @Test
    public void startButtonClickStartsService() {
        onView(withId(R.id.btStart)).perform(click());

        assertTrue(isMyServiceRunning(GPSTrackerService.class, InstrumentationRegistry.getInstrumentation().getContext()));
    }

    @Test
    public void stopTrackingStopsService() {
        onView(withId(R.id.btStart)).perform(click());

        assertTrue(isMyServiceRunning(GPSTrackerService.class, InstrumentationRegistry.getInstrumentation().getContext()));

        onView(withId(R.id.btStop)).perform(click());

        assertFalse(isMyServiceRunning(GPSTrackerService.class, InstrumentationRegistry.getInstrumentation().getContext()));
    }


    @Test
    public void numberOfTrackedLocationsIsRight() {
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

        Location location = new Location(TEST_MOCK_GPS_LOCATION);
        location.setLatitude(34.1233400);
        location.setLongitude(15.6777880);
        location.setAccuracy(Criteria.ACCURACY_FINE);
        location.setTime(System.currentTimeMillis());
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location);

//        Method locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//        if (locationJellyBeanFixMethod != null) {
//            locationJellyBeanFixMethod.invoke(location);
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Location location1 = new Location(TEST_MOCK_GPS_LOCATION);
        location1.setLatitude(33.1233400);
        location1.setLongitude(15.6777880);
        location1.setAccuracy(Criteria.ACCURACY_FINE);
        location1.setTime(System.currentTimeMillis());
        location1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location1);

        Location location2 = new Location(TEST_MOCK_GPS_LOCATION);
        location2.setLatitude(31.1233400);
        location2.setLongitude(15.6777880);
        location2.setAccuracy(Criteria.ACCURACY_FINE);
        location2.setTime(System.currentTimeMillis());
        location2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos() + 200);
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location2);

        Location location3 = new Location(TEST_MOCK_GPS_LOCATION);
        location3.setLatitude(33.1233400);
        location3.setLongitude(16.6777880);
        location3.setAccuracy(Criteria.ACCURACY_FINE);
        location3.setTime(System.currentTimeMillis());
        location3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location3);

        Location location4 = new Location(TEST_MOCK_GPS_LOCATION);
        location4.setLatitude(33.1233400);
        location4.setLongitude(15.6777880);
        location4.setAccuracy(Criteria.ACCURACY_FINE);
        location4.setTime(System.currentTimeMillis());
        location4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location4);

        String distance = this.getText(withId(R.id.tvDistance));
        Log.d("TrackingActivityTest", "Distance: " + distance);
        List<Location> locationList = GPSTrackerService.getLocationList();
        assertEquals(5, locationList.size());

    }

    @Test
    public void distanceIsDisplayedRight() {
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
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
        this.sleep();

        Location l1 = new Location(TEST_MOCK_GPS_LOCATION);
        l1.setLatitude(52.4589);
        l1.setLongitude(13.5255);
        l1.setAltitude(0.0);
        l1.setAccuracy(Criteria.ACCURACY_FINE);
        l1.setTime(System.currentTimeMillis());
        l1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l1);

        this.sleep();


        Location l2 = new Location(TEST_MOCK_GPS_LOCATION);
        l2.setLatitude(52.4587);
        l2.setLongitude(13.5260);
        l2.setAltitude(20.0);
        l2.setAccuracy(Criteria.ACCURACY_FINE);
        l2.setTime(System.currentTimeMillis());
        l2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l2);
        this.sleep();


        Location l3 = new Location(TEST_MOCK_GPS_LOCATION);
        l3.setLatitude(52.4585);
        l3.setLongitude(13.5264);
        l3.setAltitude(23.0);
        l3.setAccuracy(Criteria.ACCURACY_FINE);
        l3.setTime(System.currentTimeMillis());
        l3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l3);

        this.sleep();

        Location l4 = new Location(TEST_MOCK_GPS_LOCATION);
        l4.setLatitude(52.4580);
        l4.setLongitude(13.5271);
        l4.setAltitude(20.0);
        l4.setAccuracy(Criteria.ACCURACY_FINE);
        l4.setTime(System.currentTimeMillis());
        l4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l4);

        this.sleep();

        Location l5 = new Location(TEST_MOCK_GPS_LOCATION);
        l5.setLatitude(52.4577);
        l5.setLongitude(13.5267);
        l5.setAltitude(30.0);
        l5.setAccuracy(Criteria.ACCURACY_FINE);
        l5.setTime(System.currentTimeMillis());
        l5.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l5);

        this.sleep();

        Location l6 = new Location(TEST_MOCK_GPS_LOCATION);
        l6.setLatitude(52.4573);
        l6.setLongitude(13.5263);
        l6.setAltitude(26.5);
        l6.setAccuracy(Criteria.ACCURACY_FINE);
        l6.setTime(System.currentTimeMillis());
        l6.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l6);

        this.sleep();

        Location l7 = new Location(TEST_MOCK_GPS_LOCATION);
        l7.setLatitude(52.4572);
        l7.setLongitude(13.5264);
        l7.setAltitude(22.0);
        l7.setAccuracy(Criteria.ACCURACY_FINE);
        l7.setTime(System.currentTimeMillis());
        l7.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l7);

        this.sleep();

        Location l8 = new Location(TEST_MOCK_GPS_LOCATION);
        l8.setLatitude(52.4568);
        l8.setLongitude(13.5260);
        l8.setAltitude(20.0);
        l8.setAccuracy(Criteria.ACCURACY_FINE);
        l8.setTime(System.currentTimeMillis());
        l8.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l8);

        this.sleep();

        Location l9 = new Location(TEST_MOCK_GPS_LOCATION);
        l9.setLatitude(52.4565);
        l9.setLongitude(13.5258);
        l9.setAltitude(21.0);
        l9.setAccuracy(Criteria.ACCURACY_FINE);
        l9.setTime(System.currentTimeMillis());
        l9.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l9);

        this.sleep();

        Location l10 = new Location(TEST_MOCK_GPS_LOCATION);
        l10.setLatitude(52.4564);
        l10.setLongitude(13.5256);
        l10.setAltitude(19.0);
        l10.setAccuracy(Criteria.ACCURACY_FINE);
        l10.setTime(System.currentTimeMillis());
        l10.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l10);

        this.sleep();

        Location l11 = new Location(TEST_MOCK_GPS_LOCATION);
        l11.setLatitude(52.4562);
        l11.setLongitude(13.5260);
        l11.setAltitude(25.0);
        l11.setAccuracy(Criteria.ACCURACY_FINE);
        l11.setTime(System.currentTimeMillis());
        l11.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l11);

        this.sleep();

        Location l12 = new Location(TEST_MOCK_GPS_LOCATION);
        l12.setLatitude(52.4560);
        l12.setLongitude(13.5258);
        l12.setAltitude(10.0);
        l12.setAccuracy(Criteria.ACCURACY_FINE);
        l12.setTime(System.currentTimeMillis());
        l12.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l12);

        this.sleep();

        Location l13 = new Location(TEST_MOCK_GPS_LOCATION);
        l13.setLatitude(52.4558);
        l13.setLongitude(13.5255);
        l13.setAltitude(24.0);
        l13.setAccuracy(Criteria.ACCURACY_FINE);
        l13.setTime(System.currentTimeMillis());
        l13.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l13);

        this.sleep();

        Location l14 = new Location(TEST_MOCK_GPS_LOCATION);
        l14.setLatitude(52.4556);
        l14.setLongitude(13.5252);
        l14.setAltitude(20.0);
        l14.setAccuracy(Criteria.ACCURACY_FINE);
        l14.setTime(System.currentTimeMillis());
        l14.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l14);

        this.sleep();
        String distance = this.getText(withId(R.id.tvDistance));

        assertEquals("484.58", distance);

        //stop tracking
        onView(withId(R.id.btStop)).perform(click());

        GPSTrackerService.setProvider(LocationManager.GPS_PROVIDER);
    }

    @Test
    public void distanceIsDisplayedAfterTrackingStopped() {
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
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
        this.sleep();

        Location l1 = new Location(TEST_MOCK_GPS_LOCATION);
        l1.setLatitude(52.4589);
        l1.setLongitude(13.5255);
        l1.setAltitude(0.0);
        l1.setAccuracy(Criteria.ACCURACY_FINE);
        l1.setTime(System.currentTimeMillis());
        l1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l1);

        this.sleep();


        Location l2 = new Location(TEST_MOCK_GPS_LOCATION);
        l2.setLatitude(52.4587);
        l2.setLongitude(13.5260);
        l2.setAltitude(20.0);
        l2.setAccuracy(Criteria.ACCURACY_FINE);
        l2.setTime(System.currentTimeMillis());
        l2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l2);
        this.sleep();


        Location l3 = new Location(TEST_MOCK_GPS_LOCATION);
        l3.setLatitude(52.4585);
        l3.setLongitude(13.5264);
        l3.setAltitude(23.0);
        l3.setAccuracy(Criteria.ACCURACY_FINE);
        l3.setTime(System.currentTimeMillis());
        l3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l3);

        this.sleep();

        Location l4 = new Location(TEST_MOCK_GPS_LOCATION);
        l4.setLatitude(52.4580);
        l4.setLongitude(13.5271);
        l4.setAltitude(20.0);
        l4.setAccuracy(Criteria.ACCURACY_FINE);
        l4.setTime(System.currentTimeMillis());
        l4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l4);

        this.sleep();

        Location l5 = new Location(TEST_MOCK_GPS_LOCATION);
        l5.setLatitude(52.4577);
        l5.setLongitude(13.5267);
        l5.setAltitude(30.0);
        l5.setAccuracy(Criteria.ACCURACY_FINE);
        l5.setTime(System.currentTimeMillis());
        l5.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l5);

        this.sleep();

        Location l6 = new Location(TEST_MOCK_GPS_LOCATION);
        l6.setLatitude(52.4573);
        l6.setLongitude(13.5263);
        l6.setAltitude(26.5);
        l6.setAccuracy(Criteria.ACCURACY_FINE);
        l6.setTime(System.currentTimeMillis());
        l6.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l6);

        this.sleep();

        Location l7 = new Location(TEST_MOCK_GPS_LOCATION);
        l7.setLatitude(52.4572);
        l7.setLongitude(13.5264);
        l7.setAltitude(22.0);
        l7.setAccuracy(Criteria.ACCURACY_FINE);
        l7.setTime(System.currentTimeMillis());
        l7.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l7);

        this.sleep();

        Location l8 = new Location(TEST_MOCK_GPS_LOCATION);
        l8.setLatitude(52.4568);
        l8.setLongitude(13.5260);
        l8.setAltitude(20.0);
        l8.setAccuracy(Criteria.ACCURACY_FINE);
        l8.setTime(System.currentTimeMillis());
        l8.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l8);

        this.sleep();

        Location l9 = new Location(TEST_MOCK_GPS_LOCATION);
        l9.setLatitude(52.4565);
        l9.setLongitude(13.5258);
        l9.setAltitude(21.0);
        l9.setAccuracy(Criteria.ACCURACY_FINE);
        l9.setTime(System.currentTimeMillis());
        l9.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l9);

        this.sleep();

        Location l10 = new Location(TEST_MOCK_GPS_LOCATION);
        l10.setLatitude(52.4564);
        l10.setLongitude(13.5256);
        l10.setAltitude(19.0);
        l10.setAccuracy(Criteria.ACCURACY_FINE);
        l10.setTime(System.currentTimeMillis());
        l10.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l10);

        this.sleep();

        Location l11 = new Location(TEST_MOCK_GPS_LOCATION);
        l11.setLatitude(52.4562);
        l11.setLongitude(13.5260);
        l11.setAltitude(25.0);
        l11.setAccuracy(Criteria.ACCURACY_FINE);
        l11.setTime(System.currentTimeMillis());
        l11.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l11);

        this.sleep();

        Location l12 = new Location(TEST_MOCK_GPS_LOCATION);
        l12.setLatitude(52.4560);
        l12.setLongitude(13.5258);
        l12.setAltitude(10.0);
        l12.setAccuracy(Criteria.ACCURACY_FINE);
        l12.setTime(System.currentTimeMillis());
        l12.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l12);

        this.sleep();

        Location l13 = new Location(TEST_MOCK_GPS_LOCATION);
        l13.setLatitude(52.4558);
        l13.setLongitude(13.5255);
        l13.setAltitude(24.0);
        l13.setAccuracy(Criteria.ACCURACY_FINE);
        l13.setTime(System.currentTimeMillis());
        l13.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l13);

        this.sleep();

        Location l14 = new Location(TEST_MOCK_GPS_LOCATION);
        l14.setLatitude(52.4556);
        l14.setLongitude(13.5252);
        l14.setAltitude(20.0);
        l14.setAccuracy(Criteria.ACCURACY_FINE);
        l14.setTime(System.currentTimeMillis());
        l14.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l14);

        this.sleep();

        //stop tracking
        onView(withId(R.id.btStop)).perform(click());

        String distance = this.getText(withId(R.id.tvDistance));
        assertEquals("484.58", distance);

        GPSTrackerService.setProvider(LocationManager.GPS_PROVIDER);
    }

    @Test
    public void distanceIsResetAfterTrackingStartedTwice() {
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
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
        this.sleep();

        Location l1 = new Location(TEST_MOCK_GPS_LOCATION);
        l1.setLatitude(52.4589);
        l1.setLongitude(13.5255);
        l1.setAltitude(0.0);
        l1.setAccuracy(Criteria.ACCURACY_FINE);
        l1.setTime(System.currentTimeMillis());
        l1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l1);

        this.sleep();


        Location l2 = new Location(TEST_MOCK_GPS_LOCATION);
        l2.setLatitude(52.4587);
        l2.setLongitude(13.5260);
        l2.setAltitude(20.0);
        l2.setAccuracy(Criteria.ACCURACY_FINE);
        l2.setTime(System.currentTimeMillis());
        l2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l2);
        this.sleep();


        Location l3 = new Location(TEST_MOCK_GPS_LOCATION);
        l3.setLatitude(52.4585);
        l3.setLongitude(13.5264);
        l3.setAltitude(23.0);
        l3.setAccuracy(Criteria.ACCURACY_FINE);
        l3.setTime(System.currentTimeMillis());
        l3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l3);

        this.sleep();

        Location l4 = new Location(TEST_MOCK_GPS_LOCATION);
        l4.setLatitude(52.4580);
        l4.setLongitude(13.5271);
        l4.setAltitude(20.0);
        l4.setAccuracy(Criteria.ACCURACY_FINE);
        l4.setTime(System.currentTimeMillis());
        l4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l4);

        this.sleep();

        Location l5 = new Location(TEST_MOCK_GPS_LOCATION);
        l5.setLatitude(52.4577);
        l5.setLongitude(13.5267);
        l5.setAltitude(30.0);
        l5.setAccuracy(Criteria.ACCURACY_FINE);
        l5.setTime(System.currentTimeMillis());
        l5.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l5);

        this.sleep();

        Location l6 = new Location(TEST_MOCK_GPS_LOCATION);
        l6.setLatitude(52.4573);
        l6.setLongitude(13.5263);
        l6.setAltitude(26.5);
        l6.setAccuracy(Criteria.ACCURACY_FINE);
        l6.setTime(System.currentTimeMillis());
        l6.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l6);

        this.sleep();

        Location l7 = new Location(TEST_MOCK_GPS_LOCATION);
        l7.setLatitude(52.4572);
        l7.setLongitude(13.5264);
        l7.setAltitude(22.0);
        l7.setAccuracy(Criteria.ACCURACY_FINE);
        l7.setTime(System.currentTimeMillis());
        l7.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l7);

        this.sleep();

        Location l8 = new Location(TEST_MOCK_GPS_LOCATION);
        l8.setLatitude(52.4568);
        l8.setLongitude(13.5260);
        l8.setAltitude(20.0);
        l8.setAccuracy(Criteria.ACCURACY_FINE);
        l8.setTime(System.currentTimeMillis());
        l8.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l8);

        this.sleep();

        Location l9 = new Location(TEST_MOCK_GPS_LOCATION);
        l9.setLatitude(52.4565);
        l9.setLongitude(13.5258);
        l9.setAltitude(21.0);
        l9.setAccuracy(Criteria.ACCURACY_FINE);
        l9.setTime(System.currentTimeMillis());
        l9.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l9);

        this.sleep();

        Location l10 = new Location(TEST_MOCK_GPS_LOCATION);
        l10.setLatitude(52.4564);
        l10.setLongitude(13.5256);
        l10.setAltitude(19.0);
        l10.setAccuracy(Criteria.ACCURACY_FINE);
        l10.setTime(System.currentTimeMillis());
        l10.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l10);

        this.sleep();

        Location l11 = new Location(TEST_MOCK_GPS_LOCATION);
        l11.setLatitude(52.4562);
        l11.setLongitude(13.5260);
        l11.setAltitude(25.0);
        l11.setAccuracy(Criteria.ACCURACY_FINE);
        l11.setTime(System.currentTimeMillis());
        l11.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l11);

        this.sleep();

        Location l12 = new Location(TEST_MOCK_GPS_LOCATION);
        l12.setLatitude(52.4560);
        l12.setLongitude(13.5258);
        l12.setAltitude(10.0);
        l12.setAccuracy(Criteria.ACCURACY_FINE);
        l12.setTime(System.currentTimeMillis());
        l12.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l12);

        this.sleep();

        Location l13 = new Location(TEST_MOCK_GPS_LOCATION);
        l13.setLatitude(52.4558);
        l13.setLongitude(13.5255);
        l13.setAltitude(24.0);
        l13.setAccuracy(Criteria.ACCURACY_FINE);
        l13.setTime(System.currentTimeMillis());
        l13.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l13);

        this.sleep();

        //stop tracking
        onView(withId(R.id.btStop)).perform(click());


        //start again
        onView(withId(R.id.btStart)).perform(click());


        Location l14 = new Location(TEST_MOCK_GPS_LOCATION);
        l14.setLatitude(52.4558);
        l14.setLongitude(13.5255);
        l14.setAltitude(20.0);
        l14.setAccuracy(Criteria.ACCURACY_FINE);
        l14.setTime(System.currentTimeMillis());
        l14.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l14);

        this.sleep();


        String distance = this.getText(withId(R.id.tvDistance));
        assertEquals("0.0", distance);

        GPSTrackerService.setProvider(LocationManager.GPS_PROVIDER);
    }


    @Test
    public void trackingStartedTwice_errorMessageIsDisplayed() {
        onView(withId(R.id.btStart)).perform(click());
        onView(withId(R.id.btStart)).perform(click());

        String errorMessage = this.getText(withId(R.id.tvDebug));

        assertEquals("tracking already running", errorMessage);
    }

    @Test
    public void trackingStoppedWithoutStarting_errorMessageIsDisplayed() {
        onView(withId(R.id.btStop)).perform(click());

        String errorMessage = this.getText(withId(R.id.tvDebug));

        assertEquals("there is nothing to stop", errorMessage);
    }

    @Test
    public void changeToHistoryWhileTracking_DistanceDidntChange() {
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
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
        this.sleep();

        Location l1 = new Location(TEST_MOCK_GPS_LOCATION);
        l1.setLatitude(52.4589);
        l1.setLongitude(13.5255);
        l1.setAltitude(0.0);
        l1.setAccuracy(Criteria.ACCURACY_FINE);
        l1.setTime(System.currentTimeMillis());
        l1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l1);

        this.sleep();


        Location l2 = new Location(TEST_MOCK_GPS_LOCATION);
        l2.setLatitude(52.4587);
        l2.setLongitude(13.5260);
        l2.setAltitude(20.0);
        l2.setAccuracy(Criteria.ACCURACY_FINE);
        l2.setTime(System.currentTimeMillis());
        l2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l2);
        this.sleep();


        Location l3 = new Location(TEST_MOCK_GPS_LOCATION);
        l3.setLatitude(52.4585);
        l3.setLongitude(13.5264);
        l3.setAltitude(23.0);
        l3.setAccuracy(Criteria.ACCURACY_FINE);
        l3.setTime(System.currentTimeMillis());
        l3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l3);

        this.sleep();

        Location l4 = new Location(TEST_MOCK_GPS_LOCATION);
        l4.setLatitude(52.4580);
        l4.setLongitude(13.5271);
        l4.setAltitude(20.0);
        l4.setAccuracy(Criteria.ACCURACY_FINE);
        l4.setTime(System.currentTimeMillis());
        l4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l4);

        this.sleep();

        Location l5 = new Location(TEST_MOCK_GPS_LOCATION);
        l5.setLatitude(52.4577);
        l5.setLongitude(13.5267);
        l5.setAltitude(30.0);
        l5.setAccuracy(Criteria.ACCURACY_FINE);
        l5.setTime(System.currentTimeMillis());
        l5.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l5);
        this.sleep();

        String distanceBeforeChange = this.getText(withId(R.id.tvDistance));

        //change activity and go back
        onView(withId(R.id.btHistory)).perform(click());
        Espresso.pressBack();

        String distanceAfterChange = this.getText(withId(R.id.tvDistance));

        assertEquals(distanceBeforeChange, distanceAfterChange);
    }

    @Test
    public void timeIsDisplayedRight() {
        GPSTrackerService.setProvider(TEST_MOCK_GPS_LOCATION);
        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
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
        this.sleep();

        Location l1 = new Location(TEST_MOCK_GPS_LOCATION);
        l1.setLatitude(52.4589);
        l1.setLongitude(13.5255);
        l1.setAltitude(0.0);
        l1.setAccuracy(Criteria.ACCURACY_FINE);
        l1.setTime(System.currentTimeMillis());
        l1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l1);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l2 = new Location(TEST_MOCK_GPS_LOCATION);
        l2.setLatitude(52.4587);
        l2.setLongitude(13.5260);
        l2.setAltitude(20.0);
        l2.setAccuracy(Criteria.ACCURACY_FINE);
        l2.setTime(System.currentTimeMillis());
        l2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l2);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l3 = new Location(TEST_MOCK_GPS_LOCATION);
        l3.setLatitude(52.4585);
        l3.setLongitude(13.5264);
        l3.setAltitude(23.0);
        l3.setAccuracy(Criteria.ACCURACY_FINE);
        l3.setTime(System.currentTimeMillis());
        l3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l3);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l4 = new Location(TEST_MOCK_GPS_LOCATION);
        l4.setLatitude(52.4580);
        l4.setLongitude(13.5271);
        l4.setAltitude(20.0);
        l4.setAccuracy(Criteria.ACCURACY_FINE);
        l4.setTime(System.currentTimeMillis());
        l4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l4);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l5 = new Location(TEST_MOCK_GPS_LOCATION);
        l5.setLatitude(52.4577);
        l5.setLongitude(13.5267);
        l5.setAltitude(30.0);
        l5.setAccuracy(Criteria.ACCURACY_FINE);
        l5.setTime(System.currentTimeMillis());
        l5.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l5);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l6 = new Location(TEST_MOCK_GPS_LOCATION);
        l6.setLatitude(52.4573);
        l6.setLongitude(13.5263);
        l6.setAltitude(26.5);
        l6.setAccuracy(Criteria.ACCURACY_FINE);
        l6.setTime(System.currentTimeMillis());
        l6.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l6);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l7 = new Location(TEST_MOCK_GPS_LOCATION);
        l7.setLatitude(52.4572);
        l7.setLongitude(13.5264);
        l7.setAltitude(22.0);
        l7.setAccuracy(Criteria.ACCURACY_FINE);
        l7.setTime(System.currentTimeMillis());
        l7.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l7);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l8 = new Location(TEST_MOCK_GPS_LOCATION);
        l8.setLatitude(52.4568);
        l8.setLongitude(13.5260);
        l8.setAltitude(20.0);
        l8.setAccuracy(Criteria.ACCURACY_FINE);
        l8.setTime(System.currentTimeMillis());
        l8.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l8);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l9 = new Location(TEST_MOCK_GPS_LOCATION);
        l9.setLatitude(52.4565);
        l9.setLongitude(13.5258);
        l9.setAltitude(21.0);
        l9.setAccuracy(Criteria.ACCURACY_FINE);
        l9.setTime(System.currentTimeMillis());
        l9.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l9);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l10 = new Location(TEST_MOCK_GPS_LOCATION);
        l10.setLatitude(52.4564);
        l10.setLongitude(13.5256);
        l10.setAltitude(19.0);
        l10.setAccuracy(Criteria.ACCURACY_FINE);
        l10.setTime(System.currentTimeMillis());
        l10.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l10);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l11 = new Location(TEST_MOCK_GPS_LOCATION);
        l11.setLatitude(52.4562);
        l11.setLongitude(13.5260);
        l11.setAltitude(25.0);
        l11.setAccuracy(Criteria.ACCURACY_FINE);
        l11.setTime(System.currentTimeMillis());
        l11.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l11);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l12 = new Location(TEST_MOCK_GPS_LOCATION);
        l12.setLatitude(52.4560);
        l12.setLongitude(13.5258);
        l12.setAltitude(10.0);
        l12.setAccuracy(Criteria.ACCURACY_FINE);
        l12.setTime(System.currentTimeMillis());
        l12.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l12);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l13 = new Location(TEST_MOCK_GPS_LOCATION);
        l13.setLatitude(52.4558);
        l13.setLongitude(13.5255);
        l13.setAltitude(24.0);
        l13.setAccuracy(Criteria.ACCURACY_FINE);
        l13.setTime(System.currentTimeMillis());
        l13.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l13);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        Location l14 = new Location(TEST_MOCK_GPS_LOCATION);
        l14.setLatitude(52.4556);
        l14.setLongitude(13.5252);
        l14.setAltitude(20.0);
        l14.setAccuracy(Criteria.ACCURACY_FINE);
        l14.setTime(System.currentTimeMillis());
        l14.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, l14);

        //sleep 1sec
        for (int i = 0; i < 10; i++) {
            this.sleep();
        }

        String time = this.getText(withId(R.id.tvTime));

        assertEquals("00:13", time);
    }


    //get string from view:
    private String getText(final Matcher<View> matcher) {
        final String[] stringHolder = {null};
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    //calculate new location with distance
    private Location locationFromLocation(Location fromLocation, double distance, double bearingDegrees) {
        double distanceKm = distance / 1000.0;
        double distanceRadians = distanceKm / 6371.0;
        //6,371 = Earth's radius in km
        double bearingRadians = this.radiansFromDegrees(bearingDegrees);
        double fromLatRadians = this.radiansFromDegrees(fromLocation.getLatitude());
        double fromLonRadians = this.radiansFromDegrees(fromLocation.getLongitude());

        double toLatRadians = Math.asin(Math.sin(fromLatRadians) * Math.cos(distanceRadians)
                + Math.cos(fromLatRadians) * Math.sin(distanceRadians) * Math.cos(bearingRadians));

        double toLonRadians = fromLonRadians + Math.atan2(Math.sin(bearingRadians)
                * Math.sin(distanceRadians) * Math.cos(fromLatRadians), Math.cos(distanceRadians)
                - Math.sin(fromLatRadians) * Math.sin(toLatRadians));

        // adjust toLonRadians to be in the range -180 to +180...
        toLonRadians = ((toLonRadians + 3 * Math.PI) % (2 * Math.PI)) - Math.PI;

        Location result = new Location(LocationManager.GPS_PROVIDER);
        result.setLatitude(this.degreesFromRadians(toLatRadians));
        result.setLongitude(this.degreesFromRadians(toLonRadians));
        return result;
    }

    private double radiansFromDegrees(double degrees) {
        return degrees * (Math.PI / 180.0);
    }

    private double degreesFromRadians(double radians) {
        return radians * (180.0 / Math.PI);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
