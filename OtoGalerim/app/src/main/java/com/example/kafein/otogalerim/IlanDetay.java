package com.example.kafein.otogalerim;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.kafein.otogalerim.Models.IlanDetayPojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetay extends AppCompatActivity {


    private TextView ilanDetayBaslik , ilanDetayScrool, ilandetayFiyat,  ilandetayMarka , ilandetayModel , ilandetaySeri;
    private TextView ilandetayYil  ,ilandetayilantipi , ilandetayKimden  ,ilandetayMotorTipi  ,ilandetayMotorHacmi  ,ilandetaySurat , ilandetayYakitTipi  ,ilandetayOrtalamaYakit,ilandetayDepoHacmi, ilandetayKM;
    private Button ilanDetayFavoriyeAl ,ilanDetayAciklama;
    private ViewPager ilanDetaySlider;
    String ilanId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);
        Bundle bundle=getIntent().getExtras();
        ilanId=bundle.getString("ilanid");
        tanimla();
        getIlanDetay();
    }



    public void tanimla()
    {
        ilanDetayBaslik= findViewById(R.id.ilanDetayBaslik);
        ilandetayFiyat= findViewById(R.id.ilandetayFiyat);
        ilandetayMarka= findViewById(R.id.ilandetayMarka);
        ilandetayModel= findViewById(R.id.ilandetayModel);
        ilandetaySeri= findViewById(R.id.ilandetaySeri);
        ilandetayYil= findViewById(R.id.ilandetayYil);
        ilandetayilantipi= findViewById(R.id.ilandetayilantipi);
        ilandetayKimden= findViewById(R.id.ilandetayKimden);
        ilandetayMotorTipi= findViewById(R.id.ilandetayMotorTipi);
        ilandetayMotorHacmi= findViewById(R.id.ilandetayMotorHacmi);
        ilandetaySurat= findViewById(R.id.ilandetaySurat);
        ilandetayYakitTipi= findViewById(R.id.ilandetayYakitTipi);
        ilandetayOrtalamaYakit= findViewById(R.id.ilandetayOrtalamaYakit);
        ilandetayDepoHacmi= findViewById(R.id.ilandetayDepoHacmi);
        ilandetayKM= findViewById(R.id.ilandetayKM);

        ilanDetayFavoriyeAl= findViewById(R.id.ilanDetayFavoriyeAl);
        ilanDetayAciklama= findViewById(R.id.ilanDetayAciklama);

        ilanDetaySlider= findViewById(R.id.ilanDetaySlider);
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



//ilanDetayBaslik
  //      ilanDetayScrool   ilandetayFiyat  ilandetayMarka  ilandetayModel  ilandetaySeri  ilandetayYil  ilandetayilantipi  ilandetayKimden  ilandetayMotorTipi  ilandetayMotorHacmi  ilandetaySurat  ilandetayYakitTipi  ilandetayOrtalamaYakit

  //ilandetayDepoHacmi ilandetayKM