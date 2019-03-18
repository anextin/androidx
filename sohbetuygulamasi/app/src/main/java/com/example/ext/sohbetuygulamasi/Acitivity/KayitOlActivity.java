package com.example.ext.sohbetuygulamasi.Acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOlActivity extends AppCompatActivity {

    EditText input_email, input_password;
    Button registerButon;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        tanimla();

    }


    public void tanimla()
    {
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        registerButon=findViewById(R.id.registerButon);
        auth=FirebaseAuth.getInstance();

        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=input_email.getText().toString();
                String pass= input_password.getText().toString();

                if(!email.equals("")&&!pass.equals(""))
                {
                    input_email.setText("");
                    input_password.setText("");
                    kayitOl(email, pass);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"bilgileri doldur ilkos",Toast.LENGTH_LONG).show();
                }
                }

        });

    }



    public void kayitOl(String email, String pass)
    {
        Log.i("testt",""+auth);
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Intent intent=new Intent(KayitOlActivity.this,AnaActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"kayit olamadın",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
