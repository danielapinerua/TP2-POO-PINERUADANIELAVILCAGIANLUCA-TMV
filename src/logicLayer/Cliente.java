package logicLayer;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String telefono;

	public Cliente(String nombre, TipoUsuario tipo, String mail, String pin, String telefono) {
		super(nombre, TipoUsuario.CLIENTE, mail, pin);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " | Tel: " + telefono + " | Email: " + getMail();
    }

}
	
	
	

	
	

