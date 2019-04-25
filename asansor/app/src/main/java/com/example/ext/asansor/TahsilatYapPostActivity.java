package com.example.ext.asansor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahsilatYapPostActivity extends AppCompatActivity {



    EditText BinaAdiEditText,AsansorAdiEditText,YoneticiAdiEditText, YoneticiTelEditText,KasaEditText,OdemeTarihiEditText,TutarEditText,FisNumarasiEditText,TahsilatYapPostAciklamaEditText;
    Button OnayButon;
    Context context;
    List<ArizaPojo> list;
    List<TahsilatYapSorgulaPostPojo> tahsilatYapSorgulaPostPojo;
    public static final String FILE_NAME = "externalfileTahsilatYapPostActivity.txt";
    public static final String DIR_NAME = "externaldir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahsilat_yap_post);
        isNetworkConnected();
        tanimla();

    }


    public void tanimla()
    {
        BinaAdiEditText = findViewById(R.id.BinaAdiEditText);
        AsansorAdiEditText = findViewById(R.id.AsansorAdiEditText);
        YoneticiAdiEditText = findViewById(R.id.YoneticiAdiEditText);
        YoneticiTelEditText = findViewById(R.id.YoneticiTelEditText);
        KasaEditText = findViewById(R.id.KasaEditText);
        OdemeTarihiEditText = findViewById(R.id.OdemeTarihiEditText);
        TutarEditText = findViewById(R.id.TutarEditText);
        FisNumarasiEditText = findViewById(R.id.FisNumarasiEditText);
        TahsilatYapPostAciklamaEditText = findViewById(R.id.TahsilatYapPostAciklamaEditText);

        OnayButon = findViewById(R.id.OnayButon);


        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BinaAdiEditText.getText().toString().equals(""))
                {

                    ArizaPojo.setBinaAdi(BinaAdiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setBinaadi(BinaAdiEditText.getText().toString());    //geri dondugumuzde doldurulan bilgiler kalsın die
                    TahsilatYapSorgulaPostPojo.setAsansoradi(  AsansorAdiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setYoneticiadi(  YoneticiAdiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setYoneticiTel(  YoneticiTelEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setKasa(  KasaEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setOdemeTarihi(  OdemeTarihiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setTutar(  TutarEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setFis_numarasi(FisNumarasiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setAciklama(TahsilatYapPostAciklamaEditText.getText().toString());

                   if(isNetworkConnected()==true) {    //bunu duzelt
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        Log.i("hop123","hop123"+TahsilatYapSorgulaPostPojo.getBinaadi());
                        TahsilatYapPost(TahsilatYapSorgulaPostPojo.getBinaadi(), TahsilatYapSorgulaPostPojo.getAsansoradi(),TahsilatYapSorgulaPostPojo.getYoneticiadi(), TahsilatYapSorgulaPostPojo.getYoneticiTel(),TahsilatYapSorgulaPostPojo.getKasa(),TahsilatYapSorgulaPostPojo.getOdemeTarihi(),TahsilatYapSorgulaPostPojo.getTutar(), TahsilatYapSorgulaPostPojo.getFis_numarasi(), TahsilatYapSorgulaPostPojo.getAciklama());

                        finish();
                    }

             //       else
               //     {Toast.makeText(getApplicationContext(),"internet yok",Toast.LENGTH_LONG).show();
                 //       NoConnection noConnection = new NoConnection();
                   //     noConnection.write(getApplicationContext(), BinaAdiEditText.getText().toString(),ArizaTuruEditText.getText().toString(),AcıklamaEditText.getText().toString());




                        //            OnConnection onConnection= new OnConnection();
                        //            onConnection.readfromStorage(getApplicationContext());
                        //   read();


                        //        Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                        //       startActivity(intent);
                    //}
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




    public  void TahsilatYapPost(String binaadi,String asansoradi , String yoneticiadi,String yoneticiTel,String kasa,String odemeTarihi,String tutar,String fis_numarasi,String aciklama)
    {
        Log.i("feguli","feguli"+binaadi+asansoradi+yoneticiadi+yoneticiTel+kasa+odemeTarihi+tutar+fis_numarasi+aciklama);
        Call<TahsilatYapSorgulaPostPojo> request = ManagerAll.getInstance().TahsilatYapSorgulaPost(binaadi, asansoradi, yoneticiadi,yoneticiTel,kasa,odemeTarihi,tutar,fis_numarasi,aciklama);
        request.enqueue(new Callback<TahsilatYapSorgulaPostPojo>() {
            @Override
            public void onResponse(Call<TahsilatYapSorgulaPostPojo> call, Response<TahsilatYapSorgulaPostPojo> response) {
                Log.i("tfne", "tfne" + response.body().isTf());
                if (response.body().isTf()) {
                    //               Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                    //             startActivity(intent);

                    finish();

                }

            }

            @Override
            public void onFailure(Call<TahsilatYapSorgulaPostPojo> call, Throwable t) {

            }

        });
    }

}

