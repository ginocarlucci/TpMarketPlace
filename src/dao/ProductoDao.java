package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Producto;

public class ProductoDao 
{
    private String jdbcURL = "jdbc:mysql://localhost:3306/marketplace";
    private String jdbcUsername = "java";
    private String jdbcPassword = "java";
    
    private static final String INSERT = "INSERT INTO productos" + "  (descripcion) VALUES " +
            " (?);";;
    private static final String SELECT_PROD_BY_ID = "select idProducto,descripcion from productos where idProducto =?";
    private static final String SELECT = "select * from productos";
    private static final String DELETE = "delete from productos where idProducto = ?;";
    private static final String UPDATE = "update productos set descripcion = ? where idProducto = ?;";
    
    public ProductoDao() {}
    
    protected Connection getConnection() 
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProd(Producto prod) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
        	 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, prod.getDescripcion());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Producto selectProd(int id) {
        Producto prod = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROD_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                prod = new Producto(id, descripcion);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return prod;
    }

    public List < Producto > selectAllProd() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Producto > productos = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String descripcion = rs.getString("descripcion");
                productos.add(new Producto(idProducto, descripcion));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productos;
    }
    
    public boolean deleteProd(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProd(Producto prod) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, prod.getDescripcion());
            statement.setInt(2, prod.getIdProducto());
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
