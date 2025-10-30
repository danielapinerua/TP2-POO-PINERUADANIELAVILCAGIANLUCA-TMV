package logicLayer;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;

	public Empleado(String nombre, TipoUsuario tipo, String legajo) {
		super(nombre, tipo);
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	

	
	
	 	
	public void ingresarLegajo() {
	    String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
	    String legajo = JOptionPane.showInputDialog("Ingrese número de legajo:");

	   
	}
		
	


	
	

}
