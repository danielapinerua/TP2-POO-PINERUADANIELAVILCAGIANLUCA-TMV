package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Usuario {
	protected String nombre;
	protected TipoUsuario tipoUsuario;
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
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
	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}
	public static void setUsuarios(LinkedList<Usuario> usuarios) {
		Usuario.usuarios = usuarios;
	}
	
	
	
	public static Usuario login(String mail, String contr, TipoUsuario tipoEsperado) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getMail().equals(mail) && usuario.getPin().equals(contr)) {
	            if (usuario.getTipoUsuario() == tipoEsperado) {
	                return usuario; 
	            } else {
	                JOptionPane.showMessageDialog(null, 
	                    "Este usuario no pertenece al tipo seleccionado (" + tipoEsperado + ")");
	                return null;
	            }
	        }
	    }
	    return null; 
	}
	
	public void cambiarPin() {
	    String pinActual;
	    do {
	        pinActual = Validar.validarCampo("Ingrese su PIN actual:");
	        if (!this.getPin().equals(pinActual)) {
	            JOptionPane.showMessageDialog(null, "PIN incorrecto. Intente nuevamente.");
	        }
	    } while (!this.getPin().equals(pinActual)); 
	    String nuevoPin = Validar.validarCampo("Ingrese el nuevo PIN:");
	    this.setPin(nuevoPin);
	    JOptionPane.showMessageDialog(null, "PIN actualizado correctamente.");
	}
	

	public void Menu() {
		JOptionPane.showMessageDialog(null,
				"Menu general");
	}
	
	public static void cargaInicial() {
	    // Clientes
	    Usuario.getUsuarios().add(new Cliente("Daniela", "daniela@mail.com", "1111", "123456789"));
	    Usuario.getUsuarios().add(new Cliente("Lucas", "lucas@mail.com", "2222", "987654321"));
	    Usuario.getUsuarios().add(new Cliente("Sofia", "sofia@mail.com", "3333", "234567890"));

	    // Empleados
	    Usuario.getUsuarios().add(new Empleado("Gianluca", "gvilca@mail.com", "1234", "3"));
	    Usuario.getUsuarios().add(new Empleado("Paula", "paula@gmail.com", "12345", "4"));
	    Usuario.getUsuarios().add(new Empleado("Oriana", "oriana@gmail.com", "123456", "5"));

	    // Administrador
	    Usuario.getUsuarios().add(new Administrador("Julian", "admin@gmail.com", "9999", "1"));
	}
	
}

	


