package model;

import java.sql.Date;

public class Compra 
{
	private int id, idFormaPago, idEstado;
	private double importe, envioPrecio;
	private Date fechaHora;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFormaPago() {
		return idFormaPago;
	}
	public void setIdFormaPago(int idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getEnvioPrecio() {
		return envioPrecio;
	}
	public void setEnvioPrecio(double envioPrecio) {
		this.envioPrecio = envioPrecio;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Compra(int id, int idFormaPago, int idEstado, double importe, double envioPrecio, Date fechaHora) {
		super();
		this.id = id;
		this.idFormaPago = idFormaPago;
		this.idEstado = idEstado;
		this.importe = importe;
		this.envioPrecio = envioPrecio;
		this.fechaHora = fechaHora;
	}
	
	public Compra() {}
	
	
}
