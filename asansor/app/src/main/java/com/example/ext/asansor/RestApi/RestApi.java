package com.example.ext.asansor.RestApi;


import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;


import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @FormUrlEncoded
    @POST("/ariza.php")
    Call<BakimPojo> ariza(@Field("baslik") String baslik, @Field("binaadi") String binaadi, @Field("donemtarihi") String donemtarihi, @Field("yapilacak") String yapilacak,
            @Field("tutar") String tutar, @Field("yetkili") String yetkili, @Field("aciklama") String aciklama, @Field("tel") String tel
            , @Field("eposta") String eposta, @Field("mesaj") String mesaj, @Field("asansorserino") String asansorserino, @Field("bakimbasla") String bakimbasla
            , @Field("bakimbitir") String bakimbitir, @Field("bakimdurum") String bakimdurum);

    @FormUrlEncoded
    @POST("/tahsilatyapsorgulapost.php")
    Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("yoneticiadi") String yoneticiadi, @Field("yoneticiTel") String yoneticiTel, @Field("kasa") String kasa, @Field("odemeTarihi") String odemeTarihi, @Field("tutar") String tutar, @Field("fis_numarasi") String fis_numarasi, @Field("aciklama") String aciklama);

    @FormUrlEncoded
    @POST("/arizatanimlamapost.php")
    Call<ArizaTanimlamaPostPojo> ArizaTanimlamaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("arayankisi") String arayankisi, @Field("arayanTel") String arayanTel, @Field("arizatarih") String arizatarih, @Field("arizasaat") String arizasaat, @Field("arizakonu") String arizakonu, @Field("aciklama") String aciklama);




    @GET("/bakim.php")
    Call<BakimPojo> bakim(@Query("asansorserino") String asansorserino);


    @GET("/yapilacakbakimlar.php")
    Call<List<BakimPojo>> YapilacakBakimlarTumu();

    //////////////////////////////////////////////////////

    @GET("/tahsilatyapsorgula.php")
    Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula();

    @GET("/yapilacakbakimlarbugun.php")
    Call<List<BakimPojo>> YapilacakBakimlarbugun();

    @GET("/yapilacakbakimlarbuay.php")
    Call<List<BakimPojo>> YapilacakBakimlarBuay();



    /////////////////////////////////////////////////
    @GET("/bekleyenarizalarbugun.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbugun();

    @GET("/bekleyenarizalarbuay.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbuay();

    @GET("/bekleyenarizalar.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarTumu();

}
