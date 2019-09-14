package model;

public class Compra 
{
	private int idOperacion;
	private String fechaOP;
	private int total;
	private boolean estado;
	public String horaOP;
	
	public Compra(int idOperacion, String fechaOP, int total, boolean estado, String horaOP) {
		this.idOperacion = idOperacion;
		this.fechaOP = fechaOP;
		this.total = total;
		this.estado = estado;
		this.horaOP = horaOP;
	}

	public int getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getFechaOP() {
		return fechaOP;
	}

	public void setFechaOP(String fechaOP) {
		this.fechaOP = fechaOP;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getHoraOP() {
		return horaOP;
	}

	public void setHoraOP(String horaOP) {
		this.horaOP = horaOP;
	}

	
}
