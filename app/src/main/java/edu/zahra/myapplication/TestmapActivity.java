package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestmapActivity extends FragmentActivity implements
        OnMapReadyCallback
         {

    GoogleMap map;
    GoogleApiClient googleApiClient;

             // creating array list for adding all our locations.
             private ArrayList<LatLng> locationArrayList;
             ArrayList<String> arrayLat;
             ArrayList<String> arrayLon;

             double[] longitudeArray;
             double[] latitudeArray;

             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent=getIntent();
                 arrayLat = (intent.getStringArrayListExtra("arrayLat"));
                 arrayLon = (intent.getStringArrayListExtra("arrayLon"));

     //   latitude = Double.parseDouble(getIntent().getStringExtra("Latitude"));
     //   longitude = Double.parseDouble(getIntent().getStringExtra("Longitude"));

                // List<String> failList = new ArrayList<>();

                 latitudeArray = new double[arrayLat.size()]; //create an array with the size of the failList
                 longitudeArray = new double[arrayLon.size()];

                 for (int i = 0; i < arrayLat.size(); ++i) { //iterate over the elements of the list
                     latitudeArray[i] = Double.parseDouble(arrayLat.get(i)); //store each element as a double in the array
                     longitudeArray[i] = Double.parseDouble(arrayLon.get(i));
                 }

       // String[] stringArray = {"1.3", "4.6", "3.2"};
       // double[] doubleArray = ArrayList.stream(test).mapToDouble(Double::parseDouble).toArray();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;

        //set current location
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        for (int i = 0; i < latitudeArray.length; i++) {

           // ArrayList<LatLng> locationArrayList;

           // locationArrayList.add(failsArray[i])

            // LatLng sydney = new LatLng(54.279311, -8.460631);
            LatLng marker = new LatLng(latitudeArray[i], longitudeArray[i]);
            googleMap.addMarker(new MarkerOptions()
                    .position(marker)
                    .title("name"));

            // [START_EXCLUDE silent]
           // googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));


            // below line is use to add marker to each location of our array list.
           // map.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));

            // below lin is use to zoom our camera on map.
           // map.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            // below line is use to move our camera to the specific location.
          //  map.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }
    }

}