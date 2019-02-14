package com.example.ext.fragment2;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()


                .replace(R.id.mainActivityFragment,new KayitFragment(),"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)


                .commit();
    }
}
