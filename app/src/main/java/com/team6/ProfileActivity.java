package com.team6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
       // getImageData();



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

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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


    public void goToEvents(View view) {
        // Add an intent that takes the CalendarPresenterActivity class.
        // The CalendarPresenterActivity has been added to the manifest.
        Intent intent = new Intent(this, CalendarPresenterActivity.class);

        // Start an instance of the CalendarPresenterActivity specified by the intent.
        // This will navigate the user to the activity_view_calendar_view page.
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

    /**
    * Call new profile activity
    * */
    public void goToNewProfile(View view) {

        Intent intent = new Intent(this, NewProfile.class);

        startActivity(intent);
    }
}