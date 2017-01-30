<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
	scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.nure.kolodiazhny.SummaryTask4.resources.lang" />
<html>
<fmt:message key="registration.title" var="title" />
<%@include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div align="center">
		<form action="controller" method="post">
			<h2>
				<fmt:message key="registration.label.createAcc" />
			</h2>
			<input type="hidden" name="command" value="processRegistrarion">
			<table>
				<tr>
					<td><label for="login"><fmt:message
								key="common.label.username" /></label><br> <input type="text"
						name="login"
						pattern="^[a-zA-Z0-9]([._](?![._])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9]$"
						placeholder="Your_login" required autofocus
						title="Only latin latters, digits, dot and underscore are allowed" /></td>
					<td>*Your login. Should contain Latin letters, digits, dots
						and underscores <br>(should not be one after another and
						should not be in the beginning or end of login)
					</td>
				</tr>
				<tr>
					<td><label for="email"><fmt:message
								key="registration.label.email" /></label> <br> <input type="email"
						name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
						placeholder="Example@somemail.com" required /></td>
					<td>*Your email address (should be a valid email)</td>
				</tr>
				<tr>
					<td><label for="password1"><fmt:message
								key="common.label.password" /></label><br> <input type="password"
						name="password1" required pattern="\S{6,}"></td>
					<td>*Your password (should be at least 6 characters)</td>
				</tr>
				<tr>
					<td><label for="password2"><fmt:message
								key="registration.label.password" /></label><br> <input
						type="password" name="password2" required pattern="\S{6,}"></td>
					<td>*Should be the same as the password</td>
				</tr>
				<tr>
					<td><label for="firstName"><fmt:message
								key="registration.label.fname" /></label><br> <input
						name="firstName" placeholder="Your first name" required
						pattern="^([а-яА-ЯёЁіІїЇ]+|[a-zA-Z]+){2,}" /></td>
					<td>*Your First name</td>
				</tr>
				<tr>
					<td><label for="lastName"><fmt:message
								key="registration.label.lname" /></label><br> <input
						name="lastName" placeholder="Your last name" required
						pattern="^([а-яА-ЯёЁіІїЇ]+|[a-zA-Z]+){2,}" /></td>
					<td>*Your Last name</td>
				</tr>
				<tr>
					<td><label for="phone"><fmt:message
								key="registration.label.phone" /></label><br> <input name="phone"
						pattern="\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}"
						placeholder="(xxx)xxx-xx-xx"
						title="Phone number should match (xxx)xxx-xx-xx format" /></td>
					<td>Your phone number (should be a valid phone number)</td>
				</tr>
				<tr>
					<td><label for="address"><fmt:message
								key="registration.label.address" /></label><br> <input
						name="address" placeholder="Example str." /></td>
					<td>Your address</td>
				</tr>
				<tr>
					<td><label for="gender"><fmt:message
								key="registration.label.gender" /></label><br> <input type="radio"
						name="gender" value="male" /> <fmt:message
							key="registration.radio.male" /><br> <input type="radio"
						name="gender" value="female" /> <fmt:message
							key="registration.radio.female" /></td>
					<td>Your gender</td>
				</tr>
			</table>
			<br> <input id="sub" type="submit"
				value="<fmt:message key="registration.button.submit"/>">
		</form>
		<br> All the fields with a * are required. <br> Do you
		already have an account? <a
			href="controller?command=navigator&page=login"><fmt:message
				key="registration.href.login" /></a>
		<%@include file="/WEB-INF/jspf/message.jspf"%>
	</div>
</body>
</html>