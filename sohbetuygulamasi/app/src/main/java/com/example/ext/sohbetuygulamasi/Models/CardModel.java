package com.example.ext.sohbetuygulamasi.Models;

public class CardModel {


    private  String resim, isim;

    public CardModel(String resim, String isim) {
        this.resim = resim;
        this.isim = isim;
    }

    public CardModel() {
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    @Override
    public String toString() {
        return "CardModel{" +
                "resim='" + resim + '\'' +
                ", isim='" + isim + '\'' +
                '}';
    }
}
