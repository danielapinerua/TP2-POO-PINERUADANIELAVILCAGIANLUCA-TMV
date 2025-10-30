package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private int cbu;
	private Cliente cliente;
	private double saldo;
	private LinkedList<Movimiento>movimientos;
	private static LinkedList<Cuenta>cuentas;
	public Cuenta(int cbu, Cliente cliente, double saldo) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.movimientos = new LinkedList<Movimiento>();
	}
	
	public int getCbu() {
		return cbu;
	}
	public void setCbu(int cbu) {
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
	
	
	
	private static int generarCbu() {
	    int numero = 10000000 + cuentas.size() + 1; 
	    return numero;
	}
	
	public static void registrarse() {
	    String nombre = JOptionPane.showInputDialog("Ingresar nombre:");
	    String mail = JOptionPane.showInputDialog("Ingresar mail:");
	    String pin = JOptionPane.showInputDialog("Ingresar pin:");
	    String telefono = JOptionPane.showInputDialog("Ingresar teléfono:");
	    
	    Cliente nuevoCliente = new Cliente(nombre, TipoUsuario.CLIENTE, mail, pin, telefono);
	    
	    
	    int cbu = generarCbu();
	    
	    // Generar saldo aleatorio
	    double saldo = (int) (Math.random() * 10000);
	  
	    Cuenta nuevaCuenta = new Cuenta(cbu, nuevoCliente, saldo);
	    
	   
	    cuentas.add(nuevaCuenta);
	    
	    JOptionPane.showMessageDialog(null, 
	        "Registro exitoso.\nCliente: " + nombre + 
	        "\nCBU asignado: " + cbu + 
	        "\nSaldo inicial: $" + saldo);
	}
	
	
	
	public static Cuenta login(String email, String contrasenia) {
		for(Cuenta cuenta : cuentas) {
			if(cuenta.getCliente().getMail().equals(email) && cuenta.getCliente().getPin().equals(contrasenia)) {
				return cuenta;
			}
		}
		return null;
	}
	
	public void transferencia(Cuenta aTransferir, double monto) {
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "Monto inválido");
	        return;
	    }

	    if (this.saldo < monto) {
	        JOptionPane.showMessageDialog(null, "Saldo insuficiente");
	        return;
	    }

	    this.saldo -= monto;
	    aTransferir.saldo += monto;

	   
	    this.movimientos.add(new Movimiento("Transferencia enviada",monto));
	    aTransferir.movimientos.add(new Movimiento("Transferencia recibida", monto));

	    JOptionPane.showMessageDialog(null, 
	        "Transferencia realizada a " + aTransferir.getCliente().getNombre() +
	        " por $" + monto);
	}
	
	
	
	
	

}
