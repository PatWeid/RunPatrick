package com.example.runpatrick.view.tracking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.List;

public class GPSTrackerService extends Service implements LocationListener {

    private static final float MIN_DISTANCE_BETWEENUPDATES = 0.5f;
    private static final long MIN_TIME_BETWEENUPDATES =1;
    public static final float MIN_ACCURACY = 25.0f;
    private static String PROVIDER = LocationManager.GPS_PROVIDER;

    public static void setProvider(String provider){
        PROVIDER = provider;
    }

    //durch fusedLocationProviderClient ersetzen
    private LocationManager locationManager;
    public static List<Location> locationList = new ArrayList<>();
    public static Handler updateHandler;

    public static List<Location> getLocationList() {
        return locationList;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(){
        super.onCreate();
        locationList.clear();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEENUPDATES, MIN_DISTANCE_BETWEENUPDATES, this);
//        locationManager.requestLocationUpdates("locationtestprovider", MIN_TIME_BETWEENUPDATES, MIN_DISTANCE_BETWEENUPDATES, this);
        locationManager.requestLocationUpdates(PROVIDER, MIN_TIME_BETWEENUPDATES, MIN_DISTANCE_BETWEENUPDATES, this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onDestroy() {
        //erst die Updates beenden, danach folgt die Zerstörung
        locationManager.removeUpdates(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location.getAccuracy() < MIN_ACCURACY) {
            locationList.add(location);
            if (updateHandler != null) {
                updateHandler.sendEmptyMessage(1);
            }
            Log.d("GPSTrackerservice", "Location changed");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
