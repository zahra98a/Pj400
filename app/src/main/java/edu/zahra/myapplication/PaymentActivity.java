package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PaymentActivity extends AppCompatActivity {

    //String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

       TextView tv = findViewById(R.id.textView8);
     //   key = getIntent().getStringExtra("Key");

      //  Bundle extras = getIntent().getExtras();
       // String[] key = extras.getStringArray("Key");

        Intent intent=getIntent();
        ArrayList<String> test = intent.getStringArrayListExtra("arrayLat");

        tv.setText(test.toString());

      //  tv.setText(Arrays.toString(new ArrayList[]{test}));
       // tv.setText(key);
    }
}