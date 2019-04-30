package com.example.ext.asansor.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ext.asansor.Models.TahsilatYapSorgulaPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;

import java.util.List;

/**
 * Created by Kafein on 4/26/2019.
 */

public class YapilacakBakimlarAdapter extends BaseAdapter {
    List<YapilacakBakimlarPojo> yapilacakBakimlarPojoList;
    Context context;

    public YapilacakBakimlarAdapter(List<YapilacakBakimlarPojo> yapilacakBakimlarPojoList, Context context) {
        this.yapilacakBakimlarPojoList = yapilacakBakimlarPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return yapilacakBakimlarPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return yapilacakBakimlarPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        converView= LayoutInflater.from(context).inflate(R.layout.yapilacak_bakimlar_layout,parent,false);

        TextView YapilacakBakimlarbaslik, YapilacakBakimlarbinaadi, YapilacakBakimlaryetkili,YapilacakBakimlartel,YapilacakBakimlardonemtarihi;
        ImageView YapilacakBakimlarResim;
        YapilacakBakimlarResim=converView.findViewById(R.id.YapilacakBakimlarResim);
        YapilacakBakimlarbaslik=converView.findViewById(R.id.YapilacakBakimlarbaslik);
        YapilacakBakimlarbinaadi=converView.findViewById(R.id.YapilacakBakimlarbinaadi);
        YapilacakBakimlaryetkili=converView.findViewById(R.id.YapilacakBakimlaryetkili);
        YapilacakBakimlartel=converView.findViewById(R.id.YapilacakBakimlartel);
        YapilacakBakimlardonemtarihi=converView.findViewById(R.id.YapilacakBakimlardonemtarihi);



        YapilacakBakimlarbaslik.setText(yapilacakBakimlarPojoList.get(position).getBaslik());
        YapilacakBakimlarbinaadi.setText(yapilacakBakimlarPojoList.get(position).getBinaadi());
        YapilacakBakimlaryetkili.setText("Yetkili: "+yapilacakBakimlarPojoList.get(position).getYetkili());
        YapilacakBakimlartel.setText("Tel: "+yapilacakBakimlarPojoList.get(position).getTel());
        YapilacakBakimlardonemtarihi.setText("Dönem Tarihi: "+yapilacakBakimlarPojoList.get(position).getDonemtarihi());



        return converView;

    }
}
