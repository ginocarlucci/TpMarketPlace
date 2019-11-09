package model;

import java.sql.Date;

public class Compra 
{
	
	private int id, idFormaDePago, idEstado;
	private Date fechaHora;
	private double envioPrecio, importe;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFormaDePago() {
		return idFormaDePago;
	}
	public void setIdFormaDePago(int idFormaDePago) {
		this.idFormaDePago = idFormaDePago;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public double getEnvioPrecio() {
		return envioPrecio;
	}
	public void setEnvioPrecio(double envioPrecio) {
		this.envioPrecio = envioPrecio;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public Compra(int id, Date fechaHora, int idFormaDePago, double importe,  double envioPrecio, int idEstado) {
		super();
		this.id = id;
		this.idFormaDePago = idFormaDePago;
		this.idEstado = idEstado;
		this.fechaHora = fechaHora;
		this.envioPrecio = envioPrecio;
		this.importe = importe;
	}
	
	
	public Compra() {
		super();
	}
	
	
	
	
	
}
