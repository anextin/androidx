package com.example.ext.sohbetuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kafein on 4/1/2019.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;

    public UserAdapter(List<String> userKeysList, Activity activity, Context context) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
    }

    //layout tanımlaması yapılacak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.userlayout ,parent,false);
        return new ViewHolder(view);
    }

    //viewlara setlemeler yapılıcak
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.usernameTextview.setText(userKeysList.get(position).toString());

    }

    //adaptera olusturulucak olan listenin size ı burda olucak
    @Override
    public int getItemCount() {
        return userKeysList.size();
    }


    //viewların tanımlanma işleri
    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView usernameTextview;
        CircleImageView userimage;


        ViewHolder(View itemView)
        {
            super(itemView);
            usernameTextview= (TextView)itemView.findViewById(R.id.usernameTextview);
            userimage= (CircleImageView)itemView.findViewById(R.id.userimage);
        }
    }
}



