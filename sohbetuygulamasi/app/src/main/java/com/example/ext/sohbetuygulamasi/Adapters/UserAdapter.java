package com.example.ext.sohbetuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.Fragments.UserProfileFragment;
import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kafein on 4/1/2019.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    public UserAdapter(List<String> userKeysList, Activity activity, Context context) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

     //   holder.usernameTextview.setText(userKeysList.get(position).toString());
           reference.child("Kullanicilar").child(userKeysList.get(position).toString()).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);
                   if(!k1.getIsim().equals("null"))
                   {
                       Picasso.get().load(k1.getResim()).into(holder.userimage);
                       holder.usernameTextview.setText(k1.getIsim());
                   }
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });

           holder.userAnaLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ChangeFragment fragment= new ChangeFragment(context);
                   fragment.changeWithParameter(new UserProfileFragment(),userKeysList.get(position));
               }
           });
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
        LinearLayout userAnaLayout;


        ViewHolder(View itemView)
        {
            super(itemView);
            usernameTextview= (TextView)itemView.findViewById(R.id.usernameTextview);
            userimage= (CircleImageView)itemView.findViewById(R.id.userimage);
            userAnaLayout=itemView.findViewById(R.id.userAnaLayout);
        }
    }
}



