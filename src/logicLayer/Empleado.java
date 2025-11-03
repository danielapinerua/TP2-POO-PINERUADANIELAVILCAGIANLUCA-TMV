package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;
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
	//public static LinkedList<Empleado> getEmpleados() {
		//return empleados;
	//}
	//public static void setEmpleados(LinkedList<Empleado> empleados) {
	//	Empleado.empleados = empleados;
	//}


	
    
   // public static Empleado login (String email, String contrasenia) {
	//	for(Empleado empleado: empleados) {
	//		if(empleado.getMail().equals(email) && empleado.getPin().equals(contrasenia)) {
	//			return empleado;
		//	}
		//}
		//return null;
	//}
	
	 @Override
		public void Menu() {
			int opcion=JOptionPane.showOptionDialog(null, "Menu empleado","",0,0,null, this.getTipoUsuario().getOpciones(),this.getTipoUsuario().getOpciones());
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
