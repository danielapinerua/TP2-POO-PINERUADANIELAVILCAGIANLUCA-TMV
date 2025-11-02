package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;


	public Empleado(String nombre, TipoUsuario tipo, String mail, String pin, String legajo) {
		super(nombre, tipo, mail, pin);
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	
	// meun del empleado propio se invoca en el main
    public void mostrarMenu(Cajero cajero) {
        String[] opciones = {"Ver cuentas", "Cargar dinero al cajero", "Salir"};
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione opción",
                    "Empleado",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0: // aca vemos las cuentas
                    verCuentas(cajero);
                    break;
                case 1: // aca le cargamos los dolares
                    double monto = Double.parseDouble(JOptionPane.showInputDialog("Monto a cargar:"));
                    cargarDinero(cajero, monto);
                    break;
            }
        } while (opcion != 2);
    }

	
	
	 	
	// ver las cuentas q existen
    public void verCuentas(Cajero cajero) {
        LinkedList<Cuenta> cuentas = Cuenta.getCuentas();
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Lista de cuentas ===\n");
        for (Cuenta c : cuentas) {
            sb.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // la funcion q hace q cargen los verdessss
    public void cargarDinero(Cajero cajero, double monto) {
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido");
            return;
        }
        cajero.setDineroDisponible(cajero.getDineroDisponible() + monto);
        JOptionPane.showMessageDialog(null, "Cajero recargado. Total: $" + cajero.getDineroDisponible());
    }

    @Override
    public String toString() {
        return "Empleado: " + getNombre() + " | Legajo: " + legajo;
    }

		
	


	
	

}
