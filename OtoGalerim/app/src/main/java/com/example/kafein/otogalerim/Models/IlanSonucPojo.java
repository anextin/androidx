package com.example.kafein.otogalerim.Models;

public class IlanSonucPojo{
	private boolean tf;
	private int uyeid;
	private int ilanid;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setuyeid(int uyeid){
		this.uyeid = uyeid;
	}

	public int getuyeid(){
		return uyeid;
	}

	public void setIlanid(int ilanid){
		this.ilanid = ilanid;
	}

	public int getIlanid(){
		return ilanid;
	}

	@Override
 	public String toString(){
		return 
			"IlanSonucPojo{" + 
			"tf = '" + tf + '\'' + 
			",uye_id = '" + uyeid + '\'' +
			",ilan_id = '" + ilanid + '\'' +
			"}";
		}
}
