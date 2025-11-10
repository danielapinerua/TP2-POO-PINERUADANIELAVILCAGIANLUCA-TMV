package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	private String legajo;
	private static LinkedList<Movimiento> movimientosGenerales = new LinkedList<Movimiento>();

	//private static LinkedList<Empleado>empleados = new LinkedList<>();


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
	
	
	 //@Override
		//public void Menu() {
			//int opcion=JOptionPane.showOptionDialog(null, "Menu empleado","",0,0,null, this.getTipoUsuario().getOpciones(),this.getTipoUsuario().getOpciones());
		
	// }
	 	
	public static LinkedList<Movimiento> getMovimientosGenerales() {
		return movimientosGenerales;
	}

	public static void setMovimientosGenerales(LinkedList<Movimiento> movimientosGenerales) {
		Empleado.movimientosGenerales = movimientosGenerales;
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
	                verCuentas();
	                break;

	           
	            case 1: // Cargar dinero
	            	Cajero cajeroCarga = Cajero.elegirCajeroEmpleado();
	                if (cajeroCarga != null) {
	                    double monto = Validar.validarNumero("Monto a cargar en el cajero " + cajeroCarga.getUbicacion() + ":");
	                    cargarDinero(cajeroCarga, monto);
	                }
	                break;

	               
	            case 2: // Ver información del empleado
	                JOptionPane.showMessageDialog(null, movimientosGenerales.isEmpty()?"No hay movimientos":movimientosGenerales);
	                break;
	                
	            case 3: // dar alta cajero
	              darAltaCajero();
	              break;

	            case 4: // dar de baja cajero
	              darBajaCajero();
	                break;
	                
	            case 5: // cambiar pin 
	            	cambiarPin();
	            	break;
	            	
	            case 6: // ver informacion del empleado
	                JOptionPane.showMessageDialog(null, toString());
	                break;
	                
	            case 7 ://salir
	            	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
	            	break;
	        }
	    } while (opcion != 7);
	}
	// ver las cuentas q existen
    public void verCuentas() {
        LinkedList<Cuenta> cuentas = Cuenta.getCuentas();
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Lista de cuentas ===\n");
        for (Cuenta c : cuentas) {
            sb.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
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
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opcionesCajero,
            opcionesCajero[0]
        );

        if (opcionCajero == 0) {
        	  Cajero seleccionado = Cajero.elegirCajeroInactivo();

              if (seleccionado != null) {
                  seleccionado.setEstado(true);
                  JOptionPane.showMessageDialog(null, "Cajero en " + seleccionado.getUbicacion() + " dado de alta exitosamente.");
              }
        } else if (opcionCajero == 1) {
        	String ubicacion = Validar.validarCampo("Ingrese la ubicación del nuevo cajero:");
            double saldoInicial = Validar.validarNumero("Saldo inicial del cajero:");
            boolean estado = true;

            Cajero nuevo = new Cajero(saldoInicial, ubicacion, estado);
            Cajero.getCajeros().add(nuevo);

            JOptionPane.showMessageDialog(null, "Cajero nuevo creado en " + ubicacion + ".");
        
        }
    }
    
  
    
    
    public void darBajaCajero() {
        // Elegir entre los cajeros activos
        Cajero cajeroBaja = Cajero.elegirCajeroDisponible();

        if (cajeroBaja == null) {
            JOptionPane.showMessageDialog(null, "No hay cajeros activos para dar de baja.");
            return;
        }

        // Confirmar antes de desactivarlo
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

	@Override
	public String toString() {
		return "Legajo=" + legajo + ", nombre=" + nombre + ", mail=" + mail ;
	}
    
    
}
