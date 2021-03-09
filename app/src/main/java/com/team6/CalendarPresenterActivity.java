package com.team6;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This class interacts with the CalendarView.
 * Let's the user schedule an event on the Google Calendar App.
 *
 * Author: Jaden Mounteer
 * Date: 03/08/2021
 */
public class CalendarPresenterActivity extends AppCompatActivity {

    // Create the variables to represent the user input
    EditText title;
    EditText location;
    EditText description;

    // Create a variable to represent the button
    Button addEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be activity_calendar_view
        setContentView(R.layout.activity_calendar_view);

        // Assign the value of the text fields to variables
        title = findViewById(R.id.editTitle);
        location = findViewById(R.id.editLocation);
        description = findViewById(R.id.editDescription);

        // Assign the button to a variable
        addEvent = findViewById(R.id.addEventButton);


        // Add an event listener for when the user clicks on the button
        // The cool thing about listeners is you don't have to go into the
        // UI and assign the button to it. It just listens to see if the
        // user clicks on the button.
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            // When the user clicks the button
            public void onClick(View v) {

                // if none of the text fields are empty
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !description.getText().toString().isEmpty()) {

                    // Create a new Intent
                    Intent intent = new Intent(Intent.ACTION_INSERT);

                    // Set the data we will be working with
                    intent.setData(CalendarContract.Events.CONTENT_URI);

                    // Populate the title of the event
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());

                    // Populate the location of the event
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());

                    // Populate the description of the event
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());

                    // Set the time range of the event to all day
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true); // true means there is no start or end time

                    // Add any guests to the event
                    intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com, test2@yahoo.com, test3@yahoo.com");

                    // Start the intent
                    startActivity(intent);


                }
                // If there are fields that are missing user input...
                else {
                    // Flag the user with a Toast
                    Toast.makeText(CalendarPresenterActivity.this, "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}