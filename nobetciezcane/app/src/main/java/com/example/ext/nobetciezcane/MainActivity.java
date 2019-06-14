package com.example.ext.nobetciezcane;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String tokenText="";
    WebView webView;
    TextView textv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView= new WebView(getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsBridge(),"Android");
        this.getToken();
        textv =findViewById(R.id.textv);
        textv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEczane("1");
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
            Log.i("kuwai","handler");
            if(msg.what==1)
            {
                tokenText= (String) msg.obj;
            }
            else if(msg.what==2)
            {
                parseHtml(msg.obj);
            }

        }
    };

    private void parseHtml(Object htmlKaynak)
    {
        Jsoup.
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


}
