package com.example.ext.asansor.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.NoConnection;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BakimActivity extends AppCompatActivity {

    private EditText YapilmaliEditText,TutarEditText,BinaYetkilisiEditText, AcıklamaEditText,TelEditText,EpostaEditText,MesajEditText;
    private TextView bakimEkrani,baslikBakim,binaadiBakim,tarihBakim;

    Button OnayButon;
    Context context;
    String asansorserino;
    List<BakimPojo> list;
    public static final String FILE_NAME = "bakim.txt";
    public static final String DIR_NAME = "externaldir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakim);
        isNetworkConnected();
        tanimla();
        getBakimDetay();

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


        Bundle bundle=getIntent().getExtras();
        asansorserino=bundle.getString("asansorserino");
        Log.i("asansorserino","asansorserino"+asansorserino);

        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!YapilmaliEditText.getText().toString().equals(""))
                {


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
                BinaYetkilisiEditText.setText(response.body().getYetkili());
                AcıklamaEditText.setText(response.body().getAciklama());
                TelEditText.setText(response.body().getTel());
                EpostaEditText.setText(response.body().getEposta());
                MesajEditText.setText(response.body().getMesaj());

                baslikBakim.setText(response.body().getBaslik());
                binaadiBakim.setText(response.body().getBinaadi());
                tarihBakim.setText(response.body().getDonemtarihi());

                Log.i("basliiikk","basliiikk"+baslikBakim);

            }

            @Override
            public void onFailure(Call<BakimPojo> call, Throwable t) {

            }
        });


    }

    public  void ilaniYayinla(String binaAdi,String arizaTuru , String aciklama)
    {





        Call<BakimPojo> request = ManagerAll.getInstance().ariza(binaAdi, arizaTuru, aciklama);
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





