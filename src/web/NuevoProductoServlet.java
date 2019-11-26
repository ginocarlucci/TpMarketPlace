package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PublicacionDao;
import dao.UsuarioDao;
import model.Publicacion;
import model.Usuario;

/**
 * Servlet implementation class NuevoProductoServlet
 */
@WebServlet(name = "nuevoProducto", urlPatterns = {"/nuevoProducto"})
	public class NuevoProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	PublicacionDao publiDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoProductoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
        switch (action) {
        case "/nuevoProducto":
            RequestDispatcher dispatcher = request.getRequestDispatcher("/verProductos");
            dispatcher.forward(request, response);
            break;
        default:
            break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try{
					String action = request.getParameter("accion");
					if(action.equalsIgnoreCase("Agregar")) {
					Publicacion publi = new Publicacion();
					publiDao = new PublicacionDao();
			    	publi.setTitulo(request.getParameter("titulo"));  
			        publi.setDescripcion(request.getParameter("descripcion"));  
			        publi.setStock(Integer.valueOf(request.getParameter("stock")));
			        publi.setPrecio(Double.valueOf(request.getParameter("precio")));
			        publi.setIdCategoria(Integer.valueOf(request.getParameter("idCategoria")));
			        publiDao.insertPub(publi);
					}
					else{
						response.sendRedirect("jsp/ingresomal.jsp");
					}
			        
			} catch (Exception ex) 
			{
			    throw new ServletException(ex);
			}
	}

}
