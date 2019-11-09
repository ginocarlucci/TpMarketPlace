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
	
    public List < Categoria > selectAll() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Categoria > categorias = new ArrayList < > ();
        categorias.add(new Categoria(1,"Tecnologia"));
        categorias.add(new Categoria(2,"Automoviles"));
        categorias.add(new Categoria(3,"Inmuebles"));	
        return categorias;
    }

}
