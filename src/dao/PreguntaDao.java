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
import model.Pregunta;
import model.Usuario;

public class PreguntaDao 
{
	private static final String INSERT = "INSERT INTO preguntas" + "(pregunta, respuesta, idEmisor, idRemitente, fechaEmision, fechaRespuesta, idPublicacion) VALUES" +
            " (?,?,?,?,?,?,?,?);";
    private static final String SELECT_PRG_BY_ID = "select * from preguntas where id = ?";
    private static final String SELECT = "select * from preguntas";
    private static final String DELETE = "delete from preguntas where id = ?;";
    private static final String UPDATE = "update preguntas set pregunta=?, respuesta=?, idEmisor=?, idRemitente=?, fechaEmision=?, fechaRespuesta=?, idPublicacion=?, where id = ?;";
    
    public PreguntaDao() {}
    
    public List < Pregunta > GetAll() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Pregunta > preguntas = new ArrayList < > ();
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
                String pregunta = rs.getString("pregunta");
                String respuesta = rs.getString("respuesta");
                int idEmisor = rs.getInt("idEmisor");
                int idPublicacion = rs.getInt("idPublicacion");
                int idRemitente = rs.getInt("idRemitente");
                Date fechaEmision = rs.getDate("fechaEmision");
                Date fechaRespuesta = rs.getDate("fechaRespuesta");
                

                
                preguntas.add(new Pregunta(id, idEmisor, idRemitente, idPublicacion, pregunta, respuesta, fechaEmision, fechaRespuesta));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return preguntas;
    }
 

    //pregunta, respuesta, idEmisor, idRemitente, fechaEmision, fechaRespuesta, idPublicacion
    public void Insert(Pregunta prg) throws SQLException 
    {
        System.out.println(INSERT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Conexion.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, prg.getPregunta());
            preparedStatement.setString(2, prg.getRespuesta());
            preparedStatement.setInt(3, prg.getIdEmisor());
            preparedStatement.setInt(4, prg.getIdRemitente());
            preparedStatement.setDate(5, prg.getFechaEmision());
            preparedStatement.setDate(6, prg.getFechaRespuesta());
            preparedStatement.setInt(7, prg.getIdPublicacion());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Pregunta GetOne(int id) {
    	Pregunta prg = null; 
        // Step 1: Establishing a Connection
        try (Connection connection = Conexion.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRG_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String pregunta = rs.getString("pregunta");
                String respuesta = rs.getString("respuesta");
                int idEmisor = rs.getInt("idEmisor");
                int idPublicacion = rs.getInt("idPublicacion");
                int idRemitente = rs.getInt("idRemitente");
                Date fechaEmision = rs.getDate("fechaEmision");
                Date fechaRespuesta = rs.getDate("fechaRespuesta");
                
                prg = new Pregunta(id, idEmisor, idRemitente, idPublicacion, pregunta, respuesta, fechaEmision, fechaRespuesta);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return prg;
    }

    
    public boolean Delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean Update(Pregunta prg) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Conexion.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE);) {
            statement.setString(1, prg.getPregunta());
            statement.setString(2, prg.getRespuesta());
            statement.setInt(3, prg.getIdEmisor());
            statement.setInt(4, prg.getIdRemitente());
            statement.setDate(5, prg.getFechaEmision());
            statement.setDate(6, prg.getFechaRespuesta());
            statement.setInt(7, prg.getIdPublicacion());
            statement.setInt(8, prg.getId());
            
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
