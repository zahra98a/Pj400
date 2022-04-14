package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlaygroundDetailsActivity extends AppCompatActivity {

    TextView name, location, description, price, areaSize, capacity, contactNumber;
    ImageView pitchImage;

    DatabaseReference reff, keyRef, nameRef, descriptionRef, priceRef, areaSizeRef, contactNumberRef,
    locationRef, latitudeRef, longitudeRef, capacityRef, imageRef;

    String key, playgoundLatitude, playgoundLongitude, playgoundName, imgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);

        name =  findViewById(R.id.tvSelectedName);
        location = findViewById(R.id.tvSelectedLocation);
        description = findViewById(R.id.tvSelectedDiscription);
        price =  findViewById(R.id.tvSelectedPrice);
        areaSize = findViewById(R.id.tvSelectedAreasize);
        capacity = findViewById(R.id.tvSelectedCapacity);
        contactNumber = findViewById(R.id.tvSelectedContactNumber);
        pitchImage = findViewById(R.id.imgPitch);

        key = getIntent().getStringExtra("Key"); // get the key from the previous activity
        reff = FirebaseDatabase.getInstance().getReference("Playgrounds"); // reference the database
        keyRef = reff.child(key.toString()); // reference the selected key
        nameRef = keyRef.child("name"); // reference child name of the selected key
        descriptionRef = keyRef.child("description"); // reference child description of the selected key
        priceRef = keyRef.child("price"); // reference child price of the selected key
        contactNumberRef = keyRef.child("contactNumber"); // reference child contactNumber of the selected key
        areaSizeRef = keyRef.child("areaSize"); // reference child areaSize of the selected key
        capacityRef = keyRef.child("capacity"); // reference child capacity of the selected key
        locationRef = keyRef.child("location"); // reference child location of the selected key
        latitudeRef = keyRef.child("latitude");
        longitudeRef = keyRef.child("longitude");
        imageRef = keyRef.child("imageURL");

        //get name value
        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playgoundName = dataSnapshot.getValue(String.class);
                name.setText(playgoundName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get description value
        descriptionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String selectedValue = dataSnapshot.getValue(String.class);
                description.setText("Description: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get price value
        priceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String selectedValue = dataSnapshot.getValue(String.class);
                price.setText("Price: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get areaSize value
        areaSizeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String selectedValue = dataSnapshot.getValue(String.class);
                areaSize.setText("Area Size: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get capacity value
        capacityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String selectedValue = dataSnapshot.getValue(String.class);
                capacity.setText("Capacity: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get contactNumber value
        contactNumberRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  selectedValue = dataSnapshot.getValue(String.class);
                contactNumber.setText("Contact Number: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*
        //get openingHours value
        openingHoursRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  selectedValue = dataSnapshot.getValue(String.class);
                openingHours.setText("Opening Hours: "+selectedValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
         // get location value
        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  selectedValue = dataSnapshot.getValue(String.class);
                location.setText("Location: "+selectedValue);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // get location value latitude
        latitudeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playgoundLatitude = dataSnapshot.getValue(String.class);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // get location value longitude
        longitudeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playgoundLongitude = dataSnapshot.getValue(String.class);
                // location.setText("Location: "+selectedValue);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // get image url and set it into imageview
        imageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imgURL = dataSnapshot.getValue(String.class);
                // location.setText("Location: "+selectedValue);


                Glide.with(PlaygroundDetailsActivity.this)
                        .load(imgURL)
                        .into(pitchImage);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void goMap(View view) {

        Intent intent = new Intent(this, ViewMarkerActivity.class);
        intent.putExtra("Latitude",""+ playgoundLatitude);
        intent.putExtra("Longitude",""+ playgoundLongitude);
        intent.putExtra("Name",""+ playgoundName);

        startActivity(intent); // start the next activity

    }

    public void doBack(View view) {
        Intent intent = new Intent(this, PlaygroundListActivity.class);
        startActivity(intent); // start the next activity
    }

    public void doLogout(View view) {
        Intent intent = new Intent(this, ChooseUserActivity.class);
        startActivity(intent); // start the next activity
    }

    public void doBooking(View view) {
        Intent intent = new Intent(this, TimeListActivity.class);
        startActivity(intent); // start the next activity
    }
}