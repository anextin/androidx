package com.example.kafein.firebase4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref1,ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=FirebaseDatabase.getInstance();
        ref1=database.getReference("dbbb");
        ref2=database.getReference("xxxx");
        UserDetails userDetails= new UserDetails("arda","tel","24");
        ref1.setValue(userDetails);


        Map map = new HashMap();
        map.put("sehir","yozgat");
        map.put("ulke","turkiye");
        ref2.setValue(map);
    }
}
