package com.example.kafein.firebase3;

/**
 * Created by Kafein on 11/28/2018.
 */

public class User {

    private boolean durum;
    private String id, isim, soyisim;
    private int yas;

    public User(boolean durum, String id, String isim, String soyisim, int yas) {
        this.durum = durum;
        this.id = id;
        this.isim = isim;
        this.soyisim = soyisim;
        this.yas = yas;
    }

    public User()
    {}

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }
}
