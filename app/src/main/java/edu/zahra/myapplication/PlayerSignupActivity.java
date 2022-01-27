package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayerSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_signup);

    }
    // after signup go to client login activity
    public void goLogin(View view) {
        Intent intent = new Intent(this, PlayerLoginActivity.class);
        startActivity(intent);
    }
}