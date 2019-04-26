package com.example.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ext.asansor.Adapter.TahsilatYapSorgulaAdapter;
import com.example.ext.asansor.Adapter.YapilacakBakimlarAdapter;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YapilacakBakimlarActivity extends AppCompatActivity {

    ListView listView;
    List<YapilacakBakimlarPojo> yapilacakBakimlarPojoList;
    YapilacakBakimlarAdapter yapilacakBakimlarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacak_bakimlar);
        listView=findViewById(R.id.yapilacakBakimlarListView);


        ilanlarimigoruntule();
    }

    public void ilanlarimigoruntule()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ilanlar");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<YapilacakBakimlarPojo>> request = ManagerAll.getInstance().YapilacakBakimlar();
        request.enqueue(new Callback<List<YapilacakBakimlarPojo>>() {
            @Override
            public void onResponse(Call<List<YapilacakBakimlarPojo>> call, Response<List<YapilacakBakimlarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        yapilacakBakimlarPojoList=response.body();
                        Log.i("kakkk","kakkk"+yapilacakBakimlarPojoList);
                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(yapilacakBakimlarPojoList,getApplicationContext());
                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<YapilacakBakimlarPojo>> call, Throwable t) {

            }
        });

    }
}