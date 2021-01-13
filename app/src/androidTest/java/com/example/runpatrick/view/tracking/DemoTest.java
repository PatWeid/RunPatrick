package com.example.runpatrick.view.tracking;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.runpatrick.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DemoTest {

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
    }

    @Test
    public void demoTest(){
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
    }


    private void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
