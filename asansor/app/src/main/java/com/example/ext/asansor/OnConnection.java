package com.example.ext.asansor;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.ext.asansor.Activities.BakimActivity;
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
            String baslik= parts[0];
            String binaadi= parts[1];
            String donemtarihi= parts[2];
            String yapilacak= parts[3];
            String tutar= parts[4];
            String yetkili= parts[5];
            String aciklama= parts[6];
            String tel= parts[7];
            String eposta= parts[8];
            String mesaj= parts[9];
            String asansorserino= parts[10];
            String bakimbasla= parts[11];
            String bakimbitir= parts[12];
            String bakimdurum= parts[13];

                BakimActivity bakimActivity = new BakimActivity();
           bakimActivity.ilaniYayinla( baslik, binaadi ,  donemtarihi, yapilacak, tutar, yetkili, aciklama, tel, eposta,
                   mesaj, asansorserino, bakimbasla, bakimbitir, bakimdurum);

            Uri uri;
            File file= new File("externaldir/externalfile.txt");
             file.delete();



        }






}
