package logicLayer;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Cajero {
    private double dineroDisponible;
    private LinkedList<Cuenta> cuentas;

    public Cajero(double dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
        this.cuentas = new LinkedList<>();
    }

    // getters y setters
    public double getDineroDisponible() { return dineroDisponible; }
    public void setDineroDisponible(double dineroDisponible) { this.dineroDisponible = dineroDisponible; }

    public LinkedList<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(LinkedList<Cuenta> cuentas) { this.cuentas = cuentas; }

    public void agregarCuenta(Cuenta cuenta) {
        if (!cuentas.contains(cuenta)) {
            cuentas.add(cuenta);
        } else {
            JOptionPane.showMessageDialog(null, "Cuenta ya registrada");
        }
    }

    public void cargarDinero(double monto) {
        if (monto > 0) {
            dineroDisponible += monto;
            JOptionPane.showMessageDialog(null, "Cajero recargado. Total: $" + dineroDisponible);
        } else {
            JOptionPane.showMessageDialog(null, "Monto inválido");
        }
    }

    public boolean retirarDinero(Cuenta cuenta, double monto) {
        if (cuenta.retirar(monto)) {
            dineroDisponible -= monto;
            return true;
        } else {
            return false;
        }
    }
    
    
    
    public void mostrarCuentas() {
        if (cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas");
        } else {
            StringBuilder sb = new StringBuilder("=== Cuentas registradas ===\n");
            for (Cuenta c : cuentas) {
                sb.append(c.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    @Override
    public String toString() {
        return "Cajero con $" + dineroDisponible + " disponibles y " + cuentas.size() + " cuentas cargadas.";
    }

}
