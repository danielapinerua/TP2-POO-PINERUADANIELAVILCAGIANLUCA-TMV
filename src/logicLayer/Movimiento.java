package logicLayer;

import java.time.LocalDateTime;

public class Movimiento {
	private String tipo;
	private LocalDateTime fecha;
	private double monto;
	private String detalle;
	public Movimiento(String tipo, LocalDateTime fecha, double monto, String detalle) {
		super();
		this.tipo = tipo;
		this.fecha = fecha;
		this.monto = monto;
		this.detalle = detalle;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Movimiento [tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto + ", detalle=" + detalle + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
