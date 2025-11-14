package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class CuentaInversion {
	    private double saldo;
	    private LinkedList<Double> historialTasas; // tasas diarias
	    private LinkedList<Double> historialSaldos; // como fue cambiando el saldo

	    public CuentaInversion() {
	        this.saldo = 0;
	        this.historialTasas = new LinkedList<>();
	        this.historialSaldos = new LinkedList<>();
	    }


		public double getSaldo() {
			return saldo;
		}


		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}


		public LinkedList<Double> getHistorialTasas() {
			return historialTasas;
		}


		public void setHistorialTasas(LinkedList<Double> historialTasas) {
			this.historialTasas = historialTasas;
		}

		public LinkedList<Double> getHistorialSaldos() {
			return historialSaldos;
		}

		public void setHistorialSaldos(LinkedList<Double> historialSaldos) {
			this.historialSaldos = historialSaldos;
		}


		public void invertir(double monto) {
	        if (monto <= 0) {
	            JOptionPane.showMessageDialog(null, "Monto inválido");
	            return;
	        }
	        this.saldo += monto;
	        JOptionPane.showMessageDialog(null, "Invertiste $" + monto);
	    }
	    

	    public void simularDia() {
	        // Tasa entre -0.05 y 0.05
	        double tasa = (Math.random() * 0.10) - 0.05;
	        double rendimiento = saldo * tasa;
	        saldo += rendimiento;
	        historialTasas.add(tasa);
	        historialSaldos.add(saldo);

	        JOptionPane.showMessageDialog(
	            null,
	            "Tasa del día: " + String.format("%.3f", tasa)
	            + "\nNuevo saldo: $" + String.format("%.2f", saldo)
	        );
	    }

	    public void verHistorial() {
	        String texto = "Historial de inversión:\n";

	        for (int i = 0; i < historialTasas.size(); i++) {
	            texto += "Día " + (i+1)
	                   + " - Tasa: " + String.format("%.3f", historialTasas.get(i))
	                   + " - Saldo: $" + String.format("%.2f", historialSaldos.get(i))
	                   + "\n";
	        }

	        JOptionPane.showMessageDialog(null, texto);
	    }
	}

