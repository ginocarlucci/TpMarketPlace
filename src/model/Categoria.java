package model;

public class Categoria 
{
	private String nombre;
	private int idCategoria;
	private int idCategoriaPadre;
	
	public int getIdCategoriaPadre() {
		return idCategoriaPadre;
	}

	public void setIdCategoriaPadre(int idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}

	public Categoria(int idCategoria,String nombre, int idCategoriaPadre) {
		this.nombre = nombre;
		this.idCategoria = idCategoria;
		this.idCategoriaPadre = idCategoriaPadre;
	}
	
	public Categoria(int idCategoria,String descripcion) {
		this.nombre = descripcion;
		this.idCategoria = idCategoria;
	}
	
	public Categoria() {}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

}
