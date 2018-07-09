package com.example.kafein.otogalerim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ilanResimler extends AppCompatActivity {

    Button resimEkleButon,resimSecButon,ilanYayinlaButon;
    ImageView secilenIlanResmiImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimla();
    }


    public void tanimla()
    {
        resimSecButon=findViewById(R.id.resimSecButon);
        resimEkleButon=findViewById(R.id.resimEkleButon);
        ilanYayinlaButon=findViewById(R.id.ilanYayinlaButon);
        secilenIlanResmiImageView=findViewById(R.id.secilenIlanResmiImageView);
    }
}



