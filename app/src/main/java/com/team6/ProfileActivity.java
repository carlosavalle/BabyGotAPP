package com.team6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference("BabyProfiles");
        mDbRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                BabyProfile babyProfile = snapshot.getValue(BabyProfile.class);
                Log.d("Name", babyProfile.getName());
            }

            @Override
            public void onCancelled( DatabaseError error) {
                Log.d("Error", error.getDetails());
            }
        });
    }
}