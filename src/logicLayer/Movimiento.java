package logicLayer;

import java.time.LocalDate;


public class Movimiento {
	private String tipo;
	private LocalDate fecha;
	private double monto;

	public Movimiento(String tipo, double monto) {
		super();
		this.tipo = tipo;
		this.fecha = LocalDate.now();
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
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
		return "Movimiento [tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto + "]";
	}
		

}
