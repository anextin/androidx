package com.example.ext.fragment3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment2 extends Fragment {

    View view;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_fragment2, container, false);
            tanimla();
        return view;
    }


    public void tanimla()
    {
        button=view.findViewById(R.id.buton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });
    }


    public void action()
    {
        ChangeFragment changeFragment= new ChangeFragment(getContext());
        changeFragment.change(new Fragment3());
    }



}
