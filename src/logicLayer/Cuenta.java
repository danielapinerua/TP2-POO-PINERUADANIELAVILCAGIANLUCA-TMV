package logicLayer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private double saldo;
	private double saldoDolares;
	private double limiteCubierto;
	private LinkedList<Movimiento>movimientos;
	private static LinkedList<Cuenta>cuentas = new LinkedList<>();
	private static int contadorCbu = 1003;
	public Cuenta(String cbu, Cliente cliente, double saldo) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.saldo = saldo;
		this.saldoDolares = 0;
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
	
	public double getSaldoDolares() {
		return saldoDolares;
	}

	public void setSaldoDolares(double saldoDolares) {
		this.saldoDolares = saldoDolares;
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
	
	
	@Override
	public String toString() {
		return "cbu=" + cbu + ", cliente=" + cliente + ", saldo=" + saldo + ", limite Cubierto=" + limiteCubierto
				+ ", movimientos=" + movimientos ;
	}
	
	
	private static String generarCbu() {
	    contadorCbu++;
	    return String.valueOf(contadorCbu);
	}
	
	public static void registrarse() {
        String nombre = Validar.validarLetras("Ingresar nombre:");
        String mail = Validar.validarCampo("Ingresar mail:");
        String pin = Validar.validarCampo("Ingresar PIN:");
        String telefono = String.valueOf(Validar.validarNumero("Ingresar teléfono:"));

        Cliente nuevoCliente = new Cliente(nombre, mail, pin, telefono);
        Usuario.getUsuarios().add(nuevoCliente); 
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

	    if (monto > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }
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
	        cajero.setSaldo(cajero.getSaldo() + monto); 
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

	    if (monto > saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }

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

	    JOptionPane.showMessageDialog(null, "Retiro exitoso! Retiraste: $" + monto + "\nSaldo actual: " + this.saldo);
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
	        return; // 
	    }
	    double monto = Validar.validarNumero("Ingrese el monto a pagar para " + servicios[servicioElegido] + ":");
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "Monto inválido.");
	        return;
	    }
	    double saldoDisponible = this.saldo + this.limiteCubierto;
	    if (monto > saldoDisponible) {
	    	JOptionPane.showMessageDialog(null, 
		            "Saldo insuficiente. Tu saldo disponible (incluyendo el límite cubierto) es de $" + saldoDisponible);
	        return;
	    }

	    if (monto > this.saldo && monto <= saldoDisponible) {
	        JOptionPane.showMessageDialog(null, 
	            "Estás utilizando parte de tu límite cubierto para realizar este pago.");
	    }
	    this.saldo -= monto;
	    Movimiento mov = new Movimiento("Pago de servicio", monto, cliente);
	    this.movimientos.add(mov);
	    Empleado.getMovimientosGenerales().add(mov);
	    JOptionPane.showMessageDialog(null, 
	        "Pago de " + servicios[servicioElegido] + " realizado con éxito por $" + monto +
	        "\nSaldo actual: $" + this.saldo);
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
	    this.saldoDolares += dolares;
	    Movimiento mov = new Movimiento("Cambio de dólares", pesos, cliente);
	    movimientos.add(mov);
	    Empleado.getMovimientosGenerales().add(mov);
	    
	    JOptionPane.showMessageDialog(null,
	    	       "Cambio realizado con éxito!\nCotización: $" + cotizacion + 
	    	       "\nCompraste: " + String.format("%.2f", dolares) + " USD" + 
	    	       "\nSaldo actual en pesos: $" + String.format("%.2f", this.saldo) +
	    	       "\nSaldo actual en dólares: " + String.format("%.2f", this.saldoDolares) + " USD");
	}
	
	
	
	public void verMovimientos() {
	    if (this.movimientos.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay movimientos registrados en tu cuenta.");
	        return;
	    }
	    String[] opciones = {
	        "Ver todos",
	        "Filtrar por tipo de movimiento",
	        "Ordenar por monto (menor a mayor)"
	    };

	    int elegido = JOptionPane.showOptionDialog(
	        null,
	        "Elija una opción",
	        "Movimientos",
	        0, 0, null,
	        opciones,
	        opciones[0]
	    );

	    switch (elegido) {
	        //VER TODOS
	        case 0:
	            String listaTodos = "";
	            for (Movimiento movimiento : movimientos) {
	                listaTodos += movimiento + "\n";
	            }
	            JOptionPane.showMessageDialog(null, listaTodos);
	            break;

	        //FILTRAR POR TIPO
	        case 1:
	            String[] tipos = {
	                "Depósito", "Retiro", "Préstamo",
	                "Transferencia", "Pago de servicio",
	                "Cambio de dólares"
	            };
	            String tipo = (String) JOptionPane.showInputDialog(
	                null,
	                "Elija tipo de movimiento",
	                "",
	                0,
	                null,
	                tipos,
	                tipos[0]
	            );

	            LinkedList<Movimiento> filtradosPorTipo =
	                movimientos.stream()
	                .filter(movimiento -> movimiento.getTipo().equals(tipo))
	                .collect(Collectors.toCollection(LinkedList::new));

	            if (filtradosPorTipo.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "No hay movimientos del tipo seleccionado.");
	            } else {
	                String listaTipo = "";
	                for (Movimiento movimiento : filtradosPorTipo) {
	                    listaTipo += movimiento + "\n";
	                }
	                JOptionPane.showMessageDialog(null, listaTipo);
	            }
	            break;

	        //ORDENAR POR MONTO
	        case 2:
	            LinkedList<Movimiento> ordenadosPorMonto =
	                movimientos.stream()
	                .sorted(Comparator.comparingDouble(movimiento -> movimiento.getMonto()))
	                .collect(Collectors.toCollection(LinkedList::new));

	            String listaMonto = "";
	            for (Movimiento movimiento : ordenadosPorMonto) {
	                listaMonto += movimiento + "\n";
	            }

	            JOptionPane.showMessageDialog(null, listaMonto);
	            break;

	        default:
	            break;
	    }
	}
	public static void cargaInicial() {
	    Cliente c1 = (Cliente) Usuario.getUsuarios().get(0);
	    Cliente c2 = (Cliente) Usuario.getUsuarios().get(1);
	    Cliente c3 = (Cliente) Usuario.getUsuarios().get(2);

	    Cuenta.getCuentas().add(new Cuenta("1001", c1, 5000));
	    Cuenta.getCuentas().add(new Cuenta("1002", c2, 8000));
	    Cuenta.getCuentas().add(new Cuenta("1003", c3, 10000));
	}
}

	
	
	
	


