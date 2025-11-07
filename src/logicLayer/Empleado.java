package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;
	private static LinkedList<Movimiento> movimientosGenerales = new LinkedList<Movimiento>();
	//private static LinkedList<Empleado>empleados = new LinkedList<>();


	public Empleado(String nombre, String mail, String pin, String legajo) {
		super(nombre, TipoUsuario.Empleado, mail, pin);
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	
	
	 //@Override
		//public void Menu() {
			//int opcion=JOptionPane.showOptionDialog(null, "Menu empleado","",0,0,null, this.getTipoUsuario().getOpciones(),this.getTipoUsuario().getOpciones());
		
	// }
	 	
	public static LinkedList<Movimiento> getMovimientosGenerales() {
		return movimientosGenerales;
	}

	public static void setMovimientosGenerales(LinkedList<Movimiento> movimientosGenerales) {
		Empleado.movimientosGenerales = movimientosGenerales;
	}

	@Override
	public void Menu() {
	    // Generar un cajero con dinero aleatorio
	    double dineroAleatorio =  Math.random() * 1000000;
	    Cajero cajero = new Cajero(dineroAleatorio);

	    int opcion;
	    do {
	        opcion = JOptionPane.showOptionDialog(
	            null,
	            "Menú Empleado\nCajero actual: $" + cajero.getDineroDisponible(),
	            "Empleado",
	            0,0,
	            null,
	            this.getTipoUsuario().getOpciones(),
	            this.getTipoUsuario().getOpciones()[0]
	        );

	        switch (opcion) {
	            case 0: // Ver cuentas
	                verCuentas(cajero);
	                break;

	            case 1: // Cargar dinero
	                double monto = Validar.validarNumero("Monto a cargar:");
	                cargarDinero(cajero, monto);
	                JOptionPane.showMessageDialog(null, "Cargaste: " + monto);
	                break;

	            case 2: // Ver información del empleado
	                JOptionPane.showMessageDialog(null, movimientosGenerales.isEmpty()?"No hay movimientos":movimientosGenerales);
	                break;

	            case 3: // dar de baja cajero
	                JOptionPane.showMessageDialog(null, "Se dio de abja el cajero");
	            	
	                break;
	            case 4: // ver informacion del empleado
	                JOptionPane.showMessageDialog(null, toString());
	                break;
	            case 5 ://salir
	            	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
	        }
	    } while (opcion != 5);
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
