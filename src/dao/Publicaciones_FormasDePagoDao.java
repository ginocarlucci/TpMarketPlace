package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Publicaciones_FormasDePago;

public class Publicaciones_FormasDePagoDao {

	
	private static final String INSERT = "INSERT INTO publicaciones_formas_de_pagos" + "(idPublicacion, idFormaDePago) VALUES" + " (?,?);";
    private static final String SELECT_PUBLICACIONES_FORMA_PAGO_BY_ID = "select * from publicaciones_formas_de_pagos where id = ?";
    private static final String SELECT = "select * from publicaciones_formas_de_pagos";
    private static final String DELETE = "delete from publicaciones_formas_de_pagos where id = ?;";
    private static final String UPDATE = "update publicaciones_formas_de_pagos set idPublicacion = ?, idFormaDePago = ? where id = ?;";
    
	public Publicaciones_FormasDePagoDao() {}
    
    
	public void Insert(Publicaciones_FormasDePago pub_frm) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(INSERT))
        	{
	        	preparedStatement.setInt(1, pub_frm.getIdPublicacion());
	        	preparedStatement.setInt(2, pub_frm.getIdFormaDePago());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
        	} 
        	catch (SQLException e) 
        	{
        		printSQLException(e);
        	}
    }
	
	public Publicaciones_FormasDePago GetOne(int id) {
		Publicaciones_FormasDePago pub_frm = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLICACIONES_FORMA_PAGO_BY_ID);) 
        	{
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();
	
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) 
	            {
	                int idPublicacion = rs.getInt("idPublicacion");
	                int idFormaDePago = rs.getInt("idFormaDePago");

	                pub_frm = new Publicaciones_FormasDePago(id, idPublicacion, idFormaDePago);
	            }
        	}
        	catch (SQLException e)
	        {
	            printSQLException(e);
	        }
        	return pub_frm;
    }
	
	 public List < Publicaciones_FormasDePago > GetAll() {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Publicaciones_FormasDePago > publicaciones_formasDePago = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try (Connection connection = Conexion.getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);) {
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int idPublicacion = rs.getInt("idPublicacion");
	                int idFormaDePago = rs.getInt("idFormaDePago");
	                
	                publicaciones_formasDePago.add(new Publicaciones_FormasDePago(id,idPublicacion,idFormaDePago));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return publicaciones_formasDePago;
	    }
	
	 public boolean Delete(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    
	    public boolean updatePub(Publicaciones_FormasDePago pub_frm) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
	        	statement.setInt(1, pub_frm.getIdPublicacion());
	        	statement.setInt(2, pub_frm.getIdFormaDePago());
	        	statement.setInt(3, pub_frm.getId());
	            
	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }
	
	
	
    private void printSQLException(SQLException ex) { 
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
