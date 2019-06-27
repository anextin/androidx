package com.example.ext.nobetciezcane;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ext.nobetciezcane.Models.Eczane;
import com.example.ext.nobetciezcane.Models.EczaneDetay;

import java.util.List;

public class EczaneAdapter extends BaseAdapter{

    List<EczaneDetay> list;
    Context context;

    public EczaneAdapter(List<EczaneDetay> list, Context context) {
        this.list = list;
        this.context = context;
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
        return convertView;
    }
}
