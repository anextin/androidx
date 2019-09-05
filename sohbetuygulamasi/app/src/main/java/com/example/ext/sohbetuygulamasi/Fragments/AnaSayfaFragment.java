package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
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
    Button filtreleButton , filterAc;
    public String spinner_ilce="Tümü";
    public String spinner_irk="Tümü";
    public String spinner_cinsiyet="Tümü";
    LinearLayout filtre;


    public int spinner_ilceNum;
    public int spinner_irkNum;
    public int spinner_cinsiyetNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view= inflater.inflate(R.layout.fragment_ana_sayfa, container, false);

        filtre=view.findViewById(R.id.filtre);
        filtre.setVisibility(filtre.INVISIBLE);
        ilcespinner =view.findViewById(R.id.anasayfailceSpinner);
        irkspinner =view.findViewById(R.id.anasayfairkSpinner);
        cinsiyetspinner =view.findViewById(R.id.anasayfacinsiyetSpinner);
        filtreleButton=view.findViewById(R.id.filtreleButton);
        filterAc=view.findViewById(R.id.filterAc);
        tanimla();
        kullanicilariGetir(spinner_ilce,spinner_irk,spinner_cinsiyet);
        //     kullanicilariGetir();
        return view;
    }


    public void tanimla()
    {

        final String ilceler[]={"Tümü","Adalar","Arnavutköy","Ataşehir","Avcılar","Bağcılar","Bahçelievler","Bakırköy","Başaksehir","Bayrampaşa","Beşiktaş","Beykoz","Beylikdüzü","Beyoğlu","Büyükçekmece","Çatalca","Çekmeköy","Esenler","Esenyurt","Eyüp","Fatih","Gaziosmanpaşa","Güngören","Kadıköy","Kağıthane","Kartal","Küçükçekmece","Maltepe","Pendik","Sancaktepe","Sarıyer","Şile","Silivri","Şişli","Sultanbeyli","Sultangazi","Tuzla","Ümraniye","Üsküdar","Zeytinburnu"};

        final String irk[]={"Tümü","golden","pug","doberman","kurt"};

        final String cinsiyet[]={"Tümü","Erkek","Dişi"};


        userKeysList= new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();

        userListRecyclerView= view.findViewById(R.id.userListRecyclerView);
        RecyclerView.LayoutManager mng=new GridLayoutManager(getContext(),2);   //bunun sayesınde 1 satıra 2 tane layout koyabılecegız
        userListRecyclerView.setLayoutManager(mng);

        userAdapter= new UserAdapter(userKeysList,getActivity(),getContext());
        userListRecyclerView.setAdapter(userAdapter);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();   //kendinin gosterilmemesi icin


        ArrayAdapter<String> ilcelerdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ilceler);
        ilcelerdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilcespinner.setAdapter(ilcelerdataAdapter);

        ArrayAdapter<String> irkdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, irk);
        irkdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        irkspinner.setAdapter(irkdataAdapter);

        ArrayAdapter<String> cinsiyetdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, cinsiyet);
        cinsiyetdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinsiyetspinner.setAdapter(cinsiyetdataAdapter);

        filtreleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinner_ilce=ilcespinner.getSelectedItem().toString();
                int spinner_ilceNum= (int) ilcespinner.getSelectedItemId();

                String spinner_irk=irkspinner.getSelectedItem().toString();
                int spinner_irkNum= (int) irkspinner.getSelectedItemId();

                String spinner_cinsiyet=cinsiyetspinner.getSelectedItem().toString();
                int spinner_cinsiyetNum= (int) cinsiyetspinner.getSelectedItemId();
                //      filtrele(spinner_ilce,spinner_irk,spinner_cinsiyet);
                kullanicilariGetir(spinner_ilce,spinner_irk,spinner_cinsiyet);
                userKeysList.clear();

            }
        });

        filterAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filtre.getVisibility()==View.VISIBLE)
                {
                    filtre.setVisibility(filtre.INVISIBLE);
                }
                else
                {
                    filtre.setVisibility(filtre.VISIBLE);
                }
            }
        });


    }



    public void kullanicilariGetir(final String ilce, final String irk, final String cinsiyet)
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


                        if (k1.getIlce().equals(ilce) && k1.getIrk().equals(irk) && k1.getCinsiyet().equals(cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (k1.getIlce().equals(ilce) && irk.equals("Tümü") && cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (ilce.equals("Tümü") && k1.getIrk().equals(irk) && cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (ilce.equals("Tümü") && irk.equals("Tümü") && k1.getCinsiyet().equals(cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }

                        else if (k1.getIlce().equals(ilce) && k1.getIrk().equals(irk) && cinsiyet.equals("Tümü") && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (k1.getIlce().equals(ilce) && irk.equals("Tümü") && k1.getCinsiyet().equals(cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }
                        else if (ilce.equals("Tümü") && k1.getIrk().equals(irk) && k1.getCinsiyet().equals(cinsiyet) && !dataSnapshot.getKey().equals(user.getUid())) {
                            if (userKeysList.indexOf(dataSnapshot.getKey()) == -1) {
                                userKeysList.add(dataSnapshot.getKey());
                            }
                        }


                        else if(ilce.equals("Tümü")&&irk.equals("Tümü")&&cinsiyet.equals("Tümü")&& !dataSnapshot.getKey().equals(user.getUid())) {
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

    public void filreAc()
    {

    }



}
