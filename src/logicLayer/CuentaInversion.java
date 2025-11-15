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

	

	@Override
	public String toString() {
		return "CuentaInversion [saldo=" + saldo + ", historialTasas=" + historialTasas + ", historialSaldos="
				+ historialSaldos + "]";
	}


	public void invertir(double monto) {
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "Monto invÃ¡lido");
            return;
        }
        this.saldo += monto;
        JOptionPane.showMessageDialog(null, "Invertiste $" + monto);
    }
    

    public void simularDia() {
    	
        if (saldo <= 0) {
            JOptionPane.showMessageDialog(null, "No podÃ©s simular un dia con saldo 0");
            return;
        }
        
        // Tasa entre -0.05 y 0.05
        double tasa = (Math.random() * 0.10) - 0.05;
        double rendimiento = saldo * tasa;
        saldo += rendimiento;
        historialTasas.add(tasa);
        historialSaldos.add(saldo);

        JOptionPane.showMessageDialog(
            null,
            "Tasa del dia: " + String.format("%.3f", tasa)
            + "\nNuevo saldo: $" + String.format("%.2f", saldo)
        );
    }

    public void verHistorial() {
        String texto = "Historial de inversion:\n";

        for (int i = 0; i < historialTasas.size(); i++) {
            texto += "Dia " + (i+1)
                   + " - Tasa: " + String.format("%.3f", historialTasas.get(i))
                   + " - Saldo: $" + String.format("%.2f", historialSaldos.get(i))
                   + "\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }
    
    public double calcularGananciaTotal() {
        if (historialSaldos.isEmpty()) {
            return 0;
        }
        return saldo - historialSaldos.get(0);
    }
    
    public void simularVariosDias(int dias) {
        if (dias <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad de dis invalida.");
            return;
        }

        for (int i = 0; i < dias; i++) {
            simularDia();
        }
    }
    
    public void verResumen() {
        String resumen = "";

        resumen += "Saldo actual: $" + String.format("%.2f", saldo) + "\n";
        resumen += "Dias simulados: " + historialSaldos.size() + "\n";

        if (!historialTasas.isEmpty()) {
            double mejor = historialTasas.stream().max(Double::compare).get();
            double peor = historialTasas.stream().min(Double::compare).get();
            resumen += "Mejor tasa: " + String.format("%.3f", mejor) + "\n";
            resumen += "Peor tasa: " + String.format("%.3f", peor) + "\n";
        }

        resumen += "Ganancia total: $" + String.format("%.2f", calcularGananciaTotal());

        JOptionPane.showMessageDialog(null, resumen);
    }
}