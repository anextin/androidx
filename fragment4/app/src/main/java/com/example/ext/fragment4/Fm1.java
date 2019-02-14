package com.example.ext.fragment4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fm1 extends Fragment {

    View view;
    TextView textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fm1, container, false);


        textview = view.findViewById(R.id.textview);

        if(getArguments()!=null) {
            String isimDeger = getArguments().getString("isim").toString();
            textview.setText(isimDeger);
        }
        return view;


    }

}
