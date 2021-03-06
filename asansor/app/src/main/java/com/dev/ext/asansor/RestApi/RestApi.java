package com.dev.ext.asansor.RestApi;


import com.dev.ext.asansor.Models.ArizaPojo;
import com.dev.ext.asansor.Models.BakimPojo;
import com.dev.ext.asansor.Models.ArizaTanimlamaPostPojo;
import com.dev.ext.asansor.Models.BekleyenArizalarPojo;
import com.dev.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.dev.ext.asansor.Models.TahsilatYapSorgulaPostPojo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/bakim.php")
    Call<BakimPojo> bakim(@Query("asansorserino") String asansorserino);

    @GET("/ariza.php")
    Call<ArizaPojo> ariza(@Query("asansorserino") String asansorserino,@Query("donemtarihi") String donemtarihi);

    @FormUrlEncoded
    @POST("/arizapost.php")
    Call<ArizaPojo> arizapost(@Field("yetkili") String yetkili, @Field("arizakonu") String arizakonu, @Field("arizakodu") String arizakodu, @Field("degisenparca") String degisenparca,
            @Field("eposta") String eposta, @Field("tel") String tel, @Field("mesaj") String mesaj, @Field("donemtarihi") String donemtarihi, @Field("asansorserino") String asansorserino, @Field("arizaonarbasla") String arizaonarbasla
            , @Field("arizaonarbitir") String arizaonarbitir, @Field("arizadurum") String arizadurum);

    @FormUrlEncoded
    @POST("/bakimpost.php")
    Call<BakimPojo> bakimpost(@Field("baslik") String baslik, @Field("binaadi") String binaadi, @Field("donemtarihi") String donemtarihi, @Field("yapilacak") String yapilacak,
                              @Field("tutar") String tutar, @Field("yetkili") String yetkili, @Field("aciklama") String aciklama, @Field("tel") String tel
            , @Field("eposta") String eposta, @Field("mesaj") String mesaj, @Field("asansorserino") String asansorserino, @Field("bakimbasla") String bakimbasla
            , @Field("bakimbitir") String bakimbitir, @Field("bakimdurum") String bakimdurum);

    @FormUrlEncoded
    @POST("/tahsilatyapsorgulapost.php")
    Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("yoneticiadi") String yoneticiadi, @Field("yoneticiTel") String yoneticiTel, @Field("kasa") String kasa, @Field("odemeTarihi") String odemeTarihi, @Field("tutar") String tutar, @Field("fis_numarasi") String fis_numarasi, @Field("aciklama") String aciklama);

    @FormUrlEncoded
    @POST("/arizatanimlamapost.php")
    Call<ArizaTanimlamaPostPojo> ArizaTanimlamaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("arayankisi") String arayankisi, @Field("arayanTel") String arayanTel, @Field("arizatarih") String arizatarih, @Field("arizasaat") String arizasaat, @Field("arizakonu") String arizakonu, @Field("aciklama") String aciklama);



    @GET("/tahsilatyapsorgula.php")
    Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula();

/////////////////////////////////////////////

    @GET("/yapilacakbakimlar.php")
    Call<List<BakimPojo>> YapilacakBakimlarTumu();


    @GET("/yapilacakbakimlarbugun.php")
    Call<List<BakimPojo>> YapilacakBakimlarbugun();

    @GET("/yapilacakbakimlarbuay.php")
    Call<List<BakimPojo>> YapilacakBakimlarBuay();

    ////////////////////////////////////////////////////

    @GET("/beklemedebakimlar.php")
    Call<List<BakimPojo>> BeklemedeBakimlar();


    @GET("/baslatilmisbakimlar.php")
    Call<List<BakimPojo>> BaslatilmisBakimlar();

    @GET("/tamamlananbakimlar.php")
    Call<List<BakimPojo>> TamamlananBakimlar();



    /////////////////////////////////////////////////
    @GET("/bekleyenarizalarbugun.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbugun();

    @GET("/bekleyenarizalarbuay.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarbuay();

    @GET("/bekleyenarizalar.php")
    Call<List<BekleyenArizalarPojo>> BekleyenArizalarTumu();

    ////////////////////////////////////////////
    @GET("/arizaonarimdonemtarihisec.php")
    Call<List<BekleyenArizalarPojo>> ArizaOnarimDonemTarihiSec(@Query("asansorserino") String asansorserino);

    @GET("/bakimdonemtarihisec.php")
    Call<List<BakimPojo>> BakimDonemTarihiSec(@Query("asansorserino") String asansorserino);


}
