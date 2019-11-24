<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	body {
	  padding-top: 56px;
	}
</style>

<!DOCTYPE html>
<html lang="en">

<head>

<!-- BOOTSTRAP -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>MarketPlace</title>
</head>

<body>
  <%@include file="plantillas/header.jsp"%>
  <%@include file="modal/producto.jsp"%>
  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Shop Name</h1>
        <p class="card-text">${usuario.getEmail()}</p>
        <div class="list-group" id="categorias">
        <c:forEach items="${listCategoria}" var="categoria">
          <form id="form${categoria.getIdCategoria()}" method="post" action="<%=request.getContextPath()%>/productosByCategoria">
		  <input type="hidden" name="idCategoria" value="${categoria.getIdCategoria()}">
		  </form>
          <a href="#" class="list-group-item" onclick="document.getElementById('form${categoria.getIdCategoria()}').submit()">
          ${categoria.getNombre()}
          </a>
        </c:forEach>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      
      <div class="col-lg-9" style="padding-top:50px;">
        <div class="row">
        <c:forEach items="${listPublicacion}" var="publicacion">
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">${publicacion.getTitulo()}</a>
                </h4>
                <h5>$${publicacion.getPrecio()}</h5>
                <p class="card-text">${publicacion.getDescripcion()}</p>
                <button type="button" class="btn btn-primary col-5"  data-toggle="modal" data-target="#modalQuickView" onclick="cargarModal(${publicacion.getIdPublicacion()})">Ver mas</button>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

<script>
	function cargarModal(idPublicacion){
		$.ajax({
            type : "POST",
            data : {
                idPublicacion : idPublicacion
            },
            url : "/TpMarketPlace/detalleProducto",
            success : function(result){
            	document.getElementById("precioProductoModal").innerHTML = "$"+result.precio;
        		document.getElementById("nombreProductoModal").innerHTML = result.titulo;
        		document.getElementById("descripcionProductoModal").innerHTML = result.descripcion;
            }
        });
	}
</script>

