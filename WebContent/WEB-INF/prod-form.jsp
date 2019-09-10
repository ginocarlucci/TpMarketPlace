<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>ABMC Productos</title>
</head>
<body>
	<center>
		<h1>ABMC Productos</h1>
	</center>
	<div align="center">
		<c:if test = "${producto != null}">
		<form action="update" method="post">
		</c:if>
		<c:if test = "${producto == null}">
			<form action="insert" method="post">
		</c:if>

		<table border="1" cellpadding="5">
			<c:if test = "${producto != null}">
				<input type="hidden" name="idProducto" value="<c:out value='${producto.idProducto}' />"/>
			</c:if>
				<tr>
					<th>Producto descripcion: </th>
					<td>
						<input type="text" name="descripcion" size="45" value="<c:out value='${producto.descripcion}'/>"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="Save"/>
					</td>
				</tr>
		</table>
		</form>
	</div>
</body>
</html>