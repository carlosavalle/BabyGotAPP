package com.team6;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

public class NewProfile extends AppCompatActivity {
    String PicURL = "";
    String KeyDB;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    EditText pickDate;
    String gender = "";
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    Bitmap photo;
    private static final int CAMERA_REQ = 1;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        database = FirebaseDatabase.getInstance();

        //date picker
        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        pickDate = (EditText) findViewById(R.id.date);
        //hide keyboard
        pickDate.requestFocus();
        pickDate.setShowSoftInputOnFocus(false);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });
        // to check radio boxes gender
        RadioButton rbBoy = (RadioButton) findViewById(R.id.RB_boy);
        RadioButton rbGirl = (RadioButton) findViewById(R.id.RB_Girl);

        rbBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    rbGirl.setChecked(false);
                    gender = "Male";
                    // Do your coding
                }
                else{
                    // Do your coding
                }
            }
        });


        rbGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    // Do your coding
                    rbBoy.setChecked(false);
                    gender = "Female";
                }
                else{
                    // Do your coding
                }
            }
        });

    }

    //set the date to the box
    private void setDate() {
        pickDate.setText((mMonthIni + 1) + "-" + mDayIni + "-" + mYearIni+" ");
    }


    //pick the date
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    setDate();

                }

            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }

        return null;
    }

    // check for radio box if checked


    public void takepic (View v){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQ);

    }

    public void Upload (String key){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] b = stream.toByteArray();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Profiles").child(key);

        storageReference.putBytes(b).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                        new OnCompleteListener<Uri>() {

                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                PicURL= task.getResult().toString();
                                System.out.println("here   "+task.getResult().toString());

                                BabyProfile babyProfile = new BabyProfile(key,((EditText)findViewById(R.id.ETName)).getText().toString(), new Date(mYearIni,mMonthIni + 1,mDayIni),gender,PicURL);
                                database.getReference().child("BabyProfiles").child(babyProfile.getId()).setValue(babyProfile);

                            }
                        });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewProfile.this,"failed",Toast.LENGTH_LONG).show();


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQ && resultCode == RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");


            ImageView first = (ImageView) findViewById(R.id.IV_Pic);
            first.setImageBitmap(photo);
           //  Upload();

        }
    }

    public void Create(View v){

        String key = database.getReference().child("BabyProfiles").push().getKey();
        Upload(key);


    }
}