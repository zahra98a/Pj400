package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPlaygroundActivity extends AppCompatActivity {

    EditText name;
    EditText location;
    EditText description;
    EditText price;
    EditText areaSize;
    EditText capacity;
    EditText contactNumber;
    EditText openingHours;
    Playgrounds playground;
    FirebaseAuth mAuth;
    //FirebaseUser mUser;
    String user_id;
    DatabaseReference reff;
    DatabaseReference keyReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_playground);


        name =  findViewById(R.id.etName);
        location = findViewById(R.id.etLocation);
        description = findViewById(R.id.etDiscription);
        price =  findViewById(R.id.etPrice);
        areaSize = findViewById(R.id.etAreasize);
        capacity = findViewById(R.id.etCapacity);
        contactNumber = findViewById(R.id.etContactNumber);
        openingHours = findViewById(R.id.etOpeningHours);

        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds");
        keyReff = FirebaseDatabase.getInstance().getReference().child("Playgrounds").child(user_id);


        // display data
       reff.child(user_id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DataSnapshot> task) {

               if(task.isSuccessful()){

                   if(task.getResult().exists()){

                       DataSnapshot dataSnapshot = task.getResult();

                       String newName = String.valueOf(dataSnapshot.child("name").getValue());
                       name.setText(newName);

                       String newDescription = String.valueOf(dataSnapshot.child("description").getValue());
                       description.setText(newDescription);

                       String newPrice = String.valueOf(dataSnapshot.child("price").getValue());
                       price.setText(newPrice);

                       String newAreaSize = String.valueOf(dataSnapshot.child("areaSize").getValue());
                       areaSize.setText(newAreaSize);

                       String newCapacity = String.valueOf(dataSnapshot.child("capacity").getValue());
                       capacity.setText(newCapacity);

                       String newContactNumber = String.valueOf(dataSnapshot.child("contactNumber").getValue());
                       contactNumber.setText(newContactNumber);

                       String newOpeningHours = String.valueOf(dataSnapshot.child("openingHours").getValue());
                       openingHours.setText(newOpeningHours);

                       String newLocation = String.valueOf(dataSnapshot.child("location").getValue());
                       location.setText(newLocation);

                   }else {
                       Toast.makeText(EditPlaygroundActivity.this,"You should register your playground first!", Toast.LENGTH_SHORT).show();
                   }

               }
               else {
                   Toast.makeText(EditPlaygroundActivity.this,"You should register your playground first!", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
    //update data
    public void doUpdate(View view) {
        keyReff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("name").setValue(name.getText().toString());
                snapshot.getRef().child("description").setValue(description.getText().toString());
                snapshot.getRef().child("price").setValue(price.getText().toString());
                snapshot.getRef().child("areaSize").setValue(areaSize.getText().toString());
                snapshot.getRef().child("capacity").setValue(capacity.getText().toString());
                snapshot.getRef().child("contactNumber").setValue(contactNumber.getText().toString());
                snapshot.getRef().child("openingHours").setValue(openingHours.getText().toString());
                snapshot.getRef().child("location").setValue(location.getText().toString());
                Toast.makeText(EditPlaygroundActivity.this,"Data updated successfully", Toast.LENGTH_SHORT).show();

                //EditPlaygroundActivity.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    // delete record
    public void doDelete(View view) {
        keyReff.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditPlaygroundActivity.this,"Deleted successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditPlaygroundActivity.this,"Error accrue, data not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doRegesterPlayground(View view) {
        Intent intent = new Intent(this, RegisterPlaygroundActivity.class);
        // intent.putExtra("UID",""+ mUser);
        startActivity(intent);
    }
}