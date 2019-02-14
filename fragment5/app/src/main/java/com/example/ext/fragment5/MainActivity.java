package com.example.ext.fragment5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeFragment changeFragment= new ChangeFragment(MainActivity.this);
        changeFragment.change(new Fm1());
        changeFragment.change2(new Fm2());
    }
}
