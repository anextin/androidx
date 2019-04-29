package com.example.ext.asansor.RestApi;


import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/ariza.php")
    Call<BakimPojo> ariza(@Field("binaAdi") String binaAdi, @Field("arizaTuru") String arizaTuru, @Field("aciklama") String aciklama);

    @FormUrlEncoded
    @POST("/tahsilatyapsorgulapost.php")
    Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("yoneticiadi") String yoneticiadi, @Field("yoneticiTel") String yoneticiTel, @Field("kasa") String kasa, @Field("odemeTarihi") String odemeTarihi, @Field("tutar") String tutar, @Field("fis_numarasi") String fis_numarasi, @Field("aciklama") String aciklama);

    @FormUrlEncoded
    @POST("/arizatanimlamapost.php")
    Call<ArizaTanimlamaPostPojo> ArizaTanimlamaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("arayankisi") String arayankisi, @Field("arayanTel") String arayanTel, @Field("arizatarih") String arizatarih, @Field("arizasaat") String arizasaat, @Field("arizakonu") String arizakonu, @Field("aciklama") String aciklama);






    @GET("/tahsilatyapsorgula.php")
    Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula();

    @GET("/yapilacakbakimlarbugun.php")
    Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarbugun();

    @GET("/yapilacakbakimlarbuay.php")
    Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarBuay();

    @GET("/yapilacakbakimlar.php")
    Call<List<YapilacakBakimlarPojo>> YapilacakBakimlarTumu();

    /////////////////////////////////////////////////7
    @GET("/bekleyenarizalarbugun.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbugun();

    @GET("/bekleyenarizalarbuay.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbuay();

    @GET("/bekleyenarizalar.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarTumu();

}
