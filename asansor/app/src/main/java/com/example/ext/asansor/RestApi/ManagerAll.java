package com.example.ext.asansor.RestApi;



import android.util.Log;

import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;

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



    public Call<ArizaPojo> ariza(String binaAdi, String arizaTuru , String aciklama) {
        Call<ArizaPojo> x= getRestApi().ariza(binaAdi,arizaTuru,aciklama);
        return x;

    }

    public Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(String binaadi,String asansoradi,String yoneticiadi,String yoneticiTel,String kasa,String odemeTarihi,String tutar,String fis_numarasi,String aciklama) {
        Call<TahsilatYapSorgulaPostPojo> x= getRestApi().TahsilatYapSorgulaPost(binaadi,asansoradi,yoneticiadi,yoneticiTel,kasa,odemeTarihi,tutar,fis_numarasi,aciklama);

        return x;

    }

    public Call<ArizaTanimlamaPostPojo> ArizaTanimlamaPost(String binaadi, String asansoradi,String arayankisi, String arayanTel, String arizatarih, String arizasaat, String arizakonu, String aciklama) {
        Call<ArizaTanimlamaPostPojo> x= getRestApi().ArizaTanimlamaPost(binaadi,asansoradi,arayankisi,arayanTel,arizatarih,arizasaat,arizakonu,aciklama);

        return x;

    }

    public Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula() {

        Call<List<TahsilatYapSorgulaPojo>> x = getRestApi().TahsilatYapSorgula();
        return x;
    }


    public Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarbugun() {

        Call<List<YapilacakBakimlarPojo>> x = getRestApi().YapilacakBakimlarbugun();
        return x;
    }


    public Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarBuay() {

        Call<List<YapilacakBakimlarPojo>> x = getRestApi().YapilacakBakimlarBuay();
        return x;
    }

    public Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarTumu() {

        Call<List<YapilacakBakimlarPojo>> x = getRestApi().YapilacakBakimlarTumu();
        return x;
    }



    }







