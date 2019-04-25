package com.example.ext.asansor.RestApi;



import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;

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

    public Call<List<TahsilatYapSorgulaPojo>> TahsilatYapSorgula() {

        Call<List<TahsilatYapSorgulaPojo>> x = getRestApi().TahsilatYapSorgula();
        return x;
    }



    }







