package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.ext.sohbetuygulamasi.Acitivity.ChatActivity;
import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ShowToastMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import de.hdodenhof.circleimageview.CircleImageView;



public class OtherProfileFragment extends Fragment {

    TextView denemeText;
    View view;
    String otherId, userId;
    TextView userProfileNameText, userProfileEgitimText, userProfileDogumText, userProfileHakkimdaText, userProfileTakipText, userProfileArkadasText, userProfileNameText2;
    ImageView userProfileArkadasImage, userProfileMesajImage, userProfileTakipImage;
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
        getBegeniText();
        getArkadasText();
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

                    userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_off);
                } else {

                    userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
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
                    userProfileArkadasImage.setImageResource(R.drawable.deletinguser);
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
                    userProfileTakipImage.setImageResource(R.drawable.takip_ok);
                } else {
                    userProfileTakipImage.setImageResource(R.drawable.takip_off);
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
                userProfileNameText.setText("isim: " + kl.getIsim());
                userProfileEgitimText.setText("egitim: " + kl.getEgitim());
                userProfileDogumText.setText("dogumtarihi: " + kl.getDogumtarih());
                userProfileHakkimdaText.setText("hakkimda: " + kl.getHakkimda());
                userProfileNameText2.setText(kl.getIsim());


                if (!kl.getResim().equals("null")) {
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
                if (kontrol.equals("istek")) {
                    arkadasIptalEt(otherId, userId);
                } else if (kontrol.equals("arkadas")) {
                    arkadasTablosundanCikar(otherId, userId);
                } else {
                    arkadasEkle(otherId, userId);
                }
            }
        });

        userProfileTakipImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (begeniKontrol.equals("begendi")) {
                    begeniIptal(userId, otherId);

                } else {
                    begen(userId, otherId);
                }
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

    private void begeniIptal(String userId, String otherId) {

        reference.child("Begeniler").child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                userProfileTakipImage.setImageResource(R.drawable.takip_off);
                begeniKontrol = "";
                showToastMessage.showToast("begenme iptal edildi");
                getBegeniText();

            }
        });
    }

    public void arkadasTablosundanCikar(final String otherId, final String userId) {
        reference.child("Arkadaslar").child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child("Arkadaslar").child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        kontrol = "";
                        userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
                        showToastMessage.showToast("arkadasliktan cıkarıldı..");
                        getArkadasText();
                    }
                });
            }
        });
    }

    public void arkadasEkle(final String otherId, final String userId) {

        reference_Arkadaslik.child(userId).child(otherId).child("tip").setValue("gonderdi").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    reference_Arkadaslik.child(otherId).child(userId).child("tip").setValue("aldi").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                kontrol = "istek";
                                Toast.makeText(getContext(), "arkadasllik istegi gonderildi", Toast.LENGTH_LONG).show();
                                userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_off);

                            } else {
                                showToastMessage.showToast("bir problem var..");
                            }

                        }
                    });
                } else {
                    showToastMessage.showToast("bir problem var..");
                }

            }
        });
    }


    public void arkadasIptalEt(final String otherId, final String userId) {

        reference_Arkadaslik.child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference_Arkadaslik.child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        kontrol = "";
                        userProfileArkadasImage.setImageResource(R.drawable.arkadas_ekle_on);
                        showToastMessage.showToast("arkadaslik istegi iptal edildi..");

                    }
                });
            }
        });
    }

    public void begen(String userId, String otherId) {
        reference.child("Begeniler").child(otherId).child(userId).child("tip").setValue("begendi").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showToastMessage.showToast("profili begendiniz");
                    userProfileTakipImage.setImageResource(R.drawable.takip_ok);
                    begeniKontrol = "begendi";
                    getBegeniText();
                }
            }
        });
    }

    public void getBegeniText() {
        //    userProfileTakipText.setText("0 Begeni");
        //     final List<String> begeniList= new ArrayList<>();
        reference.child("Begeniler").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfileTakipText.setText(dataSnapshot.getChildrenCount() + " Begeni");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getArkadasText() {
        //     final List<String> arkList= new ArrayList<>();
        //    userProfileTakipText.setText("0 Arkadaş");
        reference.child("Arkadaslar").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfileArkadasText.setText(dataSnapshot.getChildrenCount() + " Arkadaş");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
