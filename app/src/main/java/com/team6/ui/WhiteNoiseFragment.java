package com.team6.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhiteNoiseFragment extends Fragment {

    // Create variables to represent the buttons
    Button startButton, pauseButton, stopButton;

    // Create a media player used to store and play the audio
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_white_noise, container, false);

        // Assign the button variables to the UI buttons
        startButton = (Button) fragment.findViewById(R.id.startButton);
        pauseButton = (Button) fragment.findViewById(R.id.pauseButton);
        stopButton = (Button) fragment.findViewById(R.id.stopButton);


        // Set an onclick listener to play the audio when the
        // Start button is pressed
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the media player is empty
                if (mediaPlayer==null) {
                    // Link the media player with the audio
                    mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.rain);
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

        return fragment;
    }
}