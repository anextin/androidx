package com.example.ext.fragment5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fm2 extends Fragment {

    View view;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_fm2, container, false);

        if(getArguments()!=null)
        {
            String veri= getArguments().getString("bilgi").toString();
            textView= view.findViewById(R.id.textView);
            textView.setText(veri);
        }
        // Inflate the layout for this fragment


        return view;
    }


}
