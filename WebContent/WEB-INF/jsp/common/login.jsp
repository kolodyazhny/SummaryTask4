<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE HTML>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
	scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.nure.kolodiazhny.SummaryTask4.resources.lang" />
<html>
<fmt:message key="login.title" var="title" />
<%@include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div align="center">
		<h2>
			<fmt:message key="login.label.signIn" />
		</h2>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="processLogin">
			<table>
				<tr>
					<td><label><fmt:message key="common.label.username" />
					</label> <br> <input name="login" required autofocus
						pattern="^[a-zA-Z0-9]([._](?![._])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9]$" /></td>
				</tr>
				<tr>
					<td><label><fmt:message key="common.label.password" />
					</label> <br> <input type="password" name="password" required
						pattern="\S{6,}" /></td>
				</tr>
			</table>
			<br> <input type="submit"
				value="<fmt:message key="login.button.submit"/>">
		</form>
		<br>
		<%@include file="/WEB-INF/jspf/message.jspf"%>
	</div>
</body>
</html>