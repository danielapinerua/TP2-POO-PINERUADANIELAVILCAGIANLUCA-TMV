package logicLayer;

import javax.swing.JOptionPane;

public class Cliente extends Usuario{
	private String telefono;

	public Cliente(String nombre, TipoUsuario tipo, String mail, String pin, String telefono) {
		super(nombre, tipo, mail, pin);
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	 // Menú del cliente
    public void mostrarMenu() {
        String[] opciones = {"Depositar dinero", "Retirar dinero", "Ver saldo", "Historial", "Salir"};

        // Buscamos la cuenta asociada
        Cuenta cuenta = Cuenta.getCuentas().stream()
                .filter(c -> c.getCliente() == this)
                .findFirst().orElse(null);

        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "Ups, no se encontró tu cuenta!");
            return;
        }

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione opción",
                    "Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == -1) break;

            switch (opcion) {
                case 0: // deposito cash
                    try {
                        double dep = Double.parseDouble(JOptionPane.showInputDialog("Monto a depositar:"));
                        cuenta.depositar(dep);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Monto inválido!");
                    }
                    break;

                case 1: // retiramos la guita
                    try {
                        double ret = Double.parseDouble(JOptionPane.showInputDialog("Monto a retirar:"));
                        cuenta.retirar(ret);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Monto inválido!");
                    }
                    break;

                case 2: // miramos el saldo
                    JOptionPane.showMessageDialog(null, "Saldo: $" + cuenta.getSaldo());
                    break;

                case 3: // historial
                    cuenta.mostrarHistorial();
                    break;

                // case 4 = Salir, no hacemos nada, esto aca tenemos q aplicarlos a los otros joptions por q tira error invalido o algo asi hay q analizarlo
            }
        } while (opcion != 4);
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " | Tel: " + telefono + " | Email: " + getMail();
    }
}
}
	
	
	

	
	
}
