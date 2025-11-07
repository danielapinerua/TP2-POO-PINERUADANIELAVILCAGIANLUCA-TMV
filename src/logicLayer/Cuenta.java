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
	
	
	
	
	private static String generarCbu() {
	    int numero = 1000000 + cuentas.size();
	    return String.valueOf(numero);
	}
	
	public static void registrarse() {
        String nombre = Validar.validarLetras("Ingresar nombre:");
        String mail = Validar.validarCampo("Ingresar mail:");
        String pin = Validar.validarCampo("Ingresar PIN:");
        String telefono = String.valueOf(Validar.validarNumero("Ingresar teléfono:"));

        Cliente nuevoCliente = new Cliente(nombre, mail, pin, telefono);
        Usuario.getUsuarios().add(nuevoCliente); // Se agrega a la lista general de usuarios

        String cbu = generarCbu();
        double saldo = (int)(Math.random() * 10000);

        Cuenta nuevaCuenta = new Cuenta(cbu, nuevoCliente, saldo);
        cuentas.add(nuevaCuenta);

        JOptionPane.showMessageDialog(null,
            "Registro exitoso.\nCliente: " + nombre +
            "\nCBU asignado: " + cbu +
            "\nSaldo inicial: $" + saldo);
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

	   
	    this.movimientos.add(new Movimiento("Transferencia enviada",monto,cliente));
	    aTransferir.movimientos.add(new Movimiento("Transferencia recibida", monto, aTransferir.cliente));
        Empleado.getMovimientosGenerales().add(new Movimiento("Transferencia", monto, cliente));


	    JOptionPane.showMessageDialog(null, 
	        "Transferencia realizada a " + aTransferir.getCliente().getNombre() +
	        " por $" + monto);
	}

	public void depositar(double monto, Cajero cajero) {
	    if (monto > 0) {
	        this.saldo += monto;
	        cajero.setSaldo(cajero.getSaldo() + monto); // <--- suma al cajero
	        Movimiento mov = new Movimiento("Depósito", monto, cliente, cajero);
	        this.movimientos.add(mov);
	        Empleado.getMovimientosGenerales().add(mov);
	        JOptionPane.showMessageDialog(null, "Se depositaron $" + monto);
	    } else {
	        JOptionPane.showMessageDialog(null, "Monto inválido");
	    }
	}

    public boolean retirar(Cajero cajero, double monto) {
        if (cajero.isEstado()== false) {
            JOptionPane.showMessageDialog(null, "El cajero de " + cajero.getUbicacion() + " no funciona");
            return false;
        }

        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
            return false;
        }

        if (monto > saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente en la cuenta. Tu saldo actual es de $" + this.saldo);
            return false;
        }

        if (cajero.getSaldo() < monto) {
            JOptionPane.showMessageDialog(null, "No hay dinero suficiente en el cajero.");
            return false;
        }

        // Si todo está bien:
        this.saldo -= monto;
        cajero.setSaldo(cajero.getSaldo() - monto);

        Movimiento mov = new Movimiento("Retiro", monto, cliente, cajero);
        this.movimientos.add(mov);
        Empleado.getMovimientosGenerales().add(mov);

        JOptionPane.showMessageDialog(null, "Retiro exitoso! Retiraste: $" + monto);
        return true;
    }

  /*  public void mostrarHistorial() {
        if (this.movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos");
        } else {
            StringBuilder sb = new StringBuilder("=== Movimientos ===\n");
            for (Movimiento m : this.movimientos) {
                sb.append(m.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
    */
    
    @Override
    public String toString() {
        return "Cuenta CBU: " + cbu + " | Cliente: " + cliente.getNombre() + " | Saldo: $" + saldo;
    }
}

	
	
	
	


