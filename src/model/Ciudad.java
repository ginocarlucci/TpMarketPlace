package model;

public class Ciudad 
{
	private String nombre;
	private int idCiudad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	public Ciudad(int idCiudad, String nombre) {
		this.idCiudad = idCiudad;
		this.nombre = nombre;
	}

}
