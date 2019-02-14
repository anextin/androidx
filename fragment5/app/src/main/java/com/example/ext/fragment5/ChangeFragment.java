package com.example.ext.fragment5;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ChangeFragment {


    private Context context;

    public ChangeFragment(Context context) {
        this.context = context;
    }

    public void change(Fragment fragment)   //1. fragmenti gosteriyor
    {


        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()

                .replace(R.id.frameLayout,fragment,"fragment" )
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    public void change2(Fragment fragment)//2. fragmenti gosteriyor
    {



        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()

                .replace(R.id.frameLayout2,fragment,"fragment" )
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void verigonder(Fragment fragment, String bilgi)
    {

        Bundle bundle = new Bundle();
        bundle.putString("bilgi",bilgi);
        fragment.setArguments(bundle);


        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()

                .replace(R.id.frameLayout2,fragment,"fragment" )
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


}
