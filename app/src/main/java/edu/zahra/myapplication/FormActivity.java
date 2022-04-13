package edu.zahra.myapplication;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class FormActivity extends AppCompatActivity {

    EditText name, description, price, capacity, areaSize, contactNumber;
    ImageView imgview;
    Button btnSubmit,btnbrowse;
    DatabaseReference reff;
    Playgrounds playgrounds;
    Uri FilePathUri;
    StorageReference storageReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    FirebaseAuth mAuth;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        name = findViewById(R.id.etName);
        description = findViewById(R.id.etDescription);
        price = findViewById(R.id.etPrice);
        capacity = findViewById(R.id.etCapacity);
        areaSize = findViewById(R.id.etAreaSize);
        contactNumber = findViewById(R.id.etContactNumber);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnbrowse = findViewById(R.id.btnbrowse);
        imgview = findViewById(R.id.image_view);
       // playgrounds = new Playgrounds();
       // mAuth = FirebaseAuth.getInstance();
      //  user_id = mAuth.getCurrentUser().getUid();

       // reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds").child(user_id); //firebase database
        //storageReference = FirebaseStorage.getInstance().getReference("Playgrounds"); //firebase storage to save images
        //progressDialog = new ProgressDialog(FormActivity.this);// context name as project name

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

    public void doNext(View view) {

        // pass the data to the next activity
        String Name = name.getText().toString();
        String Description = description.getText().toString();
        String Price = price.getText().toString();
        String Capacity = capacity.getText().toString();
        String AreaSize = areaSize.getText().toString();
        String ContactNumber = contactNumber.getText().toString();
        Uri FilePathUri1 = FilePathUri;

        Intent intent = new Intent(this, AddLocationActivity.class);
        intent.putExtra("Name",""+ Name);
        intent.putExtra("Description",""+ Description);
        intent.putExtra("Price",""+ Price);
        intent.putExtra("Capacity",""+ Capacity);
        intent.putExtra("AreaSize",""+ AreaSize);
        intent.putExtra("ContactNumber",""+ ContactNumber);
        intent.putExtra("FilePathUri1",""+ FilePathUri1);

        startActivity(intent); // start the next activity
    }

    public void doBack(View view) {
        Intent intent = new Intent(this, VendorMainActivity.class);
        // intent.putExtra("UID",""+ mUser);
        startActivity(intent);
    }

    public void doLogout(View view) {
        Intent intent = new Intent(this, ChooseUserActivity.class);
        // intent.putExtra("UID",""+ mUser);
        startActivity(intent);
    }
}