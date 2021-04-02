package com.team6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class BabyProfileAdapter extends RecyclerView.Adapter<BabyProfileAdapter.ViewHolder> {
    List<BabyProfile> babyProfiles;
    Context ct;

    public BabyProfileAdapter(List<BabyProfile> babyProfiles, Context ct) {
        this.babyProfiles = babyProfiles;
        this.ct = ct;
    }

    // create the view for the profiles.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile_list,parent,false);
        return new ViewHolder(view);
    }
// helps to select a profile from the list.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BabyProfile babyProfile= babyProfiles.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when a profile is selected, it save the id in a global variable to be use around the app. Also, it shows a toast message to let know the user the profile selected
                final GlobalVariable globalVariable = (GlobalVariable) ct.getApplicationContext();
                globalVariable.setIdProfile(babyProfile.getId());
                Toast.makeText(ct,babyProfile.getName()+" Has been selected",Toast.LENGTH_SHORT).show();

            }
        });
        //complete each profile view.
        Glide.with(ct)
                .load(babyProfile.getPicture())
                .into(holder.profileimg);
        holder.profilename.setText(babyProfile.getName());

    }

// get the babyprofiles size from the list
    @Override
    public int getItemCount() {
        return babyProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileimg;
        TextView profilename;
        // helps to  populate the item on the recycle view with photo and name
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileimg=itemView.findViewById(R.id.profile_image);
            profilename=itemView.findViewById(R.id.profile_name);
        }
    }

}

