package userLayer;

import javax.swing.JOptionPane;
import logicLayer.Cajero;
import logicLayer.Cuenta;
import logicLayer.TipoUsuario;
import logicLayer.Usuario;
import logicLayer.Validar;

public class Main {

	public static void main(String[] args) {
		
		Cajero.cargaInicial();
		Usuario.cargaInicial();
		Cuenta.cargaInicial();

        int opcionSalir;
        do {
        	
            TipoUsuario tipoElegido = TipoUsuario.elegirTipo();
           if (tipoElegido == null)
            	break;

            switch (tipoElegido) {
                case Empleado:
                	String mailEmp = Validar.validarCampo("Mail del empleado:");
                	String pinEmp = Validar.validarCampo("PIN del empleado:");
                	Usuario empleado = Usuario.login(mailEmp, pinEmp, TipoUsuario.Empleado);
                    if (empleado == null) {
                        JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    } else {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + empleado.getNombre());
                        empleado.Menu();
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
                            case 0:
                            	Cuenta.registrarse();
                            	break;
                            case 1:
                            	String mailCli = Validar.validarCampo("Mail del cliente:");
                            	String pinCli = Validar.validarCampo("PIN del cliente:");
                            	Usuario cliente = Usuario.login(mailCli, pinCli, TipoUsuario.Cliente);
                                if (cliente == null) {
                                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bienvenido " + cliente.getNombre());
                                    cliente.Menu();
                                }
                                break;
                            
                        case 2:
                        	break;
                        }
                        } while (opcionCliente != 2);
                    break;
                    
                case Administrador: 
                	String mailAdmin = Validar.validarCampo("Mail del administrador:");
                	String pinAdmin = Validar.validarCampo("PIN del administrador:");
                	Usuario administrador = Usuario.login(mailAdmin, pinAdmin, TipoUsuario.Administrador);
                    if (administrador == null) {
                        JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    } else {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + administrador.getNombre());
                        administrador.Menu();
                    }
                    break;
            }

            opcionSalir = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Continuar", JOptionPane.YES_NO_OPTION);
        } while (opcionSalir == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema bancario!");
    }
}

		
	
