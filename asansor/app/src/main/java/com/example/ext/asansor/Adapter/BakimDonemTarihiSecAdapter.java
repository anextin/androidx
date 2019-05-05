package com.example.ext.asansor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ext.asansor.Models.BakimPojo;
import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;

import java.util.List;

public class BakimDonemTarihiSecAdapter extends BaseAdapter {
    List<BakimPojo> BakimPojoPojoList;
    Context context;

    public BakimDonemTarihiSecAdapter(List<BakimPojo> BakimPojoPojoList, Context context) {
        this.BakimPojoPojoList = BakimPojoPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return BakimPojoPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return BakimPojoPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        converView= LayoutInflater.from(context).inflate(R.layout.bakim_donem_tarihi_sec_layout,parent,false);

        TextView YapilacakBakimlarbaslik, YapilacakBakimlarbinaadi, YapilacakBakimlaryetkili,YapilacakBakimlartel,YapilacakBakimlardonemtarihi;
        ImageView YapilacakBakimlarResim;
        YapilacakBakimlarResim=converView.findViewById(R.id.YapilacakBakimlarResim);
        YapilacakBakimlarbaslik=converView.findViewById(R.id.YapilacakBakimlarbaslik);
        YapilacakBakimlarbinaadi=converView.findViewById(R.id.YapilacakBakimlarbinaadi);
        YapilacakBakimlaryetkili=converView.findViewById(R.id.YapilacakBakimlaryetkili);
        YapilacakBakimlartel=converView.findViewById(R.id.YapilacakBakimlartel);
        YapilacakBakimlardonemtarihi=converView.findViewById(R.id.YapilacakBakimlardonemtarihi);



        YapilacakBakimlarbaslik.setText(BakimPojoPojoList.get(position).getBaslik());
        YapilacakBakimlarbinaadi.setText(BakimPojoPojoList.get(position).getBinaadi());
        YapilacakBakimlaryetkili.setText("Yetkili: "+BakimPojoPojoList.get(position).getYetkili());
        YapilacakBakimlartel.setText("Tel: "+BakimPojoPojoList.get(position).getTel());
        YapilacakBakimlardonemtarihi.setText("Dönem Tarihi: "+BakimPojoPojoList.get(position).getDonemtarihi());



        return converView;

    }
}