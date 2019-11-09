package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Ciudad;

public class CiudadDao 
{

	private static final String INSERT = "INSERT INTO ciudades" + "(nombre, idProvincia) VALUES" +
            " ?,?);";
    private static final String SELECT_CIU_BY_ID = "select * from ciudades where id = ?";
    private static final String SELECT = "select * from ciudades";
    private static final String DELETE = "delete from ciudades where id = ?;";
    private static final String UPDATE = "update usuarios set nombre=?, idProvincia=?, where id = ?;";
    
    public CiudadDao() {}
    
    public List < Ciudad > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Ciudad > ciudades = new ArrayList < > ();
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
                int idProvincia = rs.getInt("idProvincia");
                
                ciudades.add(new Ciudad(id, idProvincia, nombre));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ciudades;
    }
 

    public void Insert(Ciudad ciu) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, ciu.getNombre());
            preparedStatement.setInt(7, ciu.getId());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Ciudad GetOne(int id) {
    	Ciudad ciu = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CIU_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int idProvincia = rs.getInt("idProvincia");
                
                ciu = new Ciudad(id, idProvincia, nombre);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ciu;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Ciudad ciu) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, ciu.getNombre());
            statement.setInt(6, ciu.getIdProvincia());
            statement.setInt(8, ciu.getId());
            
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
