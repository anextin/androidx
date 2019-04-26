package com.example.ext.asansor.Models;

/**
 * Created by Kafein on 4/26/2019.
 */

public class ArizaTanimlamaPostPojo {

    static String  binaadi;
    static String  asansoradi;
    static String  arayankisi;
    static String  arayanTel;
    static String  arizatarih;
    static String  arizasaat;
    static String  arizakonu;
    static String  aciklama;
    private boolean tf;

    public static String getBinaadi() {
        return binaadi;
    }

    public static void setBinaadi(String binaadi) {
        ArizaTanimlamaPostPojo.binaadi = binaadi;
    }

    public static String getAsansoradi() {
        return asansoradi;
    }

    public static void setAsansoradi(String asansoradi) {
        ArizaTanimlamaPostPojo.asansoradi = asansoradi;
    }

    public static String getArayankisi() {
        return arayankisi;
    }

    public static void setArayankisi(String arayankisi) {
        ArizaTanimlamaPostPojo.arayankisi = arayankisi;
    }

    public static String getArayanTel() {
        return arayanTel;
    }

    public static void setArayanTel(String arayanTel) {
        ArizaTanimlamaPostPojo.arayanTel = arayanTel;
    }

    public static String getArizatarih() {
        return arizatarih;
    }

    public static void setArizatarih(String arizatarih) {
        ArizaTanimlamaPostPojo.arizatarih = arizatarih;
    }

    public static String getArizasaat() {
        return arizasaat;
    }

    public static void setArizasaat(String arizasaat) {
        ArizaTanimlamaPostPojo.arizasaat = arizasaat;
    }

    public static String getArizakonu() {
        return arizakonu;
    }

    public static void setArizakonu(String arizakonu) {
        ArizaTanimlamaPostPojo.arizakonu = arizakonu;
    }

    public static String getAciklama() {
        return aciklama;
    }

    public static void setAciklama(String aciklama) {
        ArizaTanimlamaPostPojo.aciklama = aciklama;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }
}
