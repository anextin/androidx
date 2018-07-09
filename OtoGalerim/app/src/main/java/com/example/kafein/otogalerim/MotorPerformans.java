package com.example.kafein.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.ilanVerPojo;

public class MotorPerformans extends AppCompatActivity {

    Button motorBilgisiButon,motorBilgisiButonGeri;
    EditText motorTipiEditText ,AzamiSuratEditText, motorHacmiEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_performans);
        tanimla();
    }

    public void tanimla()
    {
        motorTipiEditText=findViewById(R.id.motorTipiEditText);
        AzamiSuratEditText=findViewById(R.id.AzamiSuratEditText);
        motorHacmiEditText=findViewById(R.id.motorHacmiEditText);


        motorTipiEditText.setText(ilanVerPojo.getMotortipi()); //geri dondugumuzde doldurulan bilgiler kalsın die
        AzamiSuratEditText.setText(ilanVerPojo.getSurat());
        motorHacmiEditText.setText(ilanVerPojo.getMotorhacmi());


        motorBilgisiButon=findViewById(R.id.motorBilgisiButon);
        motorBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!motorTipiEditText.getText().toString().equals("") && !AzamiSuratEditText.getText().toString().equals("") && !motorHacmiEditText.getText().toString().equals("")) {

                    ilanVerPojo.setMotortipi(motorTipiEditText.getText().toString());  // ekran gecıldıkden sonra get lere dger atıyoruz kı geri dondugumuzde geti cektıgımızde degerli olarak gorunsun
                    ilanVerPojo.setSurat(AzamiSuratEditText.getText().toString());
                    ilanVerPojo.setMotorhacmi(motorHacmiEditText.getText().toString());

                    Intent intent = new Intent(MotorPerformans.this, Yakit.class);
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

        motorBilgisiButonGeri=findViewById(R.id.motorBilgisiButonGeri);
        motorBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotorPerformans.this,AracBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back,R.anim.anim_out_back);
                finish();
            }
        });



    }
}

