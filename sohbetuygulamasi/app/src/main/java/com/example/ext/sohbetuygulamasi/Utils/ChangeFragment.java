package com.example.ext.sohbetuygulamasi.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.ext.sohbetuygulamasi.R;

public class ChangeFragment {


    private Context context;

    public ChangeFragment(Context context) {
        this.context = context;
    }

    public void change(Fragment fragment)
    {

        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()

                .replace(R.id.fragmentlayout,fragment,"fragment" )
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }



}
