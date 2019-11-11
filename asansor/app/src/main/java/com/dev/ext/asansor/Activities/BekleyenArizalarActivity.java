package com.dev.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.ext.asansor.Adapter.BekleyenArizalarAdapter;
import com.dev.ext.asansor.Models.BekleyenArizalarPojo;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(BekleyenArizalarActivity.this,ArizaActivity.class);
                intent.putExtra("asansorserino", bekleyenArizalarPojoList.get(i).getAsansorserino());
                intent.putExtra("donemtarihi", bekleyenArizalarPojoList.get(i).getDonemtarihi());
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                intent.putExtra("arizaonarbasla", date);

                startActivity(intent);
            }
        });

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

                ilanlarimigoruntulebugun();
            }
        });

        bekleyenArizalarBuayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ilanlarimigoruntuleBuay();
            }
        });

        bekleyenArizalarTumuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ilanlarimigoruntuleTumu();
            }
        });


    }




    public void ilanlarimigoruntulebugun()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Arızalar");
        progressDialog.setMessage("Arızalar yukleniyor ...");
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
                        listView.setAdapter(null);
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
        progressDialog.setTitle("Arızalar");
        progressDialog.setMessage("Arızalar yukleniyor ...");
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
                        listView.setAdapter(null);
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
        progressDialog.setTitle("Arızalar");
        progressDialog.setMessage("Arızalar yukleniyor ...");
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
                        listView.setAdapter(null);
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