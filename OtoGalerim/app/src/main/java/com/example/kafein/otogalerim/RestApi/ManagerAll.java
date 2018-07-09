package com.example.kafein.otogalerim.RestApi;



import com.example.kafein.otogalerim.Models.DogrulamaPojo;
import com.example.kafein.otogalerim.Models.IlanSonucPojo;
import com.example.kafein.otogalerim.Models.LoginPojo;
import com.example.kafein.otogalerim.Models.RegisterPojo;

import retrofit2.Call;

/**
 * Created by Kafein on 5/10/2018.
 */

public class ManagerAll extends BaseManager{

    private static ManagerAll ourgetInstance= new ManagerAll();
    public static synchronized ManagerAll getInstance()
    {
        return ourgetInstance;
    }

    public Call<LoginPojo> login(String ad , String sifre) {
        Call<LoginPojo> x= getRestApi().control(ad,sifre);
        return x;

    }


    public Call<RegisterPojo> register(String ad , String sifre) {
        Call<RegisterPojo> x= getRestApi().kayitol(ad,sifre);
        return x;

    }


    public Call<DogrulamaPojo> dogrula(String ad , String kod) {
        Call<DogrulamaPojo> x= getRestApi().dogrulama(ad,kod);
        return x;

    }

    public Call<IlanSonucPojo> ilanVer(String uye_id , String sehir,String ilce , String mahalle,String marka , String seri,String model , String yil,String ilantipi , String kimden,String baslik , String aciklama,String motortipi , String motorhacmi,String surat , String yakittipi,String ortalamyakit , String depohacmi,String km) {
        Call<IlanSonucPojo> x= getRestApi().ilanVer(uye_id,sehir,ilce,mahalle,marka,seri,model,yil,ilantipi,kimden,baslik,aciklama,motortipi,motorhacmi,surat,yakittipi,ortalamyakit,depohacmi,km);
        return x;

    }







}