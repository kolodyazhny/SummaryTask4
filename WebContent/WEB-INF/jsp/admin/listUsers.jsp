<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<table border="1" style="border-collapse: collapse;">
	<caption>All users, registered in a system:</caption>
	<tr>
		<th>Identifier</th>
		<th>Login</th>
		<th>First name</th>
		<th>Last name</th>
		<th>Email</th>
		<th>Status</th>
<!-- 		<th></th> -->
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.getId()}</td>
			<td>${user.getLogin()}</td>
			<td>${user.getFirstName()}</td>
			<td>${user.getLastName()}</td>
			<td>${user.getEmail()}</td>
			<td>${user.getStatus()}<c:if test="${user.getRoleId() != 1}">
					<a title="Block this user"
						href="controller?command=changeUserStatus&userId=${user.getId()}"><img
						height="20" width="20" alt="Edit" src="img/edit_pen.png" /></a>
				</c:if>
			</td>
<!-- 			<td><a href="">Details</a></td> -->
		</tr>
	</c:forEach>
</table>
</html>