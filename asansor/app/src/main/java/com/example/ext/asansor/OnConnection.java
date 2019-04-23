package com.example.ext.asansor;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.ext.asansor.Schedule.MyJobService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class OnConnection {


    String binaAdi, arizaTuru , aciklama;
    public static final String FILE_NAME = "externalfile.txt";
    public static final String DIR_NAME = "externaldir";




    public void readfromStorage(Context context)
    {

        try {
            String message = "";

            FileInputStream fis = new FileInputStream(
                    new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME));
            int c;
            while ((c = fis.read()) != -1) {
                message += String.valueOf((char) c);
            }
            DbYaz(message);
            fis.close();

            File file = new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME);
            file.getPath();
            file.exists();
            file.getName();
            file.delete();




        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

        public void DbYaz(String message)
        {
            String parts[] = message.split("/");
            String binaAdi= parts[0];
            String arizaTuru= parts[1];
            String aciklama= parts[2];

                ArizaActivity arizaActivity = new ArizaActivity();
                arizaActivity.ilaniYayinla(binaAdi, arizaTuru, aciklama);

            Uri uri;
            File file= new File("externaldir/externalfile.txt");
             file.delete();



        }






}
