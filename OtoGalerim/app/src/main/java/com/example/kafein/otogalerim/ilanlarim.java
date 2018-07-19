package com.example.kafein.otogalerim;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kafein.otogalerim.Adapter.IlanlarimAdapter;
import com.example.kafein.otogalerim.Models.IlanlarimPojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ilanlarim extends AppCompatActivity {

    ListView listView;
    IlanlarimAdapter ilanlarimAdapter;
    List<IlanlarimPojo> ilanlarimPojos;
    SharedPreferences sharedPreferences;
    String uye_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);

        listView = findViewById(R.id.ilanlarimListView);

        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        uye_id =sharedPreferences.getString("uye_id",null);
        ilanlarimigoruntule();
    }


    public void ilanlarimigoruntule()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ilanlarim");
        progressDialog.setMessage("ilanlar yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        ilanlarimPojos= new ArrayList<>();
        Call<List<IlanlarimPojo>> request= ManagerAll.getInstance().ilanlarim(uye_id);
        request.enqueue(new Callback<List<IlanlarimPojo>>() {
            @Override
            public void onResponse(Call<List<IlanlarimPojo>> call, Response<List<IlanlarimPojo>> response) {
                if(response.isSuccessful())
                {
                    ilanlarimPojos = response.body();
                    ilanlarimAdapter = new IlanlarimAdapter(ilanlarimPojos, getApplicationContext(),ilanlarim.this);
                    listView.setAdapter(ilanlarimAdapter);
                    Toast.makeText(getApplicationContext(),response.body().get(0).getSayi()+" tane ilaniniz bulunmaktadir",Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });

    }
}
