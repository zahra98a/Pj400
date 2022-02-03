package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PlaygroundDetailsActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground_details);

        name =  findViewById(R.id.tvName);
        location = findViewById(R.id.tvDiscription);
        String tt = getIntent().getStringExtra("Test");
       // String ll = getIntent().getStringExtra("Loc");

        name.setText(tt);
       // location.setText(ll);
    }
}