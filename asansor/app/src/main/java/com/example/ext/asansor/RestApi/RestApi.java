package com.example.ext.asansor.RestApi;


import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPostPojo;


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
    Call<ArizaPojo> ariza(@Field("binaAdi") String binaAdi, @Field("arizaTuru") String arizaTuru, @Field("aciklama") String aciklama);

    @FormUrlEncoded
    @POST("/tahsilatyapsorgulapost.php")
    Call<TahsilatYapSorgulaPostPojo> TahsilatYapSorgulaPost(@Field("binaadi") String binaadi, @Field("asansoradi") String asansoradi, @Field("yoneticiadi") String yoneticiadi, @Field("yoneticiTel") String yoneticiTel, @Field("kasa") String kasa, @Field("odemeTarihi") String odemeTarihi, @Field("tutar") String tutar, @Field("fis_numarasi") String fis_numarasi, @Field("aciklama") String aciklama);






    @GET("/tahsilatyapsorgula.php")
    Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula();

}
