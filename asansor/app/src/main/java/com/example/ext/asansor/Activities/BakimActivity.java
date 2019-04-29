package com.example.ext.asansor.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    EditText YapilmaliEditText,TutarEditText,BinaYetkilisiEditText, AcıklamaEditText,TelEditText,EpostaEditText,MesajEditText;
    TextView bakimEkrani,baslikBakim,binaadiBakim;

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

    }


    public void tanimla()
    {
        bakimEkrani = findViewById(R.id.bakimEkrani);
        baslikBakim = findViewById(R.id.baslikBakim);
        binaadiBakim = findViewById(R.id.binaadiBakim);

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

        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!YapilmaliEditText.getText().toString().equals(""))
                {

                    BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die
                    BakimPojo.setArizaTuru(  ArizaTuruEditText.getText().toString());
                    BakimPojo.setAciklama(AcıklamaEditText.getText().toString());

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


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
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


    public void getIlanDetay()
    {


        Call<IlanDetayPojo> request= ManagerAll.getInstance().ilanDetay(ilanId);
        request.enqueue(new Callback<IlanDetayPojo>() {
            @Override
            public void onResponse(Call<IlanDetayPojo> call, Response<IlanDetayPojo> response) {


                ilanDetayBaslik.setText(response.body().getBaslik());
                ilandetayFiyat.setText(response.body().getUcret());
                ilandetayMarka.setText(response.body().getMarka());
                ilandetayModel.setText(response.body().getModel());
                ilandetaySeri.setText(response.body().getSeri());
                ilandetayYil.setText(response.body().getYil());
                ilandetayilantipi.setText(response.body().getIlantipi());
                ilandetayKimden.setText(response.body().getKimden());
                ilandetayMotorTipi.setText(response.body().getMotortipi());
                ilandetayMotorHacmi.setText(response.body().getMotorhacmi());
                ilandetaySurat.setText(response.body().getSurat());
                ilandetayYakitTipi.setText(response.body().getYakittipi());
                ilandetayOrtalamaYakit.setText(response.body().getOrtalamyakit());
                ilandetayDepoHacmi.setText(response.body().getDepohacmi());
                ilandetayKM.setText(response.body().getKm());

            }

            @Override
            public void onFailure(Call<IlanDetayPojo> call, Throwable t) {

            }
        });


    }

}





