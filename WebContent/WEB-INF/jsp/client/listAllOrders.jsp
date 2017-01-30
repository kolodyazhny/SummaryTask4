<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<body>
	<div align="center">
		<c:forEach items="${allOrders}" var="orderBeans">
					Order status: ${orderBeans.key.getStatus()}
					Order info: ${orderBeans.key.getPaymentInfo()}
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<th>Product:</th>
					<th>Products count:</th>
					<th>Price:</th>
				</tr>
				<c:forEach items="${orderBeans.value}" var="bean">
					<%-- 				<c:forEach items="${orderBeans}" var="bean"> --%>
					<tr>
						<td>${bean.getProductName()}</td>
						<td>${bean.getOrderItemProdctsCount()}</td>
						<td>${bean.getOrderItemPrice()}</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</div>
</body>
</html>