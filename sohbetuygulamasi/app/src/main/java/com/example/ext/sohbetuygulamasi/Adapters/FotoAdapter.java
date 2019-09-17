package com.example.ext.sohbetuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import de.hdodenhof.circleimageview.CircleImageView;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String userId;

    public FotoAdapter(List<String> userKeysList, Activity activity, Context context) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        userId = firebaseUser.getUid();

    }

    //layout tanımlaması yapılacak

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.foto_fragment_layout, parent, false);
        return new FotoAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final FotoAdapter.ViewHolder holder, final int position) {

        reference.child("Kullanicilar").child(userKeysList.get(position)).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);

                    Picasso.get().load(k1.getResim()).into(holder.image);
                    String resim= userKeysList.get(0);
                System.out.println("naaaboypos3: "+resim);
                System.out.println("naaaboypos2: "+k1.getResim());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //adaptera olusturulucak olan listenin size ı burda olucak
    @Override
    public int getItemCount() {
        return userKeysList.size();
    }


    //viewların tanımlanma işleri
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }


    }
}