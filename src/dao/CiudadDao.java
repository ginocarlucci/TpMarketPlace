package dao;

import java.util.ArrayList;
import java.util.List;

import model.Ciudad;

public class CiudadDao 
{
    public List < Ciudad > selectAll() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Ciudad > ciudades = new ArrayList < > ();
        ciudades.add(new Ciudad(1,"Rafaela"));
        ciudades.add(new Ciudad(2,"Obera"));
        ciudades.add(new Ciudad(3,"Rufino"));	
        return ciudades;
    }

}
