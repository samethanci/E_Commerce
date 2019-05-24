<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>E-Ticaret-Giriş</title>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	
</head>
<body class="bg-dark">
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-danger">
			<a class="nav-link ml-2" href="index.jsp"><img src="images/picture.png" width="200" height="50px"></a>
		</nav>
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			
			<div class="row bg-light border border-danger col-6 p-4">
					<h4 class="mb-3">Admin Giriş</h4>
					<hr class="mb-4">
					<form action="yoneticiGiris" method="post"> 
					<div class="mb-3">
						<label for="e-posta">E-posta</label>
						<input type="email" class="form-control" name="eposta" size="128" placeholder="name@example.com">
					</div>

					<div class="mb-3">
						<label for="password">Şifre</label>
						<input type="password" class="form-control" name="sifre" size="32">
					</div>

					<hr class="mb-4">
					<input class="btn btn-primary btn-lg btn-block" type="submit" value="Giriş"></input>
				</form>
			</div>

			<div class="col-3"></div>
		</div>
	</div>
		

		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	</body>
</html>
