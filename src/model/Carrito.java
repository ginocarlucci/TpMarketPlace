package model;

public class Carrito 
{
	private int id, idUsuario;
	private String nomobreCarrito;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomobreCarrito() {
		return nomobreCarrito;
	}
	public void setNomobreCarrito(String nomobreCarrito) {
		this.nomobreCarrito = nomobreCarrito;
	}
	public Carrito(int id, int idUsuario, String nomobreCarrito) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.nomobreCarrito = nomobreCarrito;
	}
	public Carrito() {}

}
