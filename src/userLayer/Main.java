package userLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import logicLayer.Cajero;
import logicLayer.Cuenta;
import logicLayer.Empleado;
import logicLayer.TipoUsuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cajero cajero = new Cajero(500000.00);
		
		LinkedList<Empleado> empleados = new LinkedList<>();
        empleados.add(new Empleado("Gianluca", TipoUsuario.EMPLEADO, "gvilca@mail.com", "1234", "L001"));

        int opcionSalir;
        do {
            // 🔹 Elegir tipo de usuario (del enum)
            TipoUsuario tipoElegido = TipoUsuario.elegirTipo();

            if (tipoElegido == null) break; // si cierra el menú, se sale

            switch (tipoElegido) {

                case EMPLEADO:
                	
                    String mailEmp = JOptionPane.showInputDialog("Mail del empleado:");
                    String pinEmp = JOptionPane.showInputDialog("PIN del empleado:");

                    // 🔹 Buscar empleado
                    Empleado empleadoLogueado = null;
                    for (Empleado empleado : empleados) {
                        if (empleado.getMail().equals(mailEmp) && empleado.getPin().equals(pinEmp)) {
                            empleadoLogueado = empleado;
                            break;
                        }
                    }

                    if (empleadoLogueado == null) {
                        JOptionPane.showMessageDialog(null, "Empleado no encontrado o datos incorrectos");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresó como EMPLEADO");
                        tipoElegido.mostrarMenu(); // 🔹 muestra el menú del enum
                    }
                    break;

                case CLIENTE:
                	String[] opcionesCliente = {"Registrarse", "Iniciar sesión", "Salir"};
                    int opcionCliente;

                    do {
                        opcionCliente = JOptionPane.showOptionDialog(
                                null,
                                "Seleccione una opción:",
                                "Menú Cliente",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                opcionesCliente,
                                opcionesCliente[0]
                        );

                        switch (opcionCliente) {
                            case 0: // Registrarse
                                Cuenta.registrarse();
                                break;

                            case 1: // Iniciar sesión
                                String mailCli = JOptionPane.showInputDialog("Mail del cliente:");
                                String pinCli = JOptionPane.showInputDialog("PIN del cliente:");

                                Cuenta cuentaLogueada = Cuenta.login(mailCli, pinCli);

                                if (cuentaLogueada == null) {
                                    JOptionPane.showMessageDialog(null, "Cliente no encontrado o datos incorrectos");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingresó como CLIENTE");
                                    cuentaLogueada.getCliente().getTipo().mostrarMenu(); 
                                    // muestra el menú definido en el enum TipoUsuario
                                }
                                break;

                            case 2: // Salir
                                JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                                break;
                        }

                    } while (opcionCliente != 2); // repetir hasta que elija "Salir"

                    break;
            }

            opcionSalir = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Continuar", JOptionPane.YES_NO_OPTION);

        } while (opcionSalir == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema bancario!");
    }
}
		
	
