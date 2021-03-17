package com.team6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BabyProfile babyProfile1 = new BabyProfile("1","Aaron", new Date(01,01,2021),"Male","https://firebasestorage.googleapis.com/v0/b/babygotapp.appspot.com/o/Profiles%2Faaron.jpg?alt=media&token=7e4214db-87f0-4330-b7c3-86e76262769a");
        BabyProfile babyProfile2 = new BabyProfile("2","Lucy", new Date(02,02,2021),"Female","https://firebasestorage.googleapis.com/v0/b/babygotapp.appspot.com/o/Profiles%2Flucy.jpg?alt=media&token=270b695e-4e8e-4df9-8e4e-7b2466fcf600");

        FirebaseDatabase.getInstance().getReference().child("BabyProfiles").child(babyProfile1.getId()).setValue(babyProfile1);
        FirebaseDatabase.getInstance().getReference().child("BabyProfiles").child(babyProfile2.getId()).setValue(babyProfile2);

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


    /**
     * Called when the user clicks on the White Noise button. For testing purposes.
     * User will be brought to the WhiteNoisePresenterActivity
     */
    public void whiteNoiseEvent(View view) {
        Intent whiteNoiseIntent = new Intent (this, WhiteNoisePresenterActivity.class);
        startActivity(whiteNoiseIntent);
    }

    public void updateStats(View view) {
        Intent intent = new Intent(this, UpdateStatsActivity.class);
        startActivity(intent);
    }


}