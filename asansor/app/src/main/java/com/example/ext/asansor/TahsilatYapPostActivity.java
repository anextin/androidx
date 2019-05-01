package com.example.ext.asansor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.asansor.Activities.ArizaTanimlamaPostActivity;
import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahsilatYapPostActivity extends AppCompatActivity {


    String binaadi,asansoradi,yoneticiadi;
    TextView BinaAdiEditText,AsansorAdiEditText,YoneticiAdiEditText,tarihTextView;
    EditText  YoneticiTelEditText,KasaEditText,OdemeTarihiEditText,TutarEditText,FisNumarasiEditText,TahsilatYapPostAciklamaEditText;
    Button OnayButon,tarihButton;
    Context context;
    List<BakimPojo> list;
    List<TahsilatYapSorgulaPostPojo> tahsilatYapSorgulaPostPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahsilat_yap_post);
        Bundle bundle=getIntent().getExtras();
        binaadi=bundle.getString("binaadi");
        asansoradi=bundle.getString("asansoradi");
        yoneticiadi=bundle.getString("yoneticiadi");
        isNetworkConnected();
        tanimla();
        Log.i("binaaditag+",binaadi);
    }


    public void tanimla()
    {
        tarihButton=findViewById(R.id.tarihButton);
        tarihTextView=findViewById(R.id.tarihTextView);

        BinaAdiEditText = findViewById(R.id.BinaAdiEditText);
        BinaAdiEditText.setText("Bina Adı: "+binaadi);
        AsansorAdiEditText = findViewById(R.id.AsansorAdiEditText);
        AsansorAdiEditText.setText("Asansör Adı: "+ asansoradi);
        YoneticiAdiEditText=findViewById(R.id.YoneticiAdiEditText);
        YoneticiAdiEditText.setText("Yönetici Adı: "+  yoneticiadi);

        YoneticiTelEditText = findViewById(R.id.YoneticiTelEditText);
        KasaEditText = findViewById(R.id.KasaEditText);
        TutarEditText = findViewById(R.id.TutarEditText);
        FisNumarasiEditText = findViewById(R.id.FisNumarasiEditText);
        TahsilatYapPostAciklamaEditText = findViewById(R.id.TahsilatYapPostAciklamaEditText);

        OnayButon = findViewById(R.id.OnayButon);


        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BinaAdiEditText.getText().toString().equals(""))
                {

        //            BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setBinaadi(binaadi);    //geri dondugumuzde doldurulan bilgiler kalsın die
                    TahsilatYapSorgulaPostPojo.setAsansoradi(asansoradi);
                    TahsilatYapSorgulaPostPojo.setYoneticiadi(yoneticiadi);
                    TahsilatYapSorgulaPostPojo.setYoneticiTel(  YoneticiTelEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setKasa(  KasaEditText.getText().toString());
                    TahsilatYapSorgulaPostPojo.setOdemeTarihi(  tarihTextView.getText().toString());
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

        tarihButton.setOnClickListener(new View.OnClickListener() {//tarihButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(TahsilatYapPostActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int ay= monthOfYear+1;
                        // TODO Auto-generated method stub
                        tarihTextView.setText( dayOfMonth + "/" + ay+ "/"+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                    }
                },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.show();

            }
        });

    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }




    public  void TahsilatYapPost(String binaadi,String asansoradi , String yoneticiadi,String yoneticiTel,String kasa,String odemeTarihi,String tutar,String fis_numarasi,String aciklama)
    {

        Call<TahsilatYapSorgulaPostPojo> request = ManagerAll.getInstance().TahsilatYapSorgulaPost(binaadi, asansoradi, yoneticiadi,yoneticiTel,kasa,odemeTarihi,tutar,fis_numarasi,aciklama);
        request.enqueue(new Callback<TahsilatYapSorgulaPostPojo>() {
            @Override
            public void onResponse(Call<TahsilatYapSorgulaPostPojo> call, Response<TahsilatYapSorgulaPostPojo> response) {

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

