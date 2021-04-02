package com.team6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom NavBar Setup
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_update_stats, R.id.navigation_white_noise, R.id.navigation_profile, R.id.navigation_calendar)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


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


//    public void goToEvents(View view) {
//        // Add an intent that takes the CalendarPresenterActivity class.
//        // The CalendarPresenterActivity has been added to the manifest.
//        Intent intent = new Intent(this, CalendarPresenterActivity.class);
//
//        // Start an instance of the CalendarPresenterActivity specified by the intent.
//        // This will navigate the user to the activity_view_calendar_view page.
//        startActivity(intent);
//    }


    /**
     * It will profile activity for testing purpose.
     *
     * Edit: I implemented the app menu bar, and reassigned this
     * method to the stroller icon. -Ramos
     */
//    public void goToProfilesEvent(View view) {
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
//    }


    /**
     * Called when the user clicks on the White Noise button. For testing purposes.
     * User will be brought to the WhiteNoisePresenterActivity
     *
     * Edit: I implemented the app menu bar, and reassigned this
     * method to the radio icon. -Ramos
     */
//    public void goToWhiteNoiseEvent(View view) {
//        Intent whiteNoiseIntent = new Intent (this, WhiteNoisePresenterActivity.class);
//        startActivity(whiteNoiseIntent);
//    }

    /**
    Edit: I implemented the app menu bar, and reassigned this
     method to the "add" icon. I-Ramos
     */

//    public void updateStats(View view) {
//        Intent intent = new Intent(this, UpdateStatsActivity.class);
//        startActivity(intent);
//    }


}