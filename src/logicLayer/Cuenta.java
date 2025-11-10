package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private double saldo;
	private double limiteCubierto;
	private LinkedList<Movimiento>movimientos;
	private static LinkedList<Cuenta>cuentas = new LinkedList<>();
	public Cuenta(String cbu, Cliente cliente, double saldo) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.limiteCubierto = 2000;
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
	    int numero = 1000 + cuentas.size();
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
        double saldo = 0;
        Cuenta nuevaCuenta = new Cuenta(cbu, nuevoCliente, saldo);
        cuentas.add(nuevaCuenta);
        JOptionPane.showMessageDialog(null,
            "Registro exitoso.\nCliente: " + nombre +
            "\nCBU asignado: " + cbu +
            "\nSaldo inicial: $" + saldo
            + "\nCBU: " + cbu);
	}
	
	
	
	public void transferencia(Cuenta aTransferir, double monto) {
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
	        return;
	    }

	    double saldoDisponible = this.saldo + this.limiteCubierto;

	    //  ni con el límite alcanza
	    if (monto > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }

	    // usa parte del límite cubierto
	    if (monto > this.saldo && monto <= saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Estás utilizando parte de tu límite cubierto para realizar esta transferencia.");
	    }
	    this.saldo -= monto;
	    aTransferir.saldo += monto;

	    this.movimientos.add(new Movimiento("Transferencia enviada", monto, cliente));
	    aTransferir.movimientos.add(new Movimiento("Transferencia recibida", monto, aTransferir.cliente));
	    Empleado.getMovimientosGenerales().add(new Movimiento("Transferencia", monto, cliente));

	    JOptionPane.showMessageDialog(null, 
	        "Transferencia realizada a " + aTransferir.getCliente().getNombre() +
	        " por $" + monto);
	}
	
	

	public void depositar(double monto, Cajero cajero) {
	    if (monto > 0) {
	        this.saldo += monto;
	        cajero.setSaldo(cajero.getSaldo() + monto); // suma al cajero
	        Movimiento mov = new Movimiento("Depósito", monto, cliente, cajero);
	        this.movimientos.add(mov);
	        Empleado.getMovimientosGenerales().add(mov);
	        JOptionPane.showMessageDialog(null, "Se depositaron $" + monto);
	    } else {
	        JOptionPane.showMessageDialog(null, "Monto inválido");
	    }
	}

	public void retirar(Cajero cajero, double monto) {
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero.");
	        return;
	    }
	    double saldoDisponible = this.saldo + this.limiteCubierto;
	    // el monto supera incluso el límite cubierto
	    if (monto > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }
	    // el monto es mayor al saldo, pero se cubre con el límite
	    if (monto > this.saldo && monto <= saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Estás utilizando parte de tu límite cubierto. Tu saldo quedará negativo después de este retiro.");
	    }

	    if (cajero.getSaldo() < monto) {
	        JOptionPane.showMessageDialog(null, "No hay dinero suficiente en el cajero.");
	        return;
	    }
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
	
	public void pagarServicio() {
	    String[] servicios = {"Internet", "Agua", "Luz", "Cargar SUBE"};
	    int servicioElegido = JOptionPane.showOptionDialog(
	        null,
	        "Seleccione el servicio que desea pagar:",
	        "Pagar servicio",
	        0, 0, null,
	        servicios,
	        servicios[0]
	    );
	    if (servicioElegido == -1) {
	        return; // si el usuario cancela
	    }
	    double monto = Validar.validarNumero("Ingrese el monto a pagar para " + servicios[servicioElegido] + ":");
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "Monto inválido.");
	        return;
	    }
	    double saldoDisponible = this.saldo + this.limiteCubierto;
	    if (monto > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, "Saldo insuficiente (incluyendo límite cubierto).");
	        return;
	    }
	    this.saldo -= monto;
	    Movimiento mov = new Movimiento("Pago de servicio: " + servicios[servicioElegido], monto, cliente);
	    this.movimientos.add(mov);
	    Empleado.getMovimientosGenerales().add(mov);
	    JOptionPane.showMessageDialog(null, 
	        "Pago de " + servicios[servicioElegido] + " realizado con éxito por $" + monto +
	        "\nSaldo actual: $" + this.saldo);
	}
	
	@Override
	public String toString() {
		return "cbu=" + cbu + ", cliente=" + cliente + ", saldo=" + saldo + ", limiteCubierto=" + limiteCubierto
				+ ", movimientos=" + movimientos ;
	}
    
	public void cambiarDolares() {
	    double cotizacion = 1500.50;
	    double pesos = Validar.validarNumero("Ingrese el monto en pesos que desea cambiar a dólares:");
	    if (pesos <= 0) {
	        JOptionPane.showMessageDialog(null, "Monto inválido.");
	        return;
	    }
	    double saldoDisponible = this.saldo + this.limiteCubierto;
	    if (pesos > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }
	    if (pesos > this.saldo) {
	        JOptionPane.showMessageDialog(null, 
	            "Usarás parte de tu límite cubierto para completar la operación.");
	    }
	    double dolares = pesos / cotizacion;
	    this.saldo -= pesos;
	    Movimiento mov = new Movimiento("Cambio de dólares", pesos, cliente);
	    movimientos.add(mov);
	    Empleado.getMovimientosGenerales().add(mov);
	    JOptionPane.showMessageDialog(null,
	       "Cambio realizado con éxito!\nCotización: $" + cotizacion + 
	       "\nCompraste: " + dolares + " USD" );
	}
}

	
	
	
	


