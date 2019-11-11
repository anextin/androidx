package com.dev.ext.asansor.Models;

/**
 * Created by Kafein on 4/26/2019.
 */

public class YapilacakBakimlarPojo {

    private String  baslik;
    private String  binaadi;
    private String  yetkili;
    private String  tel;
    private String  donemtarihi;
    private String  asansorserino;
    private boolean tf;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getBinaadi() {
        return binaadi;
    }

    public void setBinaadi(String binaadi) {
        this.binaadi = binaadi;
    }

    public String getYetkili() {
        return yetkili;
    }

    public void setYetkili(String yetkili) {
        this.yetkili = yetkili;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDonemtarihi() {
        return donemtarihi;
    }

    public void setDonemtarihi(String donemtarihi) {
        this.donemtarihi = donemtarihi;
    }

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public String getAsansorserino() {
        return asansorserino;
    }

    public void setAsansorserino(String asansorserino) {
        this.asansorserino = asansorserino;
    }
}
