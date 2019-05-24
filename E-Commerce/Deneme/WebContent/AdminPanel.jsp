<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>E-Ticaret-Panel</title>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	
</head>
<body class="bg-dark">
	 <%
		 String yonetici_turu=(String)session.getAttribute("yonetici_turu");
        String e_posta_yonetici=(String)session.getAttribute("e_posta_yonetici");
        
      if(e_posta_yonetici==null){
            response.sendRedirect("AdminGiris.jsp");
        }
        %>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark text-dark bg-danger">
			<a class="nav-link ml-2" href="main"><img src="images/picture.png" width="200px" height="50px"></a>
			<ul class="navbar-nav ml-auto mr-5">
				<li class="nav-item active">
				<p class="text-light">Hoşgeldiniz, <b><%=e_posta_yonetici%></b></p> 
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="AdminCikis.jsp">Çıkış</a>
				</li>
			</ul>
		</nav>
	</header>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-2 bg-light py-2 sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item active">
							<a class="nav-link btn bg-danger text-white mb-2" href="adminPanel">Ürün İşlemleri</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link btn bg-danger text-white mb-2" href="adminKullanici">Kullanıcı İşlemleri</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link btn bg-danger text-white mb-2" href="adminSiparislerForm">Sipariş İşlemleri</a>
						</li>
					</ul>
					<hr class="mb-4">
					<ul class="nav flex-column mb-2">
					<li class="nav-item active">
						<form action="adminBilgileriForm" method="post">
							<input type="hidden" name="eposta" value="<%=e_posta_yonetici%>">
							<input type="hidden" name="yonetici_turu" value="<%=yonetici_turu%>">
							<input class="nav-link btn bg-danger text-white btn-block mb-2" type="submit" value="Bilgilerim">
						</form>
					</li>
						<%
       					if(yonetici_turu.equals("Birincil")){ 
        				%>
						<li class="nav-item active">
							<a class="nav-link btn bg-danger text-white mb-2" href="adminYonetici">Yönetici İşlemleri</a>
						</li>
						<% } %>
					</ul>
				</div>
			</nav>

			<main role="main" class="col-10 ml-auto">
				<div class="row bg-light border-left border-danger p-3">
					<h2>Ürünler</h2>
					<div class="table-responsive">
						<table class="table table-striped table-sm">
							<thead>
								<tr>
									<th>#</th>
									<th>Ürün Adı</th>
									<th>Ürün Stok</th>
									<th>Fiyat</th>
									<th>Kategori_id</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="urun" items="${urunListesi}" varStatus="loop">
								<tr>
								<form class="needs-validation" action="urunGuncelle" method="post">
										<th class="align-middle">${loop.index+1}</th>
										<th><input type="text" class="form-control" name="adi" value="${urun.adi}"></th>
										<th><input type="number" class="form-control" name="stok" value="${urun.stok}"></th>
										<th><input type="text" class="form-control" name="fiyat" value="${urun.fiyat}"></th>
										<th><input type="number" class="form-control" name="kategori_id" value="${urun.kategori_id}"></th>
										<th>
											<input type="hidden" name="urun_id" value="${urun.urun_id}">
											<input class="btn btn-success" type="submit" value="Güncelle">
								</form>
								<form action="urunSil" method="post">
											<input type="hidden" name="urun_id" value="${urun.urun_id}">
											<input class="btn btn-danger" type="submit" value="Sil">
								</form>
										</th>
								</tr>
								</c:forEach>
								<tr>
									<form class="needs-validation" action="urunEkle" method="post">	
										<th class="align-middle">?</th>
										<th><input type="text" class="form-control"  name="adi"></th>
										<th><input type="number" class="form-control" name="stok" ></th>
										<th><input type="text" class="form-control" name="fiyat" ></th>
										<th><input type="number" class="form-control" name="kategori_id" ></th>
										<td>
											<input class="btn btn-info" type="submit" value="Ekle"></input>
										</td>
									</form>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</main>
		</div>
	</div>

	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
</body>
</html>
