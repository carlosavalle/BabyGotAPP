package com.team6;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: Jaden Mounteer
 * Date: 03/16/2021
 *
 * This class allows the user to press buttons to play different
 * white noises.
 * Used this tutorial as a reference: https://www.stechies.com/add-sound-play-button-click/
 */
public class WhiteNoisePresenterActivity extends AppCompatActivity {

    // Create a variable to represent the rain white noise button
    Button rainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_noise_view);

        // Assign the rain button to a button
        rainButton = (Button)findViewById(R.id.rainButton);

        // Create a media player in order to play the rain sound from the raw directory
        final MediaPlayer rainNoise = MediaPlayer.create(this, R.raw.rain);


        // Add an onclick listener that plays the rain noise when the
        // rain button is pressed
        rainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rainNoise.start();
            }
        });
    }

    //function for going back to dashboard.
    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
     Edit: I implemented the app menu bar, and reassigned this
     method to the "add" icon. I-Ramos
     */

    public void updateStats(View view) {
        Intent intent = new Intent(this, UpdateStatsActivity.class);
        startActivity(intent);
    }

}
