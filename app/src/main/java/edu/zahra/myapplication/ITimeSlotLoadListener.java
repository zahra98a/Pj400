package edu.zahra.myapplication;

import java.util.List;

public interface ITimeSlotLoadListener {
    void onTimeSlotLoadSuccess(List<TimeSlot>timeSlots);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
