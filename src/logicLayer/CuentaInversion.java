package logicLayer;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class CuentaInversion {
    private double saldo;
    private double totalInvertido;
    private LinkedList<Double> historialTasas; // tasas diarias
    private LinkedList<Double> historialSaldos; // como fue cambiando el saldo

    public CuentaInversion() {
        this.saldo = 0;
        this.totalInvertido = 0;
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

	


	public void invertir(Cuenta cuenta, double monto) {
	    if (monto <= 0) {
	        JOptionPane.showMessageDialog(null, "Monto inválido");
	        return;
	    }

	    if (monto > cuenta.getSaldo()) {
	        JOptionPane.showMessageDialog(null, "Saldo insuficiente. Solo podes invertir hasta $" + cuenta.getSaldo() + ". No podes usar dinero de tu limite cubierto");
	        return;
	    }

	    cuenta.setSaldo(cuenta.getSaldo() - monto);


	    this.saldo += monto;
	    this.totalInvertido += monto;
	    
	    Movimiento mov = new Movimiento("Inversion", monto, cuenta.getCliente());
	    cuenta.getMovimientos().add(mov);
	    Empleado.getMovimientosGenerales().add(mov);

	    JOptionPane.showMessageDialog(null, "Invertiste $" + monto );
	}
    

    public void simularDia() {
        if (saldo <= 0) {
            JOptionPane.showMessageDialog(null, "No podes simular un dia con saldo 0");
            return;
        }
        double tasa = (Math.random() * 0.10) - 0.05;
        double rendimiento = saldo * tasa;
        saldo += rendimiento;
        historialTasas.add(tasa);
        historialSaldos.add(saldo);

        JOptionPane.showMessageDialog(
            null,
            "Tasa del dia: " + String.format("%.2f", tasa)
            + "\nNuevo saldo: $" + String.format("%.2f", saldo)
        );
    }

    public void verHistorial() {
        String texto = "Historial de inversion:\n";
        
        if (totalInvertido == 0) {
            texto += "No realizaste ninguna inversión todavía.";
            JOptionPane.showMessageDialog(null, texto);
            return;
        }

        if (historialTasas.isEmpty()) {
            texto += "Invertiste $" + String.format("%.2f", totalInvertido) + 
                     ", pero aún no simulaste ningún día.";
            JOptionPane.showMessageDialog(null, texto);
            return;
        }
        for (int i = 0; i < historialTasas.size(); i++) {
            texto += "Dia " + (i+1)
                   + " - Tasa: " + String.format("%.2f", historialTasas.get(i))
                   + " - Saldo: $" + String.format("%.2f", historialSaldos.get(i))
                   + "\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }
    
    public double calcularGananciaTotal() {
        if (historialSaldos.isEmpty()) {
            return 0;
        }
        return saldo - totalInvertido;
    }
    
    public void simularVariosDias(int dias) {
        if (dias <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad de dias invalida.");
            return;
        }

        for (int i = 0; i < dias; i++) {
            simularDia();
        }
    }
    
    public void verResumen() {
        String resumen = "";
        resumen += "Total invertido: $" + String.format("%.2f", totalInvertido) + "\n" +
        	    "Saldo actual: $" + String.format("%.2f", saldo) + "\n" +
        	    "Rendimiento total: $" + String.format("%.2f", calcularGananciaTotal()) + "\n" +
        	    "Días simulados: " + historialSaldos.size() + "\n";

        if (!historialTasas.isEmpty()) {
            double mejor = historialTasas.stream().max(Double::compare).get();
            double peor = historialTasas.stream().min(Double::compare).get();
            resumen += "Mejor tasa: " + String.format("%.2f", mejor) + "\n";
            resumen += "Peor tasa: " + String.format("%.2f", peor) + "\n";
        }

        JOptionPane.showMessageDialog(null, resumen);
    }
}