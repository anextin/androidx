package com.dev.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dev.ext.asansor.Adapter.TahsilatYapSorgulaAdapter;
import com.dev.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahsilatYapActivity extends AppCompatActivity {

    ListView listView;
    List<TahsilatYapSorgulaPojo> tahsilatYapSorgulaPojoList;
    TahsilatYapSorgulaAdapter tahsilatYapSorgulaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahsilat_yap);
        listView=findViewById(R.id.tahsilatYapSorgulaListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(TahsilatYapActivity.this,TahsilatYapPostActivity.class);
                intent.putExtra("binaadi",tahsilatYapSorgulaPojoList.get(i).getBinaadi());
                intent.putExtra("asansoradi",tahsilatYapSorgulaPojoList.get(i).getAsansoradi());
                intent.putExtra("yoneticiadi",tahsilatYapSorgulaPojoList.get(i).getYoneticiadi());
                startActivity(intent);
            }
        });

        ilanlarimigoruntule();
    }

    public void ilanlarimigoruntule()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tahsilatlar");
        progressDialog.setMessage("Tahsilatlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<TahsilatYapSorgulaPojo>> request = ManagerAll.getInstance().TahsilatYapSorgula();
        request.enqueue(new Callback<List<TahsilatYapSorgulaPojo>>() {
            @Override
            public void onResponse(Call<List<TahsilatYapSorgulaPojo>> call, Response<List<TahsilatYapSorgulaPojo>> response) {
                System.out.println("arda..:"+ response.isSuccessful());
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        tahsilatYapSorgulaPojoList=response.body();
                        System.out.println("arda..:"+ tahsilatYapSorgulaPojoList);
                        tahsilatYapSorgulaAdapter= new TahsilatYapSorgulaAdapter(tahsilatYapSorgulaPojoList,getApplicationContext());
                        listView.setAdapter(tahsilatYapSorgulaAdapter);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TahsilatYapSorgulaPojo>> call, Throwable t) {

            }
        });

    }
}