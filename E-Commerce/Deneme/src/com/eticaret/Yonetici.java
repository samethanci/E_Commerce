package com.eticaret;

public class Yonetici {
	private String e_posta;
    private String sifre;
    private String adi;
    private String yonetici_turu;
    
    public Yonetici(String e_posta, String sifre, String yonetici_turu)
    {
    	this.e_posta = e_posta;
    	this.sifre = sifre;
    	this.yonetici_turu = yonetici_turu;
    }
    
    public Yonetici(String e_posta, String sifre, String adi, String yonetici_turu)
    {
    	this.e_posta = e_posta;
    	this.sifre = sifre;
    	this.adi = adi;
    	this.yonetici_turu = yonetici_turu;
    }
    
    public Yonetici(String e_posta)
    {
    	this.e_posta = e_posta;
    }
    
    public String getE_posta() {
        return e_posta;
    }
    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getYonetici_turu() {
		return yonetici_turu;
	}
	public void setYonetici_turu(String yonetici_turu) {
		this.yonetici_turu = yonetici_turu;
	}
}