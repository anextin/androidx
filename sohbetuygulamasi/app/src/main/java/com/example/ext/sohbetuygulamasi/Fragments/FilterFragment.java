package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class FilterFragment extends Fragment {


    Spinner ilcespinner,irkspinner,cinsiyetspinner;
    Button filtreleButton;
    View view;
    public String spinner_ilce,spinner_irk,spinner_cinsiyet;
    public int spinner_ilceNum,spinner_irkNum,spinner_cinsiyetNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_filter, container, false);
        tanimla();
        return view;
    }


    public void tanimla()
    {

        ilcespinner =view.findViewById(R.id.filterilceSpinner);
        irkspinner =view.findViewById(R.id.filterirkSpinner);
        cinsiyetspinner =view.findViewById(R.id.filtercinsiyetSpinner);
        filtreleButton=view.findViewById(R.id.filtreleButton);

        final String ilceler[]={"Tümü","Adalar","Arnavutköy","Ataşehir","Avcılar","Bağcılar","Bahçelievler","Bakırköy","Başaksehir","Bayrampaşa","Beşiktaş","Beykoz","Beylikdüzü","Beyoğlu","Büyükçekmece","Çatalca","Çekmeköy","Esenler","Esenyurt","Eyüp","Fatih","Gaziosmanpaşa","Güngören","Kadıköy","Kağıthane","Kartal","Küçükçekmece","Maltepe","Pendik","Sancaktepe","Sarıyer","Şile","Silivri","Şişli","Sultanbeyli","Sultangazi","Tuzla","Ümraniye","Üsküdar","Zeytinburnu"};

        final String irk[]={"Tümü","golden","pug","doberman","kurt"};

        final String cinsiyet[]={"Tümü","Erkek","Dişi"};



        ArrayAdapter<String> ilcelerdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ilceler);
        ilcelerdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilcespinner.setAdapter(ilcelerdataAdapter);

        ArrayAdapter<String> irkdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, irk);
        irkdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        irkspinner.setAdapter(irkdataAdapter);

        ArrayAdapter<String> cinsiyetdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, cinsiyet);
        cinsiyetdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinsiyetspinner.setAdapter(cinsiyetdataAdapter);


        filtreleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();
            }
        });

        String stringToSave = "Save me!";

        // To save data to SP
    //    SharedPreferences.Editor editor = getContext().getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE).edit();
      //  editor.putString(USER_NAME_STRING, stringToSave);
      //  editor.apply();

        // To load the data at a later time
     //   SharedPreferences prefs = getContext().getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE);
     //   String loadedString = prefs.getString(USER_NAME_STRING, null);
    }

    public void guncelle()
    {

        //     String hakkimda=input_hakkimda.getText().toString();
         spinner_ilce=ilcespinner.getSelectedItem().toString();
        spinner_ilceNum= (int) ilcespinner.getSelectedItemId();

        spinner_irk=irkspinner.getSelectedItem().toString();
        spinner_irkNum= (int) irkspinner.getSelectedItemId();

        spinner_cinsiyet=cinsiyetspinner.getSelectedItem().toString();
        spinner_cinsiyetNum= (int) cinsiyetspinner.getSelectedItemId();

        System.out.println("amasyaa: "+"..."+spinner_ilce+"....||||...."+spinner_irk+"....||||...."+spinner_cinsiyet);


        String stringToSave = "Save me!";
        // To save data to SP
   //        SharedPreferences.Editor editor = getContext().getSharedPreferences(spinner_ilce, MODE_PRIVATE).edit();
    //      editor.putString(spinner_ilce, stringToSave);
    //      editor.apply();


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("giris",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spinner_ilce",spinner_ilce);
        editor.putString("spinner_irk",spinner_irk);
        editor.putString("spinner_cinsiyet",spinner_cinsiyet);
        editor.putInt("spinner_ilceNum",spinner_ilceNum);
        editor.putInt("spinner_irkNum",spinner_irkNum);
        editor.putInt("spinner_cinsiyetNum",spinner_cinsiyetNum);
        System.out.println("amasyaa3: "+"..."+spinner_ilce+"..."+spinner_irk+"..."+spinner_cinsiyet+"..."+spinner_ilceNum+"..."+spinner_irkNum+"..."+spinner_cinsiyetNum);
        editor.apply();

//        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
  //      uye_id=sharedPreferences.getString("uye_id",null);

        // To load the data at a later time
        //   SharedPreferences prefs = getContext().getSharedPreferences(spinner_ilce, MODE_PRIVATE);
        //   String loadedString = prefs.getString(spinner_ilce, null);
    }



}