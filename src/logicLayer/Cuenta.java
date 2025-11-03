package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private double saldo;
	private LinkedList<Movimiento>movimientos;
	private static LinkedList<Cuenta>cuentas = new LinkedList<>();
	public Cuenta(String cbu, Cliente cliente, double saldo) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.movimientos = new LinkedList<Movimiento>();
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
	
	
	
	public static int validarNumero(String mensaje) {	
		boolean flag;
		String ingreso;
		int numero;
		do {
			flag = true;
			do {
				ingreso = JOptionPane.showInputDialog(mensaje);
			} while (ingreso.isEmpty());
			for (int i = 0; i < ingreso.length(); i++) {
				//si caracter no es un digito
				if (!Character.isDigit(ingreso.charAt(i))) {
					flag = false;
					break;
				}
			}
	        if (flag) {
	            numero = Integer.parseInt(ingreso);
	            if (numero < 0) {
	                flag = false;
	            }
	        }
	        
		} while (flag==false);
		return Integer.parseInt(ingreso);
	}
	
	public static String validarLetras(String mensaje) {	
		boolean flag;
		String ingreso;
		do {
			flag = true;
				ingreso = JOptionPane.showInputDialog(mensaje);
				
		        if (ingreso.isEmpty()) {
		            flag = false;
		        } else {
		            for (int i = 0; i < ingreso.length(); i++) {
		                if (!Character.isAlphabetic(ingreso.charAt(i))) {
		                    flag = false;
		                    break;
		                }
		            }
		        }
		    
		} while (flag==false);
		return ingreso;
		}
	
	public static String validarCampo(String mensaje) {
		boolean flag;
		String ingreso;
		do {
			flag = true;
				ingreso = JOptionPane.showInputDialog(mensaje);
				
		        if (ingreso.isEmpty()) {
		       
		            flag = false;
		        }
		      
		    
		} while (flag==false);
		return ingreso;
		
		
	}
	private static String generarCbu() {
	    int numero = 1000000 + (int)(Math.random() * 1000000);
	    return String.valueOf(numero);
	}
	
	public static void registrarse() {
	    String nombre = validarLetras("Ingresar nombre:");
	    String mail = validarCampo("Ingresar mail:");
	    String pin = String.valueOf(validarNumero("Ingresar PIN:"));
	    String telefono = String.valueOf(validarNumero("Ingresar teléfono:"));

	    Cliente nuevoCliente = new Cliente(nombre, TipoUsuario.CLIENTE, mail, pin, telefono);

	    String cbu = generarCbu();
	    
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
	// operaciones basicas del cajero
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            movimientos.add(new Movimiento("Depósito", monto));
            JOptionPane.showMessageDialog(null, "Se depositaron $" + monto);
        } else {
            JOptionPane.showMessageDialog(null, "Monto inválido");
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            movimientos.add(new Movimiento("Retiro", monto));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente o monto inválido");
            return false;
        }
    }

    public void mostrarHistorial() {
        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos");
        } else {
            StringBuilder sb = new StringBuilder("=== Movimientos ===\n");
            for (Movimiento m : movimientos) {
                sb.append(m.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
    
    @Override
    public String toString() {
        return "Cuenta CBU: " + cbu + " | Cliente: " + cliente.getNombre() + " | Saldo: $" + saldo;
    }
}
	
	
	
	
	


