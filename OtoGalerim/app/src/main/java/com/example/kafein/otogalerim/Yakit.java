package com.example.kafein.otogalerim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.IlanSonucPojo;
import com.example.kafein.otogalerim.Models.ilanVerPojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yakit extends AppCompatActivity {

    Button yakitTuketimBilgisiButon,yakitTuketimBilgisiButonGeri;
    EditText yakitTipiEditText ,ortalamaYakitEditText, depoHacmiEditText;
    SharedPreferences sharedPreferences;
    private View mProgressView;
    private View yakitFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yakit);
        tanimla();
    }

    public void tanimla()
    {
        yakitFormView = findViewById(R.id.yakitView);
        mProgressView = findViewById(R.id.login_progressYakit);


        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        ilanVerPojo.setUye_id(sharedPreferences.getString("uye_id",null));  //db ye atarken uye id bos kalmasın die


        yakitTipiEditText=findViewById(R.id.yakitTipiEditText);
        ortalamaYakitEditText=findViewById(R.id.ortalamaYakitEditText);
        depoHacmiEditText=findViewById(R.id.depoHacmiEditText);

        yakitTipiEditText.setText(ilanVerPojo.getYakittipi()); //geri dondugumuzde doldurulan bilgiler kalsın die
        ortalamaYakitEditText.setText(ilanVerPojo.getOrtalamyakit());
        depoHacmiEditText.setText(ilanVerPojo.getDepohacmi());


        yakitTuketimBilgisiButon=findViewById(R.id.yakitTuketimBilgisiButon);
        yakitTuketimBilgisiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yakitTipiEditText.getText().toString().equals("") && !ortalamaYakitEditText.getText().toString().equals("") && !depoHacmiEditText.getText().toString().equals("")) {

                    showProgress(true);
                    ilanVerPojo.setYakittipi(yakitTipiEditText.getText().toString());  // ekran gecıldıkden sonra get lere dger atıyoruz kı geri dondugumuzde geti cektıgımızde degerli olarak gorunsun
                    ilanVerPojo.setOrtalamyakit(ortalamaYakitEditText.getText().toString());
                    ilanVerPojo.setDepohacmi(depoHacmiEditText.getText().toString());

                    ilaniYayinla(ilanVerPojo.getUye_id(),ilanVerPojo.getSehir(),ilanVerPojo.getIlce(),ilanVerPojo.getMahalle(),ilanVerPojo.getMarka(),ilanVerPojo.getSeri(),ilanVerPojo.getModel(),ilanVerPojo.getYil(),ilanVerPojo.getIlantipi(),ilanVerPojo.getKimden(),ilanVerPojo.getBaslik(),ilanVerPojo.getAciklama(),ilanVerPojo.getMotortipi(),ilanVerPojo.getMotorhacmi(),ilanVerPojo.getSurat(),ilanVerPojo.getYakittipi(),ilanVerPojo.getOrtalamyakit(),ilanVerPojo.getDepohacmi(),ilanVerPojo.getKm());

                }

            else
            {
                Toast.makeText(getApplicationContext(),"Tum bilgileri doldur",Toast.LENGTH_LONG).show();
            }
            }
        });

        yakitTuketimBilgisiButonGeri=findViewById(R.id.yakitTuketimBilgisiButonGeri);
        yakitTuketimBilgisiButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Yakit.this,MotorPerformans.class);

                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back,R.anim.anim_out_back);
                finish();
            }
        });

    }

    public  void ilaniYayinla(String uye_id , String sehir,String ilce , String mahalle,String marka , String seri,String model , String yil,String ilantipi , String kimden,String baslik , String aciklama,String motortipi , String motorhacmi,String surat , String yakittipi,String ortalamyakit , String depohacmi,String km)
    {
        Call<IlanSonucPojo> request = ManagerAll.getInstance().ilanVer(uye_id,sehir,ilce,mahalle,marka,seri,model,yil,ilantipi,kimden,baslik,aciklama,motortipi,motorhacmi,surat,yakittipi,ortalamyakit,depohacmi,km);
        request.enqueue(new Callback<IlanSonucPojo>() {
            @Override
            public void onResponse(Call<IlanSonucPojo> call, Response<IlanSonucPojo> response) {
                if(response.body().isTf())
                {
                        Intent intent = new Intent(Yakit.this, ilanResimler.class);
                    intent.putExtra("ilan_id", response.body().getIlanId());  // ilan id yi resme yolluyoruz, resim eklerken bu id yi kullanıcıaz
                    intent.putExtra("uye_id", (Integer) response.body().getUyeId());
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_inn, R.anim.anim_out);
                    finish();
                    showProgress(false);
                }

            }

            @Override
            public void onFailure(Call<IlanSonucPojo> call, Throwable t) {

            }
        });
    }
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            yakitFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            yakitFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

