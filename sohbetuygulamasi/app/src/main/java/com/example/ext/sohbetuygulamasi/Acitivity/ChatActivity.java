package com.example.ext.sohbetuygulamasi.Acitivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.GetDate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class   ChatActivity extends AppCompatActivity {

    TextView chat_username_textview;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FloatingActionButton sendMessageButton;
    EditText mesajTextEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
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
        mesajTextEdittext= findViewById(R.id.mesajTextEdittext);

    }

    public void action()
    {
        final String message = mesajTextEdittext.getText().toString();
        mesajTextEdittext.setText("");
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(firebaseUser.getUid(),getId(),"text", GetDate.getDate(),false,message);
            }
        });
    }

    public void sendMessage(final String userId, final String otherId, String textType, String date, Boolean seen, String messageText)
    {
        final String mesajId =reference.child("Mesajlar").child(userId).child(otherId).push().getKey();
        final Map messageMap= new HashMap();
        messageMap.put("type",textType);
        messageMap.put("time",textType);
        messageMap.put("seen",textType);
        messageMap.put("messageText",textType);

        reference.child("Mesajlar").child(userId).child(otherId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Mesajlar").child(otherId).child(userId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
    }


}

