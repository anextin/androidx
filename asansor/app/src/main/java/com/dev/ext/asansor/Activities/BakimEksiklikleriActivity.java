package com.dev.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.ext.asansor.Adapter.YapilacakBakimlarAdapter;
import com.dev.ext.asansor.Models.BakimPojo;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BakimEksiklikleriActivity extends AppCompatActivity {

    ListView listView;
    List<BakimPojo> BakimPojoPojoList;
    YapilacakBakimlarAdapter yapilacakBakimlarAdapter;
    Boolean clickable;
    Button yapilacakBakimlarBugunButton,yapilacakBakimlarBuayButton,yapilacakBakimlarTumuButton,TamamlanmisButton,BaslatilmisButton,BeklemedeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakim_eksiklikleri);
        listView=findViewById(R.id.bakimeksiklikleriListView);



        tanimla();
        ilanlarimigoruntulebugun();
    }

    public void tanimla()
    {
        BeklemedeButton = findViewById(R.id.BeklemedeButton);
        BaslatilmisButton = findViewById(R.id.BaslatilmisButton);
        TamamlanmisButton = findViewById(R.id.TamamlanmisButton);


        BeklemedeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilanlarimigoruntulebugun();
            }
        });

        BaslatilmisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilanlarimigoruntuleBuay();
            }
        });

        TamamlanmisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilanlarimigoruntuleTumu();
            }
        });





    }




    public void ilanlarimigoruntulebugun()
    {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("bakımlar");
        progressDialog.setMessage("beklemede bakımlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        clickable=true;


        Call<List<BakimPojo>> request = ManagerAll.getInstance().BeklemedeBakimlar();
        request.enqueue(new Callback<List<BakimPojo>>() {
            @Override
            public void onResponse(Call<List<BakimPojo>> call, Response<List<BakimPojo>> response) {
                if(response.isSuccessful())
                {
                    Log.i("arda","arda"+response.body().get(0).getBaslik());
                    if(response.body().get(0).isTf())
                    {
                        BakimPojoPojoList=response.body();


                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(BakimPojoPojoList,getApplicationContext());

                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bugun yapılacak bakım bulunmamaktadir", Toast.LENGTH_LONG).show();


                        listView.setAdapter(null);
                        progressDialog.cancel();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<BakimPojo>> call, Throwable t) {

            }
        });


        if(clickable==true) {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(BakimEksiklikleriActivity.this, BakimActivity.class);
                    intent.putExtra("asansorserino", BakimPojoPojoList.get(i).getAsansorserino());
                    intent.putExtra("donemtarihi", BakimPojoPojoList.get(i).getDonemtarihi());
                    intent.putExtra("yetkili", BakimPojoPojoList.get(i).getYetkili());

                    startActivity(intent);
                }
            });
        }

    }


    public void ilanlarimigoruntuleBuay()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("bakımlar");
        progressDialog.setMessage("başlatılmış bakımlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        clickable=true;

        Call<List<BakimPojo>> request = ManagerAll.getInstance().BaslatilmisBakimlar();
        request.enqueue(new Callback<List<BakimPojo>>() {
            @Override
            public void onResponse(Call<List<BakimPojo>> call, Response<List<BakimPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        BakimPojoPojoList=response.body();

                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(BakimPojoPojoList,getApplicationContext());
                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bu ay yapılacak bakım bulunmamaktadir", Toast.LENGTH_LONG).show();
                        listView.setAdapter(null);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BakimPojo>> call, Throwable t) {

            }
        });


        if(clickable==true) {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(BakimEksiklikleriActivity.this, BakimActivity.class);
                    intent.putExtra("asansorserino", BakimPojoPojoList.get(i).getAsansorserino());
                    intent.putExtra("donemtarihi", BakimPojoPojoList.get(i).getDonemtarihi());
                    intent.putExtra("yetkili", BakimPojoPojoList.get(i).getYetkili());

                    startActivity(intent);
                }
            });
        }

    }

    public void ilanlarimigoruntuleTumu()
    {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("bakımlar");
        progressDialog.setMessage("tamamlanan bakımlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        clickable=false;


        Call<List<BakimPojo>> request = ManagerAll.getInstance().TamamlananBakimlar();
        request.enqueue(new Callback<List<BakimPojo>>() {
            @Override
            public void onResponse(Call<List<BakimPojo>> call, Response<List<BakimPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        BakimPojoPojoList=response.body();

                        yapilacakBakimlarAdapter= new YapilacakBakimlarAdapter(BakimPojoPojoList,getApplicationContext());

                        listView.setAdapter(yapilacakBakimlarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {

                        listView.setAdapter(null);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BakimPojo>> call, Throwable t) {

            }
        });

        if(clickable==true) {
            Log.i("click","aaa: "+clickable);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    yapilacakBakimlarAdapter.isEnabled(i);
                    Intent intent = new Intent(BakimEksiklikleriActivity.this, BakimActivity.class);
                    intent.putExtra("asansorserino", BakimPojoPojoList.get(i).getAsansorserino());
                    intent.putExtra("donemtarihi", BakimPojoPojoList.get(i).getDonemtarihi());
                    intent.putExtra("yetkili", BakimPojoPojoList.get(i).getYetkili());

                    startActivity(intent);
                }
            });
        }

    }





}