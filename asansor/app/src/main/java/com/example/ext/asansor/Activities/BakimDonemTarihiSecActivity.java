package com.example.ext.asansor.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ext.asansor.Adapter.ArizaOnarimDonemTarihiSecAdapter;
import com.example.ext.asansor.Adapter.BakimDonemTarihiSecAdapter;
import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;
import com.example.ext.asansor.RestApi.ManagerAll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BakimDonemTarihiSecActivity extends AppCompatActivity {

    ListView listView;
    List<BakimPojo> BakimPojoPojoList;
    BakimDonemTarihiSecAdapter bakimDonemTarihiSecAdapter;
    String asansorserino,arizaonarbasla,donemtarihi;
    Button bekleyenArizalarBugunButton,bekleyenArizalarBuayButton,bekleyenArizalarTumuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        asansorserino=bundle.getString("asansorserino");
        arizaonarbasla=bundle.getString("arizaonarbasla");
        donemtarihi=bundle.getString("donemtarihi");
        setContentView(R.layout.activity_bakim_donem_tarihi_sec);
        listView=findViewById(R.id.BakimDonemTarihiSecListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(BakimDonemTarihiSecActivity.this,BakimActivity.class);
                intent.putExtra("asansorserino", BakimPojoPojoList.get(i).getAsansorserino());
                intent.putExtra("donemtarihi", BakimPojoPojoList.get(i).getDonemtarihi());
                intent.putExtra("yetkili", BakimPojoPojoList.get(i).getYetkili());
                intent.putExtra("tel", BakimPojoPojoList.get(i).getTel());
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                intent.putExtra("bakimbasla", date);

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


        Call<List<BakimPojo>> request = ManagerAll.getInstance().BakimDonemTarihiSec(asansorserino);
        request.enqueue(new Callback<List<BakimPojo>>() {
            @Override
            public void onResponse(Call<List<BakimPojo>> call, Response<List<BakimPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        BakimPojoPojoList=response.body();

                        bakimDonemTarihiSecAdapter= new BakimDonemTarihiSecAdapter(BakimPojoPojoList,getApplicationContext());

                        listView.setAdapter(bakimDonemTarihiSecAdapter);
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
            public void onFailure(Call<List<BakimPojo>> call, Throwable t) {

            }
        });

    }
}
