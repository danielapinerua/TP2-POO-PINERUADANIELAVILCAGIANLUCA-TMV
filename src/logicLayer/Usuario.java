package logicLayer;

public abstract class Usuario {
	protected String nombre;
	protected TipoUsuario tipo;
	protected String mail;
	protected String pin;
	public Usuario(String nombre, TipoUsuario tipo, String mail, String pin) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.mail = mail;
		this.pin = pin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", tipo=" + tipo + ", mail=" + mail + ", pin=" + pin + "]";
	}
	
	
	

}
