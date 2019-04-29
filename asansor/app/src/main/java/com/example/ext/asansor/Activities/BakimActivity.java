package com.example.ext.asansor.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    EditText BinaAdiEditText,ArizaTuruEditText,AcıklamaEditText, kontrolEditText;
    Button OnayButon;
    Context context;
    List<BakimPojo> list;
    public static final String FILE_NAME = "externalfile.txt";
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

}





