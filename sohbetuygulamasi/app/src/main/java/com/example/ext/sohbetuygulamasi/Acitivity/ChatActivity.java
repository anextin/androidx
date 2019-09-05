package com.example.ext.sohbetuygulamasi.Acitivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.Adapters.MessageAdapter;
import com.example.ext.sohbetuygulamasi.Models.MessageModel;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.GetDate;
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
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class   ChatActivity extends AppCompatActivity {

    String checkonlinefalseornot="false";
    static String  mesajatankim="";
    String asa="";
    String mesajicerik="",mesajtime="";
    TextView chat_username_textview;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    Button sendMessageButton;
    EditText mesajTextEdittext;
    List<MessageModel> messageModelList;
    RecyclerView chat_recycler_view;
    MessageAdapter messageAdapter;
    List<String> keyList;
    Button backbutton;
    String otherplayerid;
    CircleImageView userimage,user_state_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
        loadMessage();
        loadimageAndStatus(getId());

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(true);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(final String userId, String registrationId) {


                UUID uuid = UUID.randomUUID();
                final String uuidString =uuid.toString();

                getUserName();
                getId();
                saveIdforNot(firebaseUser.getUid(),getId(),userId);

                //           try {
                //             OneSignal.postNotification(new JSONObject("{'contents': {'en':'Test Message'}, 'include_player_ids': ['" + userId + "']}"), null);
                //          } catch (JSONException e) {
                //            e.printStackTrace();
                //      }




            }
        });
    }

    public String getUserName()
    {
        Bundle bundle = getIntent().getExtras();
        String userName=bundle.getString("userName");
        return userName;
    }

    public String getId()
    {
        String id = getIntent().getExtras().getString("id").toString();
        return id;
    }


    @SuppressLint("WrongViewCast")
    public void tanimla()
    {
        getUserName();
        chat_username_textview =findViewById(R.id.chat_username_textview);
        chat_username_textview.setText(getUserName());
        auth= FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        sendMessageButton= findViewById(R.id.sendMessageButton);
        backbutton= findViewById(R.id.backbutton);
        mesajTextEdittext= findViewById(R.id.mesajTextEdittext);
        messageModelList = new ArrayList<>();
        keyList = new ArrayList<>();



        user_state_img= findViewById(R.id.user_state_img);
        userimage=findViewById(R.id.userimage);

        chat_recycler_view=findViewById(R.id.chat_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChatActivity.this,1);
        chat_recycler_view.setLayoutManager(layoutManager);
        messageAdapter= new MessageAdapter(keyList,ChatActivity.this,ChatActivity.this,messageModelList);
        chat_recycler_view.setAdapter(messageAdapter);

    }

    public void action()
    {

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ChatActivity.this, AnaActivity.class);
                startActivity(intent);
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String message = mesajTextEdittext.getText().toString();
                mesajTextEdittext.setText("");
                sendMessage(firebaseUser.getUid(),getId(),"text", GetDate.getDate(),false,message);

            }
        });
    }

    public void sendMessage(final String userId, final String otherId, String textType, String date, Boolean seen, String messageText) {


        final String mesajId = reference.child("Mesajlar").child(userId).child(otherId).push().getKey();
        final Map messageMap = new HashMap();
        messageMap.put("type", textType);
        messageMap.put("time", date);
        messageMap.put("seen", seen);
        messageMap.put("text", messageText);
        messageMap.put("from", userId);


        FirebaseAuth auth;
        FirebaseUser user;
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

//checkonline or not
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference checkonline = firebaseDatabase.getReference().child("Kullanicilar");



   //     checkonline.child(otherId).child("state");
   //     notifydetay.child(userId).child(otherId).child("text");


        DatabaseReference statefinder = checkonline.child(otherId);
        statefinder.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 checkonlinefalseornot=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //checkonline or not

        reference.child("Mesajlar").child(userId).child(otherId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Mesajlar").child(otherId).child(userId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

//notification işlermeri
                       if(checkonlinefalseornot.equals("false")) {

                           FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                           DatabaseReference checkonline = firebaseDatabase.getReference().child("Kullanicilar");
                           DatabaseReference namefinderReferance = checkonline.child(userId).child("isim");
                           DatabaseReference notifydetay = firebaseDatabase.getReference().child("Mesajlar");

                           DatabaseReference textfinder=notifydetay.child(userId).child(otherId).child(mesajId);
                           textfinder.child("text").addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   mesajicerik=dataSnapshot.getValue().toString();
                                   System.out.println("neziii: "+mesajicerik );
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });

                           textfinder.child("time").addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   mesajtime=dataSnapshot.getValue().toString();
                                   System.out.println("neziiii: "+otherplayerid );
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });

                           namefinderReferance.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {

                                   String mesajatankim;
                                   mesajatankim= dataSnapshot.getValue().toString();

                                   String arda= "Kimden: "+mesajatankim+" Mesaj: "+mesajicerik+" --"+mesajtime;

                                   try {

                                       OneSignal.postNotification(new JSONObject("{'contents': {'en':'"+arda+"'}, 'include_player_ids': ['" + otherplayerid + "']}"), null);


                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });

                       }
                    }
                });
            }
        });




    }


    public void loadMessage()
    {
        reference.child("Mesajlar").child(firebaseUser.getUid()).child(getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageModel messageModel =dataSnapshot.getValue(MessageModel.class);
                messageModelList.add(messageModel);
                messageAdapter.notifyDataSetChanged();
                keyList.add(dataSnapshot.getKey());
                chat_recycler_view.scrollToPosition(messageModelList.size()-1);
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

    public void saveIdforNot(final String userId,final String otherid,final String playerid)
    {
        System.out.println("haygursel: "+userId+otherid+playerid);
        final Map messageMap = new HashMap();
//        messageMap.put("from", userId);
//        messageMap.put("to", otherid);
        messageMap.put("playerid", playerid);

        reference.child("saveIdforNot").child(userId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });



        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("saveIdforNot").child(otherid).child("playerid");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                otherplayerid = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    protected void onDestroy(){
        super.onDestroy();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(true);
    }
/*
    @Override
    protected void onStop() {
        super.onStop();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(false);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(false);
    }


        public void loadimageAndStatus(String otherid)
        {

            DatabaseReference Resimreference = firebaseDatabase.getReference().child("Kullanicilar").child(otherid);


            Resimreference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String resimid=dataSnapshot.child("resim").getValue().toString();
                    System.out.println("sasigursel: "+resimid );
                    Picasso.get().load(resimid).into(userimage);
                    Boolean status=Boolean.parseBoolean(dataSnapshot.child("state").getValue().toString());
                    System.out.println("sasigursel1: "+status );
                    if(status==true)
                    {
                        user_state_img.setImageResource(R.drawable.online_icon);
                    }
                    else
                    {
                        user_state_img.setImageResource(R.drawable.offline_icon);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }






}

