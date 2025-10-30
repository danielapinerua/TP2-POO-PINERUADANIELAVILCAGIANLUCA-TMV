package logicLayer;

public class Cliente extends Usuario{
	private String telefono;

	public Cliente(String nombre, TipoUsuario tipo, String mail, String pin, String telefono) {
		super(nombre, tipo, mail, pin);
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
		return "Cliente [telefono=" + telefono + ", getNombre()=" + getNombre() + ", getTipo()=" + getTipo()
				+ ", getMail()=" + getMail() + ", getPin()=" + getPin() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
	

	
	
}
