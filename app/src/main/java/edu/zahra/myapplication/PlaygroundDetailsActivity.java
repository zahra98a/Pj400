package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlaygroundDetailsActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView description;
    TextView price;
    TextView areaSize;
    TextView capacity;
    TextView contactNumber;
    TextView openingHours;

    DatabaseReference reff;
    DatabaseReference keyRef;
    DatabaseReference nameRef;
    DatabaseReference descriptionRef;
    DatabaseReference priceRef;
    DatabaseReference areaSizeRef;
    DatabaseReference contactNumberRef;
    DatabaseReference openingHoursRef;
    DatabaseReference locationRef;
    DatabaseReference capacityRef;

    String key;

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
        openingHours = findViewById(R.id.tvSelectedOpeningHours);

        key = getIntent().getStringExtra("Key"); // get the key from the previous activity
        reff = FirebaseDatabase.getInstance().getReference("Playgrounds"); // reference the database
        keyRef = reff.child(key.toString()); // reference the selected key
        nameRef = keyRef.child("name"); // reference child name of the selected key
        descriptionRef = keyRef.child("description"); // reference child description of the selected key
        priceRef = keyRef.child("price"); // reference child price of the selected key
        contactNumberRef = keyRef.child("contactNumber"); // reference child contactNumber of the selected key
        areaSizeRef = keyRef.child("areaSize"); // reference child areaSize of the selected key
        openingHoursRef = keyRef.child("openingHours"); // reference child openingHours of the selected key
        capacityRef = keyRef.child("capacity"); // reference child capacity of the selected key
        locationRef = keyRef.child("location"); // reference child location of the selected key

        //get name value
        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String selectedValue = dataSnapshot.getValue(String.class);
                name.setText("Name: "+selectedValue);
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
                capacity.setText("capacity: "+selectedValue);
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
        });
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
    }
}