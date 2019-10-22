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

import dao.ProductoDao;
import model.Producto;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet("/asd")
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDao productoDao;
	
	public void init() 
	{
	 productoDao = new ProductoDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
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
                	insertProd(request, response);
                    break;
                case "/delete":
                	deleteProd(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                	updateProd(request, response);
                    break;
                default:
                	listProducto(request, response);
                    break;
            }
        } catch (SQLException ex) 
        {
            throw new ServletException(ex);
        }
    }
	
    private void listProducto(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException 
    {
        List < Producto > listProducto = productoDao.selectAllProd();
        request.setAttribute("listProducto", listProducto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/prod-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException 
    {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/prod-form.jsp");
    	        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException 
    {
    	        int id = Integer.parseInt(request.getParameter("idProducto"));
    	        Producto existingProducto = productoDao.selectProd(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/prod-form.jsp");
    	        request.setAttribute("producto", existingProducto);
    	        dispatcher.forward(request, response);

    }
    
    private void insertProd(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        Producto newProd = new Producto(descripcion);
        productoDao.insertProd(newProd);
        response.sendRedirect("list");
    }
    
    private void updateProd(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idProducto"));
        String descripcion = request.getParameter("descripcion");
        Producto updProd = new Producto(id, descripcion);
        productoDao.updateProd(updProd);
        response.sendRedirect("list");
    }
    
    private void deleteProd(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idProducto"));
        productoDao.deleteProd(id);
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
