package com.example.runpatrick.view.showOccupation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.example.runpatrick.R;
import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.modelFacade.GeoPointCreator;
import com.example.runpatrick.model.modelFacade.PojoConverter;
import com.example.runpatrick.view.showHistory.DateConverter;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.List;

public class ShowOccupationActivity extends AppCompatActivity {
    public static final String EXTRA_LONGITUDESTRING = "extra_longitudeString";
    public static final String EXTRA_LATITUDESTRING = "extra_latitudeString";
    public static final String EXTRA_ALTITUDESTRING = "extra_altitudeString";
    public static final String EXTRA_STARTDATE = "extra_startDate";
    public static final String EXTRA_ENDDATE = "extra_endDate";

    private static final double ZOOMLEVEL = 18.0;

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

        tvDate.setText(DateConverter.convertToString(occupation.getStartDate()));
        tvDistance.setText(String.format("%.2f", occupation.getDistanceInKilometers()) + " km");
        tvTime.setText(String.valueOf(occupation.getOccupationTimeInSeconds()) + " sek");
        tvSpeed.setText(occupation.getSpeed()[0] + " : " + occupation.getSpeed()[1] + " min/km");
        tvAltPos.setText("+ " + String.valueOf(occupation.getPosAltitude()) + " m");
        tvAltNeg.setText("- " + String.valueOf(occupation.getNegAltitude()) + " m");
//        tvAltPos.setText("+ " + String.format("%.2f", occupation.getPosAltitude()) + " m");
//        tvAltNeg.setText("- " + String.format("%.2f", occupation.getNegAltitude()) + " m");

        createMap(map, occupation.getLocationList());

    }

    private void createMap(MapView map, List<Location> locationList) {
        IMapController mapController = map.getController();
        mapController.setZoom(ZOOMLEVEL);

        GeoPoint startPont = new GeoPoint(locationList.get(0));
        org.osmdroid.views.overlay.Marker startMarker = new Marker(map);
        startMarker.setPosition(startPont);
        GeoPoint endPoint = new GeoPoint(locationList.get(locationList.size() - 1));
        org.osmdroid.views.overlay.Marker endMarker = new Marker(map);
        endMarker.setPosition(endPoint);

        map.getOverlays().add(startMarker);
        map.getOverlays().add(endMarker);
        mapController.animateTo(startPont);

        List<GeoPoint> geoPoints = GeoPointCreator.createGeoPointList(locationList);
        Polyline polyline = new Polyline();
        polyline.setPoints(geoPoints);
        map.getOverlayManager().add(polyline);

        map.invalidate();
    }
}
