package userLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import logicLayer.Cajero;
import logicLayer.Cliente;
import logicLayer.Cuenta;
import logicLayer.Empleado;
import logicLayer.TipoUsuario;
import logicLayer.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cajero cajero = new Cajero(500000.00);
		//agregue cuentas creadas
		Cuenta cuenta1 = new Cuenta("10000001", new Cliente("Daniela", TipoUsuario.Cliente, "daniela@mail.com", "1111", "123456789"), 5000);
		Cuenta cuenta2 = new Cuenta("10000002", new Cliente("Lucas", TipoUsuario.Cliente, "lucas@mail.com", "2222", "987654321"), 8000);

		Cuenta.getCuentas().add(cuenta1);
		Cuenta.getCuentas().add(cuenta2);

		cajero.agregarCuenta(cuenta1);
		cajero.agregarCuenta(cuenta2);
		//empleados creados
		Empleado.getEmpleados().add(new Empleado("Gianluca", TipoUsuario.Empleado, "gvilca@gmail.com", "1234", "L001"));
		Empleado.getEmpleados().add(new Empleado("Paula", TipoUsuario.Empleado, "paula@gmail.com", "12345", "L002"));
		Empleado.getEmpleados().add(new Empleado("Christian", TipoUsuario.Empleado, "christian@mgail.com", "123456", "L003"));
		Empleado.getEmpleados().add(new Empleado("Oriana", TipoUsuario.Empleado, "oriana@gmail.com", "1234567", "L004"));
		Empleado.getEmpleados().add(new Empleado("Francisco", TipoUsuario.Empleado, "francisco@gmail.com", "12345678", "L005"));




		
        int opcionSalir;
        do {
            // Elegir tipo de usuario (del enum)
            TipoUsuario tipoElegido = TipoUsuario.elegirTipo();

            if (tipoElegido == null) break; // si cierra el menú, se sale

            switch (tipoElegido) {

                case Empleado:
                	String mailEmp = Cuenta.validarCampo("Mail del empleado:");
                	String pinEmp = Cuenta.validarCampo("PIN del empleado:");

                	Empleado empleadoLogueado = Usuario.login(mailEmp, pinEmp);

                	if (empleadoLogueado == null) {
                	    JOptionPane.showMessageDialog(null, "Empleado no encontrado o datos incorrectos");
                	} else {
                	    JOptionPane.showMessageDialog(null, "Ingresó como EMPLEADO");
                	    tipoElegido.mostrarMenu(); // muestra menú según enum
                	}
                    break;

                case Cliente:
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
                                String mailCli = Cuenta.validarCampo("Mail del cliente:");
                                String pinCli = Cuenta.validarCampo("PIN del cliente:");

                                Cuenta cuentaLogueada = Usuario.login(mailCli, pinCli);

                                if (cuentaLogueada == null) {
                                    JOptionPane.showMessageDialog(null, "Cliente no encontrado o datos incorrectos");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingresó como CLIENTE");
                                    cuentaLogueada.getCliente().getTipo().mostrarMenu(); 
                                    // muestra el menú definido en el enum TipoUsuario
                                }
                                break;

                            case 2: // Salir
                                break;
                        }

                    } while (opcionCliente != 2); 
                    break;
            }

            opcionSalir = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Continuar", JOptionPane.YES_NO_OPTION);

        } while (opcionSalir == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema bancario!");
    }
}
		
	
