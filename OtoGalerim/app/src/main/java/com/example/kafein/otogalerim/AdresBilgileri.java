package com.example.kafein.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.ilanVerPojo;

public class AdresBilgileri extends AppCompatActivity {

    Button adresBilgisiButon,adresBilgisiButonGeri;
    EditText sehirBilgiEditText ,ilceBilgiEditText, mahalleBilgiEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimla();
    }

    public void tanimla()
    {
        sehirBilgiEditText=findViewById(R.id.sehirBilgiEditText);
        ilceBilgiEditText=findViewById(R.id.ilceBilgiEditText);
        mahalleBilgiEditText=findViewById(R.id.mahalleBilgiEditText);



        sehirBilgiEditText.setText(ilanVerPojo.getSehir()); //geri dondugumuzde doldurulan bilgiler kalsın die
        ilceBilgiEditText.setText(ilanVerPojo.getIlce());
        mahalleBilgiEditText.setText(ilanVerPojo.getMahalle());



        adresBilgisiButon=findViewById(R.id.adresBilgisiButon);
        adresBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sehirBilgiEditText.getText().toString().equals("") && !ilceBilgiEditText.getText().toString().equals("") && !mahalleBilgiEditText.getText().toString().equals("")) {

                    ilanVerPojo.setSehir(sehirBilgiEditText.getText().toString());
                    ilanVerPojo.setIlce(ilceBilgiEditText.getText().toString());
                    ilanVerPojo.setMahalle(mahalleBilgiEditText.getText().toString());

                    Intent intent = new Intent(AdresBilgileri.this, AracBilgileri.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_inn, R.anim.anim_out);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tum bilgileri doldur",Toast.LENGTH_LONG).show();
                }
            }
        });

        adresBilgisiButonGeri=findViewById(R.id.adresBilgisiButonGeri);
        adresBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdresBilgileri.this,ilanTuru.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back,R.anim.anim_out_back);
                finish();
            }
        });

    }
}


