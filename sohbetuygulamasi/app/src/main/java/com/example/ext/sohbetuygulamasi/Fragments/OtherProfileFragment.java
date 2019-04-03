package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class OtherProfileFragment extends Fragment {

    TextView denemeText;
    View view;
    String otherId, userId;
    TextView userProfileNameText, userProfileEgitimText, userProfileDogumText, userProfileHakkimdaText,userProfileTakipText,userProfileArkadasText,userProfileNameText2;
    ImageView userProfileArkadasImage,userProfileMesajImage,userProfileTakipImage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference reference_Arkadaslik;
    CircleImageView userProfileProfileImage;
    FirebaseAuth auth;
    FirebaseUser user;
    String kontrol="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        tanimla();
        action();
        return view;
    }


    public void tanimla()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();   //butun referansları alıoruz ıcıne kullanıcılar yazsak bı tek onu alcaktık
        reference_Arkadaslik= firebaseDatabase.getReference().child("Arkadaslik_Istek");
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userId=user.getUid();         //usteki 3 satırla beraber useri aldık


        otherId =getArguments().getString("userid"); // bunun sayesınde tıkladıgımız profilin id sine ulasıyoruz

        userProfileNameText= view.findViewById(R.id.userProfileNameText);
        userProfileEgitimText= view.findViewById(R.id.userProfileEgitimText);
        userProfileDogumText= view.findViewById(R.id.userProfileDogumText);
        userProfileHakkimdaText= view.findViewById(R.id.userProfileHakkimdaText);

        userProfileArkadasImage= view.findViewById(R.id.userProfileArkadasImage);
        userProfileMesajImage= view.findViewById(R.id.userProfileMesajImage);
        userProfileTakipImage= view.findViewById(R.id.userProfileTakipImage);
        userProfileProfileImage= view.findViewById(R.id.userProfileProfileImage);
        userProfileNameText2= view.findViewById(R.id.userProfileNameText2);

        reference_Arkadaslik.child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(userId))
                {
                    kontrol=dataSnapshot.child(userId).child("tip").getValue().toString();

                    userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_off);
                }
                else
                {
                    userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void action()
    {

        reference.child("Kullanicilar").child(otherId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Kullanicilar kl = dataSnapshot.getValue(Kullanicilar.class);//getvalue bize obje dondurdugu ıcın kullanicilar class ına gore cast liyoruz
                userProfileNameText.setText("isim: "+kl.getIsim());
                userProfileEgitimText.setText("egitim: "+kl.getEgitim());
                userProfileDogumText.setText("dogumtarihi: "+kl.getDogumtarih());
                userProfileHakkimdaText.setText("hakkimda: "+kl.getHakkimda());
                userProfileNameText2.setText(kl.getIsim());


                if(!kl.getResim().equals("null")) {
                    Picasso.get().load(kl.getResim()).into(userProfileProfileImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        userProfileArkadasImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kontrol.equals(""))
                {
                    arkadasIptalEt(otherId,userId);
                }
                else
                {
                    arkadasEkle(otherId, userId);
                }
            }
        });
    }

    public void arkadasEkle(final String otherId, final String userId)
    {

        reference_Arkadaslik.child(userId).child(otherId).child("tip").setValue("gonderdi").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    reference_Arkadaslik.child(otherId).child(userId).child("tip").setValue("aldi").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                kontrol="aldi";
                                Toast.makeText(getContext(),"arkadasllik istegi gonderildi",Toast.LENGTH_LONG).show();
                                userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_off);
                            }
                            else
                            {
                                Toast.makeText(getContext(),"bir problem var..",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(),"bir problem var..",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void arkadasIptalEt(final String otherId, final String userId)
    {

        reference_Arkadaslik.child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference_Arkadaslik.child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        kontrol="";
                        userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
                        Toast.makeText(getContext(),"arkadaslik istegi iptal edildi..",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
