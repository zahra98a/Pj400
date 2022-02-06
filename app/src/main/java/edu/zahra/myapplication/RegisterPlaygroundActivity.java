package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegisterPlaygroundActivity extends AppCompatActivity {
    EditText name, description, price, capacity, areaSize, openingHours, contactNumber, location;
    Button btnSubmit,btnbrowse;
    DatabaseReference reff;
    Playgrounds playgrounds;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    FirebaseAuth mAuth;
    //FirebaseUser mUser;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_playground);

        name = findViewById(R.id.etName);
        description = findViewById(R.id.etDescription);
        price = findViewById(R.id.etPrice);
        capacity = findViewById(R.id.etCapacity);
        areaSize = findViewById(R.id.etAreaSize);
        openingHours = findViewById(R.id.etOpeningHours);
        contactNumber = findViewById(R.id.etContactNumber);
        location = findViewById(R.id.etLocation);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnbrowse = findViewById(R.id.btnbrowse);
        imgview = findViewById(R.id.image_view);
        playgrounds = new Playgrounds();

        mAuth = FirebaseAuth.getInstance();
      //  mUser = mAuth.getCurrentUser();

              user_id = mAuth.getCurrentUser().getUid();
       // uID = getIntent().getStringExtra("UID"); // get the key from the previous activity

        reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds").child(user_id); //firebase database
        storageReference = FirebaseStorage.getInstance().getReference("Playgrounds"); //firebase storage to save images
        progressDialog = new ProgressDialog(RegisterPlaygroundActivity.this);// context name as project name

        //browse images
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });

        //when submit the form add data to firebase database
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadData();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }
    public void UploadData() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Data is Uploading...");
            progressDialog.show();
            // save images in the storage database
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // store data in realtime database
                            String Name = name.getText().toString().trim();
                            String Description = description.getText().toString().trim();
                            String Price = price.getText().toString().trim();
                            String Capacity = capacity.getText().toString().trim();
                            String AreaSize = areaSize.getText().toString().trim();
                            String OpeningHours = openingHours.getText().toString().trim();
                            String ContactNumber = contactNumber.getText().toString().trim();
                            String Location = location.getText().toString().trim();

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Playgrounds imageUploadInfo = new Playgrounds(Name,Description,Price,Capacity,AreaSize,OpeningHours,ContactNumber,Location,
                                    taskSnapshot.getUploadSessionUri().toString());

                            reff.setValue(imageUploadInfo); // push data to firebase database
                        }
                    });
        }
        else {
            Toast.makeText(RegisterPlaygroundActivity.this, "Please check your inputs", Toast.LENGTH_LONG).show();
        }
    }
}