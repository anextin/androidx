package com.example.ext.sohbetuygulamasi.Card;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListItem> {

  List<String> userKeysList;
  Activity activity;
  Context context;
  FirebaseDatabase firebaseDatabase;
  DatabaseReference reference;
  FirebaseAuth auth;
  FirebaseUser user;


  private List<Integer> items = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

  @NonNull @Override public ListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ListItem(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.dummy_list_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ListItem holder, int position) {

    holder.bind(items.get(position).intValue());

  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<Integer> getItems() {
    return items;
  }

  public void removeTopItem() {
    items.remove(0);
    notifyDataSetChanged();
  }
}
