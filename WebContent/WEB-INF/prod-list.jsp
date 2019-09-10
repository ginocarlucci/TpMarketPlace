<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Productos</title>
</head>
<body>
 <center>
  <h1>Listado Productos</h1>
        <h2>
         <a href="new">Agregar nuevo producto</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">Listar todos los productos</a>
        </h2>
 </center>
	<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Listado de productos</h2></caption>
            <tr>
                <th>ID</th>
                <th>Descripcion</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="producto" items="${listProducto}">
                <tr>
                    <td><c:out value="${producto.idProducto}" /></td>
                    <td><c:out value="${producto.descripcion}" /></td>
                    <td>
                     <a href="edit?idProducto=<c:out value='${producto.idProducto}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?idProducto=<c:out value='${producto.idProducto}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>
