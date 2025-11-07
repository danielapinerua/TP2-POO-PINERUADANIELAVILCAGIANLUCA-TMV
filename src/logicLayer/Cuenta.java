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

	public void retirar(Cajero cajero, double monto) {
	    boolean hayError = false;

	    if (cajero.isEstado() == false) {
	        JOptionPane.showMessageDialog(null, "El cajero de " + cajero.getUbicacion() + " no funciona.");
	        hayError = true;
	    }

	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
	        hayError = true;
	    }

	    if (monto > saldo) {
	        JOptionPane.showMessageDialog(null, "Saldo insuficiente en la cuenta. Tu saldo actual es de $" + this.saldo);
	        hayError = true;
	    }

	    if (cajero.getSaldo() < monto) {
	        JOptionPane.showMessageDialog(null, "No hay dinero suficiente en el cajero (saldo disponible: $" + cajero.getSaldo() + ")");
	        hayError = true;
	    }

	    if (hayError) {
	        return; // si hubo al menos un error, no hace el retiro
	    }

	    // Si todo está bien:
	    this.saldo -= monto;
	    cajero.setSaldo(cajero.getSaldo() - monto);

	    Movimiento mov = new Movimiento("Retiro", monto, cliente, cajero);
	    this.movimientos.add(mov);
	    Empleado.getMovimientosGenerales().add(mov);

	    JOptionPane.showMessageDialog(null, "Retiro exitoso! Retiraste: $" + monto);
	}
	
	
	public void solicitarPrestamo(Cajero cajero) {
	    double monto = Validar.validarNumero("Ingrese el monto del préstamo:");
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
	        return;
	    }

	    if (cajero.getSaldo() < monto) {
	        JOptionPane.showMessageDialog(null, "El cajero no tiene suficiente dinero para este préstamo.");
	        return;
	    }

	    String[] cuotasOpciones = {"3 cuotas (10% interés)", "6 cuotas (20% interés)", "12 cuotas (40% interés)"};
	    int elegido = JOptionPane.showOptionDialog(
	        null,
	        "Seleccione el plan de cuotas:",
	        "Opciones de préstamo",
	        0, 0, null,
	        cuotasOpciones,
	        cuotasOpciones[0]
	    );

	    double interes = 0;
	    int cuotas = 0;

	    switch (elegido) {
	        case 0: 
	        	interes = 0.10; 
	        	cuotas = 3;
	        break;
	        case 1: 
	        	interes = 0.20; 
	        	cuotas = 6;
	        	break;
	        case 2:
	        	interes = 0.40; 
	        	cuotas = 12;
	        	break;
	        default:
	            JOptionPane.showMessageDialog(null, "Operación cancelada.");
	            return;
	    }

	    double montoTotal = monto + (monto * interes);
	    double valorCuota = montoTotal / cuotas;

	    int confirmar = JOptionPane.showConfirmDialog(
	        null,
	        "Préstamo de $" + monto +
	        "\nInterés: " + (interes * 100) + "%" +
	        "\nTotal a devolver: $" + montoTotal +
	        "\n" + cuotas + " cuotas de $" + String.format("%.2f", valorCuota) +
	        "\n¿Desea confirmar el préstamo?",
	        "Confirmar préstamo",
	        JOptionPane.YES_NO_OPTION
	    );

	    if (confirmar == JOptionPane.YES_OPTION) {
	        this.saldo += monto;
	        cajero.setSaldo(cajero.getSaldo() - monto);

	        Movimiento mov = new Movimiento("Préstamo", monto, cliente, cajero);
	        this.movimientos.add(mov);
	        Empleado.getMovimientosGenerales().add(mov);

	        JOptionPane.showMessageDialog(null, "Préstamo acreditado exitosamente.\nNuevo saldo: $" + this.saldo);
	    } else {
	        JOptionPane.showMessageDialog(null, "Préstamo cancelado.");
	    }
	}
    
    @Override
    public String toString() {
        return "Cuenta CBU: " + cbu + " | Cliente: " + cliente.getNombre() + " | Saldo: $" + saldo;
    }
}

	
	
	
	


