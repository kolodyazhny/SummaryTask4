<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<body>
	<div align="center">
		<form action="controller">
			<input type="hidden" name="command" value="placeOrder"> <label
				for="card">Credit card number:</label><input name=card required
				pattern="[0-9]{13,16}"> <br> <br> <input
				type="submit" value="Place order!">
		</form>
	</div>
</body>
</html>