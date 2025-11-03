package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;
	private static LinkedList<Empleado>empleados = new LinkedList<>();


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
	public static LinkedList<Empleado> getEmpleados() {
		return empleados;
	}
	public static void setEmpleados(LinkedList<Empleado> empleados) {
		Empleado.empleados = empleados;
	}
	//habria que crear lista de empleados usando add 

	
    
    public static Empleado login (String email, String contrasenia) {
		for(Empleado empleado: empleados) {
			if(empleado.getMail().equals(email) && empleado.getPin().equals(contrasenia)) {
				return empleado;
			}
		}
		return null;
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
