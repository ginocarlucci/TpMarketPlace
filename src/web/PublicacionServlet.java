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

import dao.PublicacionDao;
import model.Publicacion;

/**
 * Servlet implementation class PublicacionServlet
 */
@WebServlet("/")
public class PublicacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PublicacionDao publicacionDao;
	
	public void init() 
	{
		publicacionDao = new PublicacionDao();	
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		
        try 
        {
            switch (action) 
            {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                	insertPub(request, response);
                    break;
                case "/delete":
                	deletePub(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                	updatePub(request, response);
                    break;
                default:
                	listPub(request, response);
                    break;
            }
        } catch (SQLException ex) 
        {
            throw new ServletException(ex);
        }
    }
	
    private void listPub(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException 
    {
        List < Publicacion > listPublicacion = publicacionDao.selectAllPub();
        request.setAttribute("listPublicacion", listPublicacion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException 
    {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pub-form.jsp");
    	        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException 
    {
    	        int id = Integer.parseInt(request.getParameter("idPublicacion"));
    	        Publicacion existingPublicacion = publicacionDao.selectPub(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pub-form.jsp");
    	        request.setAttribute("publicacion", existingPublicacion);
    	        dispatcher.forward(request, response);

    }
    
    private void insertPub(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        String titulo = request.getParameter("titulo");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        Publicacion newPub = new Publicacion(stock,titulo,descripcion,precio);
        publicacionDao.insertPub(newPub);
        response.sendRedirect("list");
    }
    
    private void updatePub(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idPublicacion"));
        String descripcion = request.getParameter("descripcion");
        String titulo = request.getParameter("titulo");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        Publicacion updPub = new Publicacion(id,stock,titulo,descripcion,precio);
        publicacionDao.updatePub(updPub);
        response.sendRedirect("list");
    }
    
    private void deletePub(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idPublicacion"));
        publicacionDao.deletePub(id);
        response.sendRedirect("list");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
