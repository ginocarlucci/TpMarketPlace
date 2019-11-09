package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.xml.internal.bind.v2.model.runtime.RuntimeArrayInfo;

import dao.Conexion;
import model.Compra;

public class CompraDao 
{
	private static final String INSERT = "INSERT INTO compra" + "(fechaHora, idFormaDePago, importe, envioPrecio, idEstado) VALUES" +" (?,?,?,?,?,?);";
    private static final String SELECT_CMP_BY_ID = "select * from compra where id = ?";
    private static final String SELECT = "select * from compra";
    private static final String DELETE = "delete from compra where id = ?;";
    private static final String UPDATE = "update compra set fechaHora=?, idFormaDePago=?, importe=?, envioPrecio=?, idEstado=?, where id = ?;";
    
    public CompraDao() {}
    
    public List < Compra > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Compra > compras = new ArrayList < > ();
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
            	int idFormaDePago = rs.getInt("idFormaDePago");
            	Date fechaHora = rs.getDate("fechaHora");
            	Double envioPrecio = rs.getDouble("envioPrecio");
            	Double importe = rs.getDouble("importe");
            	int idEstado = rs.getInt("idEstado");
                
                compras.add(new Compra(id, fechaHora, idFormaDePago, importe, envioPrecio, idEstado));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return compras;
    }
 

    public void Insert(Compra cmp) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setDate(1, cmp.getFechaHora());
            preparedStatement.setInt(2,cmp.getIdFormaDePago());
            preparedStatement.setDouble(3,cmp.getImporte());
            preparedStatement.setDouble(4, cmp.getEnvioPrecio());
            preparedStatement.setInt(5, cmp.getIdEstado());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Compra GetOne(int id) {
    	Compra cmp = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CMP_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	id = rs.getInt("id");
            	int idFormaDePago = rs.getInt("idFormaDePago");
            	Date fechaHora = rs.getDate("fechaHora");
            	Double envioPrecio = rs.getDouble("envioPrecio");
            	Double importe = rs.getDouble("importe");
            	int idEstado = rs.getInt("idEstado");
                
                cmp = new Compra(id, fechaHora, idFormaDePago, importe, envioPrecio, idEstado);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cmp;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Compra cmp) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setDate(1, cmp.getFechaHora());
            statement.setInt(2, cmp.getIdFormaDePago());
            statement.setDouble(3, cmp.getImporte());
            statement.setDouble(4, cmp.getEnvioPrecio());
            statement.setInt(5, cmp.getIdEstado());
            statement.setInt(6, cmp.getId());
           
            
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
