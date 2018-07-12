package com.example.kafein.otogalerim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kafein.otogalerim.Models.ResimEklePojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ilanResimler extends AppCompatActivity {

    Button resimEkleButon,resimSecButon,ilanYayinlaButon;
    ImageView secilenIlanResmiImageView;
    Bitmap bitmap;
    String uye_id, ilan_id, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_resimler);
        tanimla();
        Bundle bundle = getIntent().getExtras(); //bundle olusturduk aktivite arası verileri almak ıcın
        uye_id= String.valueOf(bundle.getInt("uye_id"));
        ilan_id=bundle.getString("ilan_id");
    }


    public void tanimla()
    {
        resimSecButon=findViewById(R.id.resimSecButon);
        resimEkleButon=findViewById(R.id.resimEkleButon);
        ilanYayinlaButon=findViewById(R.id.ilanYayinlaButon);
        secilenIlanResmiImageView=findViewById(R.id.secilenIlanResmiImageView);

        resimSecButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resimGoster();
            }
        });
    }

    public void resimGoster()
    {   //galeriyi acar
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);
    }

    public void yukle()
    {
        String image = imageToString();
        Call<ResimEklePojo> request= ManagerAll.getInstance().resimEkle(uye_id,ilan_id,image);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)   //secilen resmi bitmape cevirir
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==777 && resultCode== RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenIlanResmiImageView.setImageBitmap(bitmap);
                secilenIlanResmiImageView.setVisibility(View.VISIBLE);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byt= byteArrayOutputStream.toByteArray();
        String imageToString= Base64.encodeToString(byt,Base64.DEFAULT);
        return imageToString;
    }
}



