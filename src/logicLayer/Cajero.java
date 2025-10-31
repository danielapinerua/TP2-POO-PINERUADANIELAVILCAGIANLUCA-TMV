package logicLayer;

import java.util.LinkedList;

public class Cajero {
	private double dineroDisponible;
	private LinkedList<Cuenta>cuentas;
	public Cajero(double dineroDisponible, LinkedList<Cuenta> cuentas) {
		super();
		this.dineroDisponible = dineroDisponible;
		this.cuentas = cuentas;
	}
	public double getDineroDisponible() {
		return dineroDisponible;
	}
	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}
	public LinkedList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(LinkedList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	@Override
	public String toString() {
		return "Cajero [dineroDisponible=" + dineroDisponible + ", cuentas=" + cuentas + "]";
	}
	
	
}
