package com.team6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BabyProfile babyProfile= babyProfiles.get(position);
        Glide.with(ct)
                .load(babyProfile.getPicture())
                .into(holder.profileimg);

        holder.profilename.setText(babyProfile.getName());

    }

    @Override
    public int getItemCount() {
        return babyProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileimg;
        TextView profilename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileimg=itemView.findViewById(R.id.profile_image);
            profilename=itemView.findViewById(R.id.profile_name);
        }
    }
}

