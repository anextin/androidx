package com.example.kafein.otogalerim.Models;

public class Denemebaslik{
	private String result;
	private String resim;
	private boolean tf;
	private String aciklama;
	private String il;
	private String ilce;
	private Object uyeid;
	private String ilanid;
	private String fiyat;
	private int sayi;
	private String mahalle;
	private String baslik;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIl(String il){
		this.il = il;
	}

	public String getIl(){
		return il;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
	}

	public void setUyeid(Object uyeid){
		this.uyeid = uyeid;
	}

	public Object getUyeid(){
		return uyeid;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setSayi(int sayi){
		this.sayi = sayi;
	}

	public int getSayi(){
		return sayi;
	}

	public void setMahalle(String mahalle){
		this.mahalle = mahalle;
	}

	public String getMahalle(){
		return mahalle;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"Denemebaslik{" + 
			"result = '" + result + '\'' + 
			",resim = '" + resim + '\'' + 
			",tf = '" + tf + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",il = '" + il + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",sayi = '" + sayi + '\'' + 
			",mahalle = '" + mahalle + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
