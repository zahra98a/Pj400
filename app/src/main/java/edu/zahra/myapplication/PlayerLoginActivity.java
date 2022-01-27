package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayerLoginActivity extends AppCompatActivity {

    TextView playerSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);

        playerSignup = (TextView)findViewById(R.id.tvRegester);

        //go to register activity
        playerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerLoginActivity.this, PlayerSignupActivity.class);
                startActivity(intent);
            }
        });
    }
    // when button clicked move the next activity to display available playground list
    public void doGetList(View view) {
        Intent intent = new Intent(this, PlaygroundListActivity.class);
        startActivity(intent);
    }
}