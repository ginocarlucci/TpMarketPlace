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
import model.Ciudad;
import model.Publicacion;

/**
 * Servlet implementation class ItemPublicacionServlet
 */
@WebServlet("/ItemPublicacionServlet")
public class ItemPublicacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemPublicacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init() 
	{
    	
	}
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getServletPath();
				
		        try 
		        {
		            switch (action) 
		            {
		                case "/new":
		                    break;
		                default:
		                	mostrarPublicacion(request, response);
		                    break;
		            }
		        } catch (SQLException ex) 
		        {
		            throw new ServletException(ex);
		        }
			}
	
	
	 private void mostrarPublicacion(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException 
			    {
		 			int idPub = Integer.parseInt(request.getParameter("idPublicacion"));
			        dao.PublicacionDao publiDao = new dao.PublicacionDao();
			        model.Publicacion publi = publiDao.selectPub(idPub);
			        request.setAttribute("publicacion", publi);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/Item-Selected.jsp");
	    	        dispatcher.forward(request, response);
			        
			    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
