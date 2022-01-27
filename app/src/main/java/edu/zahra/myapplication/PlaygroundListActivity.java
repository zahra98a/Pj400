package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlaygroundListActivity extends AppCompatActivity {

    ListView playgroundList;
    DatabaseReference reff;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Playgrounds playgrounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_list);

        playgrounds = new Playgrounds();
        playgroundList = findViewById(R.id.lvPlayground);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.playground_list,R.id.listInfo,list);

        //get playground name and description as a list from firebase database
        reff = FirebaseDatabase.getInstance().getReference().child("Playgrounds");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren())
                {
                    playgrounds = ds.getValue(Playgrounds.class);
                    list.add(playgrounds.getName().toString() + " :" + playgrounds.getDescription().toString());
                }
                playgroundList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}