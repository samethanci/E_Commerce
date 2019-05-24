package com.eticaret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eticaret.Urun;
import com.eticaret.Kullanici;

public class DAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public DAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public List<Urun> urunleriListele() throws SQLException {
		List<Urun> urunListesi = new ArrayList<>();
		
		String sql = "SELECT * FROM Urun";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int urun_id = resultSet.getInt("urun_id");
			String adi = resultSet.getString("adi");
			int stok = resultSet.getInt("stok");
			int fiyat = resultSet.getInt("fiyat");
			int kategori_id = resultSet.getInt("kategori_id");
		
			Urun urun = new Urun(urun_id, adi, stok, fiyat, kategori_id);
			urunListesi.add(urun);
		}
		
		resultSet.close();
		statement.close();

		disconnect();

		return urunListesi;
	}

	
	public boolean kullaniciKayit(Kullanici kullanici) throws SQLException {
		String sql = "INSERT INTO kullanici(adi_soyadi, telefon, eposta, adres, sifre) VALUES(?, ?, ?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, kullanici.getAdi_soyadi());
		statement.setString(2, kullanici.getTelefon());	
		statement.setString(3, kullanici.getEmail());	
		statement.setString(4, kullanici.getAdres());	
		statement.setString(5, kullanici.getSifre());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public String kullaniciGiris(Kullanici kullanici) throws SQLException {
		
		String sql = "SELECT * FROM kullanici WHERE eposta= ? and sifre = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, kullanici.getEmail());
		statement.setString(2, kullanici.getSifre());
		ResultSet rs = statement.executeQuery();
		String e_posta = null;
		while(rs.next())
		 {
			e_posta = rs.getString("eposta"); 
		 }
		rs.close();
		statement.close();
		return e_posta;
		
	}
	
	public Kullanici kullaniciBilgileri(String e_posta) throws SQLException {
		Kullanici kullanici= null;
		String sql = "SELECT * FROM kullanici WHERE eposta = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, e_posta);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String adi_soyadi = resultSet.getString("adi_soyadi");
			String telefon = resultSet.getString("telefon");
			String eposta = resultSet.getString("eposta");
			String adres = resultSet.getString("adres");
			
			kullanici = new Kullanici(adi_soyadi, telefon, eposta, adres);
		}
		
		resultSet.close();
		statement.close();
		
		return kullanici;
	}
	
	public String kullaniciSifreGetir(String eposta) throws SQLException {
		String sql = "SELECT * FROM kullanici WHERE eposta = ?";
		
		connect();	
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, eposta);
		
		ResultSet resultSet = statement.executeQuery();
		String sifre = null;
		if (resultSet.next()) {
			sifre = resultSet.getString("sifre");
		}
		
		resultSet.close();
		statement.close();
		
		return sifre;
	}
	
	public boolean kullaniciBilgileriDuzenle(Kullanici kullanici) throws SQLException {
		String sql = "UPDATE kullanici SET adi_soyadi = ?, telefon = ?, adres = ?, sifre = ?";
		sql += " WHERE eposta = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, kullanici.getAdi_soyadi());
		statement.setString(2, kullanici.getTelefon());
		statement.setString(3, kullanici.getAdres());
		statement.setString(4, kullanici.getSifre());
		statement.setString(5, kullanici.getEmail());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public List<Siparis> kullaniciSiparisleri(String e_posta) throws SQLException {
		List<Siparis> siparisler = new ArrayList<>();
		
		String sql = "SELECT * FROM Siparis WHERE eposta=?";
		 
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, e_posta);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			int siparis_id = resultSet.getInt("siparis_id");
			String eposta = resultSet.getString("eposta");
			String tarih = resultSet.getString("tarih");
			String odeme_sekli = resultSet.getString("odeme_sekli");
	
		
			Siparis siparis = new Siparis(siparis_id, eposta, tarih, odeme_sekli);
			siparisler.add(siparis);
		}
		
		resultSet.close();
		statement.close();

		disconnect();

		return siparisler;
	}
	
	public List<SiparisDetay> kullaniciSiparisleriDetay(int siparis_id_gelen) throws SQLException {
		List<SiparisDetay> siparisDetayListesi = new ArrayList<>();
		String sql = "SELECT * FROM SiparisDetay";
		sql += " WHERE siparis_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, siparis_id_gelen);
		ResultSet resultSet = statement.executeQuery();
		 
		while (resultSet.next()) {
			int siparis_id = resultSet.getInt("siparis_id");
			String adi = urunAdiGetir(resultSet.getString("urun_id"));
			int fiyat = resultSet.getInt("fiyat");
			int adet = resultSet.getInt("adet");
			
		
			SiparisDetay siparisDetay = new SiparisDetay(siparis_id, adi, fiyat, adet);
			siparisDetayListesi.add(siparisDetay);
		}
		
		resultSet.close();
		statement.close();
		 
		disconnect();
		 
		return siparisDetayListesi;
	}
	
	public void kullaniciSiparisSil(int siparis_id) throws SQLException {
		connect();
		
		String sql = "DELETE FROM Siparis WHERE siparis_id=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, siparis_id);
		statement.executeUpdate();
		statement.close();
		
		String sql2 = "DELETE FROM SiparisDetay WHERE siparis_id=?";
		PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
		statement2.setInt(1, siparis_id);
		statement2.executeUpdate();
		statement2.close();
		
		disconnect();
	}
	
	public int siparisiKaydet(Siparis siparis) throws SQLException {
		String sql = "INSERT INTO Siparis(eposta, tarih, odeme_sekli) VALUES(?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, siparis.getEposta());
		statement.setString(2, siparis.getTarih());	
		statement.setString(3, siparis.getOdeme_sekli());	
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		
		String sql2 = "SELECT * FROM Siparis ORDER BY siparis_id DESC LIMIT 1;";
		Statement statement2 = jdbcConnection.createStatement();
		ResultSet rs = statement2.executeQuery(sql2);
		int siparis_id = 0;
		while(rs.next())
		 {
			siparis_id = rs.getInt("siparis_id"); 
		 }
		rs.close();
		statement2.close();
		disconnect();
		return siparis_id;
		
		
	}
	
	public int urunFiyatGetir(String urun_id) throws SQLException {
		String sql = "SELECT * FROM Urun WHERE urun_id = ?";
		
		connect();	
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, Integer.parseInt(urun_id));
		
		ResultSet resultSet = statement.executeQuery();
		int fiyat = 0;
		if (resultSet.next()) {
			fiyat = resultSet.getInt("fiyat");
		}
		
		resultSet.close();
		statement.close();
		
		return fiyat;
	}
	
	public String urunAdiGetir(String urun_id) throws SQLException {
		String sql = "SELECT * FROM Urun WHERE urun_id = ?";
		
		connect();	
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, Integer.parseInt(urun_id));
		
		ResultSet resultSet = statement.executeQuery();
		String adi = null;
		if (resultSet.next()) {
			adi = resultSet.getString("adi");
		}
		
		resultSet.close();
		statement.close();
		
		return adi;
	}
	
	public boolean siparisiDetayiKaydet(SiparisDetay siparisDetay) throws SQLException {
		String sql = "INSERT INTO SiparisDetay(siparis_id, urun_id, fiyat, adet) VALUES(?, ?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, siparisDetay.getSiparis_id());
		statement.setInt(2, siparisDetay.getUrun_id());	
		statement.setInt(3, siparisDetay.getFiyat());	
		statement.setInt(4, siparisDetay.getAdet());	

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	
	// ADMIN
	public String yoneticiTuruGetir(String e_posta) throws SQLException {
		String sql = "SELECT * FROM Yonetici WHERE eposta = ?";
		
		connect();	
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, e_posta);
		
		ResultSet resultSet = statement.executeQuery();
		String yonetici_turu = null;
		if (resultSet.next()) {
			yonetici_turu = resultSet.getString("yonetici_turu");
		}
		
		resultSet.close();
		statement.close();
		
		return yonetici_turu;
	}
	
	public String yoneticiGiris(Yonetici yonetici) throws SQLException {
		
		String sql = "SELECT * FROM Yonetici WHERE eposta= ? and sifre = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, yonetici.getE_posta());
		statement.setString(2, yonetici.getSifre());
		ResultSet rs = statement.executeQuery();
		String eposta = null;
		while(rs.next())
		 {
			eposta = rs.getString("eposta"); 
		 }
		rs.close();
		statement.close();
		return eposta;
		
	}
	
	
