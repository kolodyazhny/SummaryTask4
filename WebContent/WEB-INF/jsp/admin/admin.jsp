<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<c:set var="title" value="MyEsop#AdminPage" />
<%@include file="/WEB-INF/jspf/head.jspf"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<h2>You are logged as ${user.getFirstName()} ${user.getLastName()}</h2>
	<c:set var="command"
		value="controller?command=processAdminAction&action=" />
	<div align="left">
		<ul>
			<li><a href="${command}listProducts">List products</a></li>
			<li><a href="${command}addProduct">Add product</a></li>
			<li><a href="${command}listUsers">List users</a></li>
			<li><a href="${command}listOrders">List orders</a></li>
		</ul>
	</div>
	<c:if test="${not empty action}">
		<div align="center">
			<jsp:include page="${action}.jsp" />
		</div>
	</c:if>
	<div align="center">
		<%@include file="/WEB-INF/jspf/message.jspf"%>
	</div>
</body>
</html>