package logicLayer;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Cajero {
    private double saldo;
    private String ubicacion;
    private boolean estado;

    public Cajero(double saldo) {
        this.saldo = saldo;
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

	@Override
	public String toString() {
		return "Cajero [saldo=" + saldo + ", ubicacion=" + ubicacion + ", estado=" + estado + "]";
	}
    
    
  

    //public void agregarCuenta(Cuenta cuenta) {
       // if (!cuentas.contains(cuenta)) {
        //    cuentas.add(cuenta);
        //} else {
       //     JOptionPane.showMessageDialog(null, "Cuenta ya registrada");
       // }
    //}

  
    
    
    


}
