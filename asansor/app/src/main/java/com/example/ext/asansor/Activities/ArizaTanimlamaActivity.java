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
import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArizaTanimlamaActivity extends AppCompatActivity {



    ListView listView;
    List<TahsilatYapSorgulaPojo> tahsilatYapSorgulaPojoList;
    TahsilatYapSorgulaAdapter tahsilatYapSorgulaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ariza_tanimlama);
        listView=findViewById(R.id.arizaTanimlaListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ArizaTanimlamaActivity.this,ArizaTanimlamaPostActivity.class);
                intent.putExtra("binaadi", tahsilatYapSorgulaPojoList.get(i).getBinaadi());
                intent.putExtra("asansoradi",tahsilatYapSorgulaPojoList.get(i).getAsansoradi());
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
