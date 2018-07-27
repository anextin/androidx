package com.example.kafein.otogalerim.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.kafein.otogalerim.IlanDetay;
import com.example.kafein.otogalerim.Ilanlar;
import com.example.kafein.otogalerim.Models.FavoriSliderPojo;
import com.example.kafein.otogalerim.Models.SliderPojo;
import com.example.kafein.otogalerim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kafein on 7/25/2018.
 */

public class FavoriSliderAdapter extends PagerAdapter{


    List<FavoriSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriSliderAdapter(List<FavoriSliderPojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity=activity;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliderlayout, container,false);

        ImageView imageView= view.findViewById(R.id.sliderImageView);
        Picasso.with(context).load("http://anextin.xyz/"+list.get(position).getResimyolu()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity,IlanDetay.class);
                intent.putExtra("ilanid",list.get(position).getIlanid().toString());

                activity.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }
}
