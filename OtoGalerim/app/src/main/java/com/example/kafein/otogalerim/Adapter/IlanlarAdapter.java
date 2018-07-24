package com.example.kafein.otogalerim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kafein.otogalerim.Models.IlanlarPojo;
import com.example.kafein.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kafein on 7/23/2018.
 */

public class IlanlarAdapter extends BaseAdapter{
    List<IlanlarPojo> ilanlarPojoList;
    Context context;

    public IlanlarAdapter(List<IlanlarPojo> ilanlarPojoList, Context context) {
        this.ilanlarPojoList = ilanlarPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ilanlarPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return ilanlarPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        converView= LayoutInflater.from(context).inflate(R.layout.ilanlarlayout,parent,false);

        TextView baslik, fiyat, adres;
        ImageView resim;
        baslik=converView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat=converView.findViewById(R.id.ilanlarIlanFiyat);
        adres=converView.findViewById(R.id.ilanlarIlanAdres);
        resim=converView.findViewById(R.id.ilanlarIlanResim);

        baslik.setText(ilanlarPojoList.get(position).getBaslik());
        fiyat.setText(ilanlarPojoList.get(position).getFiyat());
        adres.setText(ilanlarPojoList.get(position).getIl()+" "+ilanlarPojoList.get(position).getIlce()+" "+ilanlarPojoList.get(position).getMahalle());
        Picasso.with(context).load("http://anextin.xyz/"+ilanlarPojoList.get(position).getResim()).resize(100,100).into(resim);
        return converView;

    }
}
