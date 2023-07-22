package com.example.drive_fit_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

public class TripActivity extends AppCompatActivity {

    MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }
}