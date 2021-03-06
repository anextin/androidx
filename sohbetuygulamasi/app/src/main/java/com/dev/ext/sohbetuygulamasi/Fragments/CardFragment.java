package com.dev.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dev.ext.sohbetuygulamasi.Card.ListAdapter;
import com.dev.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.dev.ext.sohbetuygulamasi.R;
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
    FirebaseUser user;
    FirebaseAuth auth;
    String loadedspinner_ilce ="Tümü";
    String loadedspinner_irk ="Tümü";
    String loadedspinner_cinsiyet="Tümü";
    int loadedspinner_ilceNum = 0;
    int loadedspinner_irkNum = 0;
    int loadedspinner_cinsiyetNum=0;
    Button cardmsjat;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        SharedPreferences prefs = getContext().getSharedPreferences("giris", Context.MODE_PRIVATE);
        if(prefs.contains("spinner_ilce")) {
            loadedspinner_ilce = prefs.getString("spinner_ilce", null);
            loadedspinner_irk = prefs.getString("spinner_irk", null);
            loadedspinner_cinsiyet = prefs.getString("spinner_cinsiyet", null);
            loadedspinner_ilceNum = prefs.getInt("spinner_ilceNum", 0);
            loadedspinner_irkNum = prefs.getInt("spinner_irkNum", 0);
            loadedspinner_cinsiyetNum = prefs.getInt("spinner_cinsiyetNum", 0);
        }
        else
        {

        }
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
        //    System.out.println("adanakeyler14: " + userKeysList.size());
        recyclerView.setAdapter(adapter = new ListAdapter(userKeysList,getActivity(),getContext()));



        return view;
    }


    public void tanimla()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        userKeysList= new ArrayList<>();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        //     cardmsjat=view.findViewById(R.id.cardmsjat);
        //     cardmsjat.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //       public void onClick(View v) {
        //            ChangeFragment changeFragment= new ChangeFragment((getContext()));
        //           changeFragment.changeWithParameter(new OtherProfileFragment(),userKeysList.get(position));
        //      }
        //   });

    }

    public void kullanicilariGetir() {



        reference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //      Log.i( "keyler ",dataSnapshot.getKey());
                reference.child("Kullanicilar").child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);
                        Log.i( "sisesu ",k1.getIsim());
                        //asagıdaki sartların amacları:
                        //1-kullanıcı ismi girmemis kisiyi kullanıcılar listesine almıyoruz ve hesabını kullanıcı listesinde kullanmıyoruz



                        System.out.println("kayseriii: "+"..."+loadedspinner_ilce+"..."+loadedspinner_irk+"..."+loadedspinner_cinsiyet+"..."+loadedspinner_ilceNum+"..."+loadedspinner_irkNum+"..."+loadedspinner_cinsiyetNum);

                        if (k1.getIlce().equals(loadedspinner_ilce) && k1.getIrk().equals(loadedspinner_irk) && k1.getCinsiyet().equals(loadedspinner_cinsiyet) && !dataSnapshot.getKey().equals(user.getUid()) && !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (k1.getIlce().equals(loadedspinner_ilce) && loadedspinner_irk.equals("Tümü") && loadedspinner_cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (loadedspinner_ilce.equals("Tümü") && k1.getIrk().equals(loadedspinner_irk) && loadedspinner_cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (loadedspinner_ilce.equals("Tümü") && loadedspinner_irk.equals("Tümü") && k1.getCinsiyet().equals(loadedspinner_cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }

                        else if (k1.getIlce().equals(loadedspinner_ilce) && k1.getIrk().equals(loadedspinner_irk) && loadedspinner_cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (k1.getIlce().equals(loadedspinner_ilce) && loadedspinner_irk.equals("Tümü") && k1.getCinsiyet().equals(loadedspinner_cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (loadedspinner_ilce.equals("Tümü") && k1.getIrk().equals(loadedspinner_irk) && k1.getCinsiyet().equals(loadedspinner_cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }


                        else if(loadedspinner_ilce.equals("Tümü")&&loadedspinner_irk.equals("Tümü")&&loadedspinner_cinsiyet.equals("Tümü")&& !dataSnapshot.getKey().equals(user.getUid())&& !k1.getIsim().equals("null")) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                                //                      System.out.println("adanakeyler8: "+userKeysList.toString());
                            }
                        }



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

