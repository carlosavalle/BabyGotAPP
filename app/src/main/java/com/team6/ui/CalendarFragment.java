package com.team6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.team6.GlobalVariable;
import com.team6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    // Create the variables to represent the user input
    Spinner titleSpinner;
    Spinner locationSpinner;
    EditText description;

    // Create a variable to represent the button
    Button addEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GlobalVariable globalVariable = (GlobalVariable) getContext().getApplicationContext();
        Log.d("Carlos", globalVariable.getIdProfile());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_calendar, container, false);

        // Create a spinner object for the title
        titleSpinner = (Spinner) fragment.findViewById(R.id.titleSpinnerFrag);

        // Create an adapter, a container that will hold values
        // and integrate them with a title spinner
        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getActivity().getResources().getStringArray(R.array.titles));

        // Tell the adapter that there will be a list
        titleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the spinner to the adapter
        titleSpinner.setAdapter(titleAdapter);

        // Create a spinner object for the location
        locationSpinner = (Spinner) fragment.findViewById(R.id.locationSpinnerFrag);

        // Create an adapter, a container that will hold values
        // and integrate them with a title location spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getActivity().getResources().getStringArray(R.array.locations));

        // Tell the adapter that there will be a list
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the spinner to the adapter
        locationSpinner.setAdapter(locationAdapter);


        // Assign the value of the fields to variables
        description = fragment.findViewById(R.id.editDescription);

        // Assign the button to a variable
        addEvent = fragment.findViewById(R.id.addEventButton);


        // Add an event listener for when the user clicks on the button
        // The cool thing about listeners is you don't have to go into the
        // UI and assign the button to it. It just listens to see if the
        // user clicks on the button.
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            // When the user clicks the button
            public void onClick(View v) {

                // if none of the text fields are empty
                if (!titleSpinner.getSelectedItem().toString().isEmpty() && !locationSpinner.getSelectedItem().toString().isEmpty()
                        && !description.getText().toString().isEmpty()) {

                    // Create a new Intent
                    Intent intent = new Intent(Intent.ACTION_INSERT);

                    // Set the data we will be working with
                    intent.setData(CalendarContract.Events.CONTENT_URI);

                    // Populate the title of the event
                    // getSelectedItem is used here instead of .getText because it comes
                    // from a spinner
                    intent.putExtra(CalendarContract.Events.TITLE, titleSpinner.getSelectedItem().toString());

                    // Populate the location of the event
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, locationSpinner.getSelectedItem().toString());

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
                    Toast.makeText(getContext(), "Please fill all fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragment;
    }
}