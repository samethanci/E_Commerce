package com.eticaret;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author Samet HANCI
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAO dao;
 
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/EticaretDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        dao = new DAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
        	// ================= KULLANICI ======;===========
            case "/kullaniciGirisForm":
            	kullaniciGirisForm(request, response);
            	break;
            case "/kullaniciGiris":
            	kullaniciGiris(request, response);
            	break;
            case "/kullaniciKayitForm":
            	kayitForm(request, response);
            	break;
            case "/kullaniciKayit":
            	kullaniciKayit(request, response);
            	break;
            case "/kullaniciBilgileriForm":
            	kullaniciBilgileriForm(request, response);
            	break;
            case "/kullaniciBilgileriDuzenle":
            	kullaniciBilgileriDuzenle(request, response);
            	break;
            case "/kullaniciSiparisleriForm":
            	kullaniciSiparisleriForm(request, response);
            	break;
            case "/kullaniciSiparisleriDetayForm":
            	kullaniciSiparisleriDetayForm(request, response);
            	break;
            case "/kullaniciSiparisSil":
            	kullaniciSiparisSil(request, response);
            	break;
            case "/sepeteEkle":
            	sepeteEkle(request, response);
            	break;
            case "/sepet":
            	sepet(request, response);
            	break;
            case "/sepetiTemizle":
            	sepetiTemizle(request, response);
            	break;
            case "/siparisiTamamla":
            	siparisiTamamla(request, response);
            	break;
            	// ADMIN
            case "/yoneticiGiris":
            	yoneticiGiris(request, response);
            	break;
            case "/adminPanel":
            	adminPanel(request, response);
            	break;
            case "/urunGuncelle":
            	urunGuncelle(request,response);
            	break;
            case "/urunSil":
            	urunSil(request,response);
            	break;
            case "/urunEkle":
            	urunEkle(request,response);
            	break;
            case "/adminKullanici":
            	adminKullanici(request,response);
            	break;
            case "/kullaniciGuncelle":
            	kullaniciGuncelle(request,response);
            	break;
            case "/kullaniciSil":
            	kullaniciSil(request,response);
            	break;
            case "/kullaniciEkle":
            	kullaniciEkle(request,response);
            	break;
            case "/adminSiparislerForm":
            	adminSiparislerForm(request, response);
            	break;
            case "/adminSiparislerDetayForm":
            	adminSiparislerDetayForm(request, response);
            	break;
            case "/adminSiparisSil":
            	adminSiparisSil(request, response);
            	break;
            case "/adminBilgileriForm":
            	adminBilgileriForm(request, response);
            	break;
            case "/adminBilgileriDuzenle":
            	adminBilgileriDuzenle(request, response);
            	break;
            case "/adminYonetici":
            	adminYonetici(request,response);
            	break;
            case "/adminSil":
            	adminSil(request,response);
            	break;
            case "/adminEkle":
            	adminEkle(request,response);
            	break;
            default:
                UrunListe(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        
        }
    }
    
    private void UrunListe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Urun> urunListesi = dao.urunleriListele();
		request.setAttribute("urunListesi", urunListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
    
    // ================= KULLANICI =================
    private void kullaniciGirisForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("KullaniciGiris.jsp");
        dispatcher.forward(request, response);
    }
    
	private void kullaniciGiris(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String e_posta = request.getParameter("eposta");
		String sifre = request.getParameter("sifre");
		Kullanici kullanici = new Kullanici(e_posta, sifre);
		String e_posta_gelen= dao.kullaniciGiris(kullanici);
		String gidilecekSayfa = null;
		if(e_posta_gelen!=null)
			gidilecekSayfa = "KullaniciGirisHandler.jsp";
		else
			gidilecekSayfa = "KullaniciGiris.jsp?status=false";
		RequestDispatcher dispatcher = request.getRequestDispatcher(gidilecekSayfa);
    	dispatcher.forward(request, response);
	}
    
    private void kayitForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("KullaniciKayit.jsp");
        dispatcher.forward(request, response);
    }
    
    private void kullaniciKayit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("name");
		String telefon = request.getParameter("telefon");
		String eposta = request.getParameter("eposta");
		String adres = request.getParameter("adres");
		String sifre = request.getParameter("password");


		Kullanici newKullanici = new Kullanici(adi_soyadi, telefon, eposta, adres, sifre);
		dao.kullaniciKayit(newKullanici);
		response.sendRedirect("list");
	}
    
    private void kullaniciBilgileriForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String eposta = request.getParameter("eposta");
		Kullanici kullanici = dao.kullaniciBilgileri(eposta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("KullaniciBilgileri.jsp");
		request.setAttribute("kullanici", kullanici);
		dispatcher.forward(request, response);
    }
    
    private void kullaniciBilgileriDuzenle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("name");
		String telefon = request.getParameter("telefon");
		String eposta = request.getParameter("eposta");
		String adres = request.getParameter("adres");
		String sifre = request.getParameter("password");
		String gecerliSifre = request.getParameter("currentPassword");
		String gecerliSifre_veritabani = dao.kullaniciSifreGetir(eposta);
		if( gecerliSifre_veritabani.equals(gecerliSifre))
		{
			Kullanici kullanici = new Kullanici(adi_soyadi, telefon, eposta, adres, sifre);
			dao.kullaniciBilgileriDuzenle(kullanici);
		}
		response.sendRedirect("list");
	}
    
    private void kullaniciSiparisleriForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String eposta = request.getParameter("eposta");
		List<Siparis> siparisler = dao.kullaniciSiparisleri(eposta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("KullaniciSiparisleri.jsp");
		request.setAttribute("siparisler", siparisler);
		dispatcher.forward(request, response);
    }
    
    private void kullaniciSiparisleriDetayForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		int siparis_id = Integer.parseInt(request.getParameter("siparis_id"));
		List<SiparisDetay> siparisDetaylari= dao.kullaniciSiparisleriDetay(siparis_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("KullaniciSiparisleriDetay.jsp");
		request.setAttribute("siparisDetaylari", siparisDetaylari);
		dispatcher.forward(request, response);
    }
    
    private void kullaniciSiparisSil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int siparis_id = Integer.parseInt(request.getParameter("siparis_id"));
		dao.kullaniciSiparisSil(siparis_id);
		response.sendRedirect("list");
	}
    
    @SuppressWarnings("unchecked")
	private void sepeteEkle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	String urun_id = request.getParameter("urun_id");
    	HttpSession session = request.getSession();
    	if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new HashMap<String, Integer>());
        }

    	Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
    	if (cart.containsKey(urun_id)) {
            cart.put(urun_id, cart.get(urun_id) + 1);
        }
    	else
            cart.put(urun_id, 1);
        response.sendRedirect("sepet");
	}
    
    
    @SuppressWarnings("unchecked")
	private void sepet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
    	Map<String, Integer> cart = (Map<String, Integer>) request.getSession().getAttribute("cart");
    	HttpSession session = request.getSession();
    	Map<String, Integer> cartAd = new HashMap<String, Integer>();
		if (session.getAttribute("cart") != null) 
		{
    		for (Map.Entry<String, Integer> entry : cart.entrySet())
    		{
    			String urun_adi = dao.urunAdiGetir(entry.getKey());
    			cartAd.put(urun_adi, entry.getValue());
    		}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Sepet.jsp");
		request.setAttribute("cartAd", cartAd);
		dispatcher.forward(request, response);
    }
    
    private void sepetiTemizle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().removeAttribute("cart");
    	response.sendRedirect("list");
    }
    
    
    @SuppressWarnings("unchecked")
	private void siparisiTamamla(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String odeme_sekli = request.getParameter("odeme_sekli");
		String eposta = request.getParameter("eposta");
		String tarih = request.getParameter("tarih");
		Siparis siparis = new Siparis(eposta, tarih, odeme_sekli);
		int siparis_id = dao.siparisiKaydet(siparis);
		
		Map<String, Integer> cart = (Map<String, Integer>) request.getSession().getAttribute("cart");
		for (Map.Entry<String, Integer> entry : cart.entrySet())
		{
		    int fiyat = dao.urunFiyatGetir(entry.getKey());
		    SiparisDetay siparisDetay = new SiparisDetay(siparis_id, Integer.parseInt(entry.getKey()), fiyat, entry.getValue());
		    dao.siparisiDetayiKaydet(siparisDetay);
		}
		sepetiTemizle(request, response);
    }
    
    // -------------------------------ADMIN----------------------
	private void yoneticiGiris(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String e_posta = request.getParameter("eposta");
		String sifre = request.getParameter("sifre");
		String yonetici_turu = dao.yoneticiTuruGetir(e_posta);
		Yonetici yonetici = new Yonetici(e_posta, sifre, yonetici_turu);
		String e_posta_gelen= dao.yoneticiGiris(yonetici);
		String gidilecekSayfa = null;
		if(e_posta_gelen!=null)
			gidilecekSayfa = "AdminGirisHandler.jsp";
		else
			gidilecekSayfa = "AdminGiris.jsp?status=false";
		request.setAttribute("yonetici_turu", yonetici_turu);
		RequestDispatcher dispatcher = request.getRequestDispatcher(gidilecekSayfa);
    	dispatcher.forward(request, response);
	}
	
	private void adminPanel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Urun> urunListesi = dao.urunleriListele();
		request.setAttribute("urunListesi", urunListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPanel.jsp");
		dispatcher.forward(request, response);
	}
	
    private void urunGuncelle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int urun_id = Integer.parseInt(request.getParameter("urun_id"));
    	String adi = request.getParameter("adi");
		int stok = Integer.parseInt(request.getParameter("stok"));
		int fiyat = Integer.parseInt(request.getParameter("fiyat"));
		int kategori_id = Integer.parseInt(request.getParameter("kategori_id"));
		Urun urun = new Urun(urun_id, adi, stok, fiyat, kategori_id);
		dao.urunGuncelle(urun);
		response.sendRedirect("adminPanel");
	}
    
    private void urunSil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int urun_id = Integer.parseInt(request.getParameter("urun_id"));
		dao.urunSil(urun_id);
		response.sendRedirect("adminPanel");
	}
    
    private void urunEkle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	String adi = request.getParameter("adi");
		int stok = Integer.parseInt(request.getParameter("stok"));
		int fiyat = Integer.parseInt(request.getParameter("fiyat"));
		int kategori_id = Integer.parseInt(request.getParameter("kategori_id"));
		Urun urun = new Urun(adi, stok, fiyat, kategori_id);
		dao.urunEkle(urun);
		response.sendRedirect("adminPanel");
	}
    
    private void adminKullanici(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Kullanici> kullaniciListesi = dao.kullanicilariListele();
		request.setAttribute("kullaniciListesi", kullaniciListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminKullanici.jsp");
		dispatcher.forward(request, response);
	}
    
    private void kullaniciGuncelle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("adi_soyadi");
		String eposta = request.getParameter("eposta");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		String sifre = request.getParameter("sifre");
		Kullanici kullanici = new Kullanici(adi_soyadi, telefon, eposta, adres, sifre);
		dao.kullaniciGuncelle(kullanici);
		response.sendRedirect("adminKullanici");
	}
    
    private void kullaniciSil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String eposta = request.getParameter("eposta");
		dao.kullaniciSil(eposta);
		response.sendRedirect("adminKullanici");
	}
    
    private void kullaniciEkle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("adi_soyadi");
		String eposta = request.getParameter("eposta");
		String telefon = request.getParameter("telefon");
		String adres = request.getParameter("adres");
		String sifre = request.getParameter("sifre");
		
		Kullanici kullanici = new Kullanici(adi_soyadi, telefon, eposta, adres, sifre);
		dao.kullaniciEkle(kullanici);
		response.sendRedirect("adminKullanici");
	}
    
    private void adminSiparislerForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		List<Siparis> siparisler = dao.kullaniciSiparisleri();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminSiparis.jsp");
		request.setAttribute("siparisler", siparisler);
		dispatcher.forward(request, response);
    }
    
    private void adminSiparislerDetayForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		int siparis_id = Integer.parseInt(request.getParameter("siparis_id"));
		List<SiparisDetay> siparisDetaylari= dao.kullaniciSiparisleriDetay(siparis_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminSiparisDetay.jsp");
		request.setAttribute("siparisDetaylari", siparisDetaylari);
		dispatcher.forward(request, response);
    }
    
    private void adminSiparisSil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int siparis_id = Integer.parseInt(request.getParameter("siparis_id"));
		dao.adminSiparisSil(siparis_id);
		response.sendRedirect("adminSiparislerForm");
	}
    
    private void adminBilgileriForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String eposta = request.getParameter("eposta");
		Yonetici yonetici = dao.adminBilgileri(eposta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminBilgileri.jsp");
		request.setAttribute("yonetici", yonetici);
		dispatcher.forward(request, response);
    }
    
    private void adminBilgileriDuzenle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("adi");
		String eposta = request.getParameter("eposta");
		String sifre = request.getParameter("password");
		String gecerliSifre = request.getParameter("currentPassword");
		String yonetici_turu = request.getParameter("yonetici_turu");
		String gecerliSifre_veritabani = dao.yoneticiSifreGetir(eposta);
		if( gecerliSifre_veritabani.equals(gecerliSifre))
		{
			Yonetici yonetici = new Yonetici(eposta, sifre, adi_soyadi, yonetici_turu);
			dao.adminBilgileriDuzenle(yonetici);
		}
		response.sendRedirect("adminBilgileriForm");
	}
    
    private void adminYonetici(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<Yonetici> adminListesi = dao.adminleriListele();
		request.setAttribute("adminListesi", adminListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminKayit.jsp");
		dispatcher.forward(request, response);
	}
    
    private void adminSil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String eposta = request.getParameter("e_posta");
		dao.adminSil(eposta);
		response.sendRedirect("adminYonetici");
	}
    
    private void adminEkle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String adi_soyadi = request.getParameter("adi");
		String eposta = request.getParameter("e_posta");
		String yonetici_turu = "Diğer";
		String sifre = request.getParameter("sifre");
		
		Yonetici yonetici = new Yonetici(eposta, sifre, adi_soyadi, yonetici_turu);
		dao.adminEkle(yonetici);
		response.sendRedirect("adminYonetici");
	}
    
    
}