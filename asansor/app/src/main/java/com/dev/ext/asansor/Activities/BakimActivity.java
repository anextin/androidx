package com.dev.ext.asansor.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.ext.asansor.MainActivity;
import com.dev.ext.asansor.Models.BakimPojo;
import com.dev.ext.asansor.NoConnection;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BakimActivity extends AppCompatActivity {

    private EditText YapilmaliEditText,TutarEditText,BinaYetkilisiEditText, AcıklamaEditText,TelEditText,EpostaEditText,MesajEditText;
    private TextView bakimEkrani,baslikBakim,binaadiBakim,tarihBakim;

    Button OnayButon;
    Context context;
    String asansorserino,bakimbasla,donemtarihi,yetkili,tel;
    List<BakimPojo> list;
    public  String dateHour,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakim);
        Bundle bundle=getIntent().getExtras();
        asansorserino=bundle.getString("asansorserino");
        bakimbasla=bundle.getString("bakimbasla");
        donemtarihi=bundle.getString("donemtarihi");
        yetkili=bundle.getString("yetkili");
        tel=bundle.getString("tel");

        isNetworkConnected();
        getBakimDetay();
        tanimla();


    }


    public void tanimla()
    {


        bakimEkrani = findViewById(R.id.bakimEkrani);
        baslikBakim = findViewById(R.id.baslikBakim);
        binaadiBakim = findViewById(R.id.binaadiBakim);
        tarihBakim=findViewById(R.id.tarihBakim);

        YapilmaliEditText = findViewById(R.id.YapilmaliEditText);
        TutarEditText = findViewById(R.id.TutarEditText);
        BinaYetkilisiEditText = findViewById(R.id.BinaYetkilisiEditText);
        AcıklamaEditText = findViewById(R.id.AcıklamaEditText);
        TelEditText = findViewById(R.id.TelEditText);
        EpostaEditText = findViewById(R.id.EpostaEditText);
        MesajEditText = findViewById(R.id.MesajEditText);
        OnayButon = findViewById(R.id.OnayButon);



        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!YapilmaliEditText.getText().toString().equals("")&&!TutarEditText.getText().toString().equals("")&&
                        !BinaYetkilisiEditText.getText().toString().equals("")&&
                        !AcıklamaEditText.getText().toString().equals("")&&
                        !TelEditText.getText().toString().equals("")&&
                        !EpostaEditText.getText().toString().equals("")&&
                        !MesajEditText.getText().toString().equals(""))
                {
                    if(isNetworkConnected()==true) {
                        dateHour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        ilaniYayinla(baslikBakim.getText().toString(),binaadiBakim.getText().toString(),date,YapilmaliEditText.getText().toString()
                                ,TutarEditText.getText().toString()
                                ,BinaYetkilisiEditText.getText().toString()
                                ,AcıklamaEditText.getText().toString()
                                ,TelEditText.getText().toString()
                                ,EpostaEditText.getText().toString()
                                ,MesajEditText.getText().toString()
                                ,asansorserino,bakimbasla,dateHour,"1");

                        finish();
                    }

                    else

                    {dateHour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        Toast.makeText(getApplicationContext(),"internet yok",Toast.LENGTH_LONG).show();
                        NoConnection noConnection = new NoConnection();
                       noConnection.Bakimwrite(getApplicationContext(), baslikBakim.getText().toString()
                               ,binaadiBakim.getText().toString()
                               ,date
                               ,YapilmaliEditText.getText().toString()
                            ,TutarEditText.getText().toString()
                            ,BinaYetkilisiEditText.getText().toString()
                            ,AcıklamaEditText.getText().toString()
                            ,TelEditText.getText().toString()
                            ,EpostaEditText.getText().toString()
                            ,MesajEditText.getText().toString()
                            ,asansorserino
                               ,bakimbasla
                               ,dateHour
                               ,"1");
         //              read();


                               Intent intent = new Intent(BakimActivity.this, MainActivity.class);
                               startActivity(intent);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tum bilgileri doldur",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void getBakimDetay()
    {


        Call<BakimPojo> request= ManagerAll.getInstance().bakim(asansorserino);
        request.enqueue(new Callback<BakimPojo>() {
            @Override
            public void onResponse(Call<BakimPojo> call, Response<BakimPojo> response) {


                YapilmaliEditText.setText(response.body().getYapilacak());
                TutarEditText.setText(response.body().getTutar());
                BinaYetkilisiEditText.setText(yetkili);
                AcıklamaEditText.setText(response.body().getAciklama());
                TelEditText.setText(tel);
                EpostaEditText.setText(response.body().getEposta());
                MesajEditText.setText(response.body().getMesaj());

                baslikBakim.setText(response.body().getBaslik());
                binaadiBakim.setText(response.body().getBinaadi());
                tarihBakim.setText(donemtarihi);


            }

            @Override
            public void onFailure(Call<BakimPojo> call, Throwable t) {

            }
        });


    }

    public  void ilaniYayinla(String baslik,String binaadi , String donemtarihi,String yapilacak,String tutar,String yetkili,String aciklama,String tel,String eposta,
                              String mesaj,String asansorserino,String bakimbasla,String bakimbitir,String bakimdurum)
    {





        Call<BakimPojo> request = ManagerAll.getInstance().bakimpost( baslik, binaadi ,  donemtarihi, yapilacak, tutar, yetkili, aciklama, tel, eposta,
                 mesaj, asansorserino, bakimbasla, bakimbitir, bakimdurum);
        request.enqueue(new Callback<BakimPojo>() {
            @Override
            public void onResponse(Call<BakimPojo> call, Response<BakimPojo> response) {
                if (response.body().isTf()) {
                    //               Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                    //             startActivity(intent);

                    finish();
                }

            }

            @Override
            public void onFailure(Call<BakimPojo> call, Throwable t) {

            }


        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}





