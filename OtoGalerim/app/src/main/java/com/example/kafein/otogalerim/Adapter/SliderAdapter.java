package com.example.kafein.otogalerim.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.kafein.otogalerim.Models.SliderPojo;
import com.example.kafein.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kafein on 7/25/2018.
 */

public class SliderAdapter extends PagerAdapter{


    List<SliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliderlayout, container,false);
        ImageView imageView= view.findViewById(R.id.sliderImageView);
        Picasso.with(context).load("http://anextin.xyz/"+list.get(position).getResim()).into(imageView);

        container.addView(view);

        return view;
    }
}
