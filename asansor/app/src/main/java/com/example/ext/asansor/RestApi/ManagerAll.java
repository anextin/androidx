package com.example.ext.asansor.RestApi;



import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;

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



    public Call<BakimPojo> bakimpost(String baslik, String binaadi , String donemtarihi, String yapilacak, String tutar, String yetkili, String aciklama, String tel
            , String eposta, String mesaj, String asansorserino, String bakimbasla, String bakimbitir, String bakimdurum) {
        Call<BakimPojo> x= getRestApi().bakimpost( baslik, binaadi ,  donemtarihi, yapilacak, tutar, yetkili, aciklama, tel, eposta,
                mesaj, asansorserino, bakimbasla, bakimbitir, bakimdurum);
        return x;

    }

    public Call<ArizaPojo> arizapost(String yetkili, String arizakonu , String arizakodu, String degisenparca, String eposta, String tel, String mesaj,String donemtarihi, String asansorserino
            , String arizaonarbasla, String arizaonarbitir, String arizadurum) {
        Call<ArizaPojo> x= getRestApi().arizapost( yetkili, arizakonu ,  arizakodu, degisenparca, eposta, tel, mesaj, donemtarihi,asansorserino, arizaonarbasla,
                arizaonarbitir, arizadurum);
        return x;

    }

    public Call<BakimPojo> bakim(String asansorserino) {
        Call<BakimPojo> x= getRestApi().bakim(asansorserino);
        return x;

    }

    public Call<ArizaPojo> ariza(String asansorserino) {

        Call<ArizaPojo> x = getRestApi().ariza(asansorserino);
        return x;
    }

    public Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(String binaadi,String asansoradi,String yoneticiadi,String yoneticiTel,String kasa,String odemeTarihi,String tutar,String fis_numarasi,String aciklama) {
        Call<TahsilatYapSorgulaPostPojo> x= getRestApi().TahsilatYapSorgulaPost(binaadi,asansoradi,yoneticiadi,yoneticiTel,kasa,odemeTarihi,tutar,fis_numarasi,aciklama);

        return x;

    }

    public Call<ArizaTanimlamaPostPojo> ArizaTanimlamaPost(String binaadi, String asansoradi, String arayankisi, String arayanTel, String arizatarih, String arizasaat, String arizakonu, String aciklama) {
        Call<ArizaTanimlamaPostPojo> x= getRestApi().ArizaTanimlamaPost(binaadi,asansoradi,arayankisi,arayanTel,arizatarih,arizasaat,arizakonu,aciklama);

        return x;

    }

    public Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula() {

        Call<List<TahsilatYapSorgulaPojo>> x = getRestApi().TahsilatYapSorgula();
        return x;
    }

    /////////////////////////////////////////////////////////////////////////////////777

    public Call<List<BakimPojo>> YapilacakBakimlarbugun() {

        Call<List<BakimPojo>> x = getRestApi().YapilacakBakimlarbugun();
        return x;
    }


    public Call<List<BakimPojo>> YapilacakBakimlarBuay() {

        Call<List<BakimPojo>> x = getRestApi().YapilacakBakimlarBuay();
        return x;
    }


    public Call<List<BakimPojo>> YapilacakBakimlarTumu() {

        Call<List<BakimPojo>> x = getRestApi().YapilacakBakimlarTumu();
        return x;
    }

    public Call<List<BekleyenArizalarPojo>> BekleyenArizalarbugun() {

        Call<List<BekleyenArizalarPojo>> x = getRestApi().BekleyenArizalarbugun();
        return x;
    }


    public Call<List<BekleyenArizalarPojo>> BekleyenArizalarBuay() {

        Call<List<BekleyenArizalarPojo>> x = getRestApi().BekleyenArizalarbuay();
        return x;
    }

    public Call<List<BekleyenArizalarPojo>> BekleyenArizalarTumu() {

        Call<List<BekleyenArizalarPojo>> x = getRestApi().BekleyenArizalarTumu();
        return x;
    }


    }







