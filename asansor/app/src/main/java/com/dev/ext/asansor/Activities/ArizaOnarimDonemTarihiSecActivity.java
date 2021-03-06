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

import com.dev.ext.asansor.Adapter.ArizaOnarimDonemTarihiSecAdapter;
import com.dev.ext.asansor.Models.BekleyenArizalarPojo;
import com.dev.ext.asansor.R;
import com.dev.ext.asansor.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArizaOnarimDonemTarihiSecActivity extends AppCompatActivity {

    ListView listView;
    List<BekleyenArizalarPojo> bekleyenArizalarPojoList;
    ArizaOnarimDonemTarihiSecAdapter arizaOnarimDonemTarihiSecAdapter;
    String asansorserino,arizaonarbasla,donemtarihi;
    Button bekleyenArizalarBugunButton,bekleyenArizalarBuayButton,bekleyenArizalarTumuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        asansorserino=bundle.getString("asansorserino");
        arizaonarbasla=bundle.getString("arizaonarbasla");
        donemtarihi=bundle.getString("donemtarihi");
        setContentView(R.layout.activity_ariza_onarim_donem_tarihi_sec);
        listView=findViewById(R.id.ArizaOnarimDonemTarihiSecListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ArizaOnarimDonemTarihiSecActivity.this,ArizaActivity.class);
                intent.putExtra("asansorserino", bekleyenArizalarPojoList.get(i).getAsansorserino());
                intent.putExtra("donemtarihi", bekleyenArizalarPojoList.get(i).getDonemtarihi());
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                intent.putExtra("arizaonarbasla", date);

                startActivity(intent);
            }
        });

        ilanlarimigoruntuleTumu();
    }


    public void ilanlarimigoruntuleTumu()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Arızalar");
        progressDialog.setMessage("Arızalar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<List<BekleyenArizalarPojo>> request = ManagerAll.getInstance().ArizaOnarimDonemTarihiSec(asansorserino);
        request.enqueue(new Callback<List<BekleyenArizalarPojo>>() {
            @Override
            public void onResponse(Call<List<BekleyenArizalarPojo>> call, Response<List<BekleyenArizalarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        bekleyenArizalarPojoList=response.body();

                        arizaOnarimDonemTarihiSecAdapter= new ArizaOnarimDonemTarihiSecAdapter(bekleyenArizalarPojoList,getApplicationContext());

                        listView.setAdapter(arizaOnarimDonemTarihiSecAdapter);
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
