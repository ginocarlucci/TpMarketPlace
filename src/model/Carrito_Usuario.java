package model;

public class Carrito_Usuario 
{
	private int id, idCarrito, idPublicacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Carrito_Usuario(int id, int idCarrito, int idPublicacion) {
		super();
		this.id = id;
		this.idCarrito = idCarrito;
		this.idPublicacion = idPublicacion;
	}
	public Carrito_Usuario() {}

}
