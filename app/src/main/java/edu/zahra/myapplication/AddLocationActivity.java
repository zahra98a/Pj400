package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddLocationActivity extends AppCompatActivity {

    String Name, Description, Price, Capacity, AreaSize, ContactNumber;
    Uri FilePathUri1;
    EditText location, latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

         // get data from the previous activity
         Name = getIntent().getStringExtra("Name");
         Description = getIntent().getStringExtra("Description");
         Price = getIntent().getStringExtra("Price");
         Capacity = getIntent().getStringExtra("Capacity");
         AreaSize = getIntent().getStringExtra("AreaSize");
         ContactNumber = getIntent().getStringExtra("ContactNumber");
         FilePathUri1 = Uri.parse(getIntent().getStringExtra("FilePathUri1"));

         location = findViewById(R.id.etLocation);
         latitude = findViewById(R.id.etLatitude);
         longitude = findViewById(R.id.etLongitude);

        //openingHours.setText("Name: "+Name);
    }
    public void doNext(View view) {


        String Location = location.getText().toString();
        String Latitude = latitude.getText().toString();
        String Longitude = longitude.getText().toString();

        Intent intent = new Intent(this, AddOpeningHoursActivity.class);
        intent.putExtra("Name",""+ Name);
        intent.putExtra("Description",""+ Description);
        intent.putExtra("Price",""+ Price);
        intent.putExtra("Capacity",""+ Capacity);
        intent.putExtra("AreaSize",""+ AreaSize);
        intent.putExtra("ContactNumber",""+ ContactNumber);
        intent.putExtra("FilePathUri1",""+ FilePathUri1);
        intent.putExtra("Location",""+ Location);
        intent.putExtra("Latitude",""+ Latitude);
        intent.putExtra("Longitude",""+ Longitude);

        startActivity(intent); // start the next activity
    }
}