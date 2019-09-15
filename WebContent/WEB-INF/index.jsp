<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Market Place</title>
</head>
<body>
	<center>
		<div align="center">
			<table border="1" cellpadding="5">
				<caption><h2>Listado publicaciones: </h2></caption>
				<tr>
					<th>ID Pub</th>
					<th>Titulo</th>
					<th>Descripcion</th>
					<th>Precio</th>
					<th>Stock</th>
					<th> - </th>
					
				</tr>
				<c:forEach var="publicacion" items="${listPublicacion}">
                <tr>
                    <td><c:out value="${publicacion.idPublicacion}" /></td>
                    <td><c:out value="${publicacion.titulo}" /></td>
                    <td><c:out value="${publicacion.descripcion}" /></td>
                    <td><c:out value="${publicacion.precio}" /></td>
                    <td><c:out value="${publicacion.stock}" /></td>
                    <c:choose>
    					<c:when test="${publicacion.stock > 0}">
        					<td><a href="new?idPublicacion=<c:out value='${publicacion.idPublicacion}' />">Comprar</a></td> 
    					</c:when>    
    					<c:otherwise>
        					<td>Sin stock</td> 
    					</c:otherwise>
					</c:choose>
                </tr>
            </c:forEach>
			</table>
		</div>
	</center>
</body>
</html>