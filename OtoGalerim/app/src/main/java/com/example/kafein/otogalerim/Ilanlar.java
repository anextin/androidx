package com.example.kafein.otogalerim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kafein.otogalerim.Adapter.IlanlarAdapter;
import com.example.kafein.otogalerim.Adapter.IlanlarimAdapter;
import com.example.kafein.otogalerim.Models.IlanlarPojo;
import com.example.kafein.otogalerim.Models.IlanlarimPojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ilanlar extends AppCompatActivity {

    ListView listView;
    List<IlanlarPojo> ilanlarPojoList;
    IlanlarAdapter ilanlarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlar);
        listView=findViewById(R.id.ilanlarListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Ilanlar.this,IlanDetay.class);
                intent.putExtra("ilanid",ilanlarPojoList.get(i).getIlanid());

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


        Call<List<IlanlarPojo>> request = ManagerAll.getInstance().ilanlar();
        request.enqueue(new Callback<List<IlanlarPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarPojo>> call, Response<List<IlanlarPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTf())
                    {
                        ilanlarPojoList=response.body();
                        ilanlarAdapter= new IlanlarAdapter(ilanlarPojoList,getApplicationContext());
                        listView.setAdapter(ilanlarAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilan listelenmistir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarPojo>> call, Throwable t) {

            }
        });

    }
}
