package userLayer;

import javax.swing.JOptionPane;
import logicLayer.*;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        Cajero cajero = new Cajero(50000.00);

        // lista de empleados
        
        //esto deberia estar en la clase de empoleados despues lo muevo
        LinkedList<Empleado> empleados = new LinkedList<>();
        empleados.add(new Empleado("Gianluca", TipoUsuario.EMPLEADO, "gvilca@mail.com", "1234", "L001"));

        
        //permite elegir que tipo de usuario
        //esta en el enum
         TipoUsuario tipoElegido = TipoUsuario.elegirTipo();
		
		// muestra menu depende si es cliente o empleado
         //esta en el enum
		if (tipoElegido == TipoUsuario.CLIENTE) {
			JOptionPane.showMessageDialog(null, "Ingresó como CLIENTE");
			tipoElegido.mostrarMenu();
			
		} else if (tipoElegido == TipoUsuario.EMPLEADO) {
			JOptionPane.showMessageDialog(null, "Ingresó como EMPLEADO");
			tipoElegido.mostrarMenu();
		}
	}

        
        
        
        //como estaba hecho antes
        int opcionMenu;
        do {
            String[] opcionesTipo = {"Empleado", "Cliente", "Salir"};
            opcionMenu = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione tipo de usuario",
                    "Cajero automático",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcionesTipo,
                    opcionesTipo[0]
            );

            switch (opcionMenu) {
                case 0: // empleado
                    String mailEmp = JOptionPane.showInputDialog("Mail del empleado:");
                    String pinEmp = JOptionPane.showInputDialog("PIN del empleado:");

                    Empleado empleadoLogueado = Empleado.login(mailEmp, pinEmp);
                        }
                    }

                    if (empleadoLogueado == null) {
                        JOptionPane.showMessageDialog(null, "Empleado no encontrado o datos incorrectos");
                    } else {
                        empleadoLogueado.mostrarMenu(cajero);
                    }
                    break;

                case 1: // cliente
                    if (Cuenta.getCuentas().isEmpty()) {
                        // si no hay preguntar para crear uno
                        int crear = JOptionPane.showConfirmDialog(
                                null,
                                "No hay clientes registrados. ¿Desea crear uno?",
                                "No hay clientes",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (crear == JOptionPane.YES_OPTION) {
                            Cuenta.registrarse(); // se llama al metodo para registrarse
                        }
                        break; // volver al menu principal
                    }

                    // pedimos mail y pin
                    String mailCli = JOptionPane.showInputDialog("Mail del cliente:");
                    String pinCli = JOptionPane.showInputDialog("PIN del cliente:");

                    Cuenta cuentaLogueada = Cuenta.login(mailCli, pinCli);
                    if (cuentaLogueada == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado o datos incorrectos");
                    } else {
                        cuentaLogueada.getCliente().mostrarMenu();
                    }
                    break;

                case 2: // salir
                    JOptionPane.showMessageDialog(null, "Gracias por usar el cajero!");
                    break;

                default:
                	//aca hay q modificar para ponerle q cierre si le damos en la X en el menu incial por q no ciera
                    break;
            }

        } while (opcionMenu != 2);
    }
}