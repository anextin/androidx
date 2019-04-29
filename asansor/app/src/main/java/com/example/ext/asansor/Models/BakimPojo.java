package com.example.ext.asansor.Models;

public class BakimPojo {

    static String  binaAdi;
    static String  arizaTuru;
    static String  aciklama;
    private boolean tf;

    public void setTf(boolean tf){
        this.tf = tf;
    }

    public boolean isTf(){
        return tf;
    }


    public static String getBinaAdi() {
        return binaAdi;
    }

    public static void setBinaAdi(String binaAdi) {
        BakimPojo.binaAdi = binaAdi;
    }

    public static String getArizaTuru() {
        return arizaTuru;
    }

    public static void setArizaTuru(String arizaTuru) {
        BakimPojo.arizaTuru = arizaTuru;
    }

    public static String getAciklama() {
        return aciklama;
    }

    public static void setAciklama(String aciklama) {
        BakimPojo.aciklama = aciklama;
    }




}
