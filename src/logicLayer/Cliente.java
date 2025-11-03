package logicLayer;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String telefono;

	public Cliente(String nombre, TipoUsuario tipoUsuario, String mail, String pin, String telefono) {
		super(nombre, TipoUsuario.Cliente, mail, pin);
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
    
    @Override
	public void Menu() {
		int opcion=JOptionPane.showOptionDialog(null, "Menu Cliente","",0,0,null, this.getTipoUsuario().getOpciones(),this.getTipoUsuario().getOpciones());
	
	}
}
	
	
	

	
	

