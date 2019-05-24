<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>E-Ticaret</title>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	
</head>
<body class="bg-dark">
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-danger">
			<a class="nav-link ml-2" href="list"><img src="images/picture.png" width="200px" height="50px"></a>
			<%@ page import="java.text.SimpleDateFormat" %>
			<%@ page import="java.util.Date" %>
			<% 
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
		    Date now = new Date();
		    String strDate = sdfDate.format(now);
			
		    String e_posta=(String)session.getAttribute("eposta");
		 	if(e_posta==null) {%>
				<ul class="navbar-nav mr-auto ml-5">
					<li class="nav-item active">
						<a class="nav-link" href="kullaniciGirisForm">Giriş Yap </a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="kullaniciKayitForm">Kayıt Ol</a>
					</li>
				</ul>
				<% } else { %>
				<ul class="navbar-nav ml-auto mr-5">
					<li class="nav-item active">
						<p class="nav-link">Hoşgeldiniz, <b><%=e_posta%></b></p>
					</li>
					<li class="nav-item active">
						<form action="kullaniciBilgileriForm" method="post">
							<input type="hidden" name="eposta" value="<%=e_posta%>">
							<input class="nav-link btn bg-danger text-white" type="submit" value="Bilgilerim">
						</form>
					</li>
					<li class="nav-item active">
						<form action="kullaniciSiparisleriForm" method="post">
							<input type="hidden" name="eposta" value="<%=e_posta%>">
							<input class="nav-link btn bg-danger text-white" type="submit" value="Siparislerim">
						</form>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="sepet">Sepetim</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="KullaniciCikis.jsp">Çıkış</a>
					</li>
				</ul>
			<% } %>
		</nav>
	</header>

	<div class="container">
		<div class="row bg-light p-3 m-auto">
		<% if (session.getAttribute("cart") != null) { %>
			<h2>Sepetim</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead class="thead-danger">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Ürün</th>
							<th scope="col">Adet</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="cartItem" items="${requestScope.cartAd}" varStatus="loop">	
							<tr>
								<th scope="col">${loop.index+1}</th>
								<th scope="col"><p><c:out value="${cartItem.key}" /></p></th>
								<th scope="col"><p><c:out value="${cartItem.value}" /></p></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row text-right ml-5">
				<a class="btn btn-outline-danger " href="sepetiTemizle">SepetiTemizle</a>
			</div>
			<div class="ml-auto mr-5">
			
				<form action="siparisiTamamla" method="post">
					Ödeme Türü:
					<select name="odeme_sekli" class="btn btn-outline-danger ">
		  				<option value="krediKarti">Kredi Kartı</option>
		  				<option value="KapidaOdeme">Kapıda Ödeme</option>
					</select>
					<input type="hidden" name="eposta" value="<%=e_posta%>">
					<input type="hidden" name="tarih" value="<%=strDate%>">
					<input type="submit" class="btn btn-outline-danger " value="Siparişi Tamamla">
				</form>
			</div>
			<%} else {%>
			Sepetiniz Boş...
			<%} %>
		</div>
	</div>

	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>

</body>
</html>