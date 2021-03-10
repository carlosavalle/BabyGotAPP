package com.team6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BabyProfile babyProfile = new BabyProfile("1","Aaron", new Date(01,01,2021),"Male","https://firebasestorage.googleapis.com/v0/b/babygotapp.appspot.com/o/Profiles%2Faaron.jpg?alt=media&token=7e4214db-87f0-4330-b7c3-86e76262769a");

        FirebaseDatabase.getInstance().getReference().child("BabyProfiles").child(babyProfile.getId()).setValue(babyProfile);

    }

    /**
     * Called when the user clicks on the Events Page button
     * This method is just temporary for testing purposes
     * Navigates the user to the CalendarPresenter where they
     * will be able to schedule an event
     */
    public void scheduleEvent(View view) {
        // Add an intent that takes the CalendarPresenterActivity class.
        // The CalendarPresenterActivity has been added to the manifest.
        Intent intent = new Intent(this, CalendarPresenterActivity.class);

        // Start an instance of the CalendarPresenterActivity specified by the intent.
        // This will navigate the user to the activity_view_calendar_view page.
        startActivity(intent);
    }

    /**
     * It will profile activity for testing purpose.
     */
    public void profilesEvent(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    }