package com.example.kafein.firebase5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    FirebaseDatabase database;
    EditText parola,yas,isim;
    Button kaydet;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database=FirebaseDatabase.getInstance();

        parola = findViewById(R.id.parola);
        yas = findViewById(R.id.yas);
        isim = findViewById(R.id.isim);
        kaydet=findViewById(R.id.kaydet);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=database.getReference("users/"+User.getUserName());
                UserDetails u1=new UserDetails(parola.getText().toString(),yas.getText().toString(),isim.getText().toString());
                ref.setValue(u1);

            }
        });


    }
}
