package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder>{

    Context context;
    List<TimeSlot> timeSlotList;

    public TimeSlotAdapter(Context context, List<TimeSlot> timeSlotList){
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.time_slot,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       holder.timeSlot.setText(new StringBuilder(TimeSlotToString.timeSlotToString(position)).toString()); ///

        if(timeSlotList.size() == 0){ //if all position is available then show the list

            holder.timeSlotDescription.setText("Available");
            holder.timeSlotDescription.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.timeSlot.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.cardTimeSlot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
        else{ // if the time is booked

            for(TimeSlot slotValue:timeSlotList){
                // Loop through all time in the slot and set red color for the booked slot
                int slot = Integer.parseInt(slotValue.getSlot().toString());

                if(slot == position){
                    holder.cardTimeSlot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    holder.timeSlotDescription.setText("Full");
                    holder.timeSlotDescription.setTextColor(context.getResources().getColor(android.R.color.white));
                    holder.timeSlot.setTextColor(context.getResources().getColor(android.R.color.white));
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return 10; /// *** 19:59 timeSlotList.size() = 10 h from 9 - 7
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView timeSlot, timeSlotDescription;
        CardView cardTimeSlot;

        public MyViewHolder (@NonNull View itemView){
            super(itemView);

            timeSlot= itemView.findViewById(R.id.tvTimeSlot);
            timeSlotDescription = itemView.findViewById(R.id.tvTime_description);
            cardTimeSlot = itemView.findViewById(R.id.card_time_slot);;
        }
    }
}
