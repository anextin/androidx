package com.dev.ext.sohbetuygulamasi.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.ext.sohbetuygulamasi.Adapters.Friend_Req_Adapter;
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


public class BildirimFragment extends Fragment {

    View view;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String userId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> friend_req_key_list;
    RecyclerView friend_req_recy;
    Friend_Req_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_bildirim, container, false);
        tanimla();
        istekler();
        return view;
    }


    public void tanimla()
    {
        auth= FirebaseAuth.getInstance();
        firebaseUser= auth.getCurrentUser();
        userId= firebaseUser.getUid();
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Arkadaslik_Istek");
        friend_req_key_list= new ArrayList<>();
        friend_req_recy = view.findViewById(R.id.friend_req_recy);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        friend_req_recy.setLayoutManager(layoutManager);
        adapter= new Friend_Req_Adapter(friend_req_key_list,getActivity(),getContext());
        friend_req_recy.setAdapter(adapter);
    }

    public void istekler()
    {

        reference.child(userId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    String kontrol = dataSnapshot.child("tip").getValue().toString();
                    if (kontrol.equals("aldi")) {
                        if (friend_req_key_list.indexOf(dataSnapshot.getKey())==-1) {
                            friend_req_key_list.add(dataSnapshot.getKey());
                        }
                        adapter.notifyDataSetChanged();
                    }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                friend_req_key_list.remove(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
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
