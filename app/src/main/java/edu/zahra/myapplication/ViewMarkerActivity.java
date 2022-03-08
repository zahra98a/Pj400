package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    double latitude;
    double longitude;
    String name;

    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marker);

        latitude = Double.parseDouble(getIntent().getStringExtra("Latitude"));
        longitude = Double.parseDouble(getIntent().getStringExtra("Longitude"));
        name = getIntent().getStringExtra("Name");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // [START_EXCLUDE silent]
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        // [END_EXCLUDE]
       // LatLng sydney = new LatLng(54.279311, -8.460631);
        LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title(name));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // [END_EXCLUDE]
    }
}