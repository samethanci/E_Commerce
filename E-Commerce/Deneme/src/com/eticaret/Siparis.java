package com.eticaret;

public class Siparis {
	protected int siparis_id;
	protected String eposta;
	protected String tarih;
	protected String odeme_sekli;
		
	public Siparis(int siparis_id, String eposta, String tarih, String odeme_sekli) {
		this.siparis_id = siparis_id;
		this.eposta = eposta;
		this.tarih = tarih; 
		this.odeme_sekli = odeme_sekli;
	}
	
	public Siparis(String eposta, String tarih, String odeme_sekli) {
		this.eposta = eposta;
		this.tarih = tarih; 
		this.odeme_sekli = odeme_sekli;
	}

	public int getSiparis_id() {
		return siparis_id;
	}

	public void setSiparis_id(int siparis_id) {
		this.siparis_id = siparis_id;
	}

	public String getEposta() {
		return eposta;
	}

	public void setEposta(String eposta) {
		this.eposta = eposta;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getOdeme_sekli() {
		return odeme_sekli;
	}

	public void setOdeme_sekli(String odeme_sekli) {
		this.odeme_sekli = odeme_sekli;
	}

	
	
}
