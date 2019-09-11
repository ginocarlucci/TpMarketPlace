package model;

public class Ciudad 
{
	private String nombre;
	private int codPostal;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	
	public Ciudad(String nombre, int codPostal) {
		this.nombre = nombre;
		this.codPostal = codPostal;
	}

}
