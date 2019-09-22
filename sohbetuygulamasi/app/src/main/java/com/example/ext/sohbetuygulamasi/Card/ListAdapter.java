package com.example.ext.sohbetuygulamasi.Card;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.Adapters.UserAdapter;
import com.example.ext.sohbetuygulamasi.Card.ListItem;
import com.example.ext.sohbetuygulamasi.Fragments.OtherProfileFragment;
import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.ext.sohbetuygulamasi.Card.ListItem.cardimage;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

  List<String> userKeysList;
  Activity activity;
  Context context;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference reference;
  FirebaseAuth auth;
  FirebaseUser user;
  String userId="0",lastMes,ccc;



  public ListAdapter(List<String> userKeysList, Activity activity, Context context) {
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

    View view= LayoutInflater.from(context).inflate(R.layout.dummy_list_item ,parent,false);
    return new ListAdapter.ViewHolder(view);
  }




  @Override
  public void onBindViewHolder(@NonNull final ListAdapter.ViewHolder holder, final int position) {

    //   holder.usernameTextview.setText(userKeysList.get(position).toString());
    reference.child("Kullanicilar").child(userKeysList.get(position).toString()).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        Picasso.get().load(k1.getResim()).into(holder.cardimage);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  //adaptera olusturulucak olan listenin size ı burda olucak
  @Override
  public int getItemCount() {
    System.out.println("adanakeyler12: " + userKeysList.size());
    return userKeysList.size();
  }


  //viewların tanımlanma işleri
  public class ViewHolder extends RecyclerView.ViewHolder
  {

    TextView usernameTextview,msjTextview;
    ImageView cardimage;


    ViewHolder(View itemView)
    {
      super(itemView);
      usernameTextview= (TextView)itemView.findViewById(R.id.usernameTextview);
      cardimage=itemView.findViewById(R.id.cardimage);
    }
  }
  public void removeTopItem() {
    userKeysList.remove(0);
    notifyDataSetChanged();
  }
}




