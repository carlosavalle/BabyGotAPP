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

import java.text.ParseException;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String date = ""+(month +1)+"-"+dayOfMonth+"-" + year;
                updateProgressBars(date);

                Toast.makeText(getActivity().getBaseContext(), date, 0).show();// TODO Auto-generated method stub

            }
        });

        return fragment;
    }

    public void updateProgressBars(String date) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BabyProfiles").child("1").child("Stats").child("04-02-2021");
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
        Context context = getActivity().getBaseContext();
        String milk = (String) text_view5.getText();
        String tummyTime = (String) text_view7.getText();
        String sleep = (String) text_view8.getText();
        String diapers = (String) text_view9.getText();
        CalendarView calendar = getView().findViewById(R.id.calendarView4);
        database = FirebaseDatabase.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        calendar.setOnDateChangeListener((CalendarView.OnDateChangeListener) (test1, year, month, dayOfMonth) -> {
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
                date = (Date) formatter.parse(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long mills = date.getTime();
            test1.setDate(mills);
        });

        Toast.makeText(context, "Stats Saved \n" + selectedDate + " \nMilk: " + milk + " \nTummy-time: " + tummyTime + " \nSleep: " + sleep + " \nDiapers: " + diapers, Toast.LENGTH_LONG).show();
        final GlobalVariable globalVariable = (GlobalVariable) getContext().getApplicationContext();

        NumbersUpdate babyStats = new NumbersUpdate( globalVariable.getIdProfile(), milk, tummyTime, sleep, diapers);
        //String key = database.getReference().child("BabyProfiles").child("1").push().getKey();

        Map<String, Object> statUpdate = new HashMap<>();
       // statUpdate.put("BabyStats" + new Date(), babyStats);
        statUpdate.put(selectedDate, babyStats);
        database.getReference().child("BabyProfiles").child(globalVariable.getIdProfile()).child("Stats").updateChildren(statUpdate);
    }
}