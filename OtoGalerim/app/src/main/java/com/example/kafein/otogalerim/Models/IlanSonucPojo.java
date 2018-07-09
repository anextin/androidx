package com.example.kafein.otogalerim.Models;

public class IlanSonucPojo{
	private boolean tf;
	private Object uyeId;
	private String ilanId;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setUyeId(Object uyeId){
		this.uyeId = uyeId;
	}

	public Object getUyeId(){
		return uyeId;
	}

	public void setIlanId(String ilanId){
		this.ilanId = ilanId;
	}

	public String getIlanId(){
		return ilanId;
	}

	@Override
 	public String toString(){
		return 
			"IlanSonucPojo{" + 
			"tf = '" + tf + '\'' + 
			",uye_id = '" + uyeId + '\'' + 
			",ilan_id = '" + ilanId + '\'' + 
			"}";
		}
}
