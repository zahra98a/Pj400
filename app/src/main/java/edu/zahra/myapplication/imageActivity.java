package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class imageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        ImageView pitchImage = findViewById(R.id.imgPitch);


        String urlImage = "https://firebasestorage.googleapis.com/v0/b/pj400-3ef6f.appspot.com/o/Playgrounds%2F1646591797596.jpg?alt=media&token=21a67b88-47ba-4f27-82a5-98b03b8a2cf8";

        Glide.with(imageActivity.this)
                .load(urlImage)
                .into(pitchImage);
    }
}