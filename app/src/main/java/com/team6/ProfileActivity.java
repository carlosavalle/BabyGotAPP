package com.team6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {
    RecyclerView rv;
    List<BabyProfile> babyProfiles;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rv=findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration=new DividerItemDecoration(this,0);
        rv.addItemDecoration(decoration);
        babyProfiles =new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("BabyProfiles");
        getImageData();



      /*  mDatabase = FirebaseDatabase.getInstance();
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
        });*/
    }
    private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    BabyProfile articleList = di.getValue(BabyProfile.class);
                    babyProfiles.add(articleList);
                }
                BabyProfileAdapter adapter=new BabyProfileAdapter(babyProfiles,getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}