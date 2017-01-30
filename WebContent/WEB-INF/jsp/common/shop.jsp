<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<c:set var="title" value="MyEsop#Shop" />
<%@include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%-- HEADER --%>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<%-- HEADER --%>
	<c:if test="${empty productsList || productsList.size() == 0}">
		<jsp:forward page="controller?command=displayProducts" />
	</c:if>
	<div align="center">
		<div align="left">
			<table>
				<caption>Categories:</caption>
				<tr>
					<td><a title="Display all products"
						href="controller?command=displayProducts">All</a></td>
				</tr>
				<c:forEach items="${categoriesList}" var="category">
					<tr>
						<td><a
							title="Display products category: ${category.getName()}"
							href="controller?command=displayProducts&categoryName=${category.getName()}">${category.getName()}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="left">
			<form action="controller" method="get">
				<input type="hidden" name="command" value="displayProducts">
				<input type="hidden" name="categoryName" value="${currentCategory}">
				<select name="sortMethod">
					<optgroup label="Sort by name">
						<option value="name">A-z</option>
						<option value="reverseName">Z-a</option>
					</optgroup>
					<optgroup label="Sort by price">
						<option value="price">Cheapest first</option>
						<option value="reversePrice">Costly first</option>
					</optgroup>
					<optgroup label="Sort by manufacturing date">
						<option value="date">Newest</option>
						<option value="dateReverse">Oldest</option>
					</optgroup>
				</select> <input type="submit" value="Sort">
			</form>
		</div>
		<div align="center">
			<h2>Products in stock:</h2>
			<c:if test="${not empty currentCategory}">
				<h2>Current category: ${currentCategory}</h2>
			</c:if>
			<table border="1" style="border-collapse: collapse;">
				<c:forEach items="${productsList}" var="product">
					<tr>
						<td><div align="center">${product.getName()}</div> <img
							width="500" height="250" alt="image"
							src="${product.getImageSource()}"></td>
						<td>Price: ${product.getPrice()} <br> <br> In
							Stock: ${product.getStock()}<br> <br> <c:if
								test="${not empty product.getSize()}"> Size: ${product.getSize()}<br>
								<br>
							</c:if> <c:if test="${not empty product.getColor()}"> Color: ${product.getColor()}<br>
								<br>
							</c:if> <c:choose>
								<c:when
									test="${not empty product.getStock() && product.getStock() != 0}">
									<a href="controller?command=processAddToCart&productId=${product.getId()}&currentCategory=${currentCategory}&userId=${user.getId()}">Add
										to cart</a>
								</c:when>
								<c:otherwise>
						No products left!
					</c:otherwise>
							</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>