public boolean urunGuncelle(Urun urun) throws SQLException {
		String sql = "UPDATE Urun SET adi=?, stok=?, fiyat=?, kategori_id=?";
		sql += " WHERE urun_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, urun.getAdi());
		statement.setInt(2, urun.getStok());
		statement.setInt(3, urun.getFiyat());
		statement.setInt(4, urun.getKategori_id());
		statement.setInt(5, urun.getUrun_id());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public void urunSil(int urun_id) throws SQLException {
		connect();
		String sql = "DELETE FROM Urun WHERE urun_id=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, urun_id);
		statement.executeUpdate();
		statement.close();
		disconnect();
	}
	
	public boolean urunEkle(Urun urun) throws SQLException {
		String sql = "INSERT INTO Urun (adi, stok, fiyat, kategori_id) VALUES (?, ?, ?, ?)";
		connect();
		 
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, urun.getAdi());
		statement.setInt(2, urun.getStok());
		statement.setInt(3, urun.getFiyat());
		statement.setInt(4, urun.getKategori_id());
		 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;	
	}
	
	public List<Kullanici> kullanicilariListele() throws SQLException {
		List<Kullanici> kullaniciListesi = new ArrayList<>();
		 
		String sql = "SELECT * FROM kullanici";
		 
		connect();
		 
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		 
		while (resultSet.next()) {
			String adi_soyadi = resultSet.getString("adi_soyadi");
			String e_posta = resultSet.getString("eposta");
			String telefon = resultSet.getString("telefon");
			String adres = resultSet.getString("adres");
			String sifre = resultSet.getString("sifre");
		
			Kullanici kullanici = new Kullanici(adi_soyadi, telefon, e_posta, adres, sifre);
			kullaniciListesi.add(kullanici);
		}
		
		resultSet.close();
		statement.close();
		 
		disconnect();
		 
		return kullaniciListesi;
	}
	
	public boolean kullaniciGuncelle(Kullanici kullanici) throws SQLException {
		
		String sql = "UPDATE kullanici SET adi_soyadi=?, telefon=?, adres=?, sifre=?";
		sql += " WHERE eposta = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, kullanici.getAdi_soyadi());
		statement.setString(2, kullanici.getTelefon());
		statement.setString(3, kullanici.getAdres());
		statement.setString(4, kullanici.getSifre());
		statement.setString(5, kullanici.getEmail());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;	
	}
	
	public void kullaniciSil(String eposta) throws SQLException {
		connect();
		String sql = "DELETE FROM kullanici WHERE eposta=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, eposta);
		statement.executeUpdate();
		statement.close();
		disconnect();
	}
	
	public boolean kullaniciEkle(Kullanici kullanici) throws SQLException {
		String sql = "INSERT INTO kullanici (adi_soyadi, telefon, eposta, adres, sifre) VALUES (?, ?, ?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, kullanici.getAdi_soyadi());
		statement.setString(2, kullanici.getTelefon());
		statement.setString(3, kullanici.getEmail());
		statement.setString(4, kullanici.getAdres());
		statement.setString(5, kullanici.getSifre());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;	
	}
	
	public List<Siparis> kullaniciSiparisleri() throws SQLException {
		List<Siparis> siparisler = new ArrayList<>();
		
		String sql = "SELECT * FROM Siparis";
		 
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int siparis_id = resultSet.getInt("siparis_id");
			String eposta = resultSet.getString("eposta");
			String tarih = resultSet.getString("tarih");
			String odeme_sekli = resultSet.getString("odeme_sekli");
	
		
			Siparis siparis = new Siparis(siparis_id, eposta, tarih, odeme_sekli);
			siparisler.add(siparis);
		}
		
		resultSet.close();
		statement.close();

		disconnect();

		return siparisler;
	}
	
	public List<SiparisDetay> adminSiparislerDetayForm(int siparis_id_gelen) throws SQLException {
		List<SiparisDetay> siparisDetayListesi = new ArrayList<>();
		String sql = "SELECT * FROM SiparisDetay";
		sql += " WHERE siparis_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, siparis_id_gelen);
		ResultSet resultSet = statement.executeQuery();
		 
		while (resultSet.next()) {
			int siparis_id = resultSet.getInt("siparis_id");
			int urun_id = resultSet.getInt("urun_id");
			int fiyat = resultSet.getInt("fiyat");
			int adet = resultSet.getInt("adet");
		
			SiparisDetay siparisDetay = new SiparisDetay(siparis_id, urun_id, fiyat, adet);
			siparisDetayListesi.add(siparisDetay);
		}
		
		resultSet.close();
		statement.close();
		 
		disconnect();
		 
		return siparisDetayListesi;
	}
	
	public void adminSiparisSil(int siparis_id) throws SQLException {
		connect();
		
		String sql = "DELETE FROM Siparis WHERE siparis_id=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, siparis_id);
		statement.executeUpdate();
		statement.close();
		
		String sql2 = "DELETE FROM SiparisDetay WHERE siparis_id=?";
		PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
		statement2.setInt(1, siparis_id);
		statement2.executeUpdate();
		statement2.close();
		
		disconnect();
	}
	
	public Yonetici adminBilgileri(String e_posta) throws SQLException {
		Yonetici yonetici= null;
		String sql = "SELECT * FROM Yonetici WHERE eposta = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, e_posta);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String adi_soyadi = resultSet.getString("adi_soyadi");
			String sifre = resultSet.getString("sifre");
			String eposta = resultSet.getString("eposta");
			String yonetici_turu = resultSet.getString("yonetici_turu");
			
			yonetici = new Yonetici(eposta, sifre, adi_soyadi, yonetici_turu);
		}
		
		resultSet.close();
		statement.close();
		
		return yonetici;
	}
	
	public String yoneticiSifreGetir(String eposta) throws SQLException {
		String sql = "SELECT * FROM Yonetici WHERE eposta = ?";
		
		connect();	
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, eposta);
		
		ResultSet resultSet = statement.executeQuery();
		String sifre = null;
		if (resultSet.next()) {
			sifre = resultSet.getString("sifre");
		}
		
		resultSet.close();
		statement.close();
		
		return sifre;
	}
	
	public boolean adminBilgileriDuzenle(Yonetici yonetici) throws SQLException {
		String sql = "UPDATE Yonetici SET adi_soyadi = ?, sifre = ?";
		sql += " WHERE eposta = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, yonetici.getAdi());
		statement.setString(2, yonetici.getSifre());
		statement.setString(3, yonetici.getE_posta());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public List<Yonetici> adminleriListele() throws SQLException {
		List<Yonetici> adminListesi = new ArrayList<>();
		 
		String sql = "SELECT * FROM Yonetici";
		 
		connect();
		 
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		 
		while (resultSet.next()) {
			String adi_soyadi = resultSet.getString("adi_soyadi");
			String e_posta = resultSet.getString("eposta");
			String yonetici_turu = resultSet.getString("yonetici_turu");
			String sifre = resultSet.getString("sifre");
		
			Yonetici yonetici = new Yonetici(e_posta, sifre, adi_soyadi, yonetici_turu);
			adminListesi.add(yonetici);
		}
		
		resultSet.close();
		statement.close();
		 
		disconnect();
		 
		return adminListesi;
	}
	
	public void adminSil(String eposta) throws SQLException {
		connect();
		String sql = "DELETE FROM Yonetici WHERE eposta=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, eposta);
		statement.executeUpdate();
		statement.close();
		disconnect();
	}
	
	public boolean adminEkle(Yonetici yonetici) throws SQLException {
		String sql = "INSERT INTO Yonetici (adi_soyadi, eposta, yonetici_turu, sifre) VALUES (?, ?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, yonetici.getAdi());
		statement.setString(2, yonetici.getE_posta());
		statement.setString(3, yonetici.getYonetici_turu());
		statement.setString(4, yonetici.getSifre());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;	
	}
}