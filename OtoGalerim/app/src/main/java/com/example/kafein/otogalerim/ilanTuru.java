package com.example.kafein.otogalerim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kafein.otogalerim.Models.ilanVerPojo;

import java.util.ArrayList;
import java.util.List;

public class ilanTuru extends AppCompatActivity {

    Spinner ilanturuSpinner, ilanKimdenSpinner;
    Button ilanTuruButon, ilanTuruButonGeri;
    List<String> turList;
    List<String> sahipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_turu);
        listeDoldur();
        tanimla();
    }

    public void tanimla() {
        ilanturuSpinner = findViewById(R.id.ilanturuSpinner);
        ilanKimdenSpinner = findViewById(R.id.ilanKimdenSpinner);

        ArrayAdapter<String> turListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,turList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanturuSpinner.setAdapter(turListAdapter);

        ArrayAdapter<String> sahipListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sahipList);
        sahipListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilanKimdenSpinner.setAdapter(sahipListAdapter);



        ilanTuruButon = findViewById(R.id.ilanTuruButon);
        ilanTuruButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ilanVerPojo.setKimden(ilanKimdenSpinner.getSelectedItem().toString());
                ilanVerPojo.setIlantipi(ilanturuSpinner.getSelectedItem().toString());
                Intent intent = new Intent(ilanTuru.this, AdresBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn, R.anim.anim_out);
                finish();
            }
        });

        ilanTuruButonGeri = findViewById(R.id.ilanTuruButonGeri);
        ilanTuruButonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ilanTuru.this, ilanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn_back, R.anim.anim_out_back);
                finish();
            }
        });
    }

    public void listeDoldur()
    {
        turList=new ArrayList<>();
        sahipList=new ArrayList<>();
        turList.add("Satılık");
        turList.add("Kiralık");
        sahipList.add("galeriden");
        sahipList.add("sahibinden");
    }
}


