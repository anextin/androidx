package com.example.ext.asansor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ext.asansor.Adapter.TahsilatYapSorgulaAdapter;
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.ArrayList;
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
        progressDialog.setTitle("ilanlar");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<TahsilatYapSorgulaPojo>> request = ManagerAll.getInstance().TahsilatYapSorgula();
        request.enqueue(new Callback<List<TahsilatYapSorgulaPojo>>() {
            @Override
            public void onResponse(Call<List<TahsilatYapSorgulaPojo>> call, Response<List<TahsilatYapSorgulaPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        tahsilatYapSorgulaPojoList=response.body();
                        Log.i("kakkk","kakkk"+tahsilatYapSorgulaPojoList);
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