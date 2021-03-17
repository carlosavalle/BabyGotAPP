package com.team6;

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

}
