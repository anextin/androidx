package com.example.ext.asansor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ext.asansor.Models.BekleyenArizalarPojo;
import com.example.ext.asansor.Models.YapilacakBakimlarPojo;
import com.example.ext.asansor.R;

import java.util.List;

/**
 * Created by Kafein on 4/29/2019.
 */

public class BekleyenArizalarAdapter extends BaseAdapter {
    List<BekleyenArizalarPojo> bekleyenArizalarPojoList;
    Context context;

    public BekleyenArizalarAdapter(List<BekleyenArizalarPojo> bekleyenArizalarPojoList, Context context) {
        this.bekleyenArizalarPojoList = bekleyenArizalarPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bekleyenArizalarPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return bekleyenArizalarPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        converView= LayoutInflater.from(context).inflate(R.layout.bekleyen_arizalar_layout,parent,false);

        TextView BekleyenArizalarbaslik, BekleyenArizalarkonu, BekleyenArizalaryetkili,BekleyenArizalartel,BekleyenArizalardonemtarihi;
        ImageView BekleyenArizalarResim;
        BekleyenArizalarResim=converView.findViewById(R.id.YapilacakBakimlarResim);
        BekleyenArizalarbaslik=converView.findViewById(R.id.BekleyenArizalarbaslik);
        BekleyenArizalarkonu=converView.findViewById(R.id.BekleyenArizalarkonu);
        BekleyenArizalaryetkili=converView.findViewById(R.id.BekleyenArizalaryetkili);
        BekleyenArizalartel=converView.findViewById(R.id.BekleyenArizalartel);
        BekleyenArizalardonemtarihi=converView.findViewById(R.id.BekleyenArizalardonemtarihi);



        BekleyenArizalarbaslik.setText(bekleyenArizalarPojoList.get(position).getBaslik());
        BekleyenArizalarkonu.setText(bekleyenArizalarPojoList.get(position).getArizakonusu());
        BekleyenArizalaryetkili.setText(bekleyenArizalarPojoList.get(position).getYetkili());
        BekleyenArizalartel.setText(bekleyenArizalarPojoList.get(position).getTel());
        BekleyenArizalardonemtarihi.setText(bekleyenArizalarPojoList.get(position).getDonemtarihi());



        return converView;

    }
}

