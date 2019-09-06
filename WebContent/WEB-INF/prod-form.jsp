<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>ABMC Productos</title>
</head>
<body>
 <center>
  <h1>ABMC Productos</h1>
        <h2>
         <a href="new">Agregar nuevo producto</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">Listar todos los productos</a>
         
        </h2>
 </center>
    <div align="center">
  <c:if test="${producto != null}">
   <form action="update" method="post">
        </c:if>
        <c:if test="${producto == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${producto != null}">
               Edit producto
              </c:if>
              <c:if test="${producto == null}">
               Add New producto
              </c:if>
             </h2>
            </caption>
          <c:if test="${producto != null}">
           <input type="hidden" name="id" value="<c:out value='${producto.idProducto}' />" />
          </c:if>            
            <tr>
                <th>producto descripcion: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${producto.descripcion}' />"
                  />
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div> 
</body>
</html>