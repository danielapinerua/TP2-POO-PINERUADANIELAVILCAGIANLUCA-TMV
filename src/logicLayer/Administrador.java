package logicLayer;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {
	//dar de baja cuentas y crear empleados
	private String idAdmin;

	public Administrador(String nombre, TipoUsuario tipoUsuario, String mail, String pin, String idAdmin) {
		super(nombre, tipoUsuario, mail, pin);
		this.idAdmin = idAdmin;
	}

	@Override
	public String toString() {
		return "Administrador [idAdmin=" + idAdmin + "]";
	}
	
	public void crearEmpleado() {
	    String nombre = Validar.validarLetras("Ingrese nombre del empleado:");
	    String mail = Validar.validarCampo("Ingrese mail del empleado:");
	    String pin = Validar.validarCampo("Ingrese PIN del empleado:");
	    String legajo = Validar.validarCampo("Ingrese legajo del empleado:");

	    Empleado nuevo = new Empleado(nombre, mail, pin, legajo);
	    Usuario.getUsuarios().add(nuevo);
	    JOptionPane.showMessageDialog(null, "Empleado creado con éxito.");
	}
	
	public void darDeBajaCuenta() {
	    String cbu = Validar.validarCampo("Ingrese el CBU de la cuenta a dar de baja:");
	    Cuenta cuentaABorrar = null;

	    for (Cuenta cuenta : Cuenta.getCuentas()) {
	        if (cuenta.getCbu().equals(cbu)) {
	            cuentaABorrar = cuenta;
	            break;
	        }
	    }

	    if (cuentaABorrar != null) {
	        Cuenta.getCuentas().remove(cuentaABorrar);
	        JOptionPane.showMessageDialog(null, "Cuenta eliminada correctamente.");
	    } else {
	        JOptionPane.showMessageDialog(null, "No se encontró una cuenta con ese CBU.");
	    }
	}
	

}
