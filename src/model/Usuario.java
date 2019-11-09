package model;

public class Usuario 
{
	private int id, idCiudad, admin;
	private String nombreYApellido, password, email, domicilio, telefono;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getNombreYApellido() {
		return nombreYApellido;
	}
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Usuario(int id, int idCiudad, int admin, String nombreYApellido, String password, String email,
			String domicilio, String telefono) {
		super();
		this.id = id;
		this.idCiudad = idCiudad;
		this.admin = admin;
		this.nombreYApellido = nombreYApellido;
		this.password = password;
		this.email = email;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}
	
	public Usuario() {}
	
	
	

}
