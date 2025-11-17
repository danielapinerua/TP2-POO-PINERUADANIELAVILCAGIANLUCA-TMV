package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cajero {
    private double saldo;
    private String ubicacion;
    private boolean estado;
    private static LinkedList<Cajero>cajeros = new LinkedList<Cajero>();



    public Cajero(double saldo, String ubicacion, boolean estado) {
		super();
		this.saldo = saldo;
		this.ubicacion = ubicacion;
		this.estado = estado;
	}
	// getters y setters
    public double getSaldo() { 
    	return saldo; 
    	}
    public void setSaldo(double saldo) { 
    	this.saldo = saldo; 
    	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

	public static LinkedList<Cajero> getCajeros() {
		return cajeros;
	}
	public static void setCajeros(LinkedList<Cajero> cajeros) {
		Cajero.cajeros = cajeros;
	}
	
	
	
	@Override
	public String toString() {
	    return "Ubicacion=" + ubicacion + ",saldo=" + saldo + ",estado=" + (estado ? "Activo" : "Inactivo") + "\n";
	}
	
	public static Cajero elegirCajeroRecibir() {
		int habilitados = 0;
		LinkedList<Cajero> cajerosHabilitados = new LinkedList<Cajero>();
		
		
		for(int i = 0; i < cajeros.size(); i++) {
			if( cajeros.get(i).isEstado()==true && cajeros.get(i).getSaldo()>0) {
				cajerosHabilitados.add(cajeros.get(i)); 
				habilitados++;
			}
		}
		if(habilitados == 0) {
			JOptionPane.showMessageDialog(null, "No hay cajeros disponibles");
			return null;
		} else {
		
		int actual = 0;
		String[] cajerosString = new String[habilitados];
		for(int i = 0; i < cajeros.size(); i++ ) {
			if(cajeros.get(i).isEstado()==true && cajeros.get(i).getSaldo()>0) {
			cajerosString[actual] = cajeros.get(i).getUbicacion();
				actual++;
			}
		}
		int elegido = JOptionPane.showOptionDialog(null,"Elegir cajero", "", 0,
				0, null, cajerosString, cajerosString[0]) ;
		return cajerosHabilitados.get(elegido);
		}

	}
	
	public static Cajero elegirCajeroDisponible() {
		int habilitados = 0;
		LinkedList<Cajero> cajerosHabilitados = new LinkedList<Cajero>();
		
		
		for(int i = 0; i < cajeros.size(); i++) {
			if( cajeros.get(i).isEstado()==true) {
				cajerosHabilitados.add(cajeros.get(i)); 
				habilitados++;
			}
		}
		if(habilitados == 0) {
			JOptionPane.showMessageDialog(null, "No hay cajeros disponibles");
			return null;
		} else {
		
		int actual = 0;
		String[] cajerosString = new String[habilitados];
		for(int i = 0; i < cajeros.size(); i++ ) {
			if(cajeros.get(i).isEstado()==true) {
			cajerosString[actual] = cajeros.get(i).getUbicacion();
				actual++;
			}
		}
		int elegido = JOptionPane.showOptionDialog(null,"Elegir cajero", "", 0,
				0, null, cajerosString, cajerosString[0]) ;
		return cajerosHabilitados.get(elegido);
		}

	
	}
	
	
	public static Cajero elegirCajeroEmpleado() {
		String[] cajerosString = new String[cajeros.size()];
		for(int i = 0; i < cajerosString.length; i++ ) {
			cajerosString[i] = cajeros.get(i).getUbicacion();
		}
		int elegido = JOptionPane.showOptionDialog(null,"Elegir cajero", "", 0,
				0, null, cajerosString, cajerosString[0]) ;
		return cajeros.get(elegido);
		
	}
	
	public static Cajero elegirCajeroInactivo() {
	    int inactivos = 0;
	    LinkedList<Cajero> cajerosInactivos = new LinkedList<Cajero>();

	    for (int i = 0; i < cajeros.size(); i++) {
	        if (cajeros.get(i).isEstado()==false) { 
	            cajerosInactivos.add(cajeros.get(i));
	            inactivos++;
	        }
	    }

	    if (inactivos == 0) {
	        JOptionPane.showMessageDialog(null, "No hay cajeros inactivos disponibles.");
	        return null;
	    } else {
	        int actual = 0;
	        String[] cajerosString = new String[inactivos];

	        for (int i = 0; i < cajeros.size(); i++) {
	            if (cajeros.get(i).isEstado()==false) {
	                cajerosString[actual] = cajeros.get(i).getUbicacion();
	                actual++;
	            }
	        }

	        int elegido = JOptionPane.showOptionDialog(
	            null,
	            "Elegí un cajero inactivo para dar de alta:",
	            "Cajeros inactivos",
	            0, 0, null,
	            cajerosString,
	            cajerosString[0]
	        );

	        return cajerosInactivos.get(elegido);
	    }
	}
	
	public static void cargaInicial() {
	    Cajero.getCajeros().add(new Cajero(200000, "Constituyentes", false));
	    Cajero.getCajeros().add(new Cajero(0, "Beiro", true));
	    Cajero.getCajeros().add(new Cajero(100000, "Congreso", true));
	    Cajero.getCajeros().add(new Cajero(30000, "Sarmiento", true));
	    Cajero.getCajeros().add(new Cajero(50000, "Nazca", true));
	}
  


}
