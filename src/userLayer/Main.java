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
		Cliente c1 = new Cliente("Daniela", "daniela@mail.com", "1111", "123456789");
        Cliente c2 = new Cliente("Lucas", "lucas@mail.com", "2222", "987654321");
        Cliente c3 = new Cliente("Sofia", "sofia@mail.com", "3333", "234567890");
        Empleado e1 = new Empleado("Gianluca", "gvilca@mail.com", "1234", "L001");
        Empleado e2 = new Empleado("Paula", "paula@gmail.com", "12345", "L002");
        Empleado e3 = new Empleado("Oriana", "oriana@gmail.com", "123456", "L003");

        Usuario.getUsuarios().add(c1);
        Usuario.getUsuarios().add(c2);
        Usuario.getUsuarios().add(c3);
        Usuario.getUsuarios().add(e1);
        Usuario.getUsuarios().add(e2);
        Usuario.getUsuarios().add(e3);
        
        //                                    CBU
        Cuenta.getCuentas().add(new Cuenta("10000001", c1, 5000));
        Cuenta.getCuentas().add(new Cuenta("10000002", c2, 8000));
        Cuenta.getCuentas().add(new Cuenta("10000003", c3, 8000));

        int opcionSalir;
        do {
        	
            TipoUsuario tipoElegido = TipoUsuario.elegirTipo();
           if (tipoElegido == null)
            	break;

            switch (tipoElegido) {
                case Empleado:
                    String mailEmp = Cuenta.validarCampo("Mail del empleado:");
                    String pinEmp = Cuenta.validarCampo("PIN del empleado:");
                    Usuario empleado = Usuario.login(mailEmp, pinEmp);
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
                            	
                                String mailCli = Cuenta.validarCampo("Mail del cliente:");
                                String pinCli = Cuenta.validarCampo("PIN del cliente:");
                                Usuario cliente = Usuario.login(mailCli, pinCli);
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
            }

            opcionSalir = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Continuar", JOptionPane.YES_NO_OPTION);
        } while (opcionSalir == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema bancario!");
    }
}

		
	
