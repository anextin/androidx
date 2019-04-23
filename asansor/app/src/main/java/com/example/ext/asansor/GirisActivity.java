package com.example.ext.asansor;

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
import android.widget.TextView;
import android.widget.Toast;


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

    public void tanimla()
    {
        input_email_login=findViewById(R.id.input_email_login);
        input_password_login=findViewById(R.id.input_password_login);
        loginButon=findViewById(R.id.loginButon);

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
                    Toast.makeText(getApplicationContext(),"bos girme ilkos..",Toast.LENGTH_LONG).show();
                }
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
                    Intent intent= new Intent(GirisActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"hatali giriş yaptınız",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
