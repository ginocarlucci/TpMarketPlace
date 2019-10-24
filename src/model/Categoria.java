package model;

public class Categoria 
{
	private String descripcion;
	private int idCategoria;
	
	public Categoria(int idCategoria,String descripcion) {
		this.descripcion = descripcion;
		this.idCategoria = idCategoria;
	}
	
	public Categoria() {}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

}
