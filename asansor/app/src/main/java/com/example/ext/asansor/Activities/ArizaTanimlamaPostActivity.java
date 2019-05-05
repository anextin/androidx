package com.example.ext.asansor.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArizaTanimlamaPostActivity extends AppCompatActivity {



    String binaadi,asansoradi;
    TextView BinaAdiEditText,AsansorAdiEditText;
    EditText ArayanKisiEditText, ArayanTelEditText,ArizaTarihiEditText,ArizaSaatiEditText,ArizaKonusuEditText,AcıklamaEditText;

    Button saatButton,tarihButton;
    TextView saatTextView,tarihTextView;
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
    }


    public void tanimla()
    {
        saatButton=findViewById(R.id.saatButton);
        tarihButton=findViewById(R.id.tarihButton);
        saatTextView=findViewById(R.id.saatTextView);
        tarihTextView=findViewById(R.id.tarihTextView);

        BinaAdiEditText = findViewById(R.id.BinaAdiEditText);
        BinaAdiEditText.setText("Bina Adı: "+binaadi);
        AsansorAdiEditText = findViewById(R.id.AsansorAdiEditText);
        AsansorAdiEditText.setText("Asansör Adı: "+asansoradi);
        ArayanKisiEditText = findViewById(R.id.ArayanKisiEditText);
        ArayanTelEditText = findViewById(R.id.ArayanTelEditText);
        ArizaKonusuEditText = findViewById(R.id.ArizaKonusuEditText);
        AcıklamaEditText = findViewById(R.id.AcıklamaEditText);

        OnayButon = findViewById(R.id.OnayButon);


        OnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ArayanKisiEditText.getText().toString().equals("")&&!ArayanTelEditText.getText().toString().equals("")&&!tarihTextView.getText().toString().equals("")&&
                        !saatTextView.getText().toString().equals("")&&
                        !ArizaKonusuEditText.getText().toString().equals("")&&
                        !AcıklamaEditText.getText().toString().equals(""))
                {

  //   bunu duzelt     //          BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setBinaadi(binaadi);    //geri dondugumuzde doldurulan bilgiler kalsın die
                    ArizaTanimlamaPostPojo.setAsansoradi(  asansoradi);
                    ArizaTanimlamaPostPojo.setArayankisi(  ArayanKisiEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArayanTel(  ArayanTelEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setArizatarih(  tarihTextView.getText().toString());
                    ArizaTanimlamaPostPojo.setArizasaat(  saatTextView.getText().toString());
                    ArizaTanimlamaPostPojo.setArizakonu(  ArizaKonusuEditText.getText().toString());
                    ArizaTanimlamaPostPojo.setAciklama(AcıklamaEditText.getText().toString());

                    if(isNetworkConnected()==true) {    //bunu duzelt
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


        saatButton.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(ArizaTanimlamaPostActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        saatTextView.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

                timePicker.show();
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
                datePicker = new DatePickerDialog(ArizaTanimlamaPostActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int ay= monthOfYear+1;
                        // TODO Auto-generated method stub
                        tarihTextView.setText( year + "/" + ay+ "/"+dayOfMonth);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

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




    public  void ArizaTanimlamaPost(String binaadi,String asansoradi , String arayankisi,String arayanTel,String arizatarih,String arizasaat,String arizakonu,String aciklama)
    {

        Call<ArizaTanimlamaPostPojo> request = ManagerAll.getInstance().ArizaTanimlamaPost(binaadi, asansoradi, arayankisi,arayanTel,arizatarih,arizasaat,arizakonu,aciklama);
        request.enqueue(new Callback<ArizaTanimlamaPostPojo>() {
            @Override
            public void onResponse(Call<ArizaTanimlamaPostPojo> call, Response<ArizaTanimlamaPostPojo> response) {

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

