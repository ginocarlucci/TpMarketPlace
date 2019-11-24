package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.Ciudad;
import model.Publicacion;

import dao.CategoriaDao;
import dao.PublicacionDao;
import dao.CiudadDao;
import model.Categoria;
import model.Publicacion;
import model.Ciudad;
import com.google.gson.*;
@WebServlet(name = "DetalleProducto", urlPatterns = {"/detalleProducto"})
public class DetalleProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PublicacionDao publicacionDao;
	private CategoriaDao categoriaDao;
	private CiudadDao ciudadDao;
	public void init() 
	{
		publicacionDao = new PublicacionDao();
		categoriaDao = new CategoriaDao();
		ciudadDao = new CiudadDao();
	}
    public DetalleProductoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Publicacion publicacion = publicacionDao.selectPub(Integer.parseInt(request.getParameter("idPublicacion")));
		String json = new Gson().toJson(publicacion);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

}
