package com.eticaret;

public class Urun {
	protected int urun_id;
	protected String adi;
	protected int stok;
	protected int fiyat;
	protected int kategori_id;
	
	public Urun(int urun_id, String adi, int stok, int fiyat, int kategori_id) {
		this.urun_id= urun_id; 
		this.adi = adi; 
		this.stok = stok;
		this.fiyat = fiyat; 
		this.kategori_id = kategori_id;
	}
	public Urun(String adi, int stok, int fiyat, int kategori_id) {
		this.adi = adi; 
		this.stok = stok;
		this.fiyat = fiyat; 
		this.kategori_id = kategori_id;
	}

	public int getUrun_id() {
		return urun_id;
	}
	public void setUrun_id(int urun_id) {
		this.urun_id = urun_id;
	}

	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}

	public int getFiyat() {
		return fiyat;
	}
	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}

	public int getKategori_id() {
		return kategori_id;
	}
	public void setKategori_id(int kategori_id) {
		this.kategori_id = kategori_id;
	}
	 


}
