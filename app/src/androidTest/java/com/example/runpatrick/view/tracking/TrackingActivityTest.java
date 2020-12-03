package com.example.runpatrick.view.tracking;

import android.app.ActivityManager;
import android.content.Context;
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
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.runpatrick.R;
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
    public void setUp(){
        Intents.init();
    }

    @After
    public void tearDown(){
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
    public void valueOfDistanceIsZero() {
        onView(withId(R.id.tvDistance)).check(matches(withText("0.0")));
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


//    @Test
//    public void testGPS1() {
//        LocationManager locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getContext().getSystemService(Context.LOCATION_SERVICE);
//        locationManager.addTestProvider("Test",
//                false, false, false, false, false,
//                false, false, Criteria.POWER_LOW, Criteria.ACCURACY_FINE);
//        locationManager.setTestProviderEnabled("Test", true);
//
//        // Set up your test
//        onView(withId(R.id.btStart)).perform(click());
//        Location location = new Location("Test");
//        location.setLatitude(10.0);
//        location.setLongitude(20.0);
//        locationManager.setTestProviderLocation("Test", location);
//
//
//
//        // Check if your listener reacted the right way
//
//        locationManager.removeTestProvider("Test");
//    }



    //nur zum ausprobieren - macht derzeit nichts sinnvolles
    @Test
    public void testGPS(){
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

//        locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//        if (locationJellyBeanFixMethod != null) {
//            locationJellyBeanFixMethod.invoke(location1);
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Location location2 = new Location(TEST_MOCK_GPS_LOCATION);
        location2.setLatitude(31.1233400);
        location2.setLongitude(15.6777880);
        location2.setAccuracy(Criteria.ACCURACY_FINE);
        location2.setTime(System.currentTimeMillis());
        location2.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos() + 200);
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location2);

//        locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//        if (locationJellyBeanFixMethod != null) {
//            locationJellyBeanFixMethod.invoke(location2);
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Location location3 = new Location(TEST_MOCK_GPS_LOCATION);
        location3.setLatitude(33.1233400);
        location3.setLongitude(16.6777880);
        location3.setAccuracy(Criteria.ACCURACY_FINE);
        location3.setTime(System.currentTimeMillis());
        location3.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location3);


//        locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//        if (locationJellyBeanFixMethod != null) {
//            locationJellyBeanFixMethod.invoke(location3);
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Location location4 = new Location(TEST_MOCK_GPS_LOCATION);
        location4.setLatitude(33.1233400);
        location4.setLongitude(15.6777880);
        location4.setAccuracy(Criteria.ACCURACY_FINE);
        location4.setTime(System.currentTimeMillis());
        location4.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location4);

//        locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//        if (locationJellyBeanFixMethod != null) {
//            locationJellyBeanFixMethod.invoke(location4);
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//            Method locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
//            if (locationJellyBeanFixMethod != null) {
//                locationJellyBeanFixMethod.invoke(location1);
//            }
//        } else {
//            // Check if your listener reacted the right way
//            locationManager.removeTestProvider(TEST_MOCK_GPS_LOCATION);
//        }
        String distance = this.getText(withId(R.id.tvDistance));
        Log.d("TrackingActivityTest", "Distance: " + distance);
        List<Location> locationList = GPSTrackerService.getLocationList();
        assertEquals(5, locationList.size());

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


    //todo
    @Test
    public void gpsNotAvailable_SettingsAreOpened() {
//        LocationManager locationManager = PowerMockito.mock(LocationManager.class);
//        PowerMockito.mockStatic(LocationManager.class);
//        PowerMockito.when(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(false);
//
//        intended(hasComponent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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
}
