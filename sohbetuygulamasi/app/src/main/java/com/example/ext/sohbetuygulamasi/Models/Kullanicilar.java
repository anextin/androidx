package com.example.ext.sohbetuygulamasi.Models;

public class Kullanicilar {


    private  String dogumtarih,egitim,hakkimda,isim,resim,cinsiyet,irk,ilce,sehir;
    private Object state,ilceNum,irkNum,cinsiyetNum;

    public Kullanicilar() {
    }

    public Kullanicilar(String dogumtarih, String egitim, String hakkimda, String isim, String resim, Object state, String cinsiyet, String irk, String ilce, String sehir, Object ilceNum,Object irkNum,Object cinsiyetNum) {
        this.dogumtarih = dogumtarih;
        this.egitim = egitim;
        this.hakkimda = hakkimda;
        this.isim = isim;
        this.resim = resim;
        this.state = state;
        this.cinsiyet = cinsiyet;
        this.irk = irk;
        this.ilce = ilce;
        this.sehir = sehir;
        this.ilceNum=ilceNum;
        this.irkNum=irkNum;
        this.cinsiyetNum=cinsiyetNum;
    }

    public Object getIrkNum() {
        return irkNum;
    }

    public void setIrkNum(Object irkNum) {
        this.irkNum = irkNum;
    }

    public Object getCinsiyetNum() {
        return cinsiyetNum;
    }

    public void setCinsiyetNum(Object cinsiyetNum) {
        this.cinsiyetNum = cinsiyetNum;
    }

    public Object getIlceNum() {
        return ilceNum;
    }

    public void setIlceNum(Object ilceNum) {
        this.ilceNum = ilceNum;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getIrk() {
        return irk;
    }

    public void setIrk(String irk) {
        this.irk = irk;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getDogumtarih() {
        return dogumtarih;
    }

    public void setDogumtarih(String dogumtarih) {
        this.dogumtarih = dogumtarih;
    }

    public String getEgitim() {
        return egitim;
    }

    public void setEgitim(String egitim) {
        this.egitim = egitim;
    }

    public String getHakkimda() {
        return hakkimda;
    }

    public void setHakkimda(String hakkimda) {
        this.hakkimda = hakkimda;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public Object getState() { return state; }

    public void setState(Object state) { this.state = state; }

    @Override
    public String toString() {
        return "Kullanicilar{" +
                "dogumtarih='" + dogumtarih + '\'' +
                ", egitim='" + egitim + '\'' +
                ", hakkimda='" + hakkimda + '\'' +
                ", isim='" + isim + '\'' +
                ", resim='" + resim + '\'' +
                ", state=" + state +
                '}';
    }
}
