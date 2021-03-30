package com.team6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateStatsActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view5;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar2;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view7;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar3;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view8;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar4;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view9;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stats);
        seek_bar = findViewById(R.id.seekBar);
        text_view5 = findViewById(R.id.textView5);
        seek_bar2 = findViewById(R.id.seekBar2);
        text_view7 = findViewById(R.id.textView7);
        seek_bar3 = findViewById(R.id.seekBar3);
        text_view8 = findViewById(R.id.textView8);
        seek_bar4 = findViewById(R.id.seekBar4);
        text_view9 = findViewById(R.id.textView9);
        seekBar(seek_bar,text_view5);
        seekBar(seek_bar2,text_view7);
        seekBar(seek_bar3,text_view8);
        seekBar(seek_bar4,text_view9);
    }

    @SuppressLint("SetTextI18n")
    public void seekBar(SeekBar seekbar, TextView text_view) {
        if (text_view.equals(text_view5)) {
            text_view.setText(seekbar.getProgress() + " oz");
        } else if (text_view.equals(text_view7)) {
            text_view.setText(seekbar.getProgress() + " hours");
        } else if (text_view.equals(text_view8)) {
            text_view.setText(seekbar.getProgress() + " hours");
        } else if (text_view.equals(text_view9)) {
            text_view.setText(seekbar.getProgress() + " pcs");
        }

        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        if (text_view.equals(text_view5)) {
                            text_view.setText((progress) + " oz");
                        } else if (text_view.equals(text_view7)) {
                            text_view.setText((progress) + " hours");
                        } else if (text_view.equals(text_view8)) {
                            text_view.setText((progress) + " hours");
                        } else if (text_view.equals(text_view9)) {
                            text_view.setText((progress) + " pcs");
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (text_view.equals(text_view5)) {
                            text_view.setText((progress_value) + " oz");
                        } else if (text_view.equals(text_view7)) {
                            text_view.setText((progress_value) + " hours");
                        } else if (text_view.equals(text_view8)) {
                            text_view.setText((progress_value) + " hours");
                        } else if (text_view.equals(text_view9)) {
                            text_view.setText((progress_value) + " pcs");
                        }
                    }
                }
        );

    }

    public void saveData(View view) {
        Context context = getBaseContext();
        text_view5 = findViewById(R.id.textView5);
        text_view7 = findViewById(R.id.textView7);
        text_view8 = findViewById(R.id.textView8);
        text_view9 = findViewById(R.id.textView9);
        String milk = (String) text_view5.getText();
        String tummyTime = (String) text_view7.getText();
        String sleep = (String) text_view8.getText();
        String diapers = (String) text_view9.getText();
        CalendarView test = findViewById(R.id.calendarView4);
        database = FirebaseDatabase.getInstance();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(test.getDate()));
        test.setOnDateChangeListener((CalendarView.OnDateChangeListener) (test1, year, month, dayOfMonth) -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = null;
            String MMM = "";
            if (month == 0) {
                MMM = "January";
            } else if (month == 1) {
                MMM = "February";
            } else if (month == 2) {
                MMM = "March";
            } else if (month == 3) {
                MMM = "April";
            } else if (month == 4) {
                MMM = "May";
            } else if (month == 5) {
                MMM = "June";
            } else if (month == 6) {
                MMM = "July";
            } else if (month == 7) {
                MMM = "August";
            } else if (month == 8) {
                MMM = "September";
            } else if (month == 9) {
                MMM = "October";
            } else if (month == 10) {
                MMM = "November";
            } else if (month == 11) {
                MMM = "December";
            }

            String date1 = dayOfMonth + "-" + MMM + "-" + year;
            try {
                date = (Date)formatter.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long mills = date.getTime();
                test1.setDate(mills);
            });

        Toast.makeText(context, "Stats Saved \n" + selectedDate + " \nMilk: " + milk + " \nTummy-time: " + tummyTime + " \nSleep: " + sleep + " \nDiapers: " + diapers, Toast.LENGTH_LONG).show();
        NumbersUpdate babyStats = new NumbersUpdate( "1", milk, tummyTime, sleep, diapers);
        String key = database.getReference().child("BabyProfiles").child("1").push().getKey();
        Map<String, Object> statUpdate = new HashMap<>();
        statUpdate.put("BabyStats" + key, babyStats);
        database.getReference().child("BabyProfiles").child("1").updateChildren(statUpdate);
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