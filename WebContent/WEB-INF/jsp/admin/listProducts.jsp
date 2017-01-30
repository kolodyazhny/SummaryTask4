<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<c:choose>
	<c:when test="${not empty productsList}">
		<h2>All existing products:</h2>
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Price</th>
				<th>Stock</th>
				<th>Manufacturing<br>date
				</th>
				<th>Size</th>
				<th>Color</th>
				<th>Category</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${productsList}" var="product">
				<tr>
					<td>${product.getId()}</td>
					<td>${product.getName()}</td>
					<td>${product.getPrice()}</td>
					<td>${product.getStock()}</td>
					<td>${product.getManufactoryDate()}</td>
					<td><c:choose>
							<c:when test="${not empty product.getSize()}">${product.getSize()}</c:when>
							<c:otherwise>
							no info
					</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${not empty product.getColor()}">${product.getColor()}</c:when>
							<c:otherwise>
							no info
					</c:otherwise>
						</c:choose></td>
					<td><c:forEach items="${categoriesList}" var="category">
							<c:if test="${category.getId() == product.getCategoryId()}">${category.getName()}</c:if>
						</c:forEach>
					<td><a title="Modify selected element"
						href="controller?command=processAdminAction&action=modifyProduct&productId=${product.getId()}&productName=${product.getName()}"><img
							alt="edit" src="img\edit_pen.png" height="20" width="20"></a></td>
					<td><a title="Delete selected element"
						href="controller?command=processAdminAction&action=deleteProduct&productId=${product.getId()}"><img
							alt="delete" src="img\delete.png" height="20" width="20"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		There are no any available products in a database!
	</c:otherwise>
</c:choose>
</html>