package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Publicacion;

public class PublicacionDao 
{

    
    private static final String INSERT = "INSERT INTO publicaciones" + "(descripcion, precio, titulo, stock, idCategoria, idCiudad) VALUES" +
            " (?,?,?,?,?);";;
    private static final String SELECT_PUB_BY_ID = "select idPublicacion from publicaciones where idPublicacion = ?";
    private static final String SELECT = "select * from publicaciones";
    private static final String SELECT_PUB_BY_CATEGORIA = "select * from publicaciones where idCategoria = ?";
    private static final String DELETE = "delete from publicaciones where idPublicacion = ?;";
    private static final String UPDATE = "update publicaciones set descripcion = ?, precio = ?, titulo = ?, stock = ?, idCategoria = ?, idCiudad = ? where idProducto = ?;";
    
    public PublicacionDao() {}
    


    public void insertPub(Publicacion pub) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, pub.getDescripcion());
            preparedStatement.setDouble(2, pub.getPrecio());
            preparedStatement.setString(3, pub.getTitulo());
            preparedStatement.setInt(4, pub.getStock());
            preparedStatement.setInt(5, pub.getIdCategoria());
            preparedStatement.setInt(6, pub.getIdCiudad());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Publicacion selectPub(int id) {
    	Publicacion pub = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUB_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                Double precio = rs.getDouble("precio");
                String titulo = rs.getString("titulo");
                int idCategoria = rs.getInt("idCategoria");
                int idCiudad = rs.getInt("idCiudad");
                int stock = rs.getInt("stock");
                
                pub = new Publicacion(id, idCategoria, idCiudad,stock, titulo, descripcion, precio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return pub;
    }
    
    public List < Publicacion > selectAllPubByCat(int idCat) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Publicacion > publicaciones = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUB_BY_CATEGORIA);) {
        	preparedStatement.setInt(1, idCat);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int idPublicacion = rs.getInt("idPublicacion");
                int idCategoria = rs.getInt("idCategoria");
                String descripcion = rs.getString("descripcion");
                Double precio = rs.getDouble("precio");
                int idCiudad = rs.getInt("idCiudad");
                String titulo = rs.getString("titulo");
                int stock = rs.getInt("stock");
                
                publicaciones.add(new Publicacion(idPublicacion, idCategoria,idCiudad,stock, titulo, descripcion, precio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return publicaciones;
    }

    public List < Publicacion > selectAllPub() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Publicacion > publicaciones = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int idPublicacion = rs.getInt("idPublicacion");
                int idCategoria = rs.getInt("idCategoria");
                String descripcion = rs.getString("descripcion");
                Double precio = rs.getDouble("precio");
                int idCiudad = rs.getInt("idCiudad");
                String titulo = rs.getString("titulo");
                int stock = rs.getInt("stock");
                
                publicaciones.add(new Publicacion(idPublicacion, idCategoria,idCiudad,stock, titulo, descripcion, precio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return publicaciones;
    }
    
    public boolean deletePub(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePub(Publicacion pub) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, pub.getDescripcion());
            statement.setDouble(2, pub.getPrecio());
            statement.setString(3, pub.getTitulo());
            statement.setInt(4, pub.getStock());
            statement.setInt(5, pub.getIdCategoria());
            statement.setInt(6, pub.getIdCiudad());
            statement.setInt(7, pub.getIdPublicacion());
            
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