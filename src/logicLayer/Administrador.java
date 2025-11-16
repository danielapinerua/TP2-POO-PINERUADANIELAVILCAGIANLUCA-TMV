package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {
	private String idAdmin;
	
	
	
	public Administrador(String nombre, String mail, String pin, String idAdmin) {
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
		return "idAdmin=" + idAdmin + ", nombre=" + nombre + ", tipo de usuario=" + tipoUsuario + ", mail="
				+ mail + "]";
	}



	public void crearEmpleado() {
	    String nombre = Validar.validarLetras("Ingrese nombre del empleado:");
	    String mail = Validar.validarCampo("Ingrese mail del empleado:");
	    String pin = Validar.validarCampo("Ingrese PIN del empleado:");
	    // Generar legajo aleatorio basado en el tamaño actual de usuarios
	    int legajoAleatorio = 10 + Usuario.getUsuarios().size(); 
	    String nuevoLegajo = String.valueOf(legajoAleatorio);
	    Empleado nuevo = new Empleado(nombre, mail, pin, nuevoLegajo);
	    Usuario.getUsuarios().add(nuevo);

	    JOptionPane.showMessageDialog(null,
	        "Empleado creado con éxito.\n\n" +
	        "Nombre: " + nombre +
	        "\nMail: " + mail +
	        "\nPIN: " + pin +
	        "\nLegajo asignado: " + nuevoLegajo);
	}
	
	public void darDeBajaCuenta() {
	    LinkedList<Cuenta> cuentas = Cuenta.getCuentas();
	    if (cuentas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay cuentas registradas.");
	        return;
	    }
	    // Crear un array con los nombres de los clientes
	    String[] nombres = new String[cuentas.size()];
	    for (int i = 0; i < nombres.length; i++) {
	        nombres[i] = cuentas.get(i).getCliente().getNombre();
	    }
	    // Mostrar menú desplegable con los nombres de los clientes
	    String nombreElegido = (String) JOptionPane.showInputDialog(
	        null,
	        "Seleccione la cuenta a eliminar:",
	        "Dar de baja cuenta",
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        nombres,
	        nombres[0]
	    );
	    if (nombreElegido == null) {
	        return;
	    }
	    // Buscar la cuenta que corresponde al nombre elegido
	    Cuenta cuentaABorrar = null;
	    for (Cuenta cuenta : cuentas) {
	        if (cuenta.getCliente().getNombre().equalsIgnoreCase(nombreElegido)) {
	            cuentaABorrar = cuenta;
	            break;
	        }
	    }
	        int confirmacion = JOptionPane.showConfirmDialog(
	            null,
	            "¿Seguro que desea eliminar la cuenta de " + nombreElegido + " (CBU: " + cuentaABorrar.getCbu() + ")?",
	            "Confirmar eliminación",
	            JOptionPane.YES_NO_OPTION );

	        if (confirmacion == JOptionPane.YES_OPTION) {
	            cuentas.remove(cuentaABorrar);
	            Usuario.getUsuarios().remove(cuentaABorrar.getCliente());
	            JOptionPane.showMessageDialog(null, "Cuenta eliminada correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Operación cancelada.");
	        }
	    }
	
	public void verEmpleados() {
	    LinkedList<Usuario> usuarios = Usuario.getUsuarios();
	    if (usuarios.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
	        return;
	    }
	    StringBuilder sb = new StringBuilder("Lista de Empleados: \n");
	    for (Usuario usuario : usuarios) {
	        // Si el usuario es del tipo Empleado, lo muestro
	        if (usuario.getTipoUsuario() == TipoUsuario.Empleado) {
	            sb.append(usuario.toString()).append("\n--------------------------\n");
	        }
	    }

	    JOptionPane.showMessageDialog(null, sb.toString());
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
                    
                case 2: //ver empleados
                	verEmpleados();
                	break;
                case 3: //cambiar pin
                	cambiarPin();
                	break;
                	
                case 4: // Ver información
                    JOptionPane.showMessageDialog(null, toString());
                    break;

                case 5: // Salir
                    JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
            }

        } while (opcion != 5);
	}
	
	

}
