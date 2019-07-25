package com.example.ext.sohbetuygulamasi.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class   ChatActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
        loadMessage();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(final String userId, String registrationId) {
                System.out.println("userID: "+userId);

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

        reference.child("Mesajlar").child(userId).child(otherId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Mesajlar").child(otherId).child(userId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        String deneme="ec497fe0-9347-4522-8a03-10a9b21eb8a7";
                        try {
                            OneSignal.postNotification(new JSONObject("{'contents': {'en':'mesaj geldi'}, 'include_player_ids': ['" + otherplayerid + "']}"), null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        //onesignal




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
                System.out.println("sasigursel: "+otherplayerid );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //     return playerid;
    }



}

