<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE HTML>
<html>
<div align="center">
	<form action="controller?command=processAddProduct" method="post">
		<h2>Add new product:</h2>
		${productId}
		<table>
			<tr>
				<td><label for="name">Product name:</label> <br> <input
					pattern="^[а-яА-ЯёЁa-zA-Z0-9]{3,}" name="name" required autofocus
					placeholder="Foo"></td>
			</tr>
			<tr>
				<td><label for="price">Product price:</label> <br> <input
					name="price" pattern="[0-9]+" placeholder="100" required></td>
			</tr>
			<tr>
				<td><label for="stock">Product stock:</label> <br> <input
					name="stock" pattern="[0-9]+" placeholder="10" required></td>
			</tr>
			<tr>
				<td><label for="size">Product size:</label> <br> <input
					name="size"></td>
			</tr>
			<tr>
				<td><label for="color">Product color:</label> <br> <input
					name="color"></td>
			</tr>
			<tr>
				<td><label for="image">Image source:</label> <br> <input
					name="image"></td>
			</tr>
			<tr>
				<td><label for="description">Description of a product:</label>
					<br> <input name="description"></td>
			</tr>
			<tr>
				<td><label for="category">Product category: </label> <br>
					<input name="category" required></td>
			</tr>
			<tr>
				<td><label for="date"> Manufacturing date:</label> <br> <input
					type="date" name="date" required></td>
			</tr>
		</table>
		<br> <input type="submit" value="Add">
	</form>
</div>
</html>