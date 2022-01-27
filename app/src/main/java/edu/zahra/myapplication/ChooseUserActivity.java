package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }
    //on button clicked go to vendor side
    public void doVendor(View view) {
        Intent intent = new Intent(this, RegisterPlaygroundActivity.class);
        startActivity(intent);
    }
    //on button clicked go to client side
    public void doPlayer(View view) {
        Intent intent = new Intent(this, PlayerLoginActivity.class);
        startActivity(intent);
    }
}