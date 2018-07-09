package com.example.kafein.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.ilanVerPojo;

public class AracBilgileri extends AppCompatActivity {


    Button aracBilgisiButon,aracBilgisiButonGeri;
    EditText markaBilgiEditText ,seriBilgiEditText, modelBilgiEditText,yilBilgiEditText,kilometreBilgiEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgileri);
        tanimla();
    }

    public void tanimla()
    {
        markaBilgiEditText=findViewById(R.id.markaBilgiEditText);
        seriBilgiEditText=findViewById(R.id.seriBilgiEditText);
        modelBilgiEditText=findViewById(R.id.modelBilgiEditText);
        yilBilgiEditText=findViewById(R.id.yilBilgiEditText);
        kilometreBilgiEditText=findViewById(R.id.kilometreBilgiEditText);


        markaBilgiEditText.setText(ilanVerPojo.getMarka()); //geri dondugumuzde doldurulan bilgiler kalsın die
        seriBilgiEditText.setText(ilanVerPojo.getSeri());
        modelBilgiEditText.setText(ilanVerPojo.getModel());
        yilBilgiEditText.setText(ilanVerPojo.getYil());
        kilometreBilgiEditText.setText(ilanVerPojo.getKm());

        aracBilgisiButon=findViewById(R.id.aracBilgisiButon);
        aracBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!markaBilgiEditText.getText().toString().equals("") && !seriBilgiEditText.getText().toString().equals("") && !modelBilgiEditText.getText().toString().equals("") && !yilBilgiEditText.getText().toString().equals("") && !kilometreBilgiEditText.getText().toString().equals("")) {

                    ilanVerPojo.setMarka(markaBilgiEditText.getText().toString());
                    ilanVerPojo.setSeri(seriBilgiEditText.getText().toString());
                    ilanVerPojo.setModel(modelBilgiEditText.getText().toString());
                    ilanVerPojo.setYil(yilBilgiEditText.getText().toString());
                    ilanVerPojo.setKm(kilometreBilgiEditText.getText().toString());

                    Intent intent = new Intent(AracBilgileri.this, MotorPerformans.class);
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


        aracBilgisiButonGeri=findViewById(R.id.aracBilgisiButonGeri);
        aracBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AracBilgileri.this,AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back,R.anim.anim_out_back);
                finish();
            }
        });

    }
}
