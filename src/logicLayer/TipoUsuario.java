package logicLayer;

import javax.swing.JOptionPane;

public enum TipoUsuario {
	
	Cliente(new String[]{"Depositar dinero","Transferir", "Retirar dinero", "Solicitar prestamo", "Pagar serviios", "Ver saldo", "Ver movimientos","Ver informacion", "salir"}),
	Empleado(new String[]{"Ver cuentas","Cargar dinero al cajero","Ver movimientos generales","Dar de alta cajero","Dar de baja cajero","Ver informacion", "Salir"}),
	Administrador(new String[]{"Crear empleado","Dar de baja cuenta","Ver informacion", "Salir"});
    //empleados puede ser dar de baja cajero
	private String[] opciones;

	private TipoUsuario(String[] opciones) {
		this.opciones = opciones;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
    
    public static TipoUsuario elegirTipo() {
       TipoUsuario opcion  =(TipoUsuario)JOptionPane.showInputDialog(
                null,
                "Seleccione tipo de usuario:",
                "Ingreso al sistema",
                JOptionPane.DEFAULT_OPTION,
                null,
                TipoUsuario.values(),
                TipoUsuario.values()[0]
        );
       return opcion;
    }
}



