package com.example.nurdan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView TextView01,TextView02,TextView03,TextView04;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, LineGraphActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    public void tanimla()
    {
        TextView01=findViewById(R.id.TextView01);
   //     TextView02=findViewById(R.id.TextView02);
     //   TextView03=findViewById(R.id.TextView03);
        //  TextView04=findViewById(R.id.TextView04);
        button=findViewById(R.id.button);
    }



}
