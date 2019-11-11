package com.dev.ext.asansor;

import android.content.Context;
import android.os.Environment;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class NoConnectionAriza {

    public static String binaAdi;
    public static String arizaTuru;
    public static String Aciklama;
    public Context context;
    public EditText kontrolEditText;
    public static final String FILE_NAME = "arizafile.txt";
    public static final String DIR_NAME = "ariza";



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


    public void arizawrite(Context context, String baslik, String binaadi,String date, String yapilacak, String tutar, String yetkili, String aciklama, String tel
            , String eposta, String mesaj, String asansorserino, String bakimbasla, String bakimbitir, String bakimdurum)
    {


        try {

            FileOutputStream fos= new FileOutputStream(new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME));
            String s = (baslik+"/"+binaadi+"/"+date+"/"+yapilacak+"/"+tutar+"/"+yetkili+"/"+aciklama+"/"+tel+"/"+eposta+"/"+mesaj+"/"+asansorserino+"/"+bakimbasla+"/"+bakimbitir+"/"+bakimdurum);
      //      PrintWriter printWriter = new PrintWriter(new FileOutputStream("hagi"+binaAdi));
            fos.write(s.getBytes(Charset.forName("UTF-8")));
            fos.close();
            Toast.makeText(context,"notepada yazıldı",Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void Arizawrite(Context context,String yetkili, String arizakonu, String arizakodu,String degisenparca, String eposta, String arayanTel, String mesaj,String donemtarihi, String asansorserino, String arizaonarbasla
            , String arizaonarbitir, String arizadurum)
    {


        try {

            FileOutputStream fos= new FileOutputStream(new File(context.getExternalFilesDir(DIR_NAME), FILE_NAME));
            String s = (yetkili+"/"+arizakonu+"/"+arizakodu+"/"+degisenparca+"/"+eposta+"/"+arayanTel+"/"+mesaj+"/"+donemtarihi+"/"+asansorserino+"/"+arizaonarbasla+"/"+arizaonarbitir+"/"+arizadurum);
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

