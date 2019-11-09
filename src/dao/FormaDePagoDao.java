package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.FormaDePago;

public class FormaDePagoDao {

	private static final String INSERT = "INSERT INTO formas_de_pagos" + "(descripcion) VALUES" + " (?);";
    private static final String SELECT_FORMA_PAGO_BY_ID = "select * from formas_de_pagos where id = ?";
    private static final String SELECT = "select * from formas_de_pagos";
    private static final String DELETE = "delete from formas_de_pagos where id = ?;";
    private static final String UPDATE = "update formas_de_pagos set descripcion = ? where id = ?;";
    
    public FormaDePagoDao() {}
    
    public void Insert(FormaDePago frm) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(INSERT))
        	{
	        	preparedStatement.setString(1, frm.getDescripcion());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
        	} 
        	catch (SQLException e) 
        	{
        		printSQLException(e);
        	}
    }
    
    public FormaDePago GetOne(int id) {
    	FormaDePago frm = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FORMA_PAGO_BY_ID);) 
        	{
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();
	
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) 
	            {
	                String descripcion = rs.getString("descripcion");
	                
	                frm = new FormaDePago(id, descripcion);
	            }
        	}
        	catch (SQLException e)
	        {
	            printSQLException(e);
	        }
        	return frm;
    }
    
    public List < FormaDePago > GetAll() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < FormaDePago > formasDePago = new ArrayList < > ();
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
                String descripcion = rs.getString("descripcion");
                
                formasDePago.add(new FormaDePago(id,descripcion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return formasDePago;
    }
    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updatePub(FormaDePago frm) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, frm.getDescripcion());
	        statement.setInt(2,frm.getId());
            
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
