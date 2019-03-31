package com.example.ext.sohbetuygulamasi.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.example.ext.sohbetuygulamasi.Utils.RandomName;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class KullaniciProfilFragment extends Fragment {

    String imageUrl;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    View view;
    EditText kullaniciIsmi,input_egitim,input_dogumtarih,input_hakkimda;
    CircleImageView profile_image;
    Button bilgiGuncelleButton;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_kullanici_profil, container, false);
        tanimla();
        bilgileriGetir();
        return  view;

    }


    public void tanimla()
    {
        kullaniciIsmi= view.findViewById(R.id.kullaniciIsmi);
        input_egitim= view.findViewById(R.id.input_egitim);
        input_dogumtarih= view.findViewById(R.id.input_dogumTarihi);
        input_hakkimda= view.findViewById(R.id.input_hakkimda);
        profile_image= view.findViewById(R.id.profile_image);
        bilgiGuncelleButton=view.findViewById(R.id.bilgiGuncelleButon);

        firebaseStorage= FirebaseStorage.getInstance();
        storageReference= firebaseStorage.getReference();//resim ekleme icin

        bilgiGuncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        database= FirebaseDatabase.getInstance();
        reference=database.getReference().child("Kullanicilar").child(auth.getUid());
    }







    public void galeriAc(){

        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);   //galeri acıo
        startActivityForResult(intent,5);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==5 && resultCode== Activity.RESULT_OK)
        {
            Uri filePath = data.getData();
            StorageReference ref =storageReference.child("kullaniciresimleri").child(RandomName.getSaltString()+".jpg");
            ref.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(getContext(),"resim guncellendi...", Toast.LENGTH_LONG).show();


                        String isim=kullaniciIsmi.getText().toString();
                        String egitim=input_egitim.getText().toString();
                        String dogum=input_dogumtarih.getText().toString();
                        String hakkimda=input_hakkimda.getText().toString();


                        reference=database.getReference().child("Kullanicilar").child(auth.getUid());
                        Map map = new HashMap();

                        map.put("isim",isim);
                        map.put("egitim",egitim);
                        map.put("dogumtarih",dogum);
                        map.put("hakkimda",hakkimda);
                        map.put("resim",task.getResult().getDownloadUrl().toString());

                        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    ChangeFragment fragment = new ChangeFragment(getContext());
                                    fragment.change(new KullaniciProfilFragment());
                                    Toast.makeText(getContext(), "bilgiler basarili insert edildi...", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "guncellenmedi..", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }

                    else
                    {
                        Toast.makeText(getContext(),"resim guncellenmedi..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }








    public void bilgileriGetir()
    {
            reference.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);
                  Log.i("bilgiler",k1.toString());
   // alternatif alma yontemi        //    String adi = dataSnapshot.child("isim").getValue().toString();
              //      String egitim = dataSnapshot.child("egitim").getValue().toString();


                    kullaniciIsmi.setText(k1.getIsim());
                    input_egitim.setText(k1.getEgitim());
                    input_dogumtarih.setText(k1.getDogumtarih());
                    input_hakkimda.setText(k1.getHakkimda());
                    imageUrl=k1.getResim();

                    if(!k1.getResim().equals("null"))
                    {
                        Picasso.get().load(k1.getResim()).into(profile_image);
                    }


                }
                @Override
                public void onCancelled(DatabaseError databaseError){

                }
            });
    }

    public void guncelle()
    {
        String isim=kullaniciIsmi.getText().toString();
        String egitim=input_egitim.getText().toString();
        String dogum=input_dogumtarih.getText().toString();
        String hakkimda=input_hakkimda.getText().toString();


        reference=database.getReference().child("Kullanicilar").child(auth.getUid());
        Map map = new HashMap();

        map.put("isim",isim);
        map.put("egitim",egitim);
        map.put("dogumtarih",dogum);
        map.put("hakkimda",hakkimda);
        if(imageUrl.equals("null"))
        {
            map.put("resim","null");
        }

        else
        {
            map.put("resim",imageUrl);
        }

        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    ChangeFragment fragment = new ChangeFragment(getContext());
                    fragment.change(new KullaniciProfilFragment());
                    Toast.makeText(getContext(), "bilgiler basarili insert edildi...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),"guncellenmedi..", Toast.LENGTH_LONG).show();
                }

                }
        });
    }
}
