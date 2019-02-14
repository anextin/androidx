package com.example.ext.fragment6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    Button button;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview= findViewById(R.id.textview);

        ChangeFragment changeFragment= new ChangeFragment(MainActivity.this);
        changeFragment.change(new Fm1());
        //Bundle bundle = getIntent().getExtras();

        if(getIntent().getExtras()!=null) {
            String isim = getIntent().getExtras().getString("isim").toString();
            textview.setText(isim);
        }
    }
}
