package com.example.ext.asansor.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ext.asansor.MainActivity;
import com.example.ext.asansor.R;
import com.google.firebase.auth.FirebaseAuth;

public class BakimSecActivity extends AppCompatActivity {

    Button QRCodeBakim , manualgirisOnayButton;
    EditText manualgirisEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakim_sec);
        tanimla();
    }


    public void tanimla() {


        QRCodeBakim = findViewById(R.id.QRCodeBakim);
        QRCodeBakim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BakimSecActivity.this, QRCodeActivity.class);
                startActivity(intent);
            }
        });

        manualgirisOnayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!manualgirisEditText.getText().toString().equals("") )
                {

        //            BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die


                    if(isNetworkConnected()==true) {    //bunu duzelt
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        ilaniYayinla(BakimPojo.getBinaAdi(), BakimPojo.getArizaTuru(), BakimPojo.getAciklama());

                        finish();
                    }

                    else
                    {Toast.makeText(getApplicationContext(),"internet yok",Toast.LENGTH_LONG).show();
                        NoConnection noConnection = new NoConnection();
                        noConnection.write(getApplicationContext(), BinaAdiEditText.getText().toString(),ArizaTuruEditText.getText().toString(),AcıklamaEditText.getText().toString());


                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tum bilgileri doldur",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
