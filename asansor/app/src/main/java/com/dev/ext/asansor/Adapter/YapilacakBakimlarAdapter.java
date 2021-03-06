package com.dev.ext.asansor.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.ext.asansor.Models.BakimPojo;
import com.dev.ext.asansor.R;

import java.util.List;

/**
 * Created by Kafein on 4/26/2019.
 */

public class YapilacakBakimlarAdapter extends BaseAdapter {
    List<BakimPojo> BakimPojoPojoList;
    Context context;

    public YapilacakBakimlarAdapter(List<BakimPojo> BakimPojoPojoList, Context context) {
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
        converView= LayoutInflater.from(context).inflate(R.layout.yapilacak_bakimlar_layout,parent,false);

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


        @Override
        public boolean isEnabled(int position) {
            Log.i("hop123","hop123: "+BakimPojoPojoList.get(position).getBakimdurum());
        if(BakimPojoPojoList.get(position).getBakimdurum().equals("0"))
        {
            return true;
        }
        else {
            return false;
        }
    }

}
