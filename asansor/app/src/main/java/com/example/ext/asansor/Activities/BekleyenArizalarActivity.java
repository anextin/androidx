package com.example.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ext.asansor.Adapter.BekleyenArizalarAdapter;
import com.example.ext.asansor.Adapter.YapilacakBakimlarAdapter;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BekleyenArizalarActivity extends AppCompatActivity {

    ListView listView;
    List<BekleyenArizalarPojo> bekleyenArizalarPojoList;
    BekleyenArizalarAdapter bekleyenArizalarAdapter;
    Button bekleyenArizalarBugunButton,bekleyenArizalarBuayButton,bekleyenArizalarTumuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bekleyen_arizalar);
        listView=findViewById(R.id.bekleyenArizalarListView);

        tanimla();
        ilanlarimigoruntulebugun();
    }

    public void tanimla()
    {
        bekleyenArizalarBugunButton = findViewById(R.id.bekleyenArizalarBugunButton);
        bekleyenArizalarBuayButton = findViewById(R.id.bekleyenArizalarBuayButton);
        bekleyenArizalarTumuButton = findViewById(R.id.bekleyenArizalarTumuButton);


        bekleyenArizalarBugunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bugun","bugun"+bekleyenArizalarBugunButton);
                Toast.makeText(getApplicationContext(),"bugun",Toast.LENGTH_LONG).show();
                ilanlarimigoruntulebugun();
            }
        });

        bekleyenArizalarBuayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Buay","Buay"+bekleyenArizalarBuayButton);
                Toast.makeText(getApplicationContext(),"Buay",Toast.LENGTH_LONG).show();
                ilanlarimigoruntuleBuay();
            }
        });

        bekleyenArizalarTumuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Tumu","Tumu"+bekleyenArizalarTumuButton);
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


        Call<List<BekleyenArizalarPojo>> request = ManagerAll.getInstance().BekleyenArizalarbugun();
        request.enqueue(new Callback<List<BekleyenArizalarPojo>>() {
            @Override
            public void onResponse(Call<List<BekleyenArizalarPojo>> call, Response<List<BekleyenArizalarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        bekleyenArizalarPojoList=response.body();

                        bekleyenArizalarAdapter= new BekleyenArizalarAdapter(bekleyenArizalarPojoList,getApplicationContext());

                        listView.setAdapter(bekleyenArizalarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bugun bekleyen arıza bulunmamaktadir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BekleyenArizalarPojo>> call, Throwable t) {

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


        Call<List<BekleyenArizalarPojo>> request = ManagerAll.getInstance().BekleyenArizalarBuay();
        request.enqueue(new Callback<List<BekleyenArizalarPojo>>() {
            @Override
            public void onResponse(Call<List<BekleyenArizalarPojo>> call, Response<List<BekleyenArizalarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        bekleyenArizalarPojoList=response.body();

                        bekleyenArizalarAdapter= new BekleyenArizalarAdapter(bekleyenArizalarPojoList,getApplicationContext());

                        listView.setAdapter(bekleyenArizalarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bu ay bekleyen arıza bulunmamaktadir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BekleyenArizalarPojo>> call, Throwable t) {

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


        Call<List<BekleyenArizalarPojo>> request = ManagerAll.getInstance().BekleyenArizalarTumu();
        request.enqueue(new Callback<List<BekleyenArizalarPojo>>() {
            @Override
            public void onResponse(Call<List<BekleyenArizalarPojo>> call, Response<List<BekleyenArizalarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        bekleyenArizalarPojoList=response.body();

                        bekleyenArizalarAdapter= new BekleyenArizalarAdapter(bekleyenArizalarPojoList,getApplicationContext());

                        listView.setAdapter(bekleyenArizalarAdapter);
                        progressDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "bekleyen arıza bulunmamaktadir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BekleyenArizalarPojo>> call, Throwable t) {

            }
        });

    }


}