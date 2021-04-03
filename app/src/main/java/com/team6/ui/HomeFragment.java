package com.team6.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Global Selector
    private GlobalVariable globalVariable;
    boolean profileSelected = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);
        globalVariable = (GlobalVariable) getContext().getApplicationContext();

        if (globalVariable.getIdProfile() != null) {
            updateProgressBars(fragment);
        }
        else {
            Toast.makeText(getActivity().getBaseContext(), "Select a profile", Toast.LENGTH_LONG).show();
        }

        return fragment;
    }



    public void updateProgressBars(View fragment) {

        Date date = new Date();
        SimpleDateFormat monthDayYear = new SimpleDateFormat("MM-dd-yyyy");
        String currentDate = monthDayYear.format(date);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BabyProfiles").child(globalVariable.getIdProfile()).child("Stats"); //Global Needed Here
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ProgressBar milkProgressBar = (ProgressBar) fragment.findViewById(R.id.progressBar2);
                ProgressBar sleepProgressBar = (ProgressBar) fragment.findViewById(R.id.progressBar4);
                ProgressBar tummyTimeProgressBar = (ProgressBar) fragment.findViewById(R.id.progressBar5);
                ProgressBar diaperProgressBar = (ProgressBar) fragment.findViewById(R.id.progressBar3);

                TextView textViewMilk = fragment.findViewById(R.id.textView);
                TextView textViewDiapers = fragment.findViewById(R.id.textView3);
                TextView textViewTummyTime = fragment.findViewById(R.id.textView6);
                TextView textViewSleep = fragment.findViewById(R.id.textView4);

                if (snapshot.child(currentDate).exists()) {

                    Log.w("KEY", globalVariable.getIdProfile());
                    Log.w("CurrentDate", currentDate);
                    NumbersUpdate numbersUpdate = snapshot.child(currentDate).getValue(NumbersUpdate.class);

                    int sleepValue = Integer.parseInt(numbersUpdate.getSleep().split(" ")[0]);
                    int tummyTimeValue = Integer.parseInt(numbersUpdate.getTummyTime().split(" ")[0]);
                    int diaperValue = Integer.parseInt(numbersUpdate.getDiapers().split(" ")[0]);
                    int milkValue = Integer.parseInt(numbersUpdate.getMilk().split(" ")[0]);

                    sleepProgressBar.setProgress(0);
                    tummyTimeProgressBar.setProgress(0);
                    milkProgressBar.setProgress(0);
                    diaperProgressBar.setProgress(0);

                    sleepProgressBar.setProgress(sleepValue);
                    tummyTimeProgressBar.setProgress(tummyTimeValue);
                    milkProgressBar.setProgress(milkValue);
                    diaperProgressBar.setProgress(diaperValue);

                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    Log.w("Database Reference", "does not exists");

                    sleepProgressBar.setProgress(0);
                    tummyTimeProgressBar.setProgress(0);
                    milkProgressBar.setProgress(0);
                    diaperProgressBar.setProgress(0);


                    NumbersUpdate babyStats = new NumbersUpdate(globalVariable.getIdProfile(), "0", "0", "0", "0"); //Global Needed Here
                    Map<String, Object> statUpdate = new HashMap<>();
                    statUpdate.put(currentDate, babyStats);
                    database.getReference().child("BabyProfiles").child(globalVariable.getIdProfile()).child("Stats").updateChildren(statUpdate); //Global needed Here

                }

                sleepProgressBar.setMax(12);
                tummyTimeProgressBar.setMax(15);
                milkProgressBar.setMax(20);
                diaperProgressBar.setMax(6);

                textViewMilk.setText(sleepProgressBar.getProgress() + "/20");
                textViewDiapers.setText(diaperProgressBar.getProgress() + "/6");
                textViewSleep.setText(sleepProgressBar.getProgress() + "/12");
                textViewTummyTime.setText(tummyTimeProgressBar.getProgress() + "/15");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Data Load Error", "Could not load data from firebase");
            }
        });


    }

}

