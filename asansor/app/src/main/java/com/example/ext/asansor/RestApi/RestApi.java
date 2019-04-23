package com.example.ext.asansor.RestApi;


import com.example.ext.asansor.Models.ArizaPojo;



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



}
