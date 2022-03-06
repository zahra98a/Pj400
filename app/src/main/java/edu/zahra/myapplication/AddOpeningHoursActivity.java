package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddOpeningHoursActivity extends AppCompatActivity {

    String Name, Description, Price, Capacity, AreaSize, ContactNumber, Location, Latitude, Longitude;
    Uri FilePathUri1;
    EditText from, to;

    Button btnSubmit;
    DatabaseReference reff;
    Playgrounds playgrounds;
    StorageReference storageReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    FirebaseAuth mAuth;
    //FirebaseUser mUser;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opening_hours);

        Name = getIntent().getStringExtra("Name"); // get the key from the previous activity
        Description = getIntent().getStringExtra("Description");
        Price = getIntent().getStringExtra("Price");
        Capacity = getIntent().getStringExtra("Capacity");
        AreaSize = getIntent().getStringExtra("AreaSize");
        ContactNumber = getIntent().getStringExtra("ContactNumber");
        FilePathUri1 = Uri.parse(getIntent().getStringExtra("FilePathUri1"));
        Location = getIntent().getStringExtra("Location");
        Latitude = getIntent().getStringExtra("Latitude");
        Longitude = getIntent().getStringExtra("Longitude");

        to = findViewById(R.id.etTo);
        from = findViewById(R.id.etFrom);
        btnSubmit = findViewById(R.id.btnSubmit);

        playgrounds = new Playgrounds();
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();

        reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds").child(user_id); //firebase database
        storageReference = FirebaseStorage.getInstance().getReference("Playgrounds"); //firebase storage to save images
        progressDialog = new ProgressDialog(AddOpeningHoursActivity.this);// context name as project name


        //when submit the form add data to firebase database
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadData();
            }
        });
    }
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }
    public void UploadData() {

        if (FilePathUri1 != null) {

            progressDialog.setTitle("Data is Uploading...");
            progressDialog.show();
            // save images in the storage database
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri1));
            storageReference2.putFile(FilePathUri1)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // store data in realtime database
                           /* String Name = name.getText().toString().trim();
                            String Description = description.getText().toString().trim();
                            String Price = price.getText().toString().trim();
                            String Capacity = capacity.getText().toString().trim();
                            String AreaSize = areaSize.getText().toString().trim();
                            String OpeningHours = openingHours.getText().toString().trim();
                            String ContactNumber = contactNumber.getText().toString().trim();
                            String Location = location.getText().toString().trim(); */

                            String Start = from.getText().toString().trim();
                            String End = to.getText().toString().trim();

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data Uploaded Successfully ", Toast.LENGTH_LONG).show();
                              @SuppressWarnings("VisibleForTests")
                                Playgrounds imageUploadInfo = new Playgrounds(Name,Description,Price,Capacity,AreaSize,ContactNumber,Location,Latitude, Longitude, Start, End,
                                    taskSnapshot.getUploadSessionUri().toString());

                                reff.setValue(imageUploadInfo); // push data to firebase database
                        }
                    });
        }
        else {
            Toast.makeText(AddOpeningHoursActivity.this, "Please check your inputs", Toast.LENGTH_LONG).show();
        }
    }
}