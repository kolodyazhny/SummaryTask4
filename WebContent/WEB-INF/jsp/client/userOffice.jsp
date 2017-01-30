<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<c:set var="title" value="${user.getLogin()}" />
<%@include file="/WEB-INF/jspf/head.jspf"%><body>
	<div align="center">Hello, ${user.getFirstName()}
		${user.getLastName()}</div>
	<div align="left">
		<form action="controller">
			<input type="submit" value="Display all your orders" /> <input
				type="hidden" name="command" value="findAllOrders">
		</form>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${not empty cartBeans}">
				<h4>Products in your cart:</h4>
				<table border="1">
					<tr>
						<th>Product:</th>
						<th>Products count:</th>
						<th>Price:</th>
					</tr>
					<c:forEach items="${cartBeans}" var="bean">
						<tr>
							<td>${bean.getProductName()}</td>
							<td>${bean.getOrderItemProdctsCount()}</td>
							<td>${bean.getOrderItemPrice()}</td>
						</tr>
					</c:forEach>
				</table>
				<br> Total price: ${cart.getTotalPrice()} <br>
				<br>
				<a href="controller?command=choseAction&action=placeOrder">Place
					order</a>
			</c:when>
			<c:otherwise>
				<br>
				Your cart is empty!
				<a href="shop.jsp">Start buying now</a>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${not empty action}">
		<div align="center">
			<br>
			<jsp:include page="${action}.jsp" />
		</div>
	</c:if>
	<div align="center">
		<%@include file="/WEB-INF/jspf/message.jspf"%>
	</div>
</body>
</html>