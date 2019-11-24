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

import dao.CategoriaDao;
import dao.CiudadDao;
import dao.PublicacionDao;
import model.Categoria;
import model.Publicacion;

/**
 * Servlet implementation class ProductoByCategoria
 */
@WebServlet(name = "ProductoByCategoria", urlPatterns = {"/productosByCategoria"})
public class ProductoByCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PublicacionDao publicacionDao;
	private CategoriaDao categoriaDao;
	private CiudadDao ciudadDao;
	
	public ProductoByCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() 
	{
		publicacionDao = new PublicacionDao();
		categoriaDao = new CategoriaDao();
		ciudadDao = new CiudadDao();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void buscarProductosByCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException 
	{
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		List < Publicacion > listPublicacion = publicacionDao.selectAllPubByCat(idCategoria);
		//busco las categorias hijas
		List <Categoria> listCategoriasHijas = categoriaDao.selectAllCatByCat(idCategoria);
		for(Categoria cat : listCategoriasHijas) {
			List <Publicacion> pub = publicacionDao.selectAllPubByCat(cat.getIdCategoria());
			for(Publicacion p : pub) {
				listPublicacion.add(p);
			}
		}
        List < Categoria > listCategoria = categoriaDao.selectAll();
        request.setAttribute("listPublicacion", listPublicacion);
        request.setAttribute("listCategoria", listCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ProductoList.jsp");
        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			buscarProductosByCategoria(request,response);
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
