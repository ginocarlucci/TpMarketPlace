package model;

public class Usuario 
{
	private String nombre, apellido, usuario, contraseña, email, domicilio;
	private boolean admin;
	private int telefono;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public Usuario(String nombre, String apellido, String usuario, String contraseña, String email, String domicilio,
			boolean admin, int telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.email = email;
		this.domicilio = domicilio;
		this.admin = admin;
		this.telefono = telefono;
	}
	
	

}
