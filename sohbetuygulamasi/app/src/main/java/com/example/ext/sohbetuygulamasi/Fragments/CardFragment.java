package com.example.ext.sohbetuygulamasi.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ext.sohbetuygulamasi.Acitivity.CardActivity;
import com.example.ext.sohbetuygulamasi.Adapters.UserAdapter;
import com.example.ext.sohbetuygulamasi.Card.ListAdapter;
import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;


public class CardFragment extends Fragment {

    private ListAdapter adapter;
    List<String> userKeysList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

  //      adapter = new ListAdapter(userKeysList,getActivity(),getContext());
        View view;
        view = inflater.inflate(R.layout.fragment_card, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        tanimla();
        kullanicilariGetir();
        SwipeableTouchHelperCallback swipeableTouchHelperCallback =
                new SwipeableTouchHelperCallback(new OnItemSwiped() {
                    @Override
                    public void onItemSwiped() {
                        adapter.removeTopItem();
                    }

                    @Override
                    public void onItemSwipedLeft() {
                        Log.e("SWIPE", "LEFT");
                    }

                    @Override
                    public void onItemSwipedRight() {
                        Log.e("SWIPE", "RIGHT");
                    }

                    @Override
                    public void onItemSwipedUp() {
                        Log.e("SWIPE", "UP");
                    }

                    @Override
                    public void onItemSwipedDown() {
                        Log.e("SWIPE", "DOWN");
                    }
                })
                {
                    @Override
                    public int getAllowedSwipeDirectionsMovementFlags(RecyclerView.ViewHolder viewHolder) {
                        return ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                    }
                };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeableTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new SwipeableLayoutManager().setAngle(10)
                .setAnimationDuratuion(450)
                .setMaxShowCount(3)
                .setScaleGap(0.1f)
                .setTransYGap(0));
        System.out.println("adanakeyler14: " + userKeysList.size());
        recyclerView.setAdapter(adapter = new ListAdapter(userKeysList,getActivity(),getContext()));

        AppCompatButton button = view.findViewById(R.id.swipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemTouchHelper.swipe(recyclerView.findViewHolderForAdapterPosition(0), ItemTouchHelper.DOWN);
            }

        });

        return view;
    }


    public void tanimla()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        userKeysList= new ArrayList<>();
    }

    public void kullanicilariGetir() {



        reference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //      Log.i( "keyler ",dataSnapshot.getKey());
                reference.child("Kullanicilar").child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Kullanicilar k1 = dataSnapshot.getValue(Kullanicilar.class);

                        userKeysList.add(dataSnapshot.getKey());
                        System.out.println("adanakeyler11: " + userKeysList.toString());
                        System.out.println("adanakeyler15: " + userKeysList.size());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

