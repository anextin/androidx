package com.example.ext.sohbetuygulamasi.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.R;


public class UserProfileFragment extends Fragment {

    TextView denemeText;
    View view;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        tanimla();
        action();
        return view;
    }


    public void tanimla()
    {
        denemeText=view.findViewById(R.id.denemeText);
        userId =getArguments().getString("userid");
    }


    public void action()
    {
        denemeText.setText(userId);
    }

}
