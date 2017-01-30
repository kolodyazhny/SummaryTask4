<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<div align="center">
	<form action="controller?command=modifyProduct&productId=${param.productId}" method="post">
	<input type="hidden" name="productId" value="${productId}">
		<h2>Modify '${param.productName}':</h2>
		<table>
			<tr>
				<td><label for="price">Price:</label> <br> <input
					name="price" pattern="[0-9]+" placeholder="100"></td>
			</tr>
			<tr>
				<td><label for="stock">Stock:</label> <br> <input
					name="stock" pattern="[0-9]+" placeholder="10"></td>
			</tr>
			<tr>
				<td><label for="size">Size:</label> <br> <input
					name="size"></td>
			</tr>
			<tr>
				<td><label for="color">Color:</label> <br> <input
					name="color"></td>
			</tr>
			<tr>
				<td><label for="image">Image source:</label> <br> <input
					name="image"></td>
			</tr>
			<tr>
				<td><label for="description">Description:</label>
					<br> <input name="description"></td>
			</tr>
			<tr>
				<td><label for="category">Category:</label> <br>
					<input name="category"></td>
			</tr>
			<tr>
				<td><label for="date">Manufacturing date:</label> <br> <input
					type="date" name="date"></td>
			</tr>
		</table>
		<br> <input type="submit" value="Add">
	</form>
</div>
</html>