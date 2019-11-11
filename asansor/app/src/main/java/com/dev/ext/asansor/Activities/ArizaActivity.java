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
import com.dev.ext.asansor.Models.ArizaPojo;
import com.dev.ext.asansor.NoConnection;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;

public class ArizaActivity extends AppCompatActivity {

    private EditText binayetkiliEditText,ArizaSebebiEditText,ArizaKoduEditText, DegisenParcalar,TelEditText,EpostaEditText,MesajEditText;
    private TextView arizaEkrani,baslikAriza,binaadiAriza,seciliArizaAriza;

    Button OnayButon;
    Context context;
    String asansorserino,arizaonarbasla,donemtarihi;
    List<ArizaPojo> list;
    public  String arizabitir,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ariza);
        Bundle bundle=getIntent().getExtras();
        asansorserino=bundle.getString("asansorserino");
        arizaonarbasla=bundle.getString("arizaonarbasla");
        donemtarihi=bundle.getString("donemtarihi");
        Log.i("ilker","ilker"+asansorserino);

        isNetworkConnected();
        getBakimDetay();
        tanimla();


    }


    public void tanimla()
    {


        arizaEkrani = findViewById(R.id.arizaEkrani);
        baslikAriza = findViewById(R.id.baslikAriza);
        binaadiAriza = findViewById(R.id.binaadiAriza);
        seciliArizaAriza=findViewById(R.id.seciliArizaAriza);

        binayetkiliEditText = findViewById(R.id.binayetkiliEditText);
        ArizaSebebiEditText = findViewById(R.id.ArizaSebebiEditText);
        ArizaKoduEditText = findViewById(R.id.ArizaKoduEditText);
        DegisenParcalar = findViewById(R.id.DegisenParcalar);
        TelEditText = findViewById(R.id.TelEditText);
        EpostaEditText = findViewById(R.id.EpostaEditText);
        MesajEditText = findViewById(R.id.MesajEditText);
        OnayButon = findViewById(R.id.OnayButon);



        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binayetkiliEditText.getText().toString().equals("")&&!ArizaSebebiEditText.getText().toString().equals("")&&!ArizaKoduEditText.getText().toString().equals("")
                        &&!DegisenParcalar.getText().toString().equals("")
                        &&!EpostaEditText.getText().toString().equals("")
                        &&!TelEditText.getText().toString().equals("")
                        &&!MesajEditText.getText().toString().equals(""))
                {
                    if(isNetworkConnected()==true) {
                        arizabitir = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        Toast.makeText(getApplicationContext(),"internet var",Toast.LENGTH_LONG).show();
                        ilaniYayinla(
                                binayetkiliEditText.getText().toString()
                                ,ArizaSebebiEditText.getText().toString()
                                ,ArizaKoduEditText.getText().toString()
                                ,DegisenParcalar.getText().toString()
                                ,EpostaEditText.getText().toString()
                                ,TelEditText.getText().toString()
                                ,MesajEditText.getText().toString()
                                ,donemtarihi
                                ,asansorserino
                                ,arizaonarbasla
                                ,arizabitir
                                ,"1");

                        finish();
                    }

                    else

                    {
                        arizabitir = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        Toast.makeText(getApplicationContext(),"internet yok",Toast.LENGTH_LONG).show();
                        NoConnection noConnection = new NoConnection();
                        noConnection.Arizawrite(getApplicationContext()
                                ,binayetkiliEditText.getText().toString()
                                ,ArizaSebebiEditText.getText().toString()
                                ,ArizaKoduEditText.getText().toString()
                                ,DegisenParcalar.getText().toString()
                                ,EpostaEditText.getText().toString()
                                ,TelEditText.getText().toString()
                                ,MesajEditText.getText().toString()
                                ,donemtarihi
                                ,asansorserino
                                ,arizaonarbasla
                                ,arizabitir
                                ,"1");



                        Intent intent = new Intent(ArizaActivity.this, MainActivity.class);
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


        Call<ArizaPojo> request= ManagerAll.getInstance().ariza(asansorserino,donemtarihi);
        request.enqueue(new Callback<ArizaPojo>() {
            @Override
            public void onResponse(Call<ArizaPojo> call, Response<ArizaPojo> response) {



                binayetkiliEditText.setText(response.body().getYetkili());
                ArizaSebebiEditText.setText(response.body().getArizakonu());
                ArizaKoduEditText.setText(response.body().getArizakodu());
                DegisenParcalar.setText(response.body().getDegisenparca());
                TelEditText.setText(response.body().getTel());
                EpostaEditText.setText(response.body().getEposta());
                MesajEditText.setText(response.body().getMesaj());

                baslikAriza.setText(response.body().getBinaadi());
               binaadiAriza.setText(response.body().getBinaadi());
                seciliArizaAriza.setText(response.body().getDonemtarihi());



            }

            @Override
            public void onFailure(Call<ArizaPojo> call, Throwable t) {

            }
        });


    }

    public  void ilaniYayinla(String yetkili,String arizakonu , String arizakodu,String degisenparca,String eposta,String tel,String mesaj,String donemtarihi,String asansorserino,String arizaonarbasla,
                              String arizaonarbitir,String arizadurum)

    {
        Log.i("sedat","sedat: "+asansorserino+donemtarihi);

        Call<ArizaPojo> request = ManagerAll.getInstance().arizapost( yetkili, arizakonu ,  arizakodu, degisenparca, eposta, tel, mesaj, donemtarihi,asansorserino, arizaonarbasla,
                arizaonarbitir, arizadurum);
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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}





