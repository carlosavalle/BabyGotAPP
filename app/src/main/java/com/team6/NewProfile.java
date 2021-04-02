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
import android.widget.SeekBar;
import android.widget.TextView;
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

public class NewProfile extends AppCompatActivity implements  View.OnClickListener {
    private String PicURL = "";
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    private EditText pickDate;
    private String gender = "";
    static final int DATE_ID = 0;
    private Calendar C = Calendar.getInstance();
    private Bitmap photo;
    private static final int CAMERA_REQ = 1;

    private FirebaseDatabase database;
    private RadioButton rbBoy, rbGirl;
    private static SeekBar mSB_milk, mSB_tummy, mSB_sleep, mSB_diapers;

    //seek bar
    private static TextView mTX_milk;
    private static TextView mTX_sleep;
    private static TextView mTX_tammy;
    private static TextView mTX_diapear;


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
        pickDate.setOnClickListener(this);

        // to check radio boxes gender
         rbBoy = findViewById(R.id.RB_boy);
         rbBoy.setOnClickListener(this);
         rbGirl = findViewById(R.id.RB_Girl);
         rbGirl.setOnClickListener(this);

         // set on click listener for save button and take picture
        findViewById(R.id.btn_Create).setOnClickListener(this);
        findViewById(R.id.btnTakePic).setOnClickListener(this);

        // Seek Bar local variables
        mSB_milk = findViewById(R.id.sb_milk);
        mSB_diapers = findViewById(R.id.sb_diapers);
        mSB_tummy = findViewById(R.id.sb_tummy);
        mSB_sleep = findViewById(R.id.sb_sleep);
        mTX_milk = findViewById(R.id.tx_milk);
        mTX_sleep = findViewById(R.id.tx_sleep);
        mTX_tammy = findViewById(R.id.tx_tammy);
        mTX_diapear = findViewById(R.id.tx_diaper);

        // change text on text box of each seekbar
        seekBar(mSB_milk, findViewById(R.id.tx_milk));
        seekBar(mSB_diapers, findViewById(R.id.tx_diaper));
        seekBar(mSB_tummy, findViewById(R.id.tx_tammy));
        seekBar(mSB_sleep, findViewById(R.id.tx_sleep));


    }

    public void seekBar(SeekBar seekbar, TextView text_view) {
        // set the inicial seekbar textbox text
        if (text_view.equals(mTX_milk)) {
            text_view.setText("Milk -"+ seekbar.getProgress() + " oz");
        } else if (text_view.equals(mTX_sleep)) {
            text_view.setText("Sleep -" + seekbar.getProgress() + " hours");
        } else if (text_view.equals(mTX_tammy)) {
            text_view.setText("Tummy-time -" + seekbar.getProgress() + " hours");
        } else if (text_view.equals(mTX_diapear)) {
            text_view.setText("Diapers -" + seekbar.getProgress() + " pcs");
        }

        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    @Override
                    // change text box according to seek bar changes
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        if (text_view.equals(mTX_milk)) {
                            text_view.setText("Milk -"+(progress) + " oz");
                        } else if (text_view.equals(mTX_sleep)) {
                            text_view.setText(("Sleep -" +progress) + " hours");
                        } else if (text_view.equals(mTX_tammy)) {
                            text_view.setText(("Tummy-time -" +progress) + " hours");
                        } else if (text_view.equals(mTX_diapear)) {
                            text_view.setText(("Diapers -"+progress) + " pcs");
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // no used
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // no used
                    }


                }
        );

    }


    //set the date to the box
    private void setDate() {
        pickDate.setText((mMonthIni + 1) + "-" + mDayIni + "-" + mYearIni+" ");
    }


    //pick the date from calendar, and save the value on each variable.
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    setDate();

                }

            };



    // it upload a picture to firebase
    public void Upload (String key){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // compress the picture from the camera and save it in bytes
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] b = stream.toByteArray();

        // get the storage references in Firebase
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Profiles").child(key);
        storageReference.putBytes(b).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(
                        new OnCompleteListener<Uri>() {
                            //checks if the picture as been updated, when it is compleate it saves the new baby profile on the database.
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                PicURL= task.getResult().toString();
                                System.out.println("here   "+task.getResult().toString());

                                BabyProfile babyProfile = new BabyProfile(key,((EditText)findViewById(R.id.ETName)).getText().toString(), new Date(mYearIni,mMonthIni + 1,mDayIni),gender,PicURL,mSB_milk.getProgress(),mSB_tummy.getProgress(),mSB_sleep.getProgress(),mSB_diapers.getProgress());
                                database.getReference().child("BabyProfiles").child(babyProfile.getId()).setValue(babyProfile);

                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            // if it did not upload, it will shows a message that it fails.
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewProfile.this,"failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    //check if the camera took the picture and save it into photo cariable.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the picture has been taken
        if (requestCode == CAMERA_REQ && resultCode == RESULT_OK) {
            // save the picture in the local variable
            photo = (Bitmap) data.getExtras().get("data");
            // show the picture in the image view.
            ImageView iv = (ImageView) findViewById(R.id.IV_Pic);
            iv.setImageBitmap(photo);

        }
    }

    // it will check the onClick event for the activity elements
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // check for radio gender box if checked
            case R.id.RB_boy:
                if(((RadioButton) v).isChecked()){
                    rbGirl.setChecked(false);
                    gender = "Male";
                }
                break;
            case R.id.RB_Girl:
                if(((RadioButton) v).isChecked()){
                    rbBoy.setChecked(false);
                    gender = "Female";
                }
                break;
                // check for calendar show up
            case R.id.date:
                showDialog(DATE_ID);
                break;
                // will create a id key and send it to upload
            case R.id.btn_Create:
                String mName = ((EditText)findViewById(R.id.ETName)).getText().toString().trim();
                String mDate = ((EditText)findViewById(R.id.date)).getText().toString().trim();
                // check if the new profile is compleate
                if ((photo != null) && (gender != "") && (!mName.equals("Name")) &&  (!mDate.equals("Birthday") )) {
                    // generate a new key for the profile
                    String key = database.getReference().child("BabyProfiles").push().getKey();
                    // create the profile and upload the picture.
                    Upload(key);
                    // show message of status
                    Toast.makeText(this,"New Profile Saved",Toast.LENGTH_SHORT).show();
                    // goes back to profile page
                    this.onBackPressed();

                }else{
                    // show message of status
                    Toast.makeText(this,"Please complete the profile",Toast.LENGTH_SHORT).show();
                }
                break;

            // it takes a picture with the phone camera
            case R.id.btnTakePic:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQ);
                break;
        }
    }
    // show calendar and send local date variable
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }

        return null;
    }

}