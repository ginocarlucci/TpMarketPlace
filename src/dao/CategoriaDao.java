package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaDao 
{
    private static final String INSERT = "INSERT INTO categorias" + "(idCategoria, nombre) VALUES" + " (?,?);";
    private static final String SELECT_CAT_BY_ID = "select * from categorias where id = ?";
    private static final String SELECT = "select * from categorias";
    private static final String SELECT_CAT_BY_CATPADRE = "select * from categorias where idCategoria = ?";
    private static final String DELETE = "delete from categorias where id = ?;";
    private static final String UPDATE = "update categorias set idCategoria = ?, nombre = ? where id = ?;";
    
    
    
    public void insertCategoria(Categoria cat) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(INSERT))
        	{
	            preparedStatement.setInt(1, cat.getIdCategoria());
	        	preparedStatement.setString(2, cat.getDescripcion());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
        	} 
        	catch (SQLException e) 
        	{
        		printSQLException(e);
        	}
    }
    
    public Categoria selectCategoria(int id) {
    	Categoria cat = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAT_BY_ID);) 
        	{
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();
	
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) 
	            {
	                int idCategoria = rs.getInt("idCategoria");
	                String nombre = rs.getString("nombre");
	                
	                cat = new Categoria(id, nombre, idCategoria);
	            }
        	}
        	catch (SQLException e)
	        {
	            printSQLException(e);
	        }
        	return cat;
    }
    
    public List < Categoria > selectAll() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Categoria > categorias = new ArrayList < > ();
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
                String nombre = rs.getString("nombre");
                int idCategoria = rs.getInt("idCategoria");
                
                categorias.add(new Categoria(id,nombre, idCategoria));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return categorias;
    }
    
    public List < Categoria > selectAllCatByCat(int idCatPadre) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Categoria > categorias = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAT_BY_CATPADRE);) {
        	preparedStatement.setInt(1, idCatPadre);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int idCategoria = rs.getInt("idCategoria");
                
                
                categorias.add(new Categoria(id, nombre,idCategoria));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return categorias;
    }
    
    public boolean deleteCategoria(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updatePub(Categoria cat) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setInt(1, cat.getIdCategoriaPadre());
            statement.setString(2, cat.getDescripcion());
	        statement.setInt(3,cat.getIdCategoria());
            
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
