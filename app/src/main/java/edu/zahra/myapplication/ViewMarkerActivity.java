package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    double latitude, longitude;
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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        LatLng marker = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(marker)
                .title(name));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        // [END_EXCLUDE]
    }

    public void doBack(View view) {
        Intent intent = new Intent(this, PlaygroundDetailsActivity.class);
        startActivity(intent); // start the next activity
    }

    public void doLogout(View view) {
        Intent intent = new Intent(this, ChooseUserActivity.class);
        startActivity(intent); // start the next activity
    }
}