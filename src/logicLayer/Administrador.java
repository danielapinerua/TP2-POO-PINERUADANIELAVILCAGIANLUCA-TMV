package logicLayer;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {
	//dar de baja cuentas y crear empleados
	private String idAdmin;

	public Administrador(String nombre,  String mail, String pin, String idAdmin) {
		super(nombre, TipoUsuario.Administrador, mail, pin);
		this.idAdmin = idAdmin;
	}
	

	public String getIdAdmin() {
		return idAdmin;
	}


	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}



	
	@Override
	public String toString() {
		return "idAdmin=" + idAdmin + ", getNombre()=" + getNombre() + ", getMail()=" + getMail() + "]";
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
	        int confirmacion = JOptionPane.showConfirmDialog(
	            null,
	            "¿Seguro que desea eliminar la cuenta de " + cuentaABorrar.getCliente().getNombre() +
	            " (CBU: " + cuentaABorrar.getCbu() + ")?",
	            "Confirmar eliminación",
	            JOptionPane.YES_NO_OPTION
	        );

	        if (confirmacion == JOptionPane.YES_OPTION) {
	            Cuenta.getCuentas().remove(cuentaABorrar);
	            JOptionPane.showMessageDialog(null, "Cuenta eliminada correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Operación cancelada.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "No se encontró una cuenta con ese CBU.");
	    }
	}
	
	@Override
    public void Menu() {
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                null,
                "Menú Administrador", "",
                0, 0, null,
                this.getTipoUsuario().getOpciones(),
                this.getTipoUsuario().getOpciones()[0]
            );

            switch (opcion) {
                case 0: // Crear empleado
                    crearEmpleado();
                    break;

                case 1: // Dar de baja cuenta
                    darDeBajaCuenta();
                    break;

                case 2: // Ver información
                    JOptionPane.showMessageDialog(null, toString());
                    break;

                case 3: // Salir
                    JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
            }

        } while (opcion != 3);
	}
	

}
