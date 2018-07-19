package com.example.kafein.otogalerim.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kafein.otogalerim.AlertDialogClass;
import com.example.kafein.otogalerim.Models.IlanlarimPojo;
import com.example.kafein.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kafein on 7/19/2018.
 */

public class IlanlarimAdapter extends BaseAdapter{

    List<IlanlarimPojo> list;
    Context context;
    Activity activity;
    String uye_id, ilan_id;
    AlertDialogClass alertDialogClass;


    public IlanlarimAdapter(List<IlanlarimPojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.ilanlarimlayout,parent,false);
        ImageView resim;
        TextView baslik, fiyat;
        LinearLayout linearLayout;

        linearLayout=convertView.findViewById(R.id.ilanLinearLayout);
        resim = convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik = convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarimIlanFiyat);
        ilan_id=list.get(position).getIlanid();
        uye_id=list.get(position).getUyeid();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogClass = new AlertDialogClass();
                alertDialogClass.ilanlarimAlertDialog(activity,ilan_id);
            }
        });

        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());
        Picasso.with(context).load("http://anextin.xyz/"+list.get(position).getResim()).into(resim);
        return convertView;
    }
}
