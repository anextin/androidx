package com.example.ext.asansor.RestApi;



import com.example.ext.asansor.Models.ArizaPojo;

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



    }







