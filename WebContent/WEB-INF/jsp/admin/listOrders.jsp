<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<body>
	<c:choose>
		<c:when test="${not empty adminOrderBeans}">
			<table border="1" style="border-collapse: collapse;">
				<caption>All existing orders:</caption>
				<tr>
					<th>Order id</th>
					<th>Users login</th>
					<th>Users first name</th>
					<th>Users last name</th>
					<th>Users Email</th>
					<th>Order price</th>
					<th>Payment info</th>
					<th>Order status</th>
				</tr>
				<c:forEach items="${adminOrderBeans}" var="bean">
					<tr>
						<td>${bean.getOrderId()}</td>
						<td>${bean.getUserLogin()}</td>
						<td>${bean.getUserFName()}</td>
						<td>${bean.getUserLName()}</td>
						<td>${bean.getUserEmail()}</td>
						<td>${bean.getOrderTotalPrice()}</td>
						<td>${bean.getOrderPaymentInfo()}</td>
						<%-- 						<td>${bean.getOrderStatus()} --%>
						<td><form action="controller">
								<input type="hidden" name="command" value="changeOrderStatus">
								<input type="hidden" name="orderId" value="${bean.getOrderId()}">
								<select name="staus"
									<c:if test="${bean.getOrderStatus() != 'REGISTERED'}"> disabled </c:if>>
									<option>${bean.getOrderStatus()}</option>
									<option value="PAID">PAID</option>
									<option value="CANCELED">CANCELED</option>
								</select>
								<!-- 								 <input type="submit" value="Change"> -->
								<c:if test="${bean.getOrderStatus() == 'REGISTERED'}">
									<input type="submit" value="Change">
								</c:if>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			There are no any orders yet!
		</c:otherwise>
	</c:choose>
</body>
</html>