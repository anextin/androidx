package com.example.ext.asansor;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.ext.asansor.Models.ArizaPojo;
import com.example.ext.asansor.Schedule.MyJobService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import static com.example.ext.asansor.Schedule.MyJobService.*;

public class NoConnection {

    public static String binaAdi;
    public static String arizaTuru;
    public static String Aciklama;
    public Context context;
    public EditText kontrolEditText;
    public static final String FILE_NAME = "externalfile.txt";
    public static final String DIR_NAME = "externaldir";



    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


    public void write(Context context,String binaAdi,String arizaTuru, String Aciklama)
    {

    //    x = true;


        binaAdi  = ArizaPojo.getBinaAdi().toString();
        arizaTuru = ArizaPojo.getArizaTuru().toString();
        Aciklama = ArizaPojo.getAciklama().toString();
        try {

            FileOutputStream fos= new FileOutputStream(new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME));
            String s = (binaAdi+"/"+arizaTuru+"/"+Aciklama);
      //      PrintWriter printWriter = new PrintWriter(new FileOutputStream("hagi"+binaAdi));
            fos.write(s.getBytes(Charset.forName("UTF-8")));
            fos.close();
            Toast.makeText(context,"notepada yazıldı",Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 /*   public Void read(Context context) {

        try {
            String message = "";

            FileInputStream fis = new FileInputStream(
                    new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME));
            int c;
            while ((c = fis.read()) != -1) {
                message += String.valueOf((char) c);
            }

            kontrolEditText.setText(message);
            fis.close();
            Toast.makeText(context,
                    "Data read from externalfile.txt in external storage",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */





    }

