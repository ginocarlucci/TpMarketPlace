package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Usuario;

public class UsuarioDao 
{
	private static final String INSERT = "INSERT INTO publicaciones" + "(nombreYApellido, password, email, domicilio, telefono, admin, idCiudad) VALUES" +
            " (?,?,?,?,?,?,?);";
    private static final String SELECT_USR_BY_ID = "select * from usuarios where id = ?";
    private static final String SELECT = "select * from usuarios";
    private static final String DELETE = "delete from usuarios where id = ?;";
    private static final String UPDATE = "update usuarios set nombreYApellido=?, password=?, email=?, domicilio=?, telefono=?, admin=?, idCiudad=?, where id = ?;";
    
    public UsuarioDao() {}
    
    public List < Usuario > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Usuario > usuarios = new ArrayList < > ();
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
                String nombreYApellido = rs.getString("nombreYApellido");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                int idCiudad = rs.getInt("idCiudad");
                int admin = rs.getInt("admin");
                
                usuarios.add(new Usuario(id, idCiudad,admin, nombreYApellido, password, email, domicilio, telefono));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return usuarios;
    }
 

    public void Insert(Usuario usr) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, usr.getNombreYApellido());
            preparedStatement.setString(2, usr.getPassword());
            preparedStatement.setString(3, usr.getEmail());
            preparedStatement.setString(4, usr.getDomicilio());
            preparedStatement.setString(5, usr.getTelefono());
            preparedStatement.setInt(6, usr.getAdmin());
            preparedStatement.setInt(7, usr.getIdCiudad());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Usuario GetOne(int id) {
    	Usuario usr = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nombreYApellido = rs.getString("nombreYApellido");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String domicilio = rs.getString("domicilio");
                String telefono = rs.getString("telefono");
                int idCiudad = rs.getInt("idCiudad");
                int admin = rs.getInt("admin");
                
                usr = new Usuario(id, idCiudad,admin, nombreYApellido, password, email, domicilio, telefono);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return usr;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Usuario usr) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, usr.getNombreYApellido());
            statement.setString(2, usr.getPassword());
            statement.setString(3, usr.getEmail());
            statement.setString(4, usr.getDomicilio());
            statement.setString(5, usr.getTelefono());
            statement.setInt(6, usr.getAdmin());
            statement.setInt(7, usr.getIdCiudad());
            statement.setInt(8, usr.getId());
            
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
