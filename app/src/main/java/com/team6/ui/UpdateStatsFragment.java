package com.team6.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team6.GlobalVariable;
import com.team6.NumbersUpdate;
import com.team6.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateStatsFragment#} factory method to
 * create an instance of this fragment.
 */
public class UpdateStatsFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar_milk;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view5;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar_tummytime;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view7;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar_sleep;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view8;
    @SuppressLint("StaticFieldLeak")
    private static SeekBar seek_bar_diapers;
    @SuppressLint("StaticFieldLeak")
    private static TextView text_view9;
    FirebaseDatabase database;
    // global profile id
    private GlobalVariable globalVariable;
    private String mDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the profile id
        globalVariable = (GlobalVariable) getContext().getApplicationContext();
        // load stats with the actual date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        mDate= formatter.format(date);
        // called to update stats with the actual date  and check if a profile has been selected.
        if (globalVariable.getIdProfile() != null) {
            updateProgress(mDate);
        }else{
            Toast.makeText(getActivity().getBaseContext(), "Select a profile", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_update_stats, container, false);
        seek_bar_milk = fragment.findViewById(R.id.seekBar);
        text_view5 = fragment.findViewById(R.id.textView5);
        seek_bar_tummytime = fragment.findViewById(R.id.seekBar2);
        text_view7 = fragment.findViewById(R.id.textView7);
        seek_bar_sleep = fragment.findViewById(R.id.seekBar3);
        text_view8 = fragment.findViewById(R.id.textView8);
        seek_bar_diapers = fragment.findViewById(R.id.seekBar4);
        text_view9 = fragment.findViewById(R.id.textView9);
        seekBar(seek_bar_milk, text_view5);
        seekBar(seek_bar_tummytime, text_view7);
        seekBar(seek_bar_sleep, text_view8);
        seekBar(seek_bar_diapers, text_view9);

        // Save Data Button
        Button saveDataBtn = fragment.findViewById(R.id.save_data_btn);

        // Register all click events here instead of the fragment's .xml file.
        saveDataBtn.setOnClickListener(v -> saveData(v));

        //select a date to retrieve Profile stats
        CalendarView calendar = fragment.findViewById(R.id.calendarView4);

        // check selected date
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // set a new format with the date format needed in firebase "MM-dd-yyyy"
                Date date = new Date();
                date.setYear(year - 1900);
                date.setMonth(month);
                date.setDate(dayOfMonth);
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                String strDate= formatter.format(date);
                mDate = strDate;
                // update the progress bar with the date selected
                if (globalVariable.getIdProfile() != null) {
                    updateProgress(strDate);
                }else{
                    Toast.makeText(getActivity().getBaseContext(), "Select a profile", Toast.LENGTH_LONG).show();
                }




            }
        });

        return fragment;
    }

    // update the progress bar with the date selected receive a date
    public void updateProgress(String date) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BabyProfiles").child(globalVariable.getIdProfile()).child("Stats").child(date.trim());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NumbersUpdate numbersUpdate = snapshot.getValue(NumbersUpdate.class);

                if(numbersUpdate != null) {
                    seek_bar_milk.setProgress(Integer.parseInt(numbersUpdate.getMilk()));
                    seek_bar_sleep.setProgress(Integer.parseInt(numbersUpdate.getSleep()));
                    seek_bar_tummytime.setProgress(Integer.parseInt(numbersUpdate.getTummyTime()));
                    seek_bar_diapers.setProgress(Integer.parseInt(numbersUpdate.getDiapers()));
                }else{
                    seek_bar_milk.setProgress(0);
                    seek_bar_sleep.setProgress(0);
                    seek_bar_tummytime.setProgress(0);
                    seek_bar_diapers.setProgress(0);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Data Load Error", "Could not load data from firebase");
            }
        });


    }
    @SuppressLint("SetTextI18n")
    public void seekBar(SeekBar seekbar, TextView text_view) {
        if (text_view.equals(text_view5)) {
            text_view.setText(seekbar.getProgress() + " oz");
        } else if (text_view.equals(text_view7)) {
            text_view.setText(seekbar.getProgress() + " minutes");
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
                            text_view.setText((progress) + " minutes");
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
                            text_view.setText((progress_value) + " minutes");
                        } else if (text_view.equals(text_view8)) {
                            text_view.setText((progress_value) + " hours");
                        } else if (text_view.equals(text_view9)) {
                            text_view.setText((progress_value) + " pcs");
                        }
                    }
                }
        );

    }
    // updates stats on the database.
    public void saveData(View view) {
        Context context = getActivity().getBaseContext();
        String milk = (String) text_view5.getText();
        String tummyTime = (String) text_view7.getText();
        String sleep = (String) text_view8.getText();
        String diapers = (String) text_view9.getText();
        CalendarView calendar = getView().findViewById(R.id.calendarView4);



        if (globalVariable.getIdProfile() != null) {
            //show the new changes to the user
            Toast.makeText(context, "Stats Saved \n" + mDate + " \nMilk: " + milk + " \nTummy-time: " + tummyTime + " \nSleep: " + sleep + " \nDiapers: " + diapers, Toast.LENGTH_LONG).show();
            //create an instance
            database = FirebaseDatabase.getInstance();
            // create a new baby stat with data selected in the activity.
            NumbersUpdate babyStats = new NumbersUpdate( globalVariable.getIdProfile(),String.valueOf( seek_bar_milk.getProgress()), String.valueOf(seek_bar_tummytime.getProgress()), String.valueOf(seek_bar_sleep.getProgress()),String.valueOf( seek_bar_diapers.getProgress()));
            // save stats on the database
            Map<String, Object> statUpdate = new HashMap<>();
            statUpdate.put(mDate, babyStats);
            database.getReference().child("BabyProfiles").child(globalVariable.getIdProfile()).child("Stats").updateChildren(statUpdate);
        }else{
            Toast.makeText(getActivity().getBaseContext(), "Select a profile", Toast.LENGTH_LONG).show();
        }

    }
}