package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.Conexion;
import model.Compra;
import model.Usuario;

public class LoginDao 
{
	private static final String SELECTUSUARIO = "select * from usuarios where email = ? and password = ?";
	
	
    public boolean validar(String e, String pw) 
    {
    	//Usuario usr = null; 
    	boolean estado=false;
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECTUSUARIO);) {
            preparedStatement.setString(1, e);
            preparedStatement.setString(2, pw);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            estado = rs.next();

        } catch (SQLException e1) {
            printSQLException(e1);
        }
        return estado;
    }
    

	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}
    }
    

