package com.team6;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner title;
    Spinner location;
    EditText description;

    // Create a variable to represent the button
    Button addEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the view to be activity_calendar_view
        setContentView(R.layout.activity_calendar_view);


        // Create a spinner object for the title
        Spinner titleSpinner = (Spinner) findViewById(R.id.titleSpinner);

        // Create an adapter, a container that will hold values
        // and integrate them with a title spinner
        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(CalendarPresenterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.titles));

        // Tell the adapter that there will be a list
        titleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the spinner to the adapter
        titleSpinner.setAdapter(titleAdapter);

        // Create a spinner object for the location
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);

        // Create an adapter, a container that will hold values
        // and integrate them with a title location spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(CalendarPresenterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));

        // Tell the adapter that there will be a list
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the spinner to the adapter
        locationSpinner.setAdapter(locationAdapter);


        // Assign the value of the fields to variables
        title = findViewById(R.id.titleSpinner);
        location = findViewById(R.id.locationSpinner);
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
                if (!title.getSelectedItem().toString().isEmpty() && !location.getSelectedItem().toString().isEmpty()
                        && !description.getText().toString().isEmpty()) {

                    // Create a new Intent
                    Intent intent = new Intent(Intent.ACTION_INSERT);

                    // Set the data we will be working with
                    intent.setData(CalendarContract.Events.CONTENT_URI);

                    // Populate the title of the event
                    // getSelectedItem is used here instead of .getText because it comes
                    // from a spinner
                    intent.putExtra(CalendarContract.Events.TITLE, title.getSelectedItem().toString());

                    // Populate the location of the event
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getSelectedItem().toString());

                    // Populate the description of the event
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());

                    // Set the time range of the event to all day
                    //intent.putExtra(CalendarContract.Events.ALL_DAY, true); // true means there is no start or end time

                    // Add any guests to the event
                    //intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com, test2@yahoo.com, test3@yahoo.com");

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