package logicLayer;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String telefono;
	private CuentaInversion cuentaInversion;  //le puse atributo 

	public Cliente(String nombre, String mail, String pin, String telefono) {
		super(nombre, TipoUsuario.Cliente, mail, pin);
		this.telefono = telefono;
		this.cuentaInversion = new CuentaInversion();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public CuentaInversion getCuentaInversion() {
		return cuentaInversion;
	}

	public void setCuentaInversion(CuentaInversion cuentaInversion) {
		this.cuentaInversion = cuentaInversion;
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
                	
                    break;
                    
                case 3: // Solicitar préstamo
                    Cajero cajeroPrestamo = Cajero.elegirCajeroRecibir();
                    if (cajeroPrestamo != null) {
                        cuenta.solicitarPrestamo(cajeroPrestamo);
                    }
                    break;
                    
                case 4: //pagar servicios
                	cuenta.pagarServicio();
                	break;
                	
                case 5: //cambiar dolares
                	cuenta.cambiarDolares();
                	break;
                	
                case 6://inversiones
                	menuInversiones();
                	break;
                	
                case 7: //cambiar pin
                	cambiarPin();
                	break;
                	
                case 8: // Ver saldo
                    JOptionPane.showMessageDialog(null, "Saldo actual en pesos: $" + cuenta.getSaldo() + "\nSaldo actual en dólares: " + String.format("%.2f", cuenta.getSaldoDolares()) + " USD");
                    break;

                case 9: // Ver movimientos
                  cuenta.verMovimientos();
                    break;

                case 10: // Ver información 
                    JOptionPane.showMessageDialog(null, toString());
                    break;

                case 11: // Salir
                    JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
            }

        } while (opcion != 11);
    }

    @Override
	public String toString() {
		return "telefono=" + telefono + ", nombre=" + nombre + ", tipo de usuario=" + tipoUsuario + ", mail="
				+ mail ;
	}

	public Cuenta buscarCuentaPorMail(String mailCliente) {
        for (Cuenta c : Cuenta.getCuentas()) {
            if (c.getCliente().getMail().equals(mailCliente)) {
                return c;
            }
        }
        return null;
    }
    
	public void menuInversiones() {
	    String[] opciones = {
	        "Depositar en inversión",
	        "Simular un día",
	        "Ver historial",
	        "Salir"
	    };

	    int opcion;
	    do {
	        opcion = JOptionPane.showOptionDialog(
	            null,
	            "MENÚ DE INVERSIÓN",
	            "",
	            0, 0, null,
	            opciones,
	            opciones[0]
	        );

	        switch(opcion) {
	            case 0:
	                double monto = Validar.validarNumero("Ingrese monto a invertir:");
	                cuentaInversion.invertir(monto);
	                break;

	            case 1:
	                cuentaInversion.simularDia();
	                break;

	            case 2:
	                cuentaInversion.verHistorial();
	                break;
	                
	            case 3:
	            	JOptionPane.showMessageDialog(null, "Redirigiendo al menu");
	            	break;
	        }

	    } while (opcion != 3);
	}
    
    
}



	
	
	

	
	

