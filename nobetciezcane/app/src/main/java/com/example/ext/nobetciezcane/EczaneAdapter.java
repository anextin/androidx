package com.example.ext.nobetciezcane;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.ext.nobetciezcane.Models.Eczane;
import com.example.ext.nobetciezcane.Models.EczaneDetay;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static androidx.core.content.ContextCompat.getSystemService;

public class EczaneAdapter extends BaseAdapter {

    List<EczaneDetay> list;
    Context context;
    Activity activity;

    public EczaneAdapter(List<EczaneDetay> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        TextView eczaneIsim, eczaneAdres, eczaneTel, eczaneFax, eczaneAdresTarifi;
        final Button haritadaGoster, aramaYap;
        eczaneIsim = convertView.findViewById(R.id.eczaneIsim);
        eczaneAdres = convertView.findViewById(R.id.eczaneAdres);
        eczaneTel = convertView.findViewById(R.id.eczaneTelefon);
        eczaneFax = convertView.findViewById(R.id.eczaneFax);
        eczaneAdresTarifi = convertView.findViewById(R.id.eczaneAdresTarifi);
        haritadaGoster = convertView.findViewById(R.id.eczaneHaritadaGoster);
        aramaYap = convertView.findViewById(R.id.aramaYap);

        eczaneIsim.setText(list.get(position).getEczaneIsmi());
        eczaneAdres.setText(list.get(position).getAdres());
        eczaneTel.setText(list.get(position).getTelefon());
        eczaneFax.setText(list.get(position).getFax());
        eczaneAdresTarifi.setText(list.get(position).getTarif());

        aramaYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setData(Uri.parse("tel:" + list.get(position).getTelefon()));
                activity.startActivity(intent);
            }
        });

        haritadaGoster.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                String adres = list.get(position).getAdres().toString();
                System.out.println("haygursel: " + list.get(position).getAdres().toString());

                MainActivity mainActivity = new MainActivity();
                Double latitude=mainActivity.latitude;
                Double longitude=mainActivity.longitude;
                Location location;

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + adres));
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
