package com.example.ext.fragment4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= findViewById(R.id.editText);
        button= findViewById(R.id.buton);
        Fragment fragment = new Fm1();
        Bundle bundle = new Bundle();
        bundle.putString("isim",editText.getText().toString());
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new Fm1(),"fragment")                 //burada fragment cagırılıyor
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangeFragment changeFragment= new ChangeFragment(MainActivity.this);
                changeFragment.change(new Fm1(),editText.getText().toString());
            }
        });
    }
}
