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
import javax.servlet.http.HttpSession;

import model.Categoria;
import model.Ciudad;
import model.Publicacion;
import model.Usuario;
import dao.CategoriaDao;
import dao.PublicacionDao;
import dao.CiudadDao;
import model.Categoria;
import model.Publicacion;
import model.Ciudad;

@WebServlet(name = "VerProducto", urlPatterns = {"/verProductos"})
public class VerProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PublicacionDao publicacionDao;
	private CategoriaDao categoriaDao;
	private CiudadDao ciudadDao;
	private Usuario usr;
	
	public void init() 
	{
		publicacionDao = new PublicacionDao();
		categoriaDao = new CategoriaDao();
		ciudadDao = new CiudadDao();
		usr = null;
		
	}
    public VerProductosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			buscarProductos(request,response);
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	private void buscarProductos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
	{
		HttpSession misession= (HttpSession) request.getSession();
		Usuario usr = (Usuario) misession.getAttribute("usuario");
		List < Publicacion > listPublicacion = publicacionDao.selectAllPub();
        List < Categoria > listCategoria = categoriaDao.selectAll();
        ///List < Ciudad > listCiudad = ciudadDao.GetAll();
        request.setAttribute("listPublicacion", listPublicacion);
        request.setAttribute("listCategoria", listCategoria);
        ///request.setAttribute("listCiudad", listCiudad);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ProductoList.jsp");
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}