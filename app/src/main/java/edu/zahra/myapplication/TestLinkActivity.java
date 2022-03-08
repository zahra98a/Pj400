package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URL;

public class TestLinkActivity extends AppCompatActivity {

    Button btnSubmit,btnbrowse;
    DatabaseReference reff;
    Playgrounds playgrounds;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    EditText link;

    FirebaseAuth mAuth;
    //FirebaseUser mUser;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_link);

        btnSubmit = findViewById(R.id.btnSubmit2);
        btnbrowse = findViewById(R.id.btnbrowse);
        imgview = findViewById(R.id.image_view);
        link = findViewById(R.id.tvLink);

        playgrounds = new Playgrounds();
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        //  mUser = mAuth.getCurrentUser();
        // uID = getIntent().getStringExtra("UID"); // get the key from the previous activity

        reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds").child(user_id); //firebase database
        storageReference = FirebaseStorage.getInstance().getReference("Playgrounds"); //firebase storage to save images
        progressDialog = new ProgressDialog(TestLinkActivity.this);// context name as project name

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

                            storageReference2.getDownloadUrl().addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //do something
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //get your image uri here
                                   Uri imgUrl = uri;
                                    String imgStringUrl = imgUrl.toString();

                                    link.setText(imgStringUrl);
                                }
                            });

                       }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // do something
                }
            });
                  /*
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // store data in realtime database

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            URL url = taskSnapshot.getDownloadUrl().toString();

                            //  @SuppressWarnings("VisibleForTests")
                            //    Playgrounds imageUploadInfo = new Playgrounds(Name,Description,Price,Capacity,AreaSize,OpeningHours,ContactNumber,Location,
                            //        taskSnapshot.getUploadSessionUri().toString());

                            //    reff.setValue(imageUploadInfo); // push data to firebase database
                        }
                    });
        }
        else {
            Toast.makeText(TestLinkActivity.this, "Please check your inputs", Toast.LENGTH_LONG).show();
        } */
    }
 }
}