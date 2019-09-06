package model;

public class Producto 
{
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private int idProducto;
	private String descripcion;
	
	public Producto() {}
	
	public Producto(int idProducto, String descripcion) {
		super();
		this.idProducto = idProducto;
		this.descripcion = descripcion;
	}
	
	public Producto(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
}
