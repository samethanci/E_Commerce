package com.eticaret;

public class SiparisDetay {
	protected int siparis_id;
	protected int urun_id;
	protected int fiyat;
	protected int adet;
	protected String adi;
	
	public SiparisDetay(int siparis_id, int urun_id, int fiyat, int adet) {
		this.siparis_id = siparis_id; 
		this.urun_id = urun_id; 
		this.fiyat = fiyat;
		this.adet = adet; 
	}
	public SiparisDetay(int siparis_id, String adi, int fiyat, int adet) {
		this.siparis_id = siparis_id; 
		this.adi = adi; 
		this.fiyat = fiyat;
		this.adet = adet; 
	}
	
	
	public SiparisDetay(int urun_id)
	{
		this.urun_id = urun_id;
	}
	
	public int getSiparis_id() {
		return siparis_id;
	}
	public void setSiparis_id(int siparis_id) {
		this.siparis_id = siparis_id;
	}
	public int getUrun_id() {
		return urun_id;
	}
	public void setUrun_id(int urun_id) {
		this.urun_id = urun_id;
	}
	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}


}