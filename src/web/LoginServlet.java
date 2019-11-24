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

import dao.LoginDao;
import dao.UsuarioDao;
import model.Categoria;
import model.Ciudad;
import model.Publicacion;
import model.Usuario;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String action = request.getServletPath();
        switch (action) {
        case "/login":
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(request, response);
        case "/cerrarlogin":
        	Usuario usr = null;
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("jsp/login.jsp");
            dispatcher1.forward(request, response);
            break;
        default:
            break;
		}
        
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
            		String email=request.getParameter("email");  
                    String pw=request.getParameter("pass");  
                	if(login.validar(email, pw)==true)
                    {
                    	Usuario usr = new Usuario();
                    	UsuarioDao usrDao = new UsuarioDao();
                    	usr = usrDao.obtenerUsuario(email, pw);
                    	HttpSession misession= request.getSession(true);
                    	misession.setAttribute("usuario",usr);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("");
                        dispatcher.forward(request, response);
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
