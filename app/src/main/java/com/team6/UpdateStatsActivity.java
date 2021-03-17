package com.team6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stats);
    }

    /**
     * Called when the user clicks on the Events Page button
     * This method is just temporary for testing purposes
     * Navigates the user to the CalendarPresenter where they
     * will be able to schedule an event
     *
     * Edit: I implemented the app menu bar, and reassigned this
     * method to the calendar icon.
     */

    //function for going back to dashboard.
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToEvents(View view) {
        // Add an intent that takes the CalendarPresenterActivity class.
        // The CalendarPresenterActivity has been added to the manifest.
        Intent intent = new Intent(this, CalendarPresenterActivity.class);

        // Start an instance of the CalendarPresenterActivity specified by the intent.
        // This will navigate the user to the activity_view_calendar_view page.
        startActivity(intent);
    }


    /**
     * It will profile activity for testing purpose.
     *
     * Edit: I implemented the app menu bar, and reassigned this
     * method to the stroller icon. -Ramos
     */
    public void goToProfilesEvent(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    /**
     * Called when the user clicks on the White Noise button. For testing purposes.
     * User will be brought to the WhiteNoisePresenterActivity
     *
     * Edit: I implemented the app menu bar, and reassigned this
     * method to the radio icon. -Ramos
     */
    public void goToWhiteNoiseEvent(View view) {
        Intent whiteNoiseIntent = new Intent (this, WhiteNoisePresenterActivity.class);
        startActivity(whiteNoiseIntent);
    }

    /**
     Edit: I implemented the app menu bar, and reassigned this
     method to the "add" icon. I-Ramos
     */

    public void updateStats(View view) {
        Intent intent = new Intent(this, UpdateStatsActivity.class);
        startActivity(intent);
    }
}