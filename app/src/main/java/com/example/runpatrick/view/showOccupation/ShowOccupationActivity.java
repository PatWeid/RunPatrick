package com.example.runpatrick.view.showOccupation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.example.runpatrick.R;
import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.util.GeoPointCreator;
import com.example.runpatrick.util.PojoConverter;
import com.example.runpatrick.view.mapPrinter.MapPrinter;
import com.example.runpatrick.view.mapPrinter.MapPrinterImpl;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.text.SimpleDateFormat;
import java.util.List;

public class ShowOccupationActivity extends AppCompatActivity {
    public static final String EXTRA_LONGITUDESTRING = "extra_longitudeString";
    public static final String EXTRA_LATITUDESTRING = "extra_latitudeString";
    public static final String EXTRA_ALTITUDESTRING = "extra_altitudeString";
    public static final String EXTRA_STARTDATE = "extra_startDate";
    public static final String EXTRA_ENDDATE = "extra_endDate";

    private static final double ZOOMLEVEL = 18.0;
    private MapPrinter mapPrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_occupation);

        Intent intent = getIntent();

        Occupation occupation = PojoConverter.convertToOccupation(new OccupationPojo(
                intent.getStringExtra(EXTRA_LONGITUDESTRING),
                intent.getStringExtra(EXTRA_LATITUDESTRING),
                intent.getStringExtra(EXTRA_ALTITUDESTRING),
                intent.getLongExtra(EXTRA_STARTDATE, 898455468),
                intent.getLongExtra(EXTRA_ENDDATE, 898455468)));

        TextView tvDate = findViewById(R.id.date);
        TextView tvDistance = findViewById(R.id.distance);
        TextView tvTime = findViewById(R.id.time);
        TextView tvSpeed = findViewById(R.id.speed);
        TextView tvAltPos = findViewById(R.id.altPos);
        TextView tvAltNeg = findViewById(R.id.altNeg);
        MapView map = findViewById(R.id.viewMap);
        map.setTileSource(TileSourceFactory.MAPNIK);

        this.mapPrinter = new MapPrinterImpl();
        mapPrinter.setMap(map);

        tvDate.setText(new SimpleDateFormat("hh:mm - dd:MM:yyyy").format(occupation.getStartDate()));
        tvDistance.setText(String.format("%.2f", occupation.getDistanceInKilometers()) + " km");
        tvTime.setText(String.valueOf(occupation.getOccupationTimeInSeconds()) + " sek");
        tvSpeed.setText(occupation.getSpeed()[0] + " : " + occupation.getSpeed()[1] + " min/km");
        tvAltPos.setText("+" + String.valueOf(occupation.getPosAltitude()) + " m");
        tvAltNeg.setText(" " + String.valueOf(occupation.getNegAltitude()) + " m");

        mapPrinter.update(occupation.getLocationList());
    }
}
