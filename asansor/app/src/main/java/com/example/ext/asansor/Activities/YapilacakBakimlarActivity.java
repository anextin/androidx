package com.example.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ext.asansor.Adapter.YapilacakBakimlarAdapter;
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
    Button yapilacakBakimlarBugunButton,yapilacakBakimlarBuayButton,yapilacakBakimlarTumuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yapilacak_bakimlar);
        listView=findViewById(R.id.yapilacakBakimlarListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(YapilacakBakimlarActivity.this,BakimActivity.class);
                intent.putExtra("asansorserino", yapilacakBakimlarPojoList.get(i).getBinaadi());
                startActivity(intent);
            }
        });

        tanimla();
        ilanlarimigoruntulebugun();
    }

    public void tanimla()
    {
        yapilacakBakimlarBugunButton = findViewById(R.id.yapilacakBakimlarBugunButton);
        yapilacakBakimlarBuayButton = findViewById(R.id.yapilacakBakimlarBuayButton);
        yapilacakBakimlarTumuButton = findViewById(R.id.yapilacakBakimlarTumuButton);


        yapilacakBakimlarBugunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bugun","bugun"+yapilacakBakimlarPojoList);
                Toast.makeText(getApplicationContext(),"bugun",Toast.LENGTH_LONG).show();
                ilanlarimigoruntulebugun();
            }
        });

        yapilacakBakimlarBuayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Buay","Buay"+yapilacakBakimlarPojoList);
                Toast.makeText(getApplicationContext(),"Buay",Toast.LENGTH_LONG).show();
                ilanlarimigoruntuleBuay();
            }
        });

        yapilacakBakimlarTumuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Tumu","Tumu"+yapilacakBakimlarPojoList);
                Toast.makeText(getApplicationContext(),"Tumu",Toast.LENGTH_LONG).show();
                ilanlarimigoruntuleTumu();
            }
        });


    }




    public void ilanlarimigoruntulebugun()
    {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ilanlar");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<YapilacakBakimlarPojo>> request = ManagerAll.getInstance().YapilacakBakimlarbugun();
        request.enqueue(new Callback<List<YapilacakBakimlarPojo>>() {
            @Override
            public void onResponse(Call<List<YapilacakBakimlarPojo>> call, Response<List<YapilacakBakimlarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        yapilacakBakimlarPojoList=response.body();

                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(yapilacakBakimlarPojoList,getApplicationContext());

                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bugun yapılacak bakım bulunmamaktadir", Toast.LENGTH_LONG).show();
                        yapilacakBakimlarPojoList=response.body();

                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(yapilacakBakimlarPojoList,getApplicationContext());

                        listView.setAdapter(null);
                        progressDialog.cancel();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<YapilacakBakimlarPojo>> call, Throwable t) {

            }
        });

    }


    public void ilanlarimigoruntuleBuay()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ilanlar");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<YapilacakBakimlarPojo>> request = ManagerAll.getInstance().YapilacakBakimlarBuay();
        request.enqueue(new Callback<List<YapilacakBakimlarPojo>>() {
            @Override
            public void onResponse(Call<List<YapilacakBakimlarPojo>> call, Response<List<YapilacakBakimlarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        yapilacakBakimlarPojoList=response.body();

                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(yapilacakBakimlarPojoList,getApplicationContext());
                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bu ay yapılacak bakım bulunmamaktadir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<YapilacakBakimlarPojo>> call, Throwable t) {

            }
        });

    }

    public void ilanlarimigoruntuleTumu()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ilanlar");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<YapilacakBakimlarPojo>> request = ManagerAll.getInstance().YapilacakBakimlarTumu();
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
                        Toast.makeText(getApplicationContext(),"ilanlarimigoruntuleTumu",Toast.LENGTH_LONG).show();
                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Yapılacak bakım bulunmamaktadir", Toast.LENGTH_LONG).show();
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