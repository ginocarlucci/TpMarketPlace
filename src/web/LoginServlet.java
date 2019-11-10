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

import dao.LoginDao;
import model.Categoria;
import model.Ciudad;
import model.Publicacion;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao login;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
		login = new LoginDao();
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");  
        String pw=request.getParameter("pass");  
        
        if(login.validar(email, pw)==true)
        {
        	response.sendRedirect("jsp/index.jsp");
        }  
       else{  

    	   response.sendRedirect("jsp/ingresomal.jsp");
        }  

	}
	

}
