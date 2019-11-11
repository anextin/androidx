package com.dev.ext.sohbetuygulamasi.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.ext.sohbetuygulamasi.Acitivity.ChatActivity;
import com.dev.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.dev.ext.sohbetuygulamasi.R;
import com.dev.ext.sohbetuygulamasi.Utils.ShowToastMessage;
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
    TextView userProfileNameText, userProfileEgitimText, userProfileDogumText, userProfileHakkimdaText, userProfileTakipText, userProfileArkadasText, userProfileNameText2,userProfileNotText;
    ImageView userProfileArkadasImage,  userProfileTakipImage;
    Button userProfileMesajImage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference reference_Arkadaslik;
    CircleImageView userProfileProfileImage;
    FirebaseAuth auth;
    FirebaseUser user;
    String kontrol = "", begeniKontrol = "";
    ShowToastMessage showToastMessage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        tanimla();
        action();
        return view;
    }


    public void tanimla() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();   //butun referansları alıoruz ıcıne kullanıcılar yazsak bı tek onu alcaktık
        reference_Arkadaslik = firebaseDatabase.getReference().child("Arkadaslik_Istek");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();         //usteki 3 satırla beraber useri aldık


        otherId = getArguments().getString("userid"); // bunun sayesınde tıkladıgımız profilin id sine ulasıyoruz

        userProfileTakipText = view.findViewById(R.id.userProfileTakipText);
        userProfileArkadasText = view.findViewById(R.id.userProfileArkadasText);
        userProfileNameText = view.findViewById(R.id.userProfileNameText);
        userProfileEgitimText = view.findViewById(R.id.userProfileEgitimText);
        userProfileDogumText = view.findViewById(R.id.userProfileDogumText);
        userProfileHakkimdaText = view.findViewById(R.id.userProfileHakkimdaText);
        userProfileNotText= view.findViewById(R.id.userProfileNotText);

        userProfileArkadasImage = view.findViewById(R.id.userProfileArkadasImage);
        userProfileMesajImage = view.findViewById(R.id.userProfileMesajImage);
        userProfileTakipImage = view.findViewById(R.id.userProfileTakipImage);
        userProfileProfileImage = view.findViewById(R.id.userProfileProfileImage);
        userProfileNameText2 = view.findViewById(R.id.userProfileNameText2);

        reference_Arkadaslik.child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(userId)) {
                    kontrol = "istek";

   //                 userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_off);
                } else {

     //               userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference.child("Arkadaslar").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(otherId)) {
                    kontrol = "arkadas";
      //              userProfileArkadasImage.setImageResource(R.drawable.deletinguser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference.child("Begeniler").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userId)) {
                    begeniKontrol = "begendi";
         //           userProfileTakipImage.setImageResource(R.drawable.takip_ok);
                } else {
          //          userProfileTakipImage.setImageResource(R.drawable.takip_off);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showToastMessage = new ShowToastMessage(getContext());
    }


    public void action() {

        reference.child("Kullanicilar").child(otherId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Kullanicilar kl = dataSnapshot.getValue(Kullanicilar.class);//getvalue bize obje dondurdugu ıcın kullanicilar class ına gore cast liyoruz
                userProfileNameText.setText("Sahip: " + kl.getEgitim());
                userProfileEgitimText.setText("Irk: " + kl.getIrk());
                userProfileDogumText.setText("Cinsiyet: " + kl.getCinsiyet());
                userProfileHakkimdaText.setText("Semt: " + kl.getIlce());
                userProfileNotText.setText("Hakkında: " + kl.getDogumtarih());
                userProfileNameText2.setText(kl.getIsim());


                if (!kl.getResim().equals("null")) {
                    Picasso.get().load(kl.getResim()).into(userProfileProfileImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        userProfileMesajImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("userName", userProfileNameText2.getText().toString());
                intent.putExtra("id", otherId);
                startActivity(intent);
            }
        });
    }


}
