package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Carrito_Usuario;
import model.Usuario;


public class CarritoUsuarioDao 
{
	private static final String INSERT = "INSERT INTO carritos_usuario" + "(idCarrito, idPublicacion) VALUES" +
            " (?,?);";
    private static final String SELECT_CARUSR_BY_ID = "select * from carritos_usuario where id = ?";
    private static final String SELECT = "select * from carritos_usuario";
    private static final String DELETE = "delete from carritos_usuario where id = ?;";
    private static final String UPDATE = "update carritos_usuario set idCarrito=?, idPublicacion=?, where id = ?;";
    
    public CarritoUsuarioDao() {}
    
    public List < Carrito_Usuario > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Carrito_Usuario > carritos_usuarios = new ArrayList < > ();
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
            	int idCarrito = rs.getInt("idCarrito");
            	int idPublicacion = rs.getInt("idPublicacion");

            	carritos_usuarios.add(new Carrito_Usuario(id,idCarrito,idPublicacion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return carritos_usuarios;
    }
 

    public void Insert(Carrito_Usuario cu) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, cu.getIdCarrito());
            preparedStatement.setInt(2, cu.getIdPublicacion());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Carrito_Usuario GetOne(int id) {
    	Carrito_Usuario cu = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARUSR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int idCarrito = rs.getInt("idCarrito");
            	int idPublicacion = rs.getInt("idPublicacion");
                cu = new Carrito_Usuario(id,idCarrito,idPublicacion);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cu;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Carrito_Usuario cu) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
        	statement.setInt(1, cu.getIdCarrito());
        	statement.setInt(2, cu.getIdPublicacion());
            statement.setInt(3, cu.getId());
            
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
