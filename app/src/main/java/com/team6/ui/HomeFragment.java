package com.team6.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team6.NumbersUpdate;
import com.team6.R;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        updateProgressBars();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    public void updateProgressBars() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BabyProfiles").child("1").child("Stats").child("03-31-2021");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NumbersUpdate numbersUpdate = snapshot.getValue(NumbersUpdate.class);

                ProgressBar MilkProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar5);
                ProgressBar SleepProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar2);
                ProgressBar TummyTimeProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar3);
                ProgressBar DiaperProgressBar = (ProgressBar) getView().findViewById(R.id.progressBar4);


        //       int sleepValue = Integer.parseInt( numbersUpdate.getSleep());

                MilkProgressBar.setProgress( 50);
                SleepProgressBar.setProgress( 30);
                TummyTimeProgressBar.setProgress( 70);
                DiaperProgressBar.setProgress( 80);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Data Load Error", "Could not load data from firebase");
            }
        });


    }

}

