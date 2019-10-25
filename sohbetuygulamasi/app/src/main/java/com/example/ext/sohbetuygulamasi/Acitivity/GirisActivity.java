package com.example.ext.sohbetuygulamasi.Acitivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class GirisActivity extends AppCompatActivity {

    private EditText input_email_login;
    private EditText input_password_login;
    private Button loginButon;
    private TextView hesapYok;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimla();
    }

    @SuppressLint("WrongViewCast")
    public void tanimla()
    {
        input_email_login=findViewById(R.id.input_email_login);
        input_password_login=findViewById(R.id.input_password_login);
        loginButon=findViewById(R.id.loginButon);
        hesapYok=findViewById(R.id.hesapYok);
        auth=FirebaseAuth.getInstance();
        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=input_email_login.getText().toString();
                String pass=input_password_login.getText().toString();

                if(!email.equals("")&&!pass.equals(""))
                {
                    sistemeGiris(email,pass);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Alanları Doldurunuz..",Toast.LENGTH_LONG).show();
                }
            }
        });


        hesapYok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GirisActivity.this,KayitOlActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void sistemeGiris(String email, String pass)
    {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Intent intent= new Intent(GirisActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Hatalı Giriş Yaptınız ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
