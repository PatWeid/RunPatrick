package com.example.runpatrick.view.tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.runpatrick.R;
import com.example.runpatrick.view.mapPrinter.MapPrinter;
import com.example.runpatrick.view.mapPrinter.MapPrinterImpl;
import com.example.runpatrick.view.showHistory.ShowHistoryActivity;
import com.example.runpatrick.viewModel.ViewModel;
import com.example.runpatrick.viewModel.ViewModelImpl;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackingActivity extends AppCompatActivity {

    LocationUpdateHandler handler = new LocationUpdateHandler();
    ViewModel viewModel;
    MapPrinter mapPrinter = new MapPrinterImpl();

    TextView tvDebug;

    private class LocationUpdateHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Location changed -> update
            mapPrinter.update(GPSTrackerService.getLocationList());
            viewModel.update(GPSTrackerService.getLocationList());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        //for testing:
        tvDebug = findViewById(R.id.tvDebug);

        MapView map = (MapView) findViewById(R.id.map);
        //Mapnik - öffentlich verfügbaren Daten von openstreetmap
        map.setTileSource(TileSourceFactory.MAPNIK);
        mapPrinter.setMap(map);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(ViewModelImpl.class);

        //check permissions
        if ((ActivityCompat.checkSelfPermission(TrackingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(TrackingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        //check GPS
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Activate GPS", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }

        //listener auf buttons
        final Button btStart = (Button) findViewById(R.id.btStart);
        final Button btStop = (Button) findViewById(R.id.btStop);
        Button btHistory = (Button) findViewById(R.id.btHistory);
        btStop.setEnabled(false);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btStop.setEnabled(true);
                btStart.setEnabled(false);
                viewModel.startTracking();
                Toast.makeText(TrackingActivity.this, "Tracking starts", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrackingActivity.this, GPSTrackerService.class);
                startService(intent);
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btStart.setEnabled(true);
                btStop.setEnabled(false);
                viewModel.stopTracking(GPSTrackerService.getLocationList());
                Toast.makeText(TrackingActivity.this, "Tracking stopped", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrackingActivity.this, GPSTrackerService.class);
                stopService(intent);
            }
        });


        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackingActivity.this, ShowHistoryActivity.class);
                startActivity(intent);
            }
        });

        //observe occupationdistance
        TextView tvDistance = (TextView) findViewById(R.id.tvDistance);
        viewModel.getOccupationDistance().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double newDistance) {
                tvDistance.setText(String.valueOf(newDistance));
            }
        });


    }

//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }


    @Override
    protected void onResume() {
        super.onResume();
        GPSTrackerService.updateHandler = handler;
        viewModel.update(GPSTrackerService.getLocationList());
    }
}