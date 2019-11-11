package com.dev.ext.asansor;

import android.content.Context;
import android.net.Uri;

import com.dev.ext.asansor.Activities.ArizaActivity;
import com.dev.ext.asansor.Activities.BakimActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OnConnection {


    String binaAdi, arizaTuru , aciklama;
    public static final String bakimFILE_NAME = "bakimfile.txt";
    public static final String arizaFILE_NAME = "arizafile.txt";
    public static final String DIR_NAME = "externaldir";




    public void readfromStoragebakim(Context context)
    {

        try {
            String message = "";

            FileInputStream fis = new FileInputStream(
                    new File(context.getExternalFilesDir(DIR_NAME), bakimFILE_NAME));
            int c;
            while ((c = fis.read()) != -1) {
                message += String.valueOf((char) c);
            }
            DbYazbakim(message);
            fis.close();

            File file = new File(context.getExternalFilesDir(DIR_NAME), bakimFILE_NAME);
            file.getPath();
            file.exists();
            file.getName();
            file.delete();




        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

        public void DbYazbakim(String message)
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

    public void readfromStorageariza(Context context)
    {

        try {
            String message = "";

            FileInputStream fis = new FileInputStream(
                    new File(context.getExternalFilesDir(DIR_NAME), arizaFILE_NAME));
            int c;
            while ((c = fis.read()) != -1) {
                message += String.valueOf((char) c);
            }
            DbYazariza(message);
            fis.close();

            File file = new File(context.getExternalFilesDir(DIR_NAME), arizaFILE_NAME);
            file.getPath();
            file.exists();
            file.getName();
            file.delete();




        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DbYazariza(String message)
    {
        String parts[] = message.split("/");
        String yetkili= parts[0];
        String arizakonu= parts[1];
        String arizakodu= parts[2];
        String degisenparca= parts[3];
        String eposta= parts[4];
        String tel= parts[5];
        String mesaj= parts[6];
        String donemtarihi= parts[7];
        String asansorserino= parts[8];
        String arizaonarbasla= parts[9];
        String arizaonarbitir= parts[10];
        String arizadurum= parts[11];

        ArizaActivity arizaActivity = new ArizaActivity();
        arizaActivity.ilaniYayinla( yetkili, arizakonu ,  arizakodu, degisenparca, eposta, tel, mesaj, donemtarihi, asansorserino,
                arizaonarbasla, arizaonarbitir, arizadurum);

        Uri uri;
        File file= new File("externaldir/arizafile.txt");
        file.delete();



    }






}
