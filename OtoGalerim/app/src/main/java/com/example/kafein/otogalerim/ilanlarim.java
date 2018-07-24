package com.example.kafein.otogalerim;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kafein.otogalerim.Adapter.IlanlarimAdapter;
import com.example.kafein.otogalerim.Models.IlanlarimPojo;
import com.example.kafein.otogalerim.Models.IlanlarimSilPojo;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                ilanlarimAlertDialog(ilanlarim.this,ilanlarimPojos.get(position).getIlanid());
            }
        });
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

                    if(response.body().get(0).isTf()) {

                                ilanlarimAdapter = new IlanlarimAdapter(ilanlarimPojos, getApplicationContext(), ilanlarim.this);
                        listView.setAdapter(ilanlarimAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getSayi() + " tane ilaniniz bulunmaktadir", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(), "ilaniniz bulunmamaktadir", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ilanlarim.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_inn, R.anim.anim_out);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanlarimPojo>> call, Throwable t) {

            }
        });

    }
    public  void ilanlarimAlertDialog(Activity activity, final String uye_id)  //silinicek olanın ilan_id sini almamız lazım
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view= inflater.inflate(R.layout.alertlayout,null);



        Button button= view.findViewById(R.id.buton);
        Button buttonCık= view.findViewById(R.id.buton2);

        AlertDialog.Builder alert= new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog= alert.create();

        buttonCık.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sil(uye_id);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void sil (String ilanId)
    {
        Call<IlanlarimSilPojo> request = ManagerAll.getInstance().ilanlarimSil(ilanId);
        request.enqueue(new Callback<IlanlarimSilPojo>() {
            @Override
            public void onResponse(Call<IlanlarimSilPojo> call, Response<IlanlarimSilPojo> response) {
                if(response.body().isTf())
                {
                    ilanlarimigoruntule();
                }
            }

            @Override
            public void onFailure(Call<IlanlarimSilPojo> call, Throwable t) {

            }
        });
    }
}
