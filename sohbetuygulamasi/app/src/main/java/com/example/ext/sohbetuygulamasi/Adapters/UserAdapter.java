package com.example.ext.sohbetuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.Acitivity.ChatActivity;
import com.example.ext.sohbetuygulamasi.Fragments.OtherProfileFragment;
import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.Models.MessageModel;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    String userId;


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
                   MessageModel m1= dataSnapshot.getValue(MessageModel.class);

                   auth = FirebaseAuth.getInstance();
                   user = auth.getCurrentUser();
                   userId = user.getUid();
             //      String ac=reference.child("Mesajlar").child(userId).child(userKeysList.get(position).toString()).child("-LknDWcqCk-Ca3e68ZlZ").child("text").getKey().toString();
                   DatabaseReference reff=reference.child("Mesajlar").child("oynJ1Om8KhWOai6MHXPEfQrT3du1").child("6mJhqfBrhNPIhVIgqFdTsgq4txt1").child("-LknDWcqCk-Ca3e68ZlZ").child("text");
                   reff.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           String text = dataSnapshot.getValue().toString();
                           System.out.println("tick: " + text +"....");
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
               //    Query query=ac.orderByKey().limitToLast(1);
                   //System.out.println("tick: " + ac +"....");


                   Boolean seenOrnotseen = Boolean.parseBoolean(dataSnapshot.child("cinsiyetNum").getValue().toString());
                    Boolean arda=false;
                   Boolean userState = Boolean.parseBoolean(dataSnapshot.child("state").getValue().toString());



                       Picasso.get().load(k1.getResim()).into(holder.userimage);
                       holder.usernameTextview.setText(k1.getIsim());
            //           holder.msjTextview.setText();

                       if(arda==true) { holder.msjTextview.setText(""); }

                       else { holder.msjTextview.setText("Mesajınız var"); }

                       if(userState==true)
                       {
                            holder.user_state_img.setImageResource(R.drawable.online_icon);
                       }
                       else
                       {
                           holder.user_state_img.setImageResource(R.drawable.offline_icon);
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
                   fragment.changeWithParameter(new OtherProfileFragment(),userKeysList.get(position));
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
        TextView msjTextview;
        CircleImageView userimage,user_state_img;
        LinearLayout userAnaLayout;


        ViewHolder(View itemView)
        {
            super(itemView);
            usernameTextview= (TextView)itemView.findViewById(R.id.usernameTextview);
            msjTextview=itemView.findViewById(R.id.msjTextview);
            userimage= (CircleImageView)itemView.findViewById(R.id.userimage);
            user_state_img= (CircleImageView)itemView.findViewById(R.id.user_state_img);
            userAnaLayout=itemView.findViewById(R.id.userAnaLayout);
        }
    }
}



