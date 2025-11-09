package logicLayer;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String telefono;

	public Cliente(String nombre, String mail, String pin, String telefono) {
		super(nombre, TipoUsuario.Cliente, mail, pin);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	

   
   // @Override
	//public void Menu() {
		//int opcion=JOptionPane.showOptionDialog(null, "Menu Cliente","",0,0,null, this.getTipoUsuario().getOpciones(),this.getTipoUsuario().getOpciones());
	
    @Override
	public String toString() {
		return "Telefono=" + telefono + ", nombre=" + nombre + ", mail=" + mail;
	}

	@Override
    public void Menu() {
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                null,
                "Menú Cliente",
                "",
                0,0,
                null,
                this.getTipoUsuario().getOpciones(),
                this.getTipoUsuario().getOpciones()[0]
            );

            // Busca la cuenta del cliente logueado
            Cuenta cuenta = buscarCuentaPorMail(getMail());

            switch (opcion) {
                case 0: // Depositar dinero
                	Cajero cajeroSeleccionado = Cajero.elegirCajeroDisponible();
                	if (cajeroSeleccionado != null) {
                	    double montoDep = Validar.validarNumero("Monto a depositar:");
                	    cuenta.depositar(montoDep, cajeroSeleccionado);
                	}

                    break;

                case 1: // Transferir dinero
                	String cbuATransferir = String.valueOf(Validar.validarNumero("Ingrese el CBU a transferir:"));
                    Cuenta aTransferir = null;

                    // Buscar cuenta destino
                    for (Cuenta c : Cuenta.getCuentas()) {
                        if (c.getCbu().equals(cbuATransferir)) {
                            aTransferir = c;
                            break;
                        }
                    }

                    if (aTransferir == null) {
                        JOptionPane.showMessageDialog(null, "CBU no encontrado");
                    } 
                    // Evitar transferencias a la misma cuenta
                    else if (aTransferir.getCbu().equals(cuenta.getCbu())) {
                        JOptionPane.showMessageDialog(null, "No puedes transferirte dinero a tu propia cuenta.");
                    } 
                    else {
                        double montoTransf = Validar.validarNumero("Ingrese el monto a transferir a " + aTransferir.getCliente().getNombre() + ":");
                        cuenta.transferencia(aTransferir, montoTransf);

                    }
                    break;

                case 2: // Retirar dinero
                	
                	Cajero seleccionado = Cajero.elegirCajeroRecibir();
                	if(seleccionado !=null) {
                	cuenta.retirar(seleccionado, Validar.validarNumero("ingrese monto"));
                	}
                	
                
                    //double montoRet = Validar.validarNumero("Monto a retirar:");
                    ///cuenta.retirar(Cajero corrientes,montoRet);
                    break;
                case 3: // Solicitar préstamo
                    Cajero cajeroPrestamo = Cajero.elegirCajeroRecibir();
                    if (cajeroPrestamo != null) {
                        cuenta.solicitarPrestamo(cajeroPrestamo);
                    }
                    break;
                    
                case 4: //pagar servicios
                	cuenta.pagarServicio();
                	
                case 5: //cambiar dolares
                	cuenta.cambiarDolares();

                case 6: // Ver saldo
                    JOptionPane.showMessageDialog(null, "Saldo actual: $" + cuenta.getSaldo());
                    break;

                case 7: // Ver movimientos
                   // cuenta.mostrarHistorial();
	                JOptionPane.showMessageDialog(null, cuenta.getMovimientos().isEmpty()?"No hay movimientos":cuenta.getMovimientos());


                    break;

                case 8: // Ver información 
                    JOptionPane.showMessageDialog(null, toString());
                    break;

                case 9: // Salir
                    JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
            }

        } while (opcion != 9);
    }

    private Cuenta buscarCuentaPorMail(String mailCliente) {
        for (Cuenta c : Cuenta.getCuentas()) {
            if (c.getCliente().getMail().equals(mailCliente)) {
                return c;
            }
        }
        return null;
    }
}



	
	
	

	
	

