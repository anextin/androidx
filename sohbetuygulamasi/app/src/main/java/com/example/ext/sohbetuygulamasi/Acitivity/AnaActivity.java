package com.example.ext.sohbetuygulamasi.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.Fragments.AnaSayfaFragment;
import com.example.ext.sohbetuygulamasi.Fragments.BildirimFragment;
import com.example.ext.sohbetuygulamasi.Fragments.KullaniciProfilFragment;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.example.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class    AnaActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ChangeFragment changeFragment;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userId;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment.change(new AnaSayfaFragment());
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment.change(new BildirimFragment());

                    return true;
                case R.id.navigation_profil:
                    changeFragment.change(new KullaniciProfilFragment());
                    return true;

                case R.id.navigation_exit:
                    cik();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        tanimla();
        kontrol();

        changeFragment= new ChangeFragment(AnaActivity.this);
        changeFragment.change(new AnaSayfaFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void cik()
    {
        auth.signOut();
        Intent intent = new Intent(AnaActivity.this, GirisActivity.class);
        startActivity(intent);
        finish();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(false);
    }

    public void tanimla()
    {
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(true);
    }

@Override
protected void onPause() {
    super.onPause();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
    reference.child(user.getUid()).child("state").setValue(false);
}

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(true);
    }

    public  void kontrol()
    {
        if(user==null) {

            Intent intent = new Intent(AnaActivity.this, GirisActivity.class);
            startActivity(intent);
            finish();
        }
            else
                {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference reference = firebaseDatabase.getReference().child("Kullanicilar");
            reference.child(user.getUid()).child("state").setValue(true);
        }

    }
}
