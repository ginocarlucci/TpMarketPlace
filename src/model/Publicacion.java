 package model;

public class Publicacion 
{
	private int idPublicacion,stock, idCategoria, idCiudad;
	private String titulo,descripcion;
	private double precio;
	
	public Publicacion(int idPublicacion, int idCategoria, int idCiudad, int stock, String titulo, String descripcion, double precio) {
		this.idPublicacion = idPublicacion;
		this.idCategoria = idCategoria;
		this.idCiudad = idCiudad;
		this.stock = stock;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Publicacion(int stock, String titulo, String descripcion, double precio) {
		this.stock = stock;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Publicacion() {}
	
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

}
