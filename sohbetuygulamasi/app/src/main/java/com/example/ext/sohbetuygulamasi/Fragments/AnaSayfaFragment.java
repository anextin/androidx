package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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

import com.example.ext.sohbetuygulamasi.Adapters.UserAdapter;
import com.example.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AnaSayfaFragment extends Fragment {

    Spinner ilcespinner,irkspinner,cinsiyetspinner;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> userKeysList;
    RecyclerView userListRecyclerView;
    View view;
    UserAdapter userAdapter;
    FirebaseAuth auth;
    FirebaseUser user;


    public int spinner_ilceNum;
    public int spinner_irkNum;
    public int spinner_cinsiyetNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view= inflater.inflate(R.layout.fragment_ana_sayfa, container, false);
        tanimla();
        kullanicilariGetir();
        return view;



    }


    public void tanimla()
    {


        userKeysList= new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();

        userListRecyclerView= view.findViewById(R.id.userListRecyclerView);
        RecyclerView.LayoutManager mng=new GridLayoutManager(getContext(),1);   //bunun sayesınde 1 satıra 2 tane layout koyabılecegız
        userListRecyclerView.setLayoutManager(mng);

        userAdapter= new UserAdapter(userKeysList,getActivity(),getContext());
        userListRecyclerView.setAdapter(userAdapter);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();   //kendinin gosterilmemesi icin
    }



    public void kullanicilariGetir()
    {
        reference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //      Log.i( "keyler ",dataSnapshot.getKey());
                reference.child("Kullanicilar").child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);

                        //asagıdaki sartların amacları:
                        //1-kullanıcı ismi girmemis kisiyi kullanıcılar listesine almıyoruz ve hesabını kullanıcı listesinde kullanmıyoruz



                        if(!dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());

                            }
                        }



                        userAdapter.notifyDataSetChanged();
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
