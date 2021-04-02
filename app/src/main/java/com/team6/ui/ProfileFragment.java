package com.team6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team6.BabyProfile;
import com.team6.BabyProfileAdapter;
import com.team6.NewProfile;
import com.team6.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    RecyclerView rv;
    List<BabyProfile> babyProfiles;
    DatabaseReference databaseReference;
    FloatingActionButton addBabyBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment =  inflater.inflate(R.layout.fragment_profile, container, false);

        rv = fragment.findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decoration=new DividerItemDecoration(getContext(),0);
        rv.addItemDecoration(decoration);
        babyProfiles =new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("BabyProfiles");
        getImageData();

        // Assign Layout Button to addBabyBtn instance variable
        addBabyBtn = (FloatingActionButton) fragment.findViewById(R.id.add_baby_btn);

        // Set onClick Listener that navigates to New Baby Profile activity when clicked
        addBabyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), NewProfile.class);
            startActivity(intent);
        });

        return fragment;
    }


// it gets the babyprofile for each profile and add these to the adapter.
    private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    BabyProfile babyProfile = di.getValue(BabyProfile.class);
                    babyProfiles.add(babyProfile);
                }
                BabyProfileAdapter adapter=new BabyProfileAdapter(babyProfiles, getActivity().getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}