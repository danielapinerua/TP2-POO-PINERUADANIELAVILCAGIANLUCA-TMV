package logicLayer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;


public class Empleado extends Usuario {
	private String legajo;
	private static LinkedList<Movimiento> movimientosGenerales = new LinkedList<Movimiento>();


	public Empleado(String nombre, String mail, String pin, String legajo) {
		super(nombre, TipoUsuario.Empleado, mail, pin);
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	
	 	
	public static LinkedList<Movimiento> getMovimientosGenerales() {
		return movimientosGenerales;
	}

	public static void setMovimientosGenerales(LinkedList<Movimiento> movimientosGenerales) {
		Empleado.movimientosGenerales = movimientosGenerales;
	}
	
	@Override
	public String toString() {
		return "legajo=" + legajo + ", nombre=" + nombre + ", tipo de usuario=" + tipoUsuario + ", mail=" + mail;
	}
	

	@Override
	public void Menu() {
	

	    int opcion;
	    do {
	        opcion = JOptionPane.showOptionDialog(
	            null,
	            "Menú Empleado",
	            "Empleado",
	            0,0,
	            null,
	            this.getTipoUsuario().getOpciones(),
	            this.getTipoUsuario().getOpciones()[0]
	        );

	        switch (opcion) {
	            case 0: // Ver cuentas
	                verUsuarios();
	                break;

	            case 1: // Cargar dinero
	            	Cajero cajeroCarga = Cajero.elegirCajeroEmpleado();
	                if (cajeroCarga != null) {
	                    double monto = Validar.validarNumero("Monto a cargar en el cajero " + cajeroCarga.getUbicacion() + ":");
	                    cargarDinero(cajeroCarga, monto);
	                }
	                break;
	               
	            case 2: // Ver movimientos
	                verMovimientos();
	                break;
	                
	            case 3://ver inversiones
	            	verInversiones();
	                break;
	                
	            case 4: // dar alta cajero
	              darAltaCajero();
	              break;

	            case 5: // dar de baja cajero
	              darBajaCajero();
	                break;
	                
	            case 6://ver cajeros
	            	verCajeros();
	            	break;
	                
	            case 7: // cambiar pin 
	            	cambiarPin();
	            	break;
	            	
	            case 8: // ver informacion del empleado
	                JOptionPane.showMessageDialog(null, toString());
	                break;
	                
	            case 9://salir
	            	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
	            	break;
	        }
	    } while (opcion != 9);
	}
	
	public void verUsuarios() {
		String[] opciones = {
	        "Ver todos",
	        "Ordenar alfabéticamente",
	        "Ordenar por saldo"
	    };
		int elegido = JOptionPane.showOptionDialog(
	        null,
	        "Seleccione una opción",
	        "Ver usuarios",
	        0,0,
	        null,
	        opciones,
	        opciones[0]
	    );

	    switch (elegido) {
	        // Ver todos los usuarios
	        case 0:
	            String mostrarTodos = "LISTA DE USUARIOS: \n";
	            for (Usuario usuario : Usuario.getUsuarios()) {
	                mostrarTodos += usuario.toString() + "\n";
	            }

	            JOptionPane.showMessageDialog(null, mostrarTodos);
	            break;

	        //Ordenar alfabéticamente por nombre
	        case 1:
	            LinkedList<Usuario> ordenados =
	                Usuario.getUsuarios().stream()
	                    .sorted(Comparator.comparing(Usuario::getNombre))
	                    .collect(Collectors.toCollection(LinkedList::new));

	            String mostrarAlfabeticamente = "ORDENADOS ALFABETICAMENTE: \n";

	            for (Usuario usuario : ordenados) {
	                mostrarAlfabeticamente += usuario.toString() + "\n";
	            }

	            JOptionPane.showMessageDialog(null, mostrarAlfabeticamente);
	            break;

	        //  Clientes ordenados por saldo de menor a mayor
	        case 2: 

	            LinkedList<Cliente> clientes = new LinkedList<>();
	            for (Usuario usuario : Usuario.getUsuarios()) {
	                if (usuario.getTipoUsuario() == TipoUsuario.Cliente) {
	                    clientes.add((Cliente) usuario);
	                }
	            }
	            // Preguntar si ordenar por saldo pesos o dólar
	            String[] tipoSaldo = {"Saldo en pesos", "Saldo en dólares"};
	            int opcionSaldo = JOptionPane.showOptionDialog(
	                    null,
	                    "Seleccione por qué saldo ordenar:",
	                    "Tipo de saldo",
	                    0,0,
	                    null,
	                    tipoSaldo,
	                    tipoSaldo[0]
	            );

	            LinkedList<Cliente> ordenadosPorSaldo;

	            if (opcionSaldo == 0) {
	                // ordenar por saldo en pesos
	                ordenadosPorSaldo =clientes.stream()
	                        .sorted(Comparator.comparing(cliente ->
	                            cliente.buscarCuentaPorMail(cliente.getMail()).getSaldo()
	                        ))
	                        .collect(Collectors.toCollection(LinkedList::new));

	                String mostrar = "ORDENADOS POR SALDO EN PESOS:\n";
	                for (Cliente cliente : ordenadosPorSaldo) {
	                    double saldo = cliente.buscarCuentaPorMail(cliente.getMail()).getSaldo();
	                    mostrar += cliente.getNombre() + " - $" + saldo + "\n";
	                }
	                JOptionPane.showMessageDialog(null, mostrar);

	            } else {
	                // ordenar por saldo en dólares
	                ordenadosPorSaldo =
	                    clientes.stream()
	                        .sorted(Comparator.comparing(cliente ->
	                            cliente.buscarCuentaPorMail(cliente.getMail()).getSaldoDolares()
	                        ))
	                        .collect(Collectors.toCollection(LinkedList::new));

	                String mostrar = "ORDENADOS POR SALDO EN DÓLARES:\n";
	                for (Cliente cliente : ordenadosPorSaldo) {
	                    double saldo = cliente.buscarCuentaPorMail(cliente.getMail()).getSaldoDolares();
	                    mostrar += cliente.getNombre() + " - " + String.format("%.2f", saldo) + " USD\n";
	                }
	                JOptionPane.showMessageDialog(null, mostrar);
	            }

	            break;

	        default:
	            break;
	    }
	}
	
	public void cargarDinero(Cajero cajero, double monto) {
		if (monto <= 0) {
			JOptionPane.showMessageDialog(null, "Monto inválido");
            return;
        }
        cajero.setSaldo(cajero.getSaldo() + monto);
        JOptionPane.showMessageDialog(null, "Cajero recargado. Total: $" + cajero.getSaldo());
    }
    
    public void darAltaCajero() {
        String[] opcionesCajero = {"Dar de alta cajero existente", "Crear cajero nuevo"};
        int opcionCajero = JOptionPane.showOptionDialog(
            null,
            "Seleccione una acción:",
            "Administrar cajeros",
            0,
            0,
            null,
            opcionesCajero,
            opcionesCajero[0]
        );
        if (opcionCajero == 0) {
            Cajero seleccionado = Cajero.elegirCajeroInactivo();
            if (seleccionado != null) {
                seleccionado.setEstado(true);
                JOptionPane.showMessageDialog(null, 
                    "Cajero en " + seleccionado.getUbicacion() + " dado de alta exitosamente.");
            }
        } else if (opcionCajero == 1) {
            String ubicacion = Validar.validarCampo("Ingrese la ubicación del nuevo cajero:");
            double saldoInicial = Validar.validarNumero("Saldo inicial del cajero:");

            String[] opcionesEstado = {"Activo", "Inactivo"};
            int estadoElegido = JOptionPane.showOptionDialog(
                null,
                "Seleccione el estado inicial del cajero:",
                "Estado del cajero",
                0,0,
                null,
                opcionesEstado,
                opcionesEstado[0]
            );
            boolean estado;
            if (estadoElegido == 0) {
                estado = true;
            } else {
                estado = false;
            }
            Cajero nuevo = new Cajero(saldoInicial, ubicacion, estado);
            Cajero.getCajeros().add(nuevo);
            JOptionPane.showMessageDialog(null, 
                "Cajero nuevo creado en " + ubicacion + 
                "\nSaldo inicial: $" + saldoInicial +
                "\nEstado: " + (estado ? "Activo" : "Inactivo"));
        }
    }
    
  
    
    
    public void darBajaCajero() {
        Cajero cajeroBaja = Cajero.elegirCajeroDisponible();
        if (cajeroBaja == null) {
            JOptionPane.showMessageDialog(null, "No hay cajeros activos para dar de baja.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
            null,
            "¿Seguro que desea dar de baja el cajero en " + cajeroBaja.getUbicacion() + "?",
            "Confirmar baja de cajero",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            cajeroBaja.setEstado(false);
            JOptionPane.showMessageDialog(null, 
                "Cajero en " + cajeroBaja.getUbicacion() + " dado de baja correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, 
                "Operación cancelada. El cajero sigue activo.");
        }
    }
    
    
    public void verCajeros() {
        LinkedList<Cajero> cajeros = Cajero.getCajeros();
        if (cajeros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cajeros registrados.");
            return;
        }
        String texto = "Lista de cajeros:\n";
        for (Cajero cajero : cajeros) {
            texto += cajero.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }
    
    
    
    public void verMovimientos() {
        if (movimientosGenerales.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos registrados.");
            return;
        }
        String[] opciones = {
            "Ver todos",
            "Filtrar por tipo de movimiento",
            "Filtrar por cliente",
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
            //  VER TODOS
            case 0:
                String listado = "";
                for (Movimiento movimiento : movimientosGenerales) {
                    listado += movimiento;
                }
                JOptionPane.showMessageDialog(null, listado);
                break;
            // FILTRAR POR TIPO
            case 1:
                String[] tipos = {
                    "Depósito", "Retiro", "Préstamo",
                    "Transferencia", "Pago de servicio",
                    "Cambio de dólares","Inversion"
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
                    movimientosGenerales.stream()
                    .filter(movimiento -> movimiento.getTipo().equals(tipo))
                    .collect(Collectors.toCollection(LinkedList::new));

                if (filtradosPorTipo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay movimientos del tipo seleccionado.");
                } else {
                    String listaTipo = "";
                    for (Movimiento movimiento : filtradosPorTipo) {
                        listaTipo += movimiento;
                    }
                    JOptionPane.showMessageDialog(null, listaTipo);
                }
                break;

            //FILTRAR POR CLIENTE
            case 2:
                LinkedList<Cliente> clientes = new LinkedList<>();
                for (Cuenta cuenta : Cuenta.getCuentas()) {
                    clientes.add(cuenta.getCliente());
                }

                if (clientes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
                    return;
                }

                String[] nombres = new String[clientes.size()];
                for (int i = 0; i < nombres.length; i++) {
                    nombres[i] = clientes.get(i).getNombre();
                }

                String clienteElegido = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione un cliente",
                    "",
                    0,
                    null,
                    nombres,
                    nombres[0]
                );

                LinkedList<Movimiento> filtradosPorCliente =
                    movimientosGenerales.stream()
                    .filter(movimiento -> movimiento.getCliente().getNombre().equalsIgnoreCase(clienteElegido))
                    .collect(Collectors.toCollection(LinkedList::new));

                if (filtradosPorCliente.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron movimientos para ese cliente.");
                } else {
                    String listaCliente = "";
                    for (Movimiento movimiento : filtradosPorCliente) {
                        listaCliente += movimiento ;
                    }
                    JOptionPane.showMessageDialog(null, listaCliente);
                }
                break;  

            // ORDENAR POR MONTO
            case 3:
                LinkedList<Movimiento> movimientosOrdenados =
                    movimientosGenerales.stream()
                    .sorted(Comparator.comparingDouble(movimiento -> movimiento.getMonto()))
                    .collect(Collectors.toCollection(LinkedList::new));

                String listaMonto = "";
                for (Movimiento movimiento : movimientosOrdenados) {
                    listaMonto += movimiento;
                }
                JOptionPane.showMessageDialog(null, listaMonto);
                break;
            default:
                break;
        }
    }
    
    
    public void verInversiones() {
        // Obtener todos los clientes
        LinkedList<Cliente> clientes = new LinkedList<>();
        for (Usuario usuario : Usuario.getUsuarios()) {
            if (usuario.getTipoUsuario() == TipoUsuario.Cliente) {
                clientes.add((Cliente) usuario);
            }
        }
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes con cuentas de inversión.");
            return;
        }

        String[] opciones = {
            "Ver todas las inversiones",
            "Ordenar por saldo (menor a mayor)"
        };

        int elegido = JOptionPane.showOptionDialog(
            null,
            "Seleccione una opción",
            "Inversiones",
            0,0,
            null,
            opciones,
            opciones[0]
        );

        switch (elegido) {

            // Ver todas las inversiones
            case 0:
                String texto = "TODAS LAS INVERSIONES:\n";

                for (Cliente cliente : clientes) {
                    CuentaInversion cuentaInv = cliente.getCuentaInversion();
                    texto += cliente.getNombre() + " → Saldo: $" + String.format("%.2f", cuentaInv.getSaldo()) + "\n";
                }

                JOptionPane.showMessageDialog(null, texto);
                break;


             // Ordenar por saldo
            case 1:
            	LinkedList<Cliente> ordenSaldo =
                clientes.stream()
                        .sorted(Comparator.comparingDouble(
                            c -> c.getCuentaInversion().getSaldo()
                        ))
                        .collect(Collectors.toCollection(LinkedList::new));
                String orden = "INVERSIONES ORDENADAS (menor a mayor):\n";
                for (Cliente cliente : ordenSaldo) {
                    orden += cliente.getNombre() + " - $" 
                           + String.format("%.2f", cliente.getCuentaInversion().getSaldo()) + "\n";
                }
                JOptionPane.showMessageDialog(null, orden);
                break;

            default:
                break;
        }
    }

	
	
    
    
    
}
