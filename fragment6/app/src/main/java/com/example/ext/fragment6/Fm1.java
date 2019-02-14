package com.example.ext.fragment6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fm1 extends Fragment {

    EditText edittext;
    Button button;
    View view;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fm1, container, false);
        edittext= view.findViewById(R.id.edittext);
        button=view.findViewById(R.id.buton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MainActivity.class);
                intent.putExtra("isim",edittext.getText().toString());
                getActivity().startActivity(intent);
            }
        });

        return view;
    }


}
