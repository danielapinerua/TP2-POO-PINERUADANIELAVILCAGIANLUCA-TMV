package logicLayer;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private double saldo;
    private LinkedList<Movimiento> movimientos = new LinkedList<>();
    private static LinkedList<Cuenta> cuentas = new LinkedList<>();
    

	 public Cuenta(String cbu, Cliente cliente, double saldo, LinkedList<Movimiento> movimientos) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.movimientos = movimientos;
	}
	 
	 
	 public String getCbu() {
		return cbu;
	}

	 public void setCbu(String cbu) {
		 this.cbu = cbu;
	 }

	 public Cliente getCliente() {
		 return cliente;
	 }

	 public void setCliente(Cliente cliente) {
		 this.cliente = cliente;
	 }

	 public double getSaldo() {
		 return saldo;
	 }

	 public void setSaldo(double saldo) {
		 this.saldo = saldo;
	 }


	 public LinkedList<Movimiento> getMovimientos() {
		 return movimientos;
	 }

	 public void setMovimientos(LinkedList<Movimiento> movimientos) {
		 this.movimientos = movimientos;
	 }

	 public static LinkedList<Cuenta> getCuentas() {
		 return cuentas;
	 }

	 public static void setCuentas(LinkedList<Cuenta> cuentas) {
		 Cuenta.cuentas = cuentas;
	 }

	 public static void Registrarse() {
		 String nombre = JOptionPane.showInputDialog("Ingresar nombre");
	     String mail = JOptionPane.showInputDialog("Ingresar mail");
	     String pin = JOptionPane.showInputDialog("Ingresar pin");
	    	
	 }
	
	
    
    
}
