package model;

import java.sql.Date;

public class Pregunta 
{
	private int id,idEmisor,idRemitente,idPublicacion;
	private String pregunta, respuesta;
	private Date fechaEmision, fechaRespuesta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEmisor() {
		return idEmisor;
	}
	public void setIdEmisor(int idEmisor) {
		this.idEmisor = idEmisor;
	}
	public int getIdRemitente() {
		return idRemitente;
	}
	public void setIdRemitente(int idRemitente) {
		this.idRemitente = idRemitente;
	}
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
	public Pregunta(int id, int idEmisor, int idRemitente, int idPublicacion, String pregunta, String respuesta,
			Date fechaEmision, Date fechaRespuesta) {
		super();
		this.id = id;
		this.idEmisor = idEmisor;
		this.idRemitente = idRemitente;
		this.idPublicacion = idPublicacion;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.fechaEmision = fechaEmision;
		this.fechaRespuesta = fechaRespuesta;
	}
	
	public Pregunta() {}
}
