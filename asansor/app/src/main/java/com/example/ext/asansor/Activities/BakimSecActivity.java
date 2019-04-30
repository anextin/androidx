package com.example.ext.asansor.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        manualgirisEditText= findViewById(R.id.manualgirisEditText);
        QRCodeBakim = findViewById(R.id.QRCodeBakim);
        manualgirisOnayButton= findViewById(R.id.manualgirisOnayButton);

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
                if (!manualgirisEditText.getText().toString().equals("")) {

                    Intent intent = new Intent(BakimSecActivity.this, BakimActivity.class);
                    Log.i("zzzzz","zzzzz"+manualgirisEditText.getText().toString());
                    intent.putExtra("asansorserino", manualgirisEditText.getText().toString());
                    startActivity(intent);
                    //            BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die

                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "Tum bilgileri doldur", Toast.LENGTH_LONG).show();
                    }
                }
                });

            }

}
