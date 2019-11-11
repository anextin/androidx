package com.dev.ext.asansor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.dev.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.dev.ext.asansor.R;

import java.util.List;

/**
 * Created by Kafein on 4/24/2019.
 */

public class TahsilatYapSorgulaAdapter extends BaseAdapter {


    List<TahsilatYapSorgulaPojo> tahsilatYapPojoList;
    Context context;

    public TahsilatYapSorgulaAdapter(List<TahsilatYapSorgulaPojo> tahsilatYapPojoList, Context context) {
        this.tahsilatYapPojoList = tahsilatYapPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tahsilatYapPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return tahsilatYapPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        converView= LayoutInflater.from(context).inflate(R.layout.tahsilat_yap_layout,parent,false);

        TextView TahsilatYapBinaAdi, TahsilatYapAsansorAdi, TahsilatYapYoneticiAdi;
        ImageView TahsilatYapResim;
        TahsilatYapResim=converView.findViewById(R.id.TahsilatYapResim);
        TahsilatYapBinaAdi=converView.findViewById(R.id.TahsilatYapBinaAdi);
        TahsilatYapAsansorAdi=converView.findViewById(R.id.TahsilatYapAsansorAdi);
        TahsilatYapYoneticiAdi=converView.findViewById(R.id.TahsilatYapYoneticiAdi);

        TahsilatYapBinaAdi.setText(tahsilatYapPojoList.get(position).getBinaadi());
        TahsilatYapAsansorAdi.setText(tahsilatYapPojoList.get(position).getAsansoradi());
        TahsilatYapYoneticiAdi.setText("Yetkili: "+tahsilatYapPojoList.get(position).getYoneticiadi());

        return converView;

    }
}
