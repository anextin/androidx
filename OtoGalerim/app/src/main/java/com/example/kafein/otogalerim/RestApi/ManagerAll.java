package com.example.kafein.otogalerim.RestApi;



import com.example.kafein.otogalerim.Models.DogrulamaPojo;
import com.example.kafein.otogalerim.Models.IlanDetayPojo;
import com.example.kafein.otogalerim.Models.IlanSonucPojo;
import com.example.kafein.otogalerim.Models.IlanlarPojo;
import com.example.kafein.otogalerim.Models.IlanlarimPojo;
import com.example.kafein.otogalerim.Models.IlanlarimSilPojo;
import com.example.kafein.otogalerim.Models.LoginPojo;
import com.example.kafein.otogalerim.Models.RegisterPojo;
import com.example.kafein.otogalerim.Models.ResimEklePojo;

import java.util.List;

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

    public Call<IlanSonucPojo> ilanVer(String ucret,String uye_id , String sehir,String ilce , String mahalle,String marka , String seri,String model , String yil,String ilantipi , String kimden,String baslik , String aciklama,String motortipi , String motorhacmi,String surat , String yakittipi,String ortalamyakit , String depohacmi,String km) {
        Call<IlanSonucPojo> x= getRestApi().ilanVer(ucret,uye_id,sehir,ilce,mahalle,marka,seri,model,yil,ilantipi,kimden,baslik,aciklama,motortipi,motorhacmi,surat,yakittipi,ortalamyakit,depohacmi,km);
        return x;

    }


    public Call<ResimEklePojo> resimEkle(String uye_id , String ilan_id, String image) {

        Call<ResimEklePojo> x = getRestApi().resimYukle(uye_id, ilan_id, image);
        return x;
    }


    public Call<List<IlanlarimPojo>> ilanlarim(String uyeid) {
        Call<List<IlanlarimPojo>> x= getRestApi().ilanlarim(uyeid);
        return x;

    }



    public Call<IlanlarimSilPojo> ilanlarimSil(String ilanid) {

        Call<IlanlarimSilPojo> x = getRestApi().ilanlarimSil(ilanid);
        return x;
    }

    public Call<List<IlanlarPojo>> ilanlar() {

        Call<List<IlanlarPojo>> x = getRestApi().ilanlar();
        return x;
    }

    public Call<IlanDetayPojo> ilanDetay(String ilanid) {

        Call<IlanDetayPojo> x = getRestApi().ilanDetay(ilanid);
        return x;
    }


    }







