package logicLayer;

import javax.swing.JOptionPane;

public enum TipoUsuario {
	CLIENTE("Depositar dinero/Retirar dinero/Ver saldo/Salir"),
    EMPLEADO("Ver cuentas/Cargar dinero al cajero/Salir");

    private final String opciones;

    private TipoUsuario(String opciones) {
        this.opciones = opciones;
    }

    public void mostrarMenu() {
        String[] opcionesEnum = this.opciones.split("/");
        String opcion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción:",
                null,  
                JOptionPane.DEFAULT_OPTION,
                null,
                opcionesEnum,
                opcionesEnum[0]
        );

        if (opcion != null) {
            JOptionPane.showMessageDialog(null, "Seleccionó: " + opcion);
        }
    }

    public static TipoUsuario elegirTipo() {
       TipoUsuario opcion  =(TipoUsuario)JOptionPane.showInputDialog(
                null,
                "Seleccione tipo de usuario:",
                "Ingreso al sistema",
                JOptionPane.DEFAULT_OPTION,
                null,
                TipoUsuario.values(),
                TipoUsuario.CLIENTE
        );
       return opcion;
    }

}



