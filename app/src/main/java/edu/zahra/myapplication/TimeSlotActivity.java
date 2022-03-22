package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendarView;
//import butterKnife.Unbinder;

public class TimeSlotActivity extends AppCompatActivity {
    ITimeSlotLoadListener iTimeSlotLoadListener;
    AlertDialog dialog;
   // Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;
    Calendar selectedDate;

    Playgrounds playgrounds;

  //  @BinderView(R.id.recycler_time_slot)
    RecyclerView recyclerTimeSlot;

   // @BinderView(R.id.calendarView)
    HorizontalCalendarView calendarView;
    SimpleDateFormat simpleDateFormat;

    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE,0); //current date
            availableTimeSlotPalyground(playgrounds.getName(),
                    simpleDateFormat.format(date.getTime()));
        }
    };
    private void availableTimeSlotPalyground(String name, String date) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);

        //iTimeSlotLoadListener = this;
        simpleDateFormat = new SimpleDateFormat("dd_mm_yyyy");

       // dialog = new SpotsDialog.Builder()

        selectedDate = Calendar.getInstance();
        selectedDate.add(Calendar.DATE,0);

    }

    @Override
    public void onDestroy(){
        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }

    public void init (View itemView){

        recyclerTimeSlot.setHasFixedSize(true);
      //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
     //   recyclerTimeSlot.setLayoutManager(gridLayoutManager);
     //   recyclerTimeSlot.addItemDecoration(new SpacesItemDecoration(8));

        //calender
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);

        Calendar startEnd = Calendar.getInstance();
        startEnd.add(Calendar.DATE,2);

       // CalendarView calendarView = new CalendarView.Builder(itemView,R.id.calendarView)
             //   .range(startDate),

    }
}