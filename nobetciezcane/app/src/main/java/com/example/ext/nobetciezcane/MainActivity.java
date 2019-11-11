package com.example.ext.nobetciezcane;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ext.nobetciezcane.Models.Eczane;
import com.example.ext.nobetciezcane.Models.EczaneDetay;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String tokenText="";
    WebView webView;
    Spinner spinner;
    Document document;
    List<EczaneDetay> eczaneList;
    EczaneAdapter eczaneAdapter;
    ListView listView;
    Button listeleButton;
    public  static   Double longitude;
    public  static  Double latitude;
    LinearLayout listLayout;

  //  MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        listLayout=findViewById(R.id.listLayout);
        webView= new WebView(getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);





        //anime
 //       LinearLayout your_Layout = (LinearLayout) findViewById(R.id.deneme);
 //       AnimationDrawable animationDrawable = (AnimationDrawable) your_Layout.getBackground();
 //       animationDrawable.setEnterFadeDuration(4000);
  //      animationDrawable.setExitFadeDuration(4000);
  //      animationDrawable.start();

        //anime
      //  getCurrentLocation();
       // getPermission();


        webView.addJavascriptInterface(new JsBridge(),"Android");
        this.getToken();
        final String ilceler[]={"Adalar","Arnavutköy","Ataşehir","Avcılar","Bağcılar","Bahçelievler","Bakırköy","Başaksehir","Bayrampaşa","Beşiktaş","Beykoz","Beylikdüzü","Beyoğlu","Büyükçekmece","Çatalca","Çekmeköy","Esenler","Esenyurt","Eyüp","Fatih","Gaziosmanpaşa","Güngören","Kadıköy","Kağıthane","Kartal","Küçükçekmece","Maltepe","Pendik","Sancaktepe","Sarıyer","Şile","Silivri","Şişli","Sultanbeyli","Sultangazi","Tuzla","Ümraniye","Üsküdar","Zeytinburnu"};
        final int ilceid[]=new int[]{1,33,34,2,3,4,5,35,6,7,8,36,9,10,11,37,13,38,14,15,16,17,18,19,20,21,22,23,39,24,27,25,28,26,40,29,30,31,32};

        spinner =findViewById(R.id.ilceSpinner);
        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ilceler);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        listeleButton=findViewById(R.id.listeleButton);
        listeleButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                listLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                final  ProgressDialog progressDialog = new android.app.ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Nöbetçi Eczaneler");
                progressDialog.setMessage("Yükleniyor ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        listeleButton.setEnabled(false);
        //                listeleButton.setText("count down " + millisUntilFinished);
                        System.out.println("tick: " + "tick");
                    }

                    @Override
                    public void onFinish() {

                        progressDialog.cancel();
                                listeleButton.setEnabled(true);
                                System.out.println("tick: " + "click");


                    }
                };
                timer.start();


                String item = spinner.getSelectedItem().toString();
                int index= Integer.parseInt(String.valueOf(java.util.Arrays.asList(ilceler).indexOf(item)));
                int id = ilceid[index];
                Log.i("gelenid",""+id);
                getEczane(String.valueOf(id));


            }
        });





    }

    public void getEczane(String id)
    {


        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view,String url)
            {
                super.onPageFinished(view,url);
                view.loadUrl("javascript:window.Android.htmlEczaneDetay(" +
                        "'<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }

        });
        webView.loadUrl("https://apps.istanbulsaglik.gov.tr/Eczane/nobetci?id="+id+"&token="+tokenText);
    }

    public  void getToken()
    {
        webView.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view,String url)
            {
                super.onPageFinished(view,url);
                view.loadUrl("javascript:window.Android.htmlContentForToken(" +
                        "'<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }

        }
        );

        webView.loadUrl("https://apps.istanbulsaglik.gov.tr/Eczane");

    }

    private Handler handler= new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {


            super.handleMessage(msg);
            if(msg.what==1)
            {
                tokenText= (String) msg.obj;
            }
            else if(msg.what==2)
            {


               Eczane ec = parseHtml((String) msg.obj);
                eczaneList=ec.getEczaneDetay();
                eczaneAdapter=new EczaneAdapter(eczaneList, MainActivity.this, MainActivity.this);
                listView.setAdapter(eczaneAdapter);


            }

        }
    };

    private Eczane parseHtml(String htmlKaynak)
    {

        document=Jsoup.parse(htmlKaynak);
        Elements table = document.select("table.ilce-nobet-detay");
        Elements ilceDetay= table.select("caption>b");
        Eczane eczane= new Eczane();
        eczane.setTarih(ilceDetay.get(0).text());
        eczane.setIlceIsmi(ilceDetay.get(1).text());
        Log.i("qevap",""+ilceDetay.get(1).text());


       Elements eczaneDetayElement= document.select("table.nobecti-eczane");
    //    Log.i("tanzileee",""+eczaneDetayElement.size());

        List<EczaneDetay> eczaneDetayList = new ArrayList<>();
        for (Element el : eczaneDetayElement)
        {

            EczaneDetay eczaneDetay=getEczaneDetay(el);
            Log.i("utkuu",eczaneDetay.toString());
            if(eczaneDetay!=null)
            {
                eczaneDetayList.add(eczaneDetay);
            }
        }

        eczane.setEczaneDetay(eczaneDetayList);
        Log.i("gizemm",eczane.toString());

        return eczane;
    }

    public EczaneDetay getEczaneDetay(Element el)
    {
        String fax="",tel="",adres="",adresTarif="";

        EczaneDetay eczaneDetay= new EczaneDetay();
        Elements eczaneIsmiTag= el.select("thead");
        String eczaneIsmi=eczaneIsmiTag.select("div").attr("title");
        eczaneDetay.setEczaneIsmi(eczaneIsmi);
        Log.i("veseeczaneIsmi",""+eczaneIsmi);

        Elements trTags= el.select("tbody>tr");
        Elements adresTags=trTags.select("tr#adres");
         adres =adresTags.select("label").get(1).text();
        eczaneDetay.setAdres(adres);
        Log.i("veseadres",""+adres);


        Elements telTags=trTags.select("tr#Tel");
         tel =telTags.select("label").get(1).text();
        eczaneDetay.setTelefon(tel);

        Log.i("vesetel",""+tel);

        Element faxTags=trTags.get(2);
         fax =faxTags.select("label").get(1).text();
        if(!fax.equals("")) {
            eczaneDetay.setFax(fax);
        }

        Element adresTarifTags=trTags.get(3);
        adresTarif =adresTarifTags.select("label").get(1).text();
        if(!adresTarif.equals("")) {
            eczaneDetay.setTarif(adresTarif);
        }




        return  eczaneDetay;
    }




    class JsBridge extends MainActivity
    {

        @JavascriptInterface
        public void htmlContentForToken(String str)
        {

            String token[] = str.split("token");


            if(token.length>1)
            {
                String token2[]=token[1].split(Pattern.quote("}"));
                tokenText =token2[0]
                        .replaceAll(" ","")
                        .replaceAll(":","")
                        .replaceAll("\"","");
                    Log.i("cukubik",tokenText);
                Message message= new Message();
                message.what=1;
                message.obj=tokenText;
                handler.sendMessage(message);
            }
        }

        @JavascriptInterface
        public void htmlEczaneDetay(String str)
        {
            Log.i("cevapp",str);
            Message message= new Message();
            message.what=2;
            message.obj=str;
            handler.sendMessage(message);
        }
    }

    public void getCurrentLocation()
    {
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Location location;



        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return ;
        }
        location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(location!=null){
             longitude = location.getLongitude();
             latitude = location.getLatitude();

            System.out.println("tullah: "+ longitude+" "+latitude );

        }


    }

    public void getPermission()
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            System.out.println("tanzile: " );
        }
    }










}
