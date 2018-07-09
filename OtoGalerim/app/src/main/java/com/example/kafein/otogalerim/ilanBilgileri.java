package com.example.kafein.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.ilanVerPojo;

public class ilanBilgileri extends AppCompatActivity {

    Button ilanBilgisiButon,ilanBilgisiButonGeri;
    EditText ilanBaslikEditText,ilanAciklamaEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_bilgileri);
        tanimla();
    }
    public void tanimla()
    {
        ilanAciklamaEditText=findViewById(R.id.ilanAciklamaEditText);
        ilanBaslikEditText=findViewById(R.id.ilanBaslikEditText);

        ilanAciklamaEditText.setText(ilanVerPojo.getAciklama()); //geri dondugumuzde doldurulan bilgiler kalsın die
        ilanBaslikEditText.setText(ilanVerPojo.getBaslik());

        ilanBilgisiButon=findViewById(R.id.ilanBilgisiButon);
        ilanBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ilanAciklamaEditText.getText().toString().equals("") && !ilanBaslikEditText.getText().toString().equals(""))
                {

                    ilanVerPojo.setAciklama(ilanAciklamaEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die
                    ilanVerPojo.setBaslik(  ilanBaslikEditText.getText().toString());

                    Intent intent = new Intent(ilanBilgileri.this, ilanTuru.class);
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

        ilanBilgisiButonGeri=findViewById(R.id.ilanBilgisiButonGeri);
        ilanBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ilanBilgileri.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back,R.anim.anim_out_back);
                finish();
            }
        });

    }
}









