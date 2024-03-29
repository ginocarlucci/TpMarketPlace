package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Carrito;
import model.Usuario;

public class CarritoDao 
{
	private static final String INSERT = "INSERT INTO carritos" + "(nombreCarrito, idUsuario) VALUES" +
            " (?,?);";
    private static final String SELECT_CAR_BY_ID = "select * from carritos where id = ?";
    private static final String SELECT = "select * from carritos";
    private static final String DELETE = "delete from carritos where id = ?;";
    private static final String UPDATE = "update carritos set nombreCarrito=?, idUsuario=?, where id = ?;";
    
    public CarritoDao() {}
    
    public List < Carrito > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Carrito > carritos = new ArrayList < > ();
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
                String nombreCarrito = rs.getString("nombreCarrito");
                int idUsuario = rs.getInt("idUsuario");
                carritos.add(new Carrito(id, idUsuario, nombreCarrito));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return carritos;
    }
 

    public void Insert(Carrito car) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, car.getNomobreCarrito());
            preparedStatement.setInt(2, car.getIdUsuario());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Carrito GetOne(int id) {
    	Carrito car = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nombreCarrito = rs.getString("nombreCarrito");
                int idUsuario = rs.getInt("idUsuario");
                
                car = new Carrito(id,idUsuario,nombreCarrito);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return car;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Carrito car) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, car.getNomobreCarrito());
            statement.setInt(2, car.getIdUsuario());
            statement.setInt(3, car.getId());
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
