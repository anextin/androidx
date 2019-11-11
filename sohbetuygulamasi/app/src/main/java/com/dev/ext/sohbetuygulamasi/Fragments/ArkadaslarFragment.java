package com.dev.ext.sohbetuygulamasi.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.ext.sohbetuygulamasi.Adapters.FriendAdapter;
import com.dev.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ArkadaslarFragment extends Fragment {


    View view;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView friendrecy;
    FriendAdapter friendAdapter;
    List<String> keyList;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_arkadaslar, container, false);
        tanimla();
        getArkadasList();
        return view;
    }

    public void tanimla()
    {
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        keyList= new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference().child("Arkadaslar");

        friendrecy = view.findViewById(R.id.friend_recy);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getContext(),1);
        friendrecy.setLayoutManager(layoutManager);
        friendAdapter = new FriendAdapter(keyList,getActivity(),getContext());
        friendrecy.setAdapter(friendAdapter);

    }


    public void getArkadasList()
    {
        reference.child(firebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i( "mersin",dataSnapshot.getKey());

                if(keyList.indexOf(dataSnapshot.getKey())==-1)
                {
                    keyList.add(dataSnapshot.getKey());
                }

                friendAdapter.notifyDataSetChanged();
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
