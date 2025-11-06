package logicLayer;

import java.time.LocalDateTime;

public class Movimiento {
	private String tipo;
	private LocalDateTime fecha;
	private double monto;

	public Movimiento(String tipo,double monto) {
		super();
		this.tipo = tipo;
		this.fecha = LocalDateTime.now();
		this.monto = monto;
		
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}

	
	@Override
	public String toString() {
		return "tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto ;
	}
	
	

	
}