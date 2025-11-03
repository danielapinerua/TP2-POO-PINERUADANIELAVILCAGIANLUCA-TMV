package logicLayer;

import javax.swing.JOptionPane;

public abstract class Usuario {
	protected String nombre;
	protected TipoUsuario tipoUsuario;
	protected String mail;
	protected String pin;
	public Usuario(String nombre, TipoUsuario tipoUsuario, String mail, String pin) {
		super();
		this.nombre = nombre;
		this.tipoUsuario = tipoUsuario;
		this.mail = mail;
		this.pin = pin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipoUsuario = tipo;
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
		return "Usuario [nombre=" + nombre + ", tipo=" + tipoUsuario + ", mail=" + mail + ", pin=" + pin + "]";
	}
	
	public void Menu() {
		JOptionPane.showMessageDialog(null,
				"Menu general");
	}
}

	


