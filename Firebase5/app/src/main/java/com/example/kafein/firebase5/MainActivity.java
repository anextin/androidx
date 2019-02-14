package com.example.kafein.firebase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText userName;
    Button ekle;
    FirebaseDatabase database;
    DatabaseReference ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
    }

    public void tanimla()
    {
        database=FirebaseDatabase.getInstance();
        userName = findViewById(R.id.userName);
        ekle=findViewById(R.id.ekle);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref1=database.getReference("users/"+userName.getText().toString());
                ref1.setValue("");
                User.setUserName(userName.getText().toString());
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
