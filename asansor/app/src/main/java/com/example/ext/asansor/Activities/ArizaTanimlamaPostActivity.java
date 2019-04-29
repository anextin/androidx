package com.example.ext.asansor.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArizaTanimlamaPostActivity extends AppCompatActivity {

    String binaadi,asansoradi;
    EditText BinaAdiEditText,AsansorAdiEditText,ArayanKisiEditText, ArayanTelEditText,ArizaTarihiEditText,ArizaSaatiEditText,ArizaKonusuEditText,AcıklamaEditText;
    Button OnayButon;
    Context context;
    List<ArizaTanimlamaPostPojo> list;
    List<ArizaTanimlamaPostPojo> arizaTanimlamaPostPojo;
    public static final String FILE_NAME = "externalfileTahsilatYapPostActivity.txt";
    public static final String DIR_NAME = "externaldir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ariza_tanimlama_post);
        Bundle bundle=getIntent().getExtras();
        binaadi=bundle.getString("binaadi");
        asansoradi=bundle.getString("asansoradi");
        isNetworkConnected();
        tanimla();
        Log.i("binaaditag+",binaadi);
    }


    public void tanimla()
    {
        BinaAdiEditText = findViewById(R.id.BinaAdiEditText);
        BinaAdiEditText.setText(binaadi);
        AsansorAdiEditText = findViewById(R.id.AsansorAdiEditText);
        AsansorAdiEditText.setText(asansoradi);
        ArayanKisiEditText = findViewById(R.id.ArayanKisiEditText);
        ArayanTelEditText = findViewById(R.id.ArayanTelEditText);
        ArizaTarihiEditText = findViewById(R.id.ArizaTarihiEditText);
        ArizaSaatiEditText = findViewById(R.id.ArizaSaatiEditText);
        ArizaKonusuEditText = findViewById(R.id.ArizaKonusuEditText);
        AcıklamaEditText = findViewById(R.id.AcıklamaEditText);

        OnayButon = findViewById(R.id.OnayButon);


        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BinaAdiEditText.getText().toString().equals(""))
                {

                    BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setBinaadi(binaadi);    //geri dondugumuzde doldurulan bilgiler kalsın die
                    ArizaTanimlamaPostPojo.setAsansoradi(  asansoradi);
                    ArizaTanimlamaPostPojo.setArayankisi(  ArayanKisiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArayanTel(  ArayanTelEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArizatarih(  ArizaTarihiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArizasaat(  ArizaSaatiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArizakonu(  ArizaKonusuEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setAciklama(AcıklamaEditText.getText().toString());

                    if(isNetworkConnected()==true) {    //bunu duzelt
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        Log.i("hop123","hop123"+TahsilatYapSorgulaPostPojo.getBinaadi());
                        ArizaTanimlamaPost(ArizaTanimlamaPostPojo.getBinaadi(), ArizaTanimlamaPostPojo.getAsansoradi(),ArizaTanimlamaPostPojo.getArayankisi(), ArizaTanimlamaPostPojo.getArayanTel(),ArizaTanimlamaPostPojo.getArizatarih(),ArizaTanimlamaPostPojo.getArizasaat(),ArizaTanimlamaPostPojo.getArizakonu(), ArizaTanimlamaPostPojo.getAciklama());

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




    public  void ArizaTanimlamaPost(String binaadi,String asansoradi , String arayankisi,String arayanTel,String arizatarih,String arizasaat,String arizakonu,String aciklama)
    {

        Call<ArizaTanimlamaPostPojo> request = ManagerAll.getInstance().ArizaTanimlamaPost(binaadi, asansoradi, arayankisi,arayanTel,arizatarih,arizasaat,arizakonu,aciklama);
        request.enqueue(new Callback<ArizaTanimlamaPostPojo>() {
            @Override
            public void onResponse(Call<ArizaTanimlamaPostPojo> call, Response<ArizaTanimlamaPostPojo> response) {
                Log.i("tfne", "tfne" + response.body().isTf());
                if (response.body().isTf()) {
                    //               Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
                    //             startActivity(intent);

                    finish();

                }

            }

            @Override
            public void onFailure(Call<ArizaTanimlamaPostPojo> call, Throwable t) {

            }

        });
    }

}

