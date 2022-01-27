package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VendorLoginActivity extends AppCompatActivity {

    TextView vendorSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        vendorSignup = (TextView)findViewById(R.id.tvRegester);
          //go to register activity
        vendorSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorLoginActivity.this, VendorSignupActivity.class);
                startActivity(intent);
            }
        });
    }
    //go to register playground activity to display the form
    public void doRegisterPlayground(View view) {
        Intent intent = new Intent(this, RegisterPlaygroundActivity.class);
        startActivity(intent);
    }
}