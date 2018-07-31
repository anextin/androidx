package com.example.kafein.otogalerim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kafein.otogalerim.Adapter.FavoriSliderAdapter;
import com.example.kafein.otogalerim.Models.FavoriSliderPojo;
import com.example.kafein.otogalerim.RestApi.ManagerAll;

import java.awt.font.TextAttribute;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        SharedPreferences sharedPreferences;
        String navHeaderText;
        TextView navHeaderTextView;
        SharedPreferences.Editor editor;
        Button ilanVerButon ,ilanlarimMenuButon,ilanButon;
        ViewPager mainActivitySliderFavori;
        CircleIndicator mainActivitySliderCircle;
        String uye_id;
        FavoriSliderAdapter favoriSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        navHeaderText=sharedPreferences.getString("uye_kullaniciAdi",null);
        uye_id=sharedPreferences.getString("uye_id",null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);
        navHeaderTextView=headerView.findViewById(R.id.navHeaderText);
        navHeaderTextView.setText(navHeaderText);
        tanimla();
        getSlider();
    }

    public void tanimla()
    {

        mainActivitySliderFavori=(ViewPager)findViewById(R.id.mainActivitySliderFavori);
        mainActivitySliderCircle=findViewById(R.id.mainActivitySliderCircle);

        ilanVerButon=findViewById(R.id.ilanVerButon);
        ilanVerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,ilanBilgileri.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn,R.anim.anim_out);
            }
        });

        ilanlarimMenuButon=findViewById(R.id.ilanlarimMenuButon);
        ilanlarimMenuButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,ilanlarim.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn,R.anim.anim_out);
            }
        });
        ilanButon=findViewById(R.id.ilanButon);
        ilanButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Ilanlar.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_inn,R.anim.anim_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.cikisYap) {

            editor=sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent= new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getSlider()
    {
        Call<List<FavoriSliderPojo>> request= ManagerAll.getInstance().setSlider(uye_id);
        Log.i("ardaaa", uye_id);

        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {

                if(response.body().get(0).isTf())
                {
                    if(response.body().size()>0) {

                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);

                        mainActivitySliderFavori.setAdapter(favoriSliderAdapter);

                        mainActivitySliderCircle.setViewPager(mainActivitySliderFavori);
                        mainActivitySliderCircle.bringToFront();
                    }
                }
                else
                {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(), MainActivity.this, MainActivity.this);

                    mainActivitySliderFavori.setAdapter(favoriSliderAdapter);

                    mainActivitySliderCircle.setViewPager(mainActivitySliderFavori);
                    mainActivitySliderCircle.bringToFront();
                }

            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }
    //bir ilanı favoriye aldıgımızda ana ekrana gelssın yada cıkardıgımızda cıksın dıe
    protected  void onRestart()
    {
        super.onRestart();
        getSlider();
    }
}
