package com.example.kafein.otogalerim;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.kafein.otogalerim.Models.User;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BilgiActivity extends AppCompatActivity {

    EditText userbilgi_username,userbilgi_pass;
    Button userbilgi_buton;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        uye_id=sharedPreferences.getString("uye_id",null);
        istekAt(uye_id);
    }


    @SuppressLint("WrongViewCast")
    public  void tanimla()
    {
        userbilgi_username=findViewById(R.id.userbilgi_username);
        userbilgi_pass=findViewById(R.id.userbilgi_pass);
        userbilgi_buton=(Button)findViewById(R.id.userbilgi_buton);

    }

    public void istekAt(String uye_id)
    {
        Call<User> request= ManagerAll.getInstance().getInformation(uye_id);
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful())
                {
                   userbilgi_username.setText(response.body().getKadi());
                   userbilgi_pass.setText(response.body().getSifre());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
