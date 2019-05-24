package com.eticaret;

public class Kullanici {
	protected String adi_soyadi;
	protected String telefon;
	protected String email;
	protected String adres;
	protected String sifre;
	
	public Kullanici(String email, String sifre) {
		this.email = email; 
		this.sifre = sifre;
	}
	
	public Kullanici(String adi_soyadi, String telefon, String email, String adres, String sifre) {
		this.adi_soyadi = adi_soyadi; 
		this.telefon = telefon;
		this.email = email; 
		this.adres = adres;
		this.sifre = sifre;
	}
	
	public Kullanici(String adi_soyadi, String telefon, String email, String adres) {
		this.adi_soyadi = adi_soyadi; 
		this.telefon = telefon;
		this.email = email; 
		this.adres = adres;
	}

	public String getAdi_soyadi() {
		return adi_soyadi;
	}
	public void setAdi_soyadi(String adi_soyadi) {
		this.adi_soyadi = adi_soyadi;
	}

	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	
}