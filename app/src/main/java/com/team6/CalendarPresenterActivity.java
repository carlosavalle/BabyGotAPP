package com.team6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class interacts with the CalendarView.
 * Let's the user schedule an event on the Google Calendar App.
 *
 * Author: Jaden Mounteer
 * Date: 03/08/2021
 */
public class CalendarPresenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be activity_calendar_view
        setContentView(R.layout.activity_calendar_view);
    }

}