package com.example.ext.asansor;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.RestApi.ManagerAll;
import com.example.ext.asansor.Schedule.MyJobService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ext.asansor.NoConnection.DIR_NAME;
import static com.example.ext.asansor.NoConnection.FILE_NAME;

public class ArizaActivity extends AppCompatActivity {

    EditText BinaAdiEditText,ArizaTuruEditText,AcıklamaEditText, kontrolEditText;
    Button OnayButon;
    Context context;
    List<ArizaPojo> list;
    public static final String FILE_NAME = "externalfile.txt";
    public static final String DIR_NAME = "externaldir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ariza);
        isNetworkConnected();
        tanimla();

    }


    public void tanimla()
    {
        BinaAdiEditText = findViewById(R.id.BinaAdiEditText);
        ArizaTuruEditText = findViewById(R.id.ArizaTuruEditText);
        AcıklamaEditText = findViewById(R.id.AcıklamaEditText);
        OnayButon = findViewById(R.id.OnayButon);
        kontrolEditText = findViewById(R.id.kontrolEditText);


        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BinaAdiEditText.getText().toString().equals("") && !ArizaTuruEditText.getText().toString().equals("") && !AcıklamaEditText.getText().toString().equals(""))
                {

                    ArizaPojo.setBinaAdi(BinaAdiEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die
                    ArizaPojo.setArizaTuru(  ArizaTuruEditText.getText().toString());
                    ArizaPojo.setAciklama(AcıklamaEditText.getText().toString());

                    if(isNetworkConnected()==true) {    //bunu duzelt
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        ilaniYayinla(ArizaPojo.getBinaAdi(), ArizaPojo.getArizaTuru(), ArizaPojo.getAciklama());

                        finish();
                    }

                    else
                    {Toast.makeText(getApplicationContext(),"internet yok",Toast.LENGTH_LONG).show();
                        NoConnection noConnection = new NoConnection();
                        noConnection.write(getApplicationContext(), BinaAdiEditText.getText().toString(),ArizaTuruEditText.getText().toString(),AcıklamaEditText.getText().toString());




          //            OnConnection onConnection= new OnConnection();
            //            onConnection.readfromStorage(getApplicationContext());
                     //   read();


                        //        Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                        //       startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tum bilgileri doldur",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    public  void ilaniYayinla(String binaAdi,String arizaTuru , String aciklama)
    {





            Call<ArizaPojo> request = ManagerAll.getInstance().ariza(binaAdi, arizaTuru, aciklama);
            request.enqueue(new Callback<ArizaPojo>() {
                @Override
                public void onResponse(Call<ArizaPojo> call, Response<ArizaPojo> response) {
                    if (response.body().isTf()) {
                        //               Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                        //             startActivity(intent);

                        finish();
                    }

                }

                @Override
                public void onFailure(Call<ArizaPojo> call, Throwable t) {

                }


            });
        }

    }





