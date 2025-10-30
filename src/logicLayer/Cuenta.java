package logicLayer;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private double saldo;
	private String mail;
	private String pin;
    private LinkedList<Movimiento> movimientos = new LinkedList<>();
    private static LinkedList<Cuenta> cuentas = new LinkedList<>();
    
	public Cuenta(String cbu, Cliente cliente, double saldo, String mail, String pin,
			LinkedList<Movimiento> movimientos) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.mail = mail;
		this.pin = pin;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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
