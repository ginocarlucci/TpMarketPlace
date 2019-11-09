package model;

public class Publicaciones_FormasDePago {
	
	
	private int id, idPublicacion, idFormaDePago;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public int getIdFormaDePago() {
		return idFormaDePago;
	}

	public void setIdFormaDePago(int idFormaDePago) {
		this.idFormaDePago = idFormaDePago;
	}

	public Publicaciones_FormasDePago(int id, int idPublicacion, int idFormaDePago) {
		super();
		this.id = id;
		this.idPublicacion = idPublicacion;
		this.idFormaDePago = idFormaDePago;
	}

	public Publicaciones_FormasDePago() {
		super();
	}
	
	

}
