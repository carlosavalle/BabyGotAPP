package com.team6.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.team6.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateStatsFragment#} factory method to
 * create an instance of this fragment.
 */
public class UpdateStatsFragment extends Fragment implements View.OnClickListener {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_update_stats, container, false);
        seek_bar = fragment.findViewById(R.id.seekBar);
        text_view5 = fragment.findViewById(R.id.textView5);
        seek_bar2 = fragment.findViewById(R.id.seekBar2);
        text_view7 = fragment.findViewById(R.id.textView7);
        seek_bar3 = fragment.findViewById(R.id.seekBar3);
        text_view8 = fragment.findViewById(R.id.textView8);
        seek_bar4 = fragment.findViewById(R.id.seekBar4);
        text_view9 = fragment.findViewById(R.id.textView9);
        seekBar(seek_bar,text_view5);
        seekBar(seek_bar2,text_view7);
        seekBar(seek_bar3,text_view8);
        seekBar(seek_bar4,text_view9);

        // Save Data Button
        Button saveDataBtn = fragment.findViewById(R.id.save_data_btn);
        saveDataBtn.setOnClickListener(this);

        return fragment;
    }

    /**
     * Register all click events here instead of the fragment's .xml file.
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_data_btn:
                saveData(v);
            default:
                saveData(v);
        }
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
        CalendarView test = getView().findViewById(R.id.calendarView4);
        Long[] test2 = new Long[1];
        test2[0] = test.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(test.getDate()));
        test.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView test, int year, int month, int dayOfMonth) {
                if(test.getDate() != test2[0]){
                    test2[0] = test.getDate();
                    Toast.makeText(test.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                }
            }
        });

        Toast.makeText(context, selectedDate + " milk: " + milk + " tummy-time: " + tummyTime + " sleep: " + sleep + " diapers: " + diapers, Toast.LENGTH_LONG).show();
    }

}