package edu.zahra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TimeListActivity extends AppCompatActivity {

    ListView TimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);

        TimeList = findViewById(R.id.lvTime);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("09:00 - 10:00");
        arrayList.add("10:00 - 11:00");
        arrayList.add("11:00 - 12:00");
        arrayList.add("01:00 - 02:00");
        arrayList.add("02:00 - 03:00");
        arrayList.add("03:00 - 04:00");
        arrayList.add("05:00 - 06:00");
        arrayList.add("06:00 - 07:00");
        arrayList.add("07:00 - 08:00");
        arrayList.add("08:00 - 09:00");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        TimeList.setAdapter(arrayAdapter);

    }

    public void doLogout(View view) {
        Intent intent = new Intent(this, ChooseUserActivity.class);
        startActivity(intent); // start the next activity
    }

    public void doBack(View view) {
        Intent intent = new Intent(this, PlaygroundDetailsActivity.class);
        startActivity(intent); // start the next activity
    }
}