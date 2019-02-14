package com.example.ext.fragment5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fm1 extends Fragment {

    EditText editText;
    Button button;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fm1, container, false);

        editText=view.findViewById(R.id.edittext);
        button=view.findViewById(R.id.buton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangeFragment changeFragment= new ChangeFragment(getContext());
                changeFragment.verigonder(new Fm2(),editText.getText().toString());

            }
        });
        return view;

    }


}
