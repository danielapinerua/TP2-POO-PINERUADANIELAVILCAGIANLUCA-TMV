package logicLayer;

import java.time.LocalDateTime;

public class Movimiento {
	private String tipo;
	private LocalDateTime fecha;
	private double monto;
	private Cliente cliente;
	public Movimiento(String tipo,double monto, Cliente cliente) {
		super();
		this.tipo = tipo;
		this.fecha = LocalDateTime.now();
		this.monto = monto;
		this.cliente = cliente;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Movimiento [tipo=" + tipo + ", fecha=" + fecha + ", monto=" + monto + ", cliente=" + cliente + "]";
	}
	
	

	
}