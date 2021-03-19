package com.team6;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    // Create variables to represent the buttons
    Button startButton, pauseButton, stopButton;

    // Create a media player used to store and play the audio
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_noise_view);

        // Assign the button variables to the UI buttons
        startButton = (Button)findViewById(R.id.startButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        stopButton = (Button)findViewById(R.id.stopButton);


        // Set an onclick listener to play the audio when the
        // Start button is pressed
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the media player is empty
                if (mediaPlayer==null) {
                    // Link the media player with the audio
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.rain);
                }
                // Start the audio
                mediaPlayer.start();
            }
        });


        // Set an onclick listener to pause the audio when
        // the user clicks on the pause button
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the media player is not empty
                if (mediaPlayer!=null) {
                    mediaPlayer.pause();
                }
            }
        });


        // Set an onclick listener that will stop the audio from playing
        // When the user clicks on the stop button
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the media player is not empty
                if (mediaPlayer!=null) {
                    // Release the audio
                    // This is necessary so we don't encounter any bugs
                    // by playing multiple instances of audio
                    mediaPlayer.release();
                    // Set the media player to null
                    mediaPlayer = null;
                }
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
