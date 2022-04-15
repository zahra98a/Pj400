package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }
    //on button clicked go to vendor side - login activity
    public void doVendor(View view) {
        Intent intent = new Intent(this, VendorLoginActivity.class);
        startActivity(intent);
    }
    //on button clicked go to client side - login activity
    public void doPlayer(View view) {
        Intent intent = new Intent(this, PlayerLoginActivity.class);
        startActivity(intent);
    }
}