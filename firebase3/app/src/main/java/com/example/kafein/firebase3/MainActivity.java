package com.example.kafein.firebase3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref1,id,isim,soyisim,yas,durum;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        oku("kisi");
    }

    public void tanimla()
    {
        database= FirebaseDatabase.getInstance();//firebase de olan database imize database objemizi set ettik
        ref1=database.getReference("kisi");
        ref1.setValue("123");

        id=ref1.child("id");
        id.setValue("1");

        isim= ref1.child("isim");
        isim.setValue("arda");

        soyisim=ref1.child("soyisim");
        soyisim.setValue("tel");

        yas=ref1.child("yas");
        yas.setValue(28);

        durum=ref1.child("durum");
        durum.setValue(true);
    }

    public void oku(String anaKey)
    {
        ref1 = database.getReference(anaKey);

        ref1.addValueEventListener(new ValueEventListener() { //tek kırılım oldugu ıcın bu , bunlarında childları olsa getChlid kullanılıcaktı
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("isim", dataSnapshot.getValue().toString());
                User d=dataSnapshot.getValue(User.class);
                Log.i("isim", d.getIsim());
                list.add(d);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
