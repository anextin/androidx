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
import com.google.firebase.database.Query;
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
    DatabaseReference reference,referenceSeen;
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
        loadMessage();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(firebaseUser.getUid()).child("state").setValue(true);


    }




    @SuppressLint("WrongViewCast")
    public void tanimla()
    {
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
                firebaseDatabase= FirebaseDatabase.getInstance();
                referenceSeen = firebaseDatabase.getReference();

                Query query=referenceSeen.child("Mesajlar").child(firebaseUser.getUid()).child(getId()).orderByKey().limitToLast(1);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String lastMessageId=dataSnapshot.getKey();
                        Boolean ggg=Boolean.parseBoolean(dataSnapshot.child("seen").getValue().toString());
                        System.out.println("fox:"+ "---"+ggg+"---"+lastMessageId);
                       referenceSeen.child("Mesajlar").child(firebaseUser.getUid()).child(getId()).child(lastMessageId).child("seen").setValue(true);
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



    public String getId()
    {
        String id = getIntent().getExtras().getString("id").toString();
        return id;
    }



}

