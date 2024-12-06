<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap"
	rel="stylesheet" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="./css/index.css">
<title>Gemul</title>
</head>
<body>
	<header>
		<div class="container">
			<div class="logo">Game Store</div>
			<nav>
				<ul>
					<div class="search-bar">
						<i class="fa-solid fa-magnifying-glass"></i> <input type="text"
							placeholder="Search" />
					</div>
					<li><a href="#"> <i class="fa-solid fa-cart-shopping"></i>
					</a></li>
					<li><a href="#"> <i class="fa-solid fa-user-group"></i>
					</a></li>
					<li>
						<%
							Object obj = session.getAttribute("User");
							User user = null;
							if (obj != null){
								user = (User) obj;
							}
							if (user == null) {
							%>
						<a href="Login.jsp"><i class="fa-solid fa-user"></i></a>
							<%} else {%>
						<a href="#"><i class="fa-solid fa-right-to-bracket"></i></a>
							<%}%>
					</li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="slider">
		<div class="list">
			<div class="item">
				<img src="img1.jpg">
			</div>
			<div class="item">
				<img src="img2.jpg">
			</div>
			<div class="item">
				<img src="img3.jpg">
			</div>
			<div class="item">
				<img src="img4.jpg">
			</div>
			<div class="item">
				<img src="img5.jpg">
			</div>
		</div>
		<!-- button prev and next -->
		<div class="buttons">
			<button id="prev"><</button>
			<button id="next">></button>
		</div>
		<ul class="dots">
			<li class="active"></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<section class="product">
		<h2 class="product-category">best selling</h2>
		<button class="pre-btn">
			<img src="images/arrow.png" alt="">
		</button>
		<button class="nxt-btn">
			<img src="images/arrow.png" alt="">
		</button>
		<div class="product-container">
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card1.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card2.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card3.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card4.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card5.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card6.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card7.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card8.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card9.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
			<div class="product-card">
				<div class="product-image">
					<span class="discount-tag">50% off</span> <img
						src="images/card10.jpg" class="product-thumb" alt="">
					<button class="card-btn">add to wishlist</button>
				</div>
				<div class="product-info">
					<h2 class="product-brand">Game</h2>
					<p class="product-short-description">a short line about the
						cloth..</p>
					<span class="price">$20</span><span class="actual-price">$40</span>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<p>© 2023 Game Store. All rights reserved.</p>
	</footer>
	<script type="text/javascript" src="./js/app.js"></script>
</body>
</html>