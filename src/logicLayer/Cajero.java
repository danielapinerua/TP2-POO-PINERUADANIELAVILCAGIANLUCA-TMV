package logicLayer;

import java.lang.reflect.Array;
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
		return "Cajero [saldo=" + saldo + ", ubicacion=" + ubicacion + ", estado=" + estado + "]";
	}
	
	
	public static Cajero elegirCajero() {
		//me aseguro que no se pueda retirar dinero de un cajero que no esta habilitado
		int habilitados = 0;
		LinkedList<Cajero> cajerosHabilitados = new LinkedList<Cajero>();
		
		
		for(int i = 0; i < cajeros.size(); i++) {
			if( cajeros.get(i).isEstado()==true && cajeros.get(i).getSaldo()>0) {
				cajerosHabilitados.add(cajeros.get(i)); //cada vez q tengo un cajero habilitado traigo este de aca
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
			}//guardo el actual porque pueden ser menos cajeros habilitados de los que yo tengo en total
		}
		int elegido = JOptionPane.showOptionDialog(null,"Elegir cajero", "", 0,
				0, null, cajerosString, cajerosString[0]) ;
		return cajerosHabilitados.get(elegido);
		}

	}
	
	
	public static Cajero elegirCajeroEmpleado() {
		//como empleado puede ver todos los cajeros habilitados o no
		String[] cajerosString = new String[cajeros.size()];
		for(int i = 0; i < cajerosString.length; i++ ) {
			cajerosString[i] = cajeros.get(i).getUbicacion();
		}
		int elegido = JOptionPane.showOptionDialog(null,"Elegir cajero", "", 0,
				0, null, cajerosString, cajerosString[0]) ;
		return cajeros.get(elegido);
	
		
	}
    
  

    //public void agregarCuenta(Cuenta cuenta) {
       // if (!cuentas.contains(cuenta)) {
        //    cuentas.add(cuenta);
        //} else {
       //     JOptionPane.showMessageDialog(null, "Cuenta ya registrada");
       // }
    //}

  
    
    
    


}